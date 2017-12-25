package com.zhongba01.dao;

import com.zhongba01.domain.Article;
import org.springframework.data.repository.CrudRepository;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */
public interface ArticleDao extends CrudRepository<Article, Long> {

    long countByMsgId(String msgId);
}
