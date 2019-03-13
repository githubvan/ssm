package com.tenjak.controller.impl;


import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tenjak.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@RequestMapping("/caffe")
@Controller
@ResponseBody
public class caffeineController {
    @Autowired
    private userService userService;

    @Autowired
   private Cache<String, Object> manualCache;


    private Object createExpensiveGraph(String key) {
        System.out.println("缓存不存在或过期，调用了createExpensiveGraph方法获取缓存key的值");
        if (key.equals("name")) {
            throw new RuntimeException("调用了该方法获取缓存key的值的时候出现异常");
        }
        return userService.findAll();
    }

    @RequestMapping("/testManual")
    public Object testManual(String uid) {
        long l1 = System.currentTimeMillis();
        System.out.println("l1 = " + l1);
        String key = uid;
        Object graph = null;

        // 根据key查询一个缓存，如果没有返回NULL
        //graph = manualCache.getIfPresent(key);
        System.out.println("1------"+graph);
        // 根据Key查询一个缓存，如果没有调用createExpensiveGraph方法，并将返回值保存到缓存。
        // 如果该方法返回Null则manualCache.get返回null，如果该方法抛出异常则manualCache.get抛出异常
        graph = manualCache.get(key, k -> createExpensiveGraph(k));
        long l2 = System.currentTimeMillis();
        System.out.println("l2 = " + l2);
        System.out.println("共花费时间："+(l2-l1));

        //manualCache.put(key, graph); //将一个值放入缓存，如果以前有值就覆盖以前的值
        // 删除一个缓存
        //manualCache.invalidate(key);

        ConcurrentMap<String, Object> map = manualCache.asMap();//所有的值得集合
        System.out.println("=======>map:"+map.toString());
        return graph;
    }




}
