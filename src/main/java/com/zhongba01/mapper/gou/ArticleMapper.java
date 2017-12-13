package com.zhongba01.mapper.gou;

import com.zhongba01.domain.gou.Account;
import com.zhongba01.domain.gou.Article;
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

    @Insert("insert into gou_articles(account_id, msg_id, seq, author, title, pub_date, url, digest, content, created_at, updated_at) values" +
            "(#{accountId}, #{msgId}, #{seq}, #{author}, #{title}, #{pubDate}, #{url}, #{digest}, #{content}, #{createdAt}, #{updatedAt})")
    int insert(Article article);

    @Results({
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "msgId", column = "msg_id"),
            @Result(property = "pubDate", column = "pub_date"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    @Select("Select * from gou_articles")
    List<Article> findAll();
}
