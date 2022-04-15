package com.heyongqiang.work.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 *
 * 图片上传到服务器
 *
 * @auther Lazy
 * @create 2020/04/30
 */
@CrossOrigin
@RestController
public class FileUpload {
	//获取配置文件中得主要信息
    @Value("${tengxun.cos.secretId}")
    private String secretId;

    @Value("${tengxun.cos.secretKey}")
    private String secretKey;

    @Value("${tengxun.cos.bucket}")
    private String bucket;

    @Value("${tengxun.cos.bucketName}")
    private String bucketName;

    @Value("${tengxun.cos.prefix}")
    private String prefix;

    /**
     * 上传道腾讯云服务器
     * @return
     */
    @PostMapping("/uploadFile")
    public Object Upload(@RequestParam(value = "file") MultipartFile file, HttpSession session){
        ResData resData = new ResData();
		//判断文件是否为空
        if(file == null){
            resData.setError("文件为空!","error");
            return resData;
        }
		//获取旧文件名称
        String oldFileName = file.getOriginalFilename();
		//旧文件名称后缀
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
		//新的文件名称
        String newFileName = UUID.randomUUID()+eName;
		/*腾讯云上传开始*/
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = this.bucketName;
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = "a/" + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);

            resData.setSuccess("上传图片成功!",this.prefix + putObjectRequest.getKey());
        } catch (IOException e) {
            e.printStackTrace();
            resData.setError(ResData.ERRORMSG,e.getMessage());
        }finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
        return resData;
    }

}
