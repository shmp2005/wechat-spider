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
public class Article {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer accountId;

    @Getter
    @Setter
    private String msgId;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private LocalDate pubDate;

    @Getter
    @Setter
    private String digest;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Getter
    @Setter
    private LocalDateTime updatedAt;
}
