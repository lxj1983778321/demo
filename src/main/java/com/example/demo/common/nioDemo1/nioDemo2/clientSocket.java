package com.example.demo.common.nioDemo1.nioDemo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

public class clientSocket {
    //服务器地址
    private InetSocketAddress SERVER;
    private ByteBuffer rf = ByteBuffer.allocate(1024);
    private ByteBuffer wf = ByteBuffer.allocate(1024);
    private Selector selector;
    private Charset charset = Charset.forName("UTF-8");

    clientSocket(int port){
        SERVER = new InetSocketAddress("127.0.0.1",port);
        try {
            init();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void init() throws IOException {
        //创建客户端通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //打开selector连接，进行注册
        selector = Selector.open();
        //注入到选择器中
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        //连接客户端
        socketChannel.connect(SERVER);
        while (true){
            selector.select(1);
            Set<SelectionKey> selectionKey = selector.selectedKeys();
            selectionKey.forEach(s->handler(s));
            selectionKey.clear();
        }
    }

    public void handler(SelectionKey selectionKey){
        try {
            //如果是连接就绪事件
            if(selectionKey.isConnectable()){
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                //判断此通道是否正在进行连接操作，当已在此通道上发起连接操作，但尚未通过调用 finishconnect 方法完成连接时才返回 true
                if(socketChannel.isConnectionPending()){
                    socketChannel.finishConnect();
                    new Thread(){
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    wf.clear();
                                    Scanner scanner = new Scanner(System.in);
                                    String sendText = scanner.nextLine();
                                    wf.put(charset.encode(sendText));
                                    wf.flip();
                                    socketChannel.write(wf);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                    //注册可读时间
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
            }else if(selectionKey.isReadable()){
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                rf.clear();
                int bytes = socketChannel.read(rf);
                if(bytes>0){
                    String msg = new String(rf.array(),0,bytes);
                    System.out.println(msg);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new clientSocket(7777);
    }
}
