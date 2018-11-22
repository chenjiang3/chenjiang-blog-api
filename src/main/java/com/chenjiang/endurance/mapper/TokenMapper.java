package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.Token;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Mapper
public interface TokenMapper {

    @InsertProvider(type = TokenSqlProvider.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "id", before = false, resultType = int.class)
    public void createToken(Token token);

    @DeleteProvider(type = TokenSqlProvider.class, method = "delete")
    public void deleteToken(@Param("id") Long id);

    @Select("SELECT * FROM T_TOKEN WHERE id = #{tid}")
    @Results(value = {
            @Result(column = "id", property = "id", javaType = int.class),
            @Result(column = "token", property = "token", javaType = String.class),
            @Result(column = "client_id", property = "clientId", javaType = int.class),
            @Result(column = "user_id", property = "userId", javaType = int.class),
            @Result(column = "expire_time", property = "expireTime", javaType = Date.class),
    })
    public Token findById(@Param("tid") Long tid);

    class TokenSqlProvider extends BaseSQLProvider<Token> {
        @Override
        protected String table() {
            return "T_TOKEN";
        }

        @Override
        protected void doInsert(Token token, SQL sql) {
            sql.VALUES("token", "#{token}")
                    .VALUES("client_id", "#{clientId}")
                    .VALUES("user_id", "#{userId}")
                    .VALUES("expire_time", "#{expireTime}");
        }

        public String delete() {
            return new SQL()
                    .DELETE_FROM(table())
                    .WHERE("ID=#{id}")
                    .toString();
        }
    }

}




























