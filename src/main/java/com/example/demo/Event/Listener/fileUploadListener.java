package com.example.demo.Event.Listener;

import com.example.demo.Event.event.fileUploadEvent;

/**
 * @author muyou
 * @date 2020/11/10 16:36
 */
public class fileUploadListener implements ApplicationListener<fileUploadEvent>{


    @Override
    public void onEvent(fileUploadEvent event) {
        //因为如果是int类型相处的话，小于1，被直接抹除为0了，所以需要转
        double file_size = event.getFileSize();
        double read_size = event.getReadSize();
        double value = read_size/file_size;
        System.out.println("当前文件上传百分比：" + value*100+"%");
    }
}
