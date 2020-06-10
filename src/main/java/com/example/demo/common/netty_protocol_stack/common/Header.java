package com.example.demo.common.netty_protocol_stack.common;

import java.util.HashMap;
import java.util.Map;

/**
 * netty消息头定义
 */
public class Header {
    //netty消息验证码
    private int crcCode = 0xabef0101;
    private int length;//消息长度
    private long sessionID;//会话ID
    private byte Type;//业务请求类型
    private byte Priority;//消息优先级
    private Map<String,Object> attachment = new HashMap<String, Object>();//用于扩展消息头

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return Type;
    }

    public void setType(byte type) {
        Type = type;
    }

    public byte getPriority() {
        return Priority;
    }

    public void setPriority(byte priority) {
        Priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", Type=" + Type +
                ", Priority=" + Priority +
                ", attachment=" + attachment +
                '}';
    }
}
