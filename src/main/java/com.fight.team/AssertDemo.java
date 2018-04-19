package com.fight.team;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * Author: bail
 * Time: 2017/10/31.11:29
 */
public class AssertDemo{
    public static void main(String[] args) throws Exception{
        List<Integer> intList = Arrays.asList(11,22,21);
        /**
         * min(param)
         * @param Comparator接口,这个接口形如 (x,y) -> x.compareTo(y),返回-1,0,1
         * @return 返回最小值Optional对象
         *
         * comparingInt()
         * @param ToIntFunction接口,这个接口形如 (x) -> parseIntFunc(x),返回int类型的值
         * @return Comparator对象
         */
        Optional<Integer> op = intList.stream().min(Comparator.comparingInt(i->i.intValue()));
        /**
         * orElseGet
         * @param Supplier接口,这个接口形如 () -> new Object(),返回一个造出来的对象
         * @return 返回Optional的泛型对象
         */
        System.out.println(op.orElseGet(() -> new Integer(-1)));


        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1));
        List<Integer> sameOrder = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(numbers);
        System.out.println(sameOrder);*/

        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1));
        //取平均值方法1
        System.out.println(numbers.stream().mapToInt(i->i).average());
        //取平均值方法2
        System.out.println(numbers.stream().mapToInt(i->i).summaryStatistics().getAverage());
        //取平均值方法3
        System.out.println(numbers.stream().collect(Collectors.averagingInt(i->i)));*/

        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1));
        //一分为二,分块
        System.out.println(numbers.stream().collect(Collectors.partitioningBy(i->i%2==0)));*/


        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1));
        //分组
        Map<Integer,List<Integer>> groupList = numbers.stream().collect(Collectors.groupingBy(i->i));
        System.out.println(groupList);*/

        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1));
        //转换成字符串
        String numbersStr = numbers.stream().map(i->i.toString()).collect(Collectors.joining(",","[","]"));
        System.out.println(numbersStr);*/

        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1));
        //组合收集器,计算每个分组的值数量
        Map<Integer,Long> groupList = numbers.stream().collect(Collectors.groupingBy(i->i%2,Collectors.counting()));
        System.out.println(groupList);*/

        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1));
        //组合收集器,对每个分组进行计算后输出,一次性求出与本来集合不同类型的内容
        Map<Integer,List<Integer>> groupList = numbers.stream().collect(Collectors.groupingBy(i->i%2,Collectors.mapping(i->i*3,Collectors.toList())));
        System.out.println(groupList);*/

        /*Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 32, 2, 1, 3));
        System.out.println(numbers.parallelStream().reduce(0,(count,i)->count+1));//使用并行流的限制 恒等值+满足结合律
        System.out.println(numbers.stream().reduce(0,(count,i)->count+1));*/


        //future测试
        /*List<Future<String>> results = new ArrayList<Future<String>>();
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i=0; i<100;i++) {
            results.add(es.submit(new Task()));
        }
        for(Future<String> res : results) {
            System.out.println(res.get());
        }*/


        //CompletableFuture测试
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
    }

    public static void test1() throws Exception{
        CompletableFuture<String> completableFuture=new CompletableFuture();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //模拟执行耗时任务
                System.out.println("task doing...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //告诉completableFuture任务已经完成
                completableFuture.complete("result");
            }
        }).start();
        //获取任务结果，如果没有完成会一直阻塞等待
        String result=completableFuture.get();
        System.out.println("计算结果:"+result);
    }

    public static void test2() throws Exception{
        CompletableFuture<String> completableFuture=new CompletableFuture();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟执行耗时任务
                    System.out.println("task doing...");
                    Thread.sleep(3000);

                    String a=null;
                    a.charAt(0);
                    completableFuture.complete("result");
                }catch (Exception e) {
                    //告诉completableFuture任务发生异常了
                    completableFuture.completeExceptionally(e);
                }
            }
        }).start();
        //获取任务结果，如果没有完成会一直阻塞等待
        String result=completableFuture.get();
        System.out.println("计算结果:"+result);
    }

    public static void test3() throws Exception {
        //supplyAsync内部使用ForkJoinPool线程池执行任务
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result";
        });
        System.out.println("计算结果:"+completableFuture.get());
    }

    public static void test4() throws Exception {

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        });

        CompletableFuture<String> completableFuture2=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result2";
        });

        CompletableFuture<Object> anyResult=CompletableFuture.anyOf(completableFuture1,completableFuture2);

        System.out.println("第一个完成的任务结果:"+anyResult.get());

        CompletableFuture<Void> allResult=CompletableFuture.allOf(completableFuture1,completableFuture2);

        //阻塞等待所有任务执行完成
        allResult.join();
        System.out.println("所有任务执行完成");

    }

    public static void test5() throws Exception {

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        });

        //等第一个任务完成后，将任务结果传给参数result，执行后面的任务并返回一个代表任务的completableFuture
        CompletableFuture<String> completableFuture2= completableFuture1.thenCompose(result->CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return result+" and result2";
        }));

        System.out.println(completableFuture2.get());

    }

    public static void test6() throws Exception {

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return 100;
        });

        //将第一个任务与第二个任务组合一起执行，都执行完成后，将两个任务的结果合并
        CompletableFuture<Integer> completableFuture2 = completableFuture1.thenCombine(
                //第二个任务
                CompletableFuture.supplyAsync(() -> {
                    //模拟执行耗时任务
                    System.out.println("task2 doing...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //返回结果
                    return 2000;
                }),
                //合并函数
                (result1, result2) -> result1 + result2);

        System.out.println(completableFuture2.get());

    }

    public static void test7() throws Exception {

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return 100;
        });

        //注册完成事件
        completableFuture1.thenAccept(result->System.out.println("task1 done,result:"+result));

        CompletableFuture<Integer> completableFuture2=
                //第二个任务
                CompletableFuture.supplyAsync(() -> {
                    //模拟执行耗时任务
                    System.out.println("task2 doing...");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //返回结果
                    return 2000;
                });

        //注册完成事件
        completableFuture2.thenAccept(result->System.out.println("task2 done,result:"+result));

        //将第一个任务与第二个任务组合一起执行，都执行完成后，将两个任务的结果合并
        CompletableFuture<Integer> completableFuture3 = completableFuture1.thenCombine(completableFuture2,
                //合并函数
                (result1, result2) -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return result1 + result2;
                });

        System.out.println(completableFuture3.get());

    }



    public static class Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            String tid = String.valueOf(Thread.currentThread().getId());
            System.out.printf("Thread#%s : in call\n", tid);
            return tid;
        }
    }

    private static String getStr(String str){
        int a = str.indexOf('\b');
        if(a<0){
            return str;
        }else if(a<=1){
            return getStr(str.substring(a+1));
        }else{
            return getStr(str.substring(0,a-1)+str.substring(a+1));
        }
    }

    private static String getStr2(String str){
        StringBuilder sb = new StringBuilder();
        char ch;
        for(int i=0;i<str.length();i++){
            ch = str.charAt(i);
            if(ch == '\b'){
                if(sb.length()>0){
                    sb.deleteCharAt(sb.length()-1);
                }
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
