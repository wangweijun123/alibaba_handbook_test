package com.le.www.tt;

import android.support.annotation.NonNull;

/**
 * Created by wangweijun1 on 2017/6/29.
 */

public class Student implements Comparable<Student> {

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

    public void setAge(int age) {
        this.age = age;
    }
}
