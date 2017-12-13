package com.zhongba01.mapper;

import com.zhongba01.domain.Account;
import org.apache.ibatis.jdbc.SQL;

/**
 * 中巴价值投资研习社
 *
 * @ author: tangjianhua
 * @ date: 2017/12/13.
 */
public class AccountSqlProvider {
    public String findByWxAccountSql() {
        return new SQL() {{
            SELECT("*");
            FROM("gou_accounts");
            WHERE("account = #{wxAccount}");
        }}.toString();
    }

    public String findAllSql() {
        return "Select * from gou_accounts";
    }

    public String insertSql(final Account account) {
        return new SQL() {{
            INSERT_INTO("gou_accounts");
            VALUES("nickname", "#{nickname}");
            VALUES("account", "#{account}");
            VALUES("description", "#{description}");
            VALUES("vname", "#{vname}");
            VALUES("avatar", "#{avatar}");
            VALUES("last_publish", "#{lastPublish}");
            VALUES("active", "1");
            VALUES("created_at", "#{createdAt}");
            VALUES("updated_at", "#{updatedAt}");
        }}.toString();
    }

    public String updateLastPublishSql(final Account account) {
        return new SQL() {{
            UPDATE("gou_accounts");
            SET("description", "#{description}");
            SET("last_publish", "#{lastPublish}");
            SET("updated_at", "#{updatedAt}");
            WHERE("id=" + account.getId());
        }}.toString();
    }

    public String countSql() {
        return "select count(1) from gou_accounts";
    }
}
