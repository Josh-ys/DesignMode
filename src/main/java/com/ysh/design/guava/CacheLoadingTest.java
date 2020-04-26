package com.ysh.design.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * LoadingCache是Cache的子接口，相比较于Cache，当从LoadingCache中读取一个指定key的记录时，如果该记录不存在，则LoadingCache可以自动执行加载数据到缓存的操作
 * https://www.jianshu.com/p/64b0df87e51b
 *
 * @author yangshenghong
 * @date 2020-04-26
 */
public class CacheLoadingTest {
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                //最大数量
                .maximumSize(3)
                //缓存项在给定时间内没有被写访问（创建或覆盖），则回收 下次取的时候从loading中取
                .expireAfterWrite(3, TimeUnit.MINUTES)
                //缓存项在给定时间内没有被读或写访问，则回收 下次取的时候从loading中取
                .expireAfterAccess(3, TimeUnit.MINUTES)
                //定时刷新 在指定时间内没有被创建/覆盖，则指定时间过后，再次访问时，会去刷新该缓存，在新值没有到来之前，始终返回旧值
                //跟expire的区别是，指定时间过后，expire是remove该key，下次访问是同步去获取返回新值；而refresh则是指定时间后，不会remove该key，下次访问会触发刷新，新值没有回来时返回旧值
                //和expireAfterWrite相反，refreshAfterWrite通过定时刷新可以让缓存项保持可用，
                //但请注意：缓存项只有在被检索时才会真正刷新（如果CacheLoader.refresh实现为异步，那么检索不会被刷新拖慢）。
                //因此，如果你在缓存上同时声明expireAfterWrite和refreshAfterWrite，缓存并不会因为刷新盲目地定时重置，如果缓存项没有被检索，那刷新就不会真的发生，缓存项在过期时间后也变得可以回收
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                //执行逻辑
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws InterruptedException {
                        TimeUnit.SECONDS.sleep(1);
                        return "hello " + key + "!";
                    }
                });
        System.out.println(cache.get("a"));
        System.out.println(cache.get("b"));
        System.out.println(cache.get("c"));
    }
}
