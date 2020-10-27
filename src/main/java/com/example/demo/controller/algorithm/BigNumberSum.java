package com.example.demo.controller.algorithm;

/**
 * @Description TODO
 *  * @Author muyou
 *  * @DATE 2020/7/29 9:33
 *  * @Version 1.0
 * 大整数相加
 * 思路：
 * 将一个大整数拆分到可以计算的程度，进行计算相加
 * 例如：
 * 输入俩个超长的字符串，因为int类型的范围是正负10位数，
 * 那么将超长的字符串每九位截取为一个新的数存入到数组中，然后就可以直接遍历数组进行相加即可
 * 面临的问题是：
 * 你如何确定俩个数组位相加产生了进位呢？待解决？？？？
 **/
public class BigNumberSum {

    /**
     * 用来确定截取次数（即截取几次可以将一个数字字符串分为每九个为一组）
     * @param bigNumber
     * @return
     */
    public static int cyclicNum(String bigNumber){
        int cyclic = bigNumber.length() % 9;
        int cyclicNum = bigNumber.length() / 9;
        return cyclic == 0 ? cyclicNum : cyclicNum+1;
    }

    /**
     * 大数相加的主方法（这俩个数的实际大小可能超过当前所有类型的范围）
     * @param bigNumberA
     * @param bigNumberB
     * @return
     */
    public static String bigNumberSum(String bigNumberA,String bigNumberB){
        //将数字字符串处理成符合条件的数字数组
        int[] arrayTempA = subString(bigNumberA);
        int[] arrayTempB = subString(bigNumberB);
        //通过俩个数组中，长度最大的数组得到最终存储结果的数组的大小，
        int maxLength = Math.max(arrayTempA.length,arrayTempB.length);
        //转换为大小加一，是为了和存储结果的数组大小一致，为了防止出现数组下标越界异常
        int[] arrayA = new int[maxLength + 1];
        int[] arrayB = new int[maxLength + 1];
        //将数组元素复制到新的元素
        copyOf(arrayA,arrayTempA);
        copyOf(arrayB,arrayTempB);
        //存储结果的数组
        int[] result = new int[maxLength + 1];
        //遍历每个元素，然后进行想加
        for (int i = 0; i < result.length; i++) {
            int temp = result[i];
            temp += arrayA[i];
            temp += arrayB[i];
            //获取每个数组元素的长度，为了判断是否产生进位做准备
            int oldLengthA = String.valueOf(arrayA[i]).length();
            int oldLngthB = String.valueOf(arrayB[i]).length();
            //获取俩个元素中长度最大的值
            int oldLength = Math.max(oldLengthA,oldLngthB);
            //temp就是俩数相加之后的结果
            String isCarry = Integer.toString(temp);
             // 判断是否产生进位的判断条件为：
             // 俩个数相加之后实际的长度是否比未相加之前的长度最大者的长度大
             // 如果是：那么就是产生了进位，将最高位截取掉然后重新复制给temp,result数组的下一位的值更新为1
             // 如果否：那么就是没有产生进位，直接赋值即可
            if(isCarry.length() > oldLength){
                temp = Integer.valueOf(isCarry.substring(1,isCarry.length()));
                result[i+1] = 1;
            }
            result[i] = temp;
        }
        return output(result);
    }

    public static void copyOf(int[] newArray,int[] oldArray){
        for (int i = oldArray.length-1; i >= 0 ; i--) {
            newArray[i] = oldArray[i];
        }
    }

    public static String output(int[] result){
        //重新拼接为string类型，然后输出结果
        StringBuilder sb = new StringBuilder();
        //是为了从高位找到第一个不为0的值的标识
        boolean findFirst = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if(!findFirst){
                if(result[i] == 0){
                    continue;
                }
                findFirst = true;
            }
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static int[] subString(String bigNumber){
        //int类型的范围大体是-21亿-正的21亿，即10位数吧，为了保证在范围内，以九位数进行分割，保证俩数相加后在范围内。
        //从尾部开始截取九位，目的是为了由地位到高位，每个位次对齐
        int cyclicNumA = cyclicNum(bigNumber);
        int[] array = new int[cyclicNumA];
        for (int i = 0; i < cyclicNumA; i++) {
            int startTemp = bigNumber.length() - (i * 9);
            int endTemp = bigNumber.length() - ((i+1) * 9);
            int startIndex = Math.max(startTemp,0);
            int endIndex = Math.max(endTemp,0);
            array[i] = Integer.valueOf(bigNumber.substring(endIndex,startIndex));
        }
        return array;
    }

    public static void main(String[] args) {
        //19 997 775 550
        System.out.println(bigNumberSum("9998887779", "9998887771"));
        System.out.println(bigNumberSum("426709752318", "95481253129"));
    }
}
