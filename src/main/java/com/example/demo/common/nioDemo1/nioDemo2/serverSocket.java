package com.example.demo.common.nioDemo1.nioDemo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * nio服务端流程
 * 1：初始化配置信息--》端口号，读写缓冲，选择器声明
 * （1）创建NIO通道，设置为非阻塞模式
 * （2）创建基于NIO通道的socket连接，
 * （3）绑定通信端口
 * （4）打开Selector选择器
 * （5）将NIO通道注入到Selector选择器，监听确认事件（SelectionKey.OP_ACCEPT）
 * 2：根据不同的事件作出不同的处理-》连接或者读取事件
 * （1）selector选择器开始轮询通道，
 * （2）获取的事件集合，遍历集合，处理每个事件---》连接事件还是读取事件走不同的方式
 * 将每个客户端信息存入map集合存储
 * 3：将客户端发送的数据分发出去
 *（1）根据客户端连接信息的map集合，将信息发送到除自己之外的其他客户端
 */
public class serverSocket {
    private int port = 8088;
    private Charset charset = Charset.forName("UTF-8");
    private ByteBuffer rf = ByteBuffer.allocate(1024);
    private ByteBuffer wf = ByteBuffer.allocate(1024);
    private Selector selector;
    private Map<String, SocketChannel> clientMap = new HashMap<String, SocketChannel>(16);

    serverSocket(int port){
        try {
            this.port = port;
            init();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void init()throws IOException{
        //创建NIO通道，设置非阻塞
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //创建基于nio通道设置socket连接
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() {
        while (true){
            try {
                //轮询获取事件
                selector.select(1);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey->handler(selectionKey));
                selectionKeys.clear();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void handler(SelectionKey selectionKey){
        try {
            //是连接事件
            if(selectionKey.isAcceptable()){
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector,SelectionKey.OP_READ);
                clientMap.put(getSocketName(socketChannel),socketChannel);
                System.out.println(socketChannel.getRemoteAddress()+"come to server");
            }
            //如果是读事件
            else if(selectionKey.isReadable()){
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                rf.clear();
                int bytes = socketChannel.read(rf);
                if(bytes>0){
                    //复位
                    rf.flip();
                    //获取信息
                    String msg = String.valueOf(charset.decode(rf));
                    System.out.println(msg);
                    //发送到除自己之外其他的客户端
                    sendMsg(socketChannel,msg);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //发送数据到各个客户端
    public void sendMsg(SocketChannel client,String msg) throws IOException {
        if(!clientMap.isEmpty()){
            for (Map.Entry<String, SocketChannel> entry:clientMap.entrySet()) {
                SocketChannel temp = entry.getValue();
                //排除自己的客户端
                if(!client.equals(temp)){
                    wf.clear();
                    wf.put(charset.encode(getSocketName(client)+":"+msg));
                    wf.flip();
                    temp.write(wf);
                }
            }
        }
    }

    public String getSocketName(SocketChannel socketChannel){
        Socket socket = socketChannel.socket();
        return "[" + socket.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(socketChannel.hashCode()) + "]";
    }

    public static void main(String[] args) throws IOException {
        serverSocket ss = new serverSocket(7777);
        ss.listen();
    }

}
