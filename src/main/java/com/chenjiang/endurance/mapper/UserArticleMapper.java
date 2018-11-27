package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.UserArticle;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserArticleMapper {

    @InsertProvider(type = UserArticleSqlProvider.class, method = "insert")
    public int add(UserArticle userArticle);

    class UserArticleSqlProvider extends BaseSQLProvider<UserArticle> {
        @Override
        protected String table() {
            return "T_USER_ARTICLE";
        }

        @Override
        protected void doInsert(UserArticle userArticle, SQL sql) {
            sql.VALUES("user_id", "#{userId}")
                    .VALUES("article_id", "#{articleId}");
        }
    }
}
