package com.heyongqiang.work.common.aop;


import com.alibaba.fastjson.JSON;

import com.heyongqiang.work.utils.HttpContextUtils;
import com.heyongqiang.work.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect  //切面 定义了通知和切点的关系
@Slf4j
public class LogAspect {
    //定义切点
    @Pointcut("@annotation(com.heyongqiang.work.common.aop.LogAnnotation)")
    public void pt(){};


    //定义切面类  环绕类
    @Around("pt()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        recordLog(joinPoint, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start================================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operator());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

//        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}",params);

        //获取request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IPUtils.getIpAddr(request));


        log.info("excute time : {} ms",time);
        log.info("=====================log end================================");
    }

    public static void main(String[] args) {
        LogAspect logAspect = new LogAspect();
        int[][] ans = new int[6][2];
        ans[0] = new int[]{1,3};
        ans[1] = new int[]{2,3};
        ans[5] = new int[]{1,4};
        ans[2] = new int[]{4,3};
        ans[4] = new int[]{5,6};
        ans[3] = new int[]{5,8};
        logAspect.findWinners(ans);

    }


    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer,int[]> map = new HashMap<Integer,int[]>();
        for(int[] wins : matches){
            // 遍历
            int win = wins[0];
            int los = wins[1];
            if(!map.containsKey(win)){
                map.put(win,new int[]{1,1});
            } else {
                int[] awin = map.get(win);
                awin[0]++;
                awin[1]++;
            }
            if(!map.containsKey(los)){
                map.put(los,new int[]{1,-1});
            } else {
                int[] alos = map.get(los);
                alos[0]++;
            }
        }
        List<Integer> win0 = new ArrayList<>();
        List<Integer> win1 = new ArrayList<>();
        for(Map.Entry<Integer,int[]> sets : map.entrySet()){
            int win = sets.getKey();
            int[] nums = sets.getValue();
            if(nums[0] == nums[1]){
                win0.add(win);
            } else if(nums[0] == nums[1] + 1){
                win1.add(win);
            }
        }
        res.add(win0);
        res.add(win1);
        return res;
    }
}
