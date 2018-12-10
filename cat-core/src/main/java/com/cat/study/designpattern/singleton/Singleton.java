package com.cat.study.designpattern.singleton;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 饿汉，推荐使用
 * @author wangxiaoqiang
 * @since 2018/11/27
 **/
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton() {}

    public static Singleton getInstance() {
        return singleton;
    }
}

/**
 * 懒汉，非线程安全，可使用synchronized，推荐使用饿汉模式
 */
class Singleton2 {
    private static Singleton2 singleton = null;
    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }
}

/**
 * 可容纳多个对象的单例
 */
class ExtendSingleton {
    private static int maxInstance = 2;
    private static List<ExtendSingleton> list = Lists.newArrayList();
    private ExtendSingleton() {
        for (int i = 0; i < maxInstance; i++) {
            list.add(new ExtendSingleton());
        }
    }

    public static ExtendSingleton getInstance() {
        return list.get(0);
    }

}
