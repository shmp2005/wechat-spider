package com.zhongba01.mapper;

import com.zhongba01.domain.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
@Component
@Mapper
public interface ArticleMapper {
    @Select("Select * From gou_articles WHERE id = #{id}")
    Article findById(@Param("id") String id);

    @Select("Select * From gou_articles WHERE msg_id = #{msgId}")
    Article findByMsgId(@Param("msgId") String msgId);

    @Select("Select * From gou_articles WHERE account_id = #{accountId}")
    Article findByAccountId(@Param("accountId") String accountId);

    @Insert("insert into gou_articles(account_id, msg_id, seq, origin, author, title, pub_date, url, post_url, digest, content, created_at, updated_at) values" +
            "(#{accountId}, #{msgId}, #{seq}, #{isOrigin}, #{author}, #{title}, #{pubDate}, #{url}, #{postUrl}, #{digest}, #{content}, #{createdAt}, #{updatedAt})")
    int insert(Article article);

    @Results({
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "msgId", column = "msg_id"),
            @Result(property = "isOrigin", column = "origin", javaType = Boolean.class),
            @Result(property = "pubDate", column = "pub_date"),
            @Result(property = "postUrl", column = "post_url"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    @Select("Select * from gou_articles")
    List<Article> findAll();
}
