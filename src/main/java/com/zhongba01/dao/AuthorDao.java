package com.zhongba01.dao;

import com.zhongba01.domain.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */
public interface AuthorDao extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
