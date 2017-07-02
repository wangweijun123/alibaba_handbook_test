package com.le.www.tt;

import android.support.annotation.NonNull;

/**
 * 可以不注释，要注释就要按照规范注释
 * Created by wangweijun1 on 2017/6/29.
 */

public class Student implements Comparable<Student> {

    /** 年龄 */
    private int age;

    /**
     * 三种case
     * @param other
     * @return
     */
    @Override
    public int compareTo(@NonNull Student other) {
        if (this.age > other.age) {
            return 1;
        } else if (this.age < other.age) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getAge() {
        return age;
    }

    /**
     * 年龄
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    private void compute() {
        // (注释单行)活这么就我就可以长生不老了
        int result = age * 1024 * 1024;

        /* 注释底下三行*/
        int result23 = age * 1024 * 1024;
        int result3 = age * 1024 * 1024;
        int result4 = age * 1024 * 1024;

    }

}
