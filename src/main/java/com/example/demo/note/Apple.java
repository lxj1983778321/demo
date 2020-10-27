package com.example.demo.note;

/**
 * @Description TODO
 * @Author lixingjian
 * @DATE 2020/6/17 14:08
 * @Version 1.0
 **/
public class Apple {

    @FruitName("Apple")
    private String appleName;

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleName() {
        return appleName;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "appleName='" + appleName + '\'' +
                '}';
    }

    public static void main(String[] args) {
        FruitInfoUtil
                .getFruitInfo(Apple.class);
    }
}
