package com.tenjak.pojo;

public class a {
}
 class Test {

    static {
        System.out.println("父类静态块");
    }

    public Test(String str){
        System.out.println("父类构造方法"+str);
    }

    {
        System.out.println("父类构造块");
    }
}

class Test1 extends Test{

    static {
        System.out.println("子类静态块");
    }

    {
        System.out.println(" 子类构造块");
    }

    public Test1(String str){
        super(str);
        System.out.println("子类构造方法"+str);
    }

    public static void main(String[] args){
        new Test1("init");
        new Test1("second");
    }
}
 class Test3 {

    public static void method(int a,int b,int c,int d){
        long e =(long) a*b*c*d;
        System.out.println(e);
    }

    public static void main(String[] args){
        method(1000,3600,24,1000);
    }
}