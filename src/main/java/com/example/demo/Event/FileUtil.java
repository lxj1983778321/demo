package com.example.demo.Event;

import com.example.demo.Event.Listener.AListener;
import com.example.demo.Event.Listener.BListener;
import com.example.demo.Event.Listener.fileUploadListener;
import com.example.demo.Event.event.AEvent;
import com.example.demo.Event.event.BEvent;
import com.example.demo.Event.event.fileUploadEvent;
import com.example.demo.Event.manage.listenerManage;

import java.io.*;
import java.util.Scanner;

/**
 * @author muyou
 * @date 2020/11/10 15:26
 * 文件操作帮助类
 *
 * 定义一个文件上传方法，读取某个文件写到某个类里面去
 * 但是，有可能会需要记录文件进度条的需求
 * 有时候调用文件不需要读取进度条
 * 有时候需要进度条
 */
public class FileUtil {

    public static int READ_SIZE = 100;

    public static void fileWrite(InputStream is, OutputStream os) throws IOException {
        //不需要
        fileWrite(is,os,null);
    }

    public static void fileWrite(InputStream is, OutputStream os,FileListener fileListener) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        //会阻塞，最好使用Filelei的getSize,这里只是验证
        int fileSize = is.available();
        int readSize = 0;

        byte[] bytes = new byte[READ_SIZE];
        boolean f = true;
        while (f){
            //文件实在小于第一次读的,文件小于100字节，一次读完
            if(fileSize<READ_SIZE){
                byte[] bytess = new byte[fileSize];
                bis.read(bytess);
                bos.write(bytess);
                readSize = fileSize;
                f = false;
            }
            //当你是最后一次读的时候
            else if(fileSize < readSize+READ_SIZE){
                byte[] bytess = new byte[fileSize-readSize];
                readSize = fileSize;
                bis.read(bytess);
                bos.write(bytess);
                f = false;
            }
            else {
                bis.read(bytes);
                readSize += READ_SIZE;
                bos.write(bytes);
            }
           listenerManage.pushEvent(new fileUploadEvent(fileSize,readSize));
        }
        bis.close();
        bos.close();
    }

    public static void main(String[] args) throws IOException {
        //添加一个监听器
        listenerManage.addListener(new fileUploadListener());
        listenerManage.addListener(new AListener());
        listenerManage.addListener(new BListener());
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("你要监听的事件");
            String sc = scanner.next();
            if(sc.equals("A")){
                listenerManage.pushEvent(new AEvent());
            }
            else {
                listenerManage.pushEvent(new BEvent());
            }
        }

        /*
        File fileRead = new File("c://student/读我.txt");
        File fileWriter = new File("c://student/写我.txt");
        if (fileWriter.exists()) {
            fileWriter.createNewFile();
        }
        fileWrite(new FileInputStream(fileRead),new FileOutputStream(fileWriter));
        */
    }
}
