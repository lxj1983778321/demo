package com.example.demo.ThreadTest;
/**
 * @author muyou
 * @date 2020/9/23 16:40
 */

public class SynchronizedTest {

    private volatile int i = 1;

    /**
     * 当synchronized加在方法上时：
     *  public synchronized void outputTest1();
     *     Code:
     *        0: return
     * 通过javap反编译发现 就是public synchronized void outputTest1();并没有使用monitorenter和monitorexit指令
     */
    public synchronized void outputTest1(){

    }

    /**
     * 当有重入情况发生时，
     *  public synchronized void outputTest3();
     *     Code:
     *        0: aload_0
     *        1: invokevirtual #3                  // Method outputTest3:()V
     *        4: return
     * 多了invokevirtual指令：用于调用普通的需要动态加载的方法
     * jvm常见指令：
     * new            创建一个对象并将地址放入虚拟机栈
     * dup             复制一个对象地址放入虚拟机栈
     * invokespecial            用于调用私有方法及final方法
     * invokevirtual            用于调用普通的需要动态加载的方法
     * invokestatic             用于调用静态方法
     * invokeinterface　　　　用于调用接口方法
     * pop             将上面执行的最近的栈帧弹出栈
     */
    public synchronized void outputTest3(){

        outputTest3();
    }

    /**
     * 当synchronized是在方法中时，通过反编译发现monitorenter、monitorexit、monitorexit：
     * monitorexit使用俩次的原因是为了保证锁的正常释放，
     * 当出现异常的时候使用第二个monitorexit进行所得释放
     * 当正常执行，没有出现异常时，使用第一个monitorexit进行锁的释放
     * public void outputTest2();
     *     Code:
     *        0: aload_0
     *        1: dup
     *        2: astore_1
     *        3: monitorenter
     *        4: aload_1
     *        5: monitorexit
     *        6: goto          14
     *        9: astore_2
     *       10: aload_1
     *       11: monitorexit
     *       12: aload_2
     *       13: athrow
     *       14: return
     *     Exception table:
     *        from    to  target type
     *            4     6     9   any
     *            9    12     9   any
     */
    public void outputTest2(){
        synchronized(this){

        }
    }

    /**
     * 当有重入情况发生时，也是使用一次monitorenter、和俩次monitorexit、monitorexit
     *  public void outputTest4();
     *     Code:
     *        0: aload_0
     *        1: dup
     *        2: astore_1
     *        3: monitorenter
     *        4: aload_0
     *        5: invokevirtual #4                  // Method outputTest4:()V
     *        8: aload_1
     *        9: monitorexit
     *       10: goto          18
     *       13: astore_2
     *       14: aload_1
     *       15: monitorexit
     *       16: aload_2
     *       17: athrow
     *       18: return
     *     Exception table:
     *        from    to  target type
     *            4    10    13   any
     *           13    16    13   any
     * }
     */
    public void outputTest4(){
        synchronized(this){
            outputTest4();
        }
    }

    /**
     *   public void outputTest5();
     *     Code:
     *        0: aload_0
     *        1: dup
     *        2: astore_1
     *        3: monitorenter
     *        4: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *        7: ldc           #5                  // String test
     *        9: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       12: aload_1
     *       13: monitorexit
     *       14: goto          22
     *       17: astore_2
     *       18: aload_1
     *       19: monitorexit
     *       20: aload_2
     *       21: athrow
     *       22: return
     *     Exception table:
     *        from    to  target type
     *            4    14    17   any
     *           17    20    17   any
     */
    public void outputTest5(){
        synchronized(this){
            System.out.println("test");
        }
    }

    /**
     *   public void outputTest6();
     *     Code:
     *        0: aload_0
     *        1: dup
     *        2: astore_1
     *        3: monitorenter
     *        4: aload_0
     *        5: invokevirtual #7                  // Method outputTest6:()V
     *        8: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *       11: ldc           #5                  // String test
     *       13: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       16: aload_1
     *       17: monitorexit
     *       18: goto          26
     *       21: astore_2
     *       22: aload_1
     *       23: monitorexit
     *       24: aload_2
     *       25: athrow
     *       26: return
     *     Exception table:
     *        from    to  target type
     *            4    18    21   any
     *           21    24    21   any
     * }
     */
    public void outputTest6(){
        synchronized(this){
            outputTest6();
            System.out.println("test");
        }
    }
}
