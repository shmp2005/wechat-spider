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

    @Insert("insert into gou_articles(account_id, msg_id, seq, origin, title, pub_date, url, post_url, digest, created_at, updated_at) values" +
            "(#{accountId}, #{msgId}, #{seq}, #{origin}, #{title}, #{pubDate}, #{url}, #{postUrl}, #{digest}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true)
    int insert(Article article);

    @Update("update gou_articles set author = #{author}, content = #{content} where id = #{id}")
    void update(@Param("id") int id, @Param("author") String author, @Param("content") String content);

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

    @Select("select count(msg_id) from gou_articles where msg_id = #{msgId}")
    int countByMsgId(String msgId);
}
