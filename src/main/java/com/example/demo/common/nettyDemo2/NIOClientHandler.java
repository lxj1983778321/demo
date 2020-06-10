package com.example.demo.common.nettyDemo2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

public class NIOClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //测试发送100条数据
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("客户端发送的第"+i+"条消息$", CharsetUtil.UTF_8));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String str = (String) msg;
        System.out.println("Server 返回的应答消息是： "+str);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("进入client 端的 exceptionCaught方法");
        ctx.close();
        System.out.println("dlient 端的 exceptionCaught方法结束");
    }
}
