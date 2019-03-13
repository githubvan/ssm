package com.tenjak.controller.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Field;
import java.util.Objects;

@Aspect
@Component
public class LogAop {
    private static String[] types = {"java.lang.Integer", "java.lang.Double",
            "java.lang.Float", "java.lang.Long", "java.lang.Short",
            "java.lang.Byte", "java.lang.Boolean", "java.lang.Char",
            "java.lang.String", "int", "double", "long", "short", "byte",
            "boolean", "char", "float"};

    private static final Logger log = LoggerFactory.getLogger(LogAop.class);
    //private static ThreadLocal<Long> startTime = new ThreadLocal<Long>();


    @Before("execution(* com.tenjak.controller.impl.*.*(..))")
    private String getMethodInfo(JoinPoint point) {
        String className = point.getSignature().getDeclaringType().getSimpleName();//类名
        String methodName = point.getSignature().getName();//方法名
        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        StringBuilder sb = null;
        if (Objects.nonNull(parameterNames)) {
            sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                // 对参数解析(参数有可能为基础数据类型，也可能为一个对象，若为对象则需要解析对象中变量名以及值)
                String value = "";
                if (point.getArgs()[i] == null) {
                    value = "null";
                } else {
                    // 获取对象类型
                    String typeName = point.getArgs()[i].getClass().getTypeName();
                    boolean flag = false;
                    for (String t : types) {
                        //1 判断是否是基础类型
                        if (t.equals(typeName)) {
                            value = point.getArgs()[i].toString();
                            flag = true;
                        }
                        if (flag) {
                            break;
                        }
                    }
                    if (!flag) {
                        //2 通过反射获取实体类属性
                        value = getFieldsValue(point.getArgs()[i]);
                    }
                }
                sb.append(parameterNames[i] + ":" + value + "; ");
            }
        }
        sb = sb == null ? new StringBuilder() : sb;
        String info = String.format("\n =======> 请求类名: %s \n =======> 请求方法: %s \n =======> 请求参数: %s", className, methodName, sb.toString());
        log.info(info);
        return info;
    }

    /**
     * 解析实体类，获取实体类中的属性
     */
    public static String getFieldsValue(Object obj) {
        //通过反射获取所有的字段，getFileds()获取public的修饰的字段
        //getDeclaredFields获取private protected public修饰的字段
        Field[] fields = obj.getClass().getDeclaredFields();
        String typeName = obj.getClass().getTypeName();
        for (String t : types) {
            if (t.equals(typeName)) {
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Field f : fields) {
            //在反射时能访问私有变量
            f.setAccessible(true);
            try {
                for (String str : types) {
                    //这边会有问题，如果实体类里面继续包含实体类，这边就没法获取。
                    //其实，我们可以通递归的方式去处理实体类包含实体类的问题。
                    if (f.getType().getName().equals(str)) {
                        sb.append(f.getName() + " : " + f.get(obj) + ", ");
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        sb.append("}");
        return sb.toString();
    }
    /*方法的返回值日志*/
    @AfterReturning(returning = "rvt", pointcut = "execution(* com.tenjak.controller.impl.*.*(..))")
    public void AfterExec(JoinPoint joinPoint, Object rvt) {
        log.info("=======> 返回值:{}", rvt);
    }

}


