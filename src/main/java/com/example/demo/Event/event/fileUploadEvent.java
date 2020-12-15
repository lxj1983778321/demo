package com.example.demo.Event.event;
/**
 * @author muyou
 * @date 2020/11/10 16:34
 */
public class fileUploadEvent extends ApplicationEvent{

    private int fileSize;
    private int readSize;

    public fileUploadEvent(int fileSize, int readSize) {
        this.fileSize = fileSize;
        this.readSize = readSize;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getReadSize() {
        return readSize;
    }

    public void setReadSize(int readSize) {
        this.readSize = readSize;
    }
}
