package com.example.demo.common.netty_protocol_stack.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;
import java.util.Map;

public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    private NettyMarshallingEncoder MarshallingEncoder;

    NettyMessageEncoder(){
        MarshallingEncoder = MarshallingCodecFactory.buildMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, List<Object> list) throws Exception {
        if(nettyMessage == null || nettyMessage.getHeader() == null){
            throw new Exception("the Message is null");
        }
        ByteBuf buffer = Unpooled.buffer();
        System.out.println("开始进行数据编码： "+nettyMessage);
        //开始对消息头进行处理，对头信息放入buffer，进行编码，解码时也要按顺序解码
        buffer.writeInt(nettyMessage.getHeader().getCrcCode());
        buffer.writeInt(nettyMessage.getHeader().getLength());
        buffer.writeLong(nettyMessage.getHeader().getSessionID());
        buffer.writeByte(nettyMessage.getHeader().getType());
        buffer.writeByte(nettyMessage.getHeader().getPriority());
        buffer.writeInt(nettyMessage.getHeader().getAttachment().size());
        String key = null;
        Object value = null;
        byte[] keyArray = null;
        /**
         * 对扩展信息进行编码
         */
        for (Map.Entry<String,Object> parram:nettyMessage.getHeader().getAttachment().entrySet()) {
            key = parram.getKey();
            keyArray = key.getBytes("UTF-8");
            value = parram.getValue();

            buffer.writeInt(keyArray.length);
            buffer.writeBytes(keyArray);
            MarshallingEncoder.encode(channelHandlerContext,value,buffer);
        }

        if(nettyMessage.getBody()!=null){
            MarshallingEncoder.encode(channelHandlerContext,nettyMessage.getBody(),buffer);
        }else {
            buffer.writeInt(0);
        }
        //这是 《Netty权威指南》中的写错的地方
        buffer.setInt(4, buffer.readableBytes()-8);
        //书中此处没有add，也即没有将ByteBuf加入到List中，也就没有消息进行编码了，所以导致运行了没有效果……
        list.add(buffer);
    }
}
