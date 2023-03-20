package com.music;

import java.util.Arrays;
import java.util.Scanner;
public class App{
    public static void main(String[] args) {
        int[] a = new int[]{1,3,5,6,9};
        int[] b = new int[a.length+1];
//        System.out.println(a);

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int index = 0;
        //遍历原数组，判断输入的值，小于就直接赋值给新数组，有一次循环赋值两次
        for (int i = 0; i <a.length ; i++) {
            if(a[i]<num){
                b[i]=a[i];
            }else {
                //只添加一次
                if(index==0){
                    b[i]=num;
                    index++;
                }
                b[i+1]=a[i];
            }
        }
        System.out.println(Arrays.toString(b));
    }
}
