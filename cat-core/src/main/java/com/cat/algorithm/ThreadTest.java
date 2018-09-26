package com.cat.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangxiaoqiang
 * @since 2018/09/25
 **/
public class ThreadTest {

    static class InnerClass implements Runnable {

        public InnerClass(List<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (list != null && list.size() > 0) {

                try {
                    //放慢线程
                    Thread.currentThread().sleep(1000);
                    log = list.remove(0); //报下标错误

                    System.out.println(Thread.currentThread().getName() + log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }

        private List<String> list;
        private String log = "";
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"aa", "bb", "cc"};
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs));
        InnerClass innerClass = new InnerClass(list);
        Thread t1 = new Thread(innerClass);
        Thread t2 = new Thread(innerClass);
        t1.start();
        t2.start();
    }

}