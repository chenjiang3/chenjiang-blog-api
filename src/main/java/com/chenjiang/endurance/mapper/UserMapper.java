package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.RegisterRequest;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {

    @InsertProvider(type = UserSqlProvider.class, method = "insert")
    public int register(User registerUser);

    @Select("SELECT * FROM T_USER WHERE MOBILE = #{mobile};")
    @Results(value = {
            @Result(column = "ID", property = "id", javaType = Integer.class),
            @Result(column = "CLIENT_ID", property = "clientId", javaType = Integer.class),
            @Result(column = "USER_ID", property = "userId", javaType = Integer.class),
            @Result(column = "MOBILE", property = "mobile", javaType = String.class),
            @Result(column = "USER_NAME", property = "userName", javaType = String.class),
            @Result(column = "SALT", property = "salt", javaType = String.class),
            @Result(column = "STATUS", property = "status", javaType = String.class),
            @Result(column = "brief", property = "brief", javaType = String.class),
            @Result(column = "header_src", property = "headerSrc", javaType = String.class),
            @Result(column = "email", property = "email", javaType = String.class),
            @Result(column = "access", property = "access", javaType = Integer.class)
    })
    public User findUserByMobile(@Param("mobile") String mobile);

    @SelectProvider(type = UserSqlProvider.class, method = "findById")
    public User findById(String id);

    @SelectProvider(type = UserSqlProvider.class, method = "listSql")
    public List<User> userList(int pageIndex, int pageSize);

    @Select("SELECT * FROM T_USER ORDER BY USER_ID DESC LIMIT 0, 1;")
    public Integer findMaxUserId();

    class UserSqlProvider extends BaseSQLProvider<User> {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        public String findById(String uid) {
            String sql = new SQL()
                    .SELECT("ID")
                    .SELECT("CLIENT_ID as clientId")
                    .SELECT("USER_ID as userId")
                    .SELECT("MOBILE as mobile")
                    .SELECT("USER_NAME as userName")
                    .SELECT("STATUS as status")
                    .SELECT("brief")
                    .SELECT("header_src as headerSrc")
                    .SELECT("email as email")
                    .SELECT("access as access")
                    .FROM(table())
                    .WHERE("USER_ID = #{uid}")
                    .toString();
            return sql;
        }

        public String listSql(int pageIndex, int pageSize) {
            String sql = new SQL()
                    .SELECT("ID")
                    .SELECT("CLIENT_ID as clientId")
                    .SELECT("USER_ID as userId")
                    .SELECT("MOBILE as mobile")
                    .SELECT("USER_NAME as userName")
                    .SELECT("STATUS as status")
                    .SELECT("brief")
                    .SELECT("header_src as headerSrc")
                    .SELECT("email as email")
                    .SELECT("access as access")
                    .FROM(table())
                    .toString();
            logger.debug("UserMapper listSql = " + sql);
            return sql;
        }

        @Override
        protected String table() {
            return "T_USER";
        }

        @Override
        protected void doInsert(User registerUser, SQL sql) {
            sql.VALUES("USER_ID", "#{userId}")
                    .VALUES("MOBILE", "#{mobile}")
                    .VALUES("USER_NAME", "#{userName}")
                    .VALUES("SALT", "#{salt}")
                    .VALUES("PASSWORD", "#{password}")
                    .VALUES("STATUS", "#{status}")
                    .VALUES("VERSION", "#{version}")
                    .VALUES("CLIENT_ID", "#{clientId}");
        }
    }
}















































