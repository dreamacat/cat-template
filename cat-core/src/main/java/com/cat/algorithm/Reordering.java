package com.cat.algorithm;

/**
 * @author wangxiaoqiang
 * @since 2018/09/26
 **/
public class Reordering {
    static int x = 1, y = 0;
    static int a = 0, b = 0;
    static int co = 0;

    public static void main(String[] args) throws InterruptedException {
        while (!(x == 0 && y == 0)) {
            x=0;y=0;
            a=0;b=0;
            test();
        }
    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }

    private static void test() throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                shortWait(100000);
                a = 1;
                x = b;
            }
        });

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });
        one.start();
        other.start();
        one.join();
        other.join();

        String result = "第" + (co++) + "次 (" + x + "," + y + "）";
        System.out.println(result);
    }
}
