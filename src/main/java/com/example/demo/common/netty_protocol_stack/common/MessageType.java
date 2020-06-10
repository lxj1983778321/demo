package com.example.demo.common.netty_protocol_stack.common;

/**
 * 请求类型
 */
public class MessageType {
    public static final byte LOGIN_REQ=3;//握手请求消息
    public static final byte LOGIN_RESP=4;//握手应答消息
    public static final byte HEARTBEAT_REQ=5;//心跳请求消息
    public static final byte HEARTBEAT_RESP=6;//心跳应答消息
}
