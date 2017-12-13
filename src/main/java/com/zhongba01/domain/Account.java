package com.zhongba01.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@ToString
@Getter
@Setter
public class Account {
    private Integer id;
    private String nickname;
    private String account;
    private String description;
    private String vname;
    private String avatar;
    private LocalDateTime lastPublish;
    private int active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
