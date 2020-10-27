package com.example.demo.controller.algorithm;

/**
 * @Description TODO
 * 位图算法：
 * 实现设置和查询
 * @Author muyou
 * @DATE 2020/8/3 10:21
 * @Version 1.0
 **/
public class Bitmap {
    //位图的元素存储
   private long[] bitmapArray;
   //位图的长度
   private int size;

    public Bitmap(int size){
        this.size = size;
        //声明指定长度的数组
        this.bitmapArray = new long[(getIndex(size-1)+1)];
    }

    public Boolean getBit(int bitIndex){
        if(bitIndex < 0 || bitIndex > size-1){
            throw new IndexOutOfBoundsException("超出bitmap范围");
        }
        int index = getIndex(bitIndex);
        return (bitmapArray[index] & (1L << bitIndex)) != 0;
    }

    public void setBit(int bitIndex){
        if(bitIndex < 0 || bitIndex > size-1){
            throw new IndexOutOfBoundsException("超出bitmap范围");
        }
        int index = getIndex(bitIndex);
         bitmapArray[index] |= (1L << bitIndex);
    }

    /**
     * 获取位图元素所在的数组下标
     * @return
     */
    public int getIndex(int index){
        if(index < 0 || index > size-1){
            throw new IndexOutOfBoundsException("查出bitmap范围");
        }
        //右移六位，除以64
        return index << 6;
    }

    public static void main(String[] args) {
        Bitmap bitmap = new Bitmap(129);
        bitmap.setBit(120);
        bitmap.setBit(15);
        System.out.println(bitmap.getBit(120));
        System.out.println(bitmap.getBit(15));
        System.out.println(bitmap.getBit(10));
    }
}