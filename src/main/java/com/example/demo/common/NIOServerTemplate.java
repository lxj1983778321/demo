package com.example.demo.common;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NIOServerTemplate {

    public static void main(String[] args) {
        System.out.println("Server 端测试开始");
        new NIOServerTemplate().bind(8088);
        System.out.println("Server 端测试结束");
    }

    public void bind(int port){
        System.out.printf("传入的端口号为: %d ：\n",port);
        //一个主线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //多个工作线程，默认是Runtime.getRuntime().availableProcessors() * 2（电脑内核*2）
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //ServerBootstrap 用来创建服务端的Channel以监听来自客户端的连接。
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    //服务端的请求通道，相当于nio的ServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    //用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
                    //用于临时存放已完成三次握手的请求队列的最大长度，如果未设置，默认50
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //以$作为分隔符进行解码，防止TCP的粘包/拆包问题
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$".getBytes())));
                            //字符串编码器
                            socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                            //字符串解码器
                            socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                            socketChannel.pipeline().addLast(new NioServerHandler());
                        }
                    });
            //sync阻塞在这里直到绑定端口成功，这个代码底层就是server.accpet()建立连接。
            ChannelFuture future = b.bind(port).sync();
            //等待直到服务器套接字关闭
            future.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        } finally{
            //关闭连接
            System.out.println("进入ServerTemplate的finally块，关闭连接");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
