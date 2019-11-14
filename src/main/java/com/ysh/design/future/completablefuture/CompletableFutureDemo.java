package com.ysh.design.future.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * https://juejin.im/post/5adbf8226fb9a07aac240a67
 *
 * @author yangshenghong
 * @date 2019-11-14
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        try {
            test11();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 最简单的 CompletableFuture，获取CompletableFuture 的结果可以使用 CompletableFuture.get() 方法
     * get() 方法会一直阻塞直到 Future 完成。因此，以上的调用将被永远阻塞，因为该Future一直不会完成。
     * <p>
     * 使用 CompletableFuture.complete() 手工的完成一个 Future,所有等待这个 Future 的客户端都将得到一个指定的结果
     */
    private static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Future's Result");
        String result = completableFuture.get();
        System.out.println(result);
    }

    /**
     * 使用 runAsync() 运行异步计算
     * <p>
     * 想异步的运行一个后台任务并且不想改任务返回任务东西，
     * 可以使用 CompletableFuture.runAsync()方法，它持有一个Runnable 对象，并返回 CompletableFuture<Void>
     * <p>
     * 也可以创建一个线程池并传给runAsync()和supplyAsync()方法来让他们从线程池中获取一个线程执行它们的任务
     */
    private static void test2() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });
        future.get();
    }

    /**
     * 使用 supplyAsync() 运行一个异步任务并且返回结果
     * 它持有supplier<T> 并且返回CompletableFuture<T>，T 是通过调用 传入的supplier取得的值的类型
     * <p>
     * 也可以创建一个线程池并传给runAsync()和supplyAsync()方法来让他们从线程池中获取一个线程执行它们的任务
     */
    private static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });
        System.out.println(future.get());
    }

    /**
     * 使用 thenApply(), thenAccept() 和thenRun()方法附上一个回调给CompletableFuture
     * <p>
     * thenApply() 处理和改变CompletableFuture的结果。持有一个Function<R,T>作为参数
     * <p>
     * 不想从你的回调函数中返回任何东西，仅仅想在Future完成后运行一些代码片段，你可以使用thenAccept()和 thenRun()方法,这些方法经常在调用链的最末端的最后一个回调函数中使用
     * <p>
     * thenAccept()可以访问CompletableFuture的结果，但thenRun()不能访Future的结果，它持有一个Runnable返回CompletableFuture
     */
    private static void test4() throws Exception {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "joey";
        }).thenApply(name -> "Hello " + name);
        System.out.println(completableFuture.get());

        completableFuture.thenAccept(s -> System.out.println("123 " + s));
    }

    /**
     * 使用 thenCompose()组合两个独立的future
     * <p>
     * 如果回调函数返回一个CompletableFuture，但是想从CompletableFuture链中获取一个直接合并后的结果，这时候可以使用thenCompose()
     */
    private static void test5() throws Exception {
        String s1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "joey1";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return s + " joey2";
        })).get();
        System.out.println(s1);
    }

    /**
     * 使用thenCombine()组合两个独立的 future
     * <p>
     * thenCompose()被用于当一个future依赖另外一个future的时候用来组合两个future。
     * thenCombine()被用来当两个独立的Future都完成的时候，用来做一些事情。
     */
    private static void test6() throws Exception {
        String str = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "joey3";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return " joey4";
        }), (s, s2) -> s + s2).get();
        System.out.println(str);
    }

    /**
     * 使用thenCompose()和 thenCombine()把两个CompletableFuture组合在一起。现在如果想组合任意数量的CompletableFuture可以使用以下两个方法组合任意数量的CompletableFuture
     * <p>
     * static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
     * static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
     * <p>
     * CompletableFuture.allOf()的使用场景是当你一个列表的独立future，并且你想在它们都完成后并行的做一些事情
     * <p>
     * join()方法和get()方法非常类似，这唯一不同的地方是如果最顶层的CompletableFuture完成的时候发生了异常，它会抛出一个未经检查的异常。
     */
    private static void test7() throws Exception {
        List<CompletableFuture<Integer>> futures = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            int a = i;
            futures.add(CompletableFuture.supplyAsync(() -> {
                System.out.println(a);
                return a;
            }));
        }
        //当所有future完成的时候，我们调用了future.join()，因此我们不会在任何地方阻塞(调用thenApply 执行的时候代表所有的我们调用了future已经完成，在获取他们的结果 所以不会阻塞)
        CompletableFuture<List<Integer>> apply = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        apply.thenAccept(System.out::println);
        System.out.println("finish");
    }

    /**
     * CompletableFuture.anyOf()和其名字介绍的一样，当任何一个CompletableFuture完成的时候【相同的结果类型】，返回一个新的CompletableFuture
     * <p>
     * 当三个中的任何一个CompletableFuture完成， anyOfFuture就会完成。因为future2的休眠时间最少，因此她最先完成，最终的结果将是future2的结果。
     */
    private static void test8() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);
        System.out.println(anyOfFuture.get());
    }

    /**
     * 如果在原始的supplyAsync()任务中发生一个错误，这时候没有任何thenApply会被调用并且future将以一个异常结束。如果在第一个thenApply发生错误，这时候第二个和第三个将不会被调用，同样的，future将以异常结束
     * <p>
     * 使用 exceptionally() 回调处理异常 exceptionally()回调给你一个从原始Future中生成的错误恢复的机会。你可以在这里记录这个异常并返回一个默认值。
     */
    private static void test9() throws Exception {
        int age = -1;
        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
        System.out.println("Maturity : " + maturityFuture.get());
    }

    /**
     * 使用 handle() 方法处理异常 API提供了一个更通用的方法 - handle()从异常恢复，无论一个异常是否发生它都会被调用
     */
    private static void test10() throws Exception {
        int age = -1;
        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if (ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });
        System.out.println("Maturity : " + maturityFuture.get());
    }

    private static void test11() throws ExecutionException, InterruptedException {
        List<CompletableFuture<Void>> futureList = new ArrayList<>(3);
        for (int i = 1; i <= 3; i++) {
            int ii = i;
            futureList.add(CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    if (ii == 2) {
                        throw new RuntimeException("hahh ");
                    }
                    System.out.println(ii);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).handle((res, ex) -> {
            if (ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage() + "name==>" + Thread.currentThread().getName());
            }
            return res;
        }).get();
    }

}
