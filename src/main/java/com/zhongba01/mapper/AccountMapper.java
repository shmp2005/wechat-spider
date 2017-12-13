package com.zhongba01.mapper;

import com.zhongba01.domain.Account;
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
    @SelectProvider(type = AccountSqlProvider.class, method = "findByWxAccountSql")
    Account findByWxAccount(String wxAccount);

    @InsertProvider(type = AccountSqlProvider.class, method = "insertSql")
    int insert(Account account);

    @UpdateProvider(type = AccountSqlProvider.class, method = "updateLastPublishSql")
    boolean updateLastPublish(Account account);

    @Results({
            @Result(property = "lastPublish", column = "last_publish"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    })
    @SelectProvider(type = AccountSqlProvider.class, method = "findAllSql")
    List<Account> findAll();

    /**
     * 总的公众号数量
     *
     * @return 总的公众号数量
     */
    @SelectProvider(type = AccountSqlProvider.class, method = "countSql")
    int count();
}
