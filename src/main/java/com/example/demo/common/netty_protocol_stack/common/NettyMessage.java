package com.example.demo.common.netty_protocol_stack.common;

/**
 * 定义netty消息类-》由消息头和消息体组成
 */
public class NettyMessage {
    //消息头
    private Header header;
    //消息体
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "nettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
