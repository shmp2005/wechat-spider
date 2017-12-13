package com.zhongba01.domain.gou;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
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
public class Article {
    private Integer id;
    private Integer accountId;

    /**
     * 两种情况，
     * 1，一个msgId下面挂一篇文章，seq=1
     * 2，一个msgId下面挂多篇文章, 用seq来区分
     */
    private String msgId;
    private int seq;
    private String author;
    private String title;
    private LocalDate pubDate;
    private String url;
    private String digest;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
