package com.zhongba01.domain.gou;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@ToString
public class Account {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String nickname;

    @Getter
    @Setter
    private String account;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String vname;

    @Getter
    @Setter
    private String avatar;

    @Getter
    @Setter
    private int active;

    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Getter
    @Setter
    private LocalDateTime updatedAt;
}
