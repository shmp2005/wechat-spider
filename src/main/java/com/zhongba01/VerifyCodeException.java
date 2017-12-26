package com.zhongba01;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/26.
 */
public class VerifyCodeException extends Exception {
    public VerifyCodeException() {
        super("访问太频繁，要求输入验证码!");
    }
}
