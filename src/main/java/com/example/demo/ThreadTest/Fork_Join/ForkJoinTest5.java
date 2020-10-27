package com.example.demo.ThreadTest.Fork_Join;
import java.util.concurrent.*;

/**
 * @author muyou
 * @date 2020/10/23 9:51
 */
public class ForkJoinTest5  {

    /**
     * public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task)
     * @param args
     * @throws InterruptedException
     */
/*    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
       ForkJoinTask<String> forkJoinTask = forkJoinPool.submit(new MyRecursiveTask5());
        System.out.println(System.currentTimeMillis());
        System.out.println(forkJoinTask.join());
        System.out.println(System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(5);
    }*/

    /**
     * @param args
     * public ForkJoinTask<?> submit(Runnable task)
     */
/*    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("begin implement：" + System.currentTimeMillis());
        ForkJoinTask forkJoinTask = forkJoinPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("ThreadName = " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(forkJoinTask.join());
        System.out.println("end implement："+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(4);
    }*/

    /**
     * public <T> ForkJoinTask<T> submit(Callable<T> task)
     * @param args
     */
/*    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("begin "+System.currentTimeMillis());
        ForkJoinTask<String> forkJoinTask = forkJoinPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                return "This is call return value";
            }
        });
        System.out.println(forkJoinTask.join());
        System.out.println("end "+System.currentTimeMillis());
    }*/

    private String name;

    ForkJoinTest5(){

    }

    ForkJoinTest5(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * public <T> ForkJoinTask<T> submit(Runnable task, T result)
     * @param args
     */
    public static void main(String[] args) {
        try {
            ForkJoinTest5 forkJoinTest5 = new ForkJoinTest5();
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            ForkJoinTask<ForkJoinTest5> forkJoinTask = forkJoinPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        forkJoinTest5.setName("这是设置的值");
                        System.out.println("已经设置完毕");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },forkJoinTest5);
            System.out.println(forkJoinTask);
            System.out.println("name =" + forkJoinTask.get().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyRecursiveTask5 extends RecursiveTask<String>{

    @Override
    protected String compute() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "this is return";
    }
}