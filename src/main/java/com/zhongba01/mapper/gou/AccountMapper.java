package com.zhongba01.mapper.gou;

import com.zhongba01.domain.gou.Account;
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
public interface AccountMapper {
    @Results({
            @Result(property = "lastPublish", column = "last_publish"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    @Select("Select * From gou_accounts WHERE account = #{wxAccount}")
    Account findByWxAccount(@Param("wxAccount") String wxAccount);

    @Insert("insert into gou_accounts(nickname, account, description, vname, avatar, last_publish, active, created_at, updated_at) values" +
            "(#{nickname}, #{account}, #{description}, #{vname}, #{avatar}, #{lastPublish}, #{active}, #{createdAt}, #{updatedAt})")
    int insert(Account account);

    @Results({
            @Result(property = "lastPublish", column = "last_publish"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    @Select("Select * from gou_accounts")
    List<Account> findAll();

    /**
     * 总的公众号数量
     * @return 总的公众号数量
     */
    @Select("select count(1) from gou_accounts")
    int count();
}
