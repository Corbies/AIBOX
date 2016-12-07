package com.wx.aibox.tools;

/**
 * Created by cxj on 2016/12/6.
 */
public class Test {

    @org.junit.Test
    public void c(){
        String temp="问题&我是谁&1557655749";
        System.out.println(temp.substring(0,2));
        String[] str = temp.split("&");
        for (String str1:str){
            System.out.println(str1);
        }
          System.out.println(str[0]);
        System.out.println(str[1]);
       System.out.println(str[2]);
      System.out.println(str.length);
//        System.out.println(str[1]);
//        System.out.println(str[2]);
//        System.out.println(str[3]);
//        System.out.println( str[1]+" 2 "+str[2]+"  3  "+str[3]);
//        System.out.println( str[0]+" 2 "+str[1]+"  3  "+str[2]);
//        if(temp.substring(0,2).equals("问题")) {System.out.println( "zhenghao ");}

    }
}
