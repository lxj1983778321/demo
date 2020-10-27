package com.example.demo.ThreadTest.Exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author muyou
 * @date 2020/10/14 16:10
 * 类Exchanger的功能是可以使2个线程之间传输数据，它比wait/notify更加方便
 * 主要就是exchange()方法
 *
 */
public class ExchangerTest1 {
    /**
     * exchange()方法具有阻塞特性，此方法被调用后等待其他线程来取得数据，如果没有其它线程来取得数据，则一直阻塞
     * exchange(V x, long timeout, TimeUnit unit)方法是在指定时间内没有其他线程获取数据，则出现超时异常
     */
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        ExchangerTest1_A exchangerTestA = new ExchangerTest1_A(exchanger);
        ExchangerTest1_B exchangerTestB = new ExchangerTest1_B(exchanger);
        Thread thread1 = new Thread(exchangerTestA);
        Thread thread2 = new Thread(exchangerTestB);
        thread1.start();
        thread2.start();
    }
}
class ExchangerTest1_A implements Runnable{
    private Exchanger<String> exchangerA;

    public ExchangerTest1_A(Exchanger<String> exchangerA){
        this.exchangerA = exchangerA;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程A获取线程B的值为：" + exchangerA.exchange("这是A的值"));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
class ExchangerTest1_B implements Runnable{
    private Exchanger<String> exchangerB;

    public ExchangerTest1_B(Exchanger<String> exchangerB){
        this.exchangerB = exchangerB;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程B获取线程A的值为：" + exchangerB.exchange("这是B的值"));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}