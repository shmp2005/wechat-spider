package com.zhongba01.service;

import com.zhongba01.domain.User;

import java.util.List;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
public interface UserService {
    /**
     * 抓取某公众号账号对应的信息
     *
     * @param weixin 微信公众号账号
     */
    void dumpUser(String weixin);

    /**
     * 抓取某公众号对应的最新的10篇文章
     *
     * @param weixin     公众号
     * @param profileUrl 公众号对应的profile
     */
    void userProfile(String weixin, String profileUrl);

    /**
     * 可采集的公众号
     *
     * @return users
     */
    List<User> findActives();
}
