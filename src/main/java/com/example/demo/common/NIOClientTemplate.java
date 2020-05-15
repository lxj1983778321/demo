package com.example.demo.common;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NIOClientTemplate {

    public static void main(String[] args) {
        System.out.println("client 端测试开始");
        new NIOClientTemplate().client("127.0.0.1",8088);
        System.out.println("client 端测试结束");
    }

    public void client(String host,int port){
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap  = new Bootstrap();
            bootstrap.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //以$作为分隔符进行解码，防止TCP的粘包/拆包问题
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$".getBytes())));
                            socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                            socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                            socketChannel.pipeline().addLast(new NIOClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(host,port).sync();
            future.channel().closeFuture().sync();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //关闭连接
            System.out.println("进入clientTemplate的finally块，关闭连接");
            clientGroup.shutdownGracefully();
        }
    }
}
