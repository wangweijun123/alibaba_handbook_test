package com.le.www.tt;

/**
 * 接口的注释是必须的，强制性的
 * this is callback for request
 * Created by wangweijun1 on 2017/6/30.
 */

public interface Callback {

    /**
     * 计算结果成功的回调
     * @param resutlt 计算结果
     */
    void onResponse(int resutlt);


}
