package com.zhongba01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/25.
 */

@Entity
@Table(name = "dogs")
public class Dog {
    @Column(name = "name")
    private String userName;
}
