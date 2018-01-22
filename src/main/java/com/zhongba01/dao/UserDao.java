package com.zhongba01.dao;

import com.zhongba01.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */
public interface UserDao extends CrudRepository<User, Long> {

    User findByWeixin(String weixin);

    List<User> findByActiveOrderByCrawlAt(boolean active);
}
