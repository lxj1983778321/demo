package com.example.demo.controller.algorithm;

/**
 * @Description 求解最大公约数
 * @Author lixingjian
 * @DATE 2020/7/20 9:57
 * @Version 1.0
 **/
public class greatest_common_divisor {

    /**
     * 辗转相除法
     * 算法基于的原理为：
     * 俩个正整数a和b（a>b），他们的最大公约数等于a除以b的余数c和b之间的最大公约数
     * 缺点为：
     * 当俩个整数较大时，做a%b性能较差
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisorV1(int a,int b){
        int max = a>b?a:b;
        int min = a<b?a:b;
        if(max % min == 0){
            return min;
        }
        return getGreatestCommonDivisorV1(max%min,min);
    }

    /**
     * 更相减损术
     * 原理：
     * 俩个正整数a和b（a>b）他们的最大公约数等于a-b的差值c和较小数b的最大公约数
     * 缺点：靠俩数求差方式来进行递归，运算次数大于辗转相除法的取模方式
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisorV2(int a,int b){
        int max = a>b?a:b;
        int min = a<b?a:b;
        if(max % min == 0){
            return min;
        }
        return getGreatestCommonDivisorV2(max-min,min);
    }

    /**
     * 最终进阶版：采用辗转相除法+更相减损术的方式实现
     * 原理：在更相减损术的基础上使用移位运算
     * 以下获取最大公约数方法简写成gcd
     * 当a和b都为偶数时，gcd(a,b) = 2*gcd(a/2,b/2) = 2*gcd(a>>1,b>>1) = gcd(a>>1,b>>1)<<1;
     * 当a为偶数，b为奇数时，gcd(a,b) = gcd(a/2,b) = gcd(a>>1,b);
     * 当a为奇数，b为偶数时，gcd(a,b) = gcd(a,b/2) = gcd(a,b>>1);
     * 当a,b都为奇数时，先利用更相减损术运算一次，gcd(a,b) = gcd(a-b,b),此时a-b必然为偶数，然后可以继续进行移位运算
     * 当俩数相等时，即为最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisorV3(int a,int b){
        if(a==b){
            return a;
        }
        //a，b为偶数
        if((a&1)==0 && (b&1) == 0){
            return getGreatestCommonDivisorV3(a>>1,b>>1)<<1;
        }
        //a为偶数，b为奇数
        else if((a&1)==0 && (b&1)!=0){
            return getGreatestCommonDivisorV3(a>>1,b);
        }
        //a为奇数，b为偶数
        else if((a&1)!=0 && (b&1) == 0){
            return getGreatestCommonDivisorV3(a,b>>1);
        }
        //a,b都为奇数 max-min即为偶数
        else {
            int max = a>b?a:b;
            int min = a<b?a:b;
            return getGreatestCommonDivisorV3(max-min,min);
        }
    }

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisorV1(100,80));
        System.out.println(getGreatestCommonDivisorV1(27,14));
        System.out.println("V1------------------------");
        System.out.println(getGreatestCommonDivisorV1(27,14));
        System.out.println(getGreatestCommonDivisorV2(100,80));
        System.out.println("V2------------------------");
        System.out.println(getGreatestCommonDivisorV1(27,14));
        System.out.println(getGreatestCommonDivisorV3(100,80));
        System.out.println("V3------------------------");
    }
}
