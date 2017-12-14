package com.zhongba01.service;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
public interface AccountService {
    /**
     * 抓取某关键词对应的公众号信息
     *
     * @param keywords 关键词
     */
    void dumpAccounts(String keywords);

    /**
     * 抓取某公众号对应的最新的10篇文章
     *
     * @param wxAccount  公众号
     * @param profileUrl 公众号对应的profile
     */
    void accountProfile(String wxAccount, String profileUrl);
}
