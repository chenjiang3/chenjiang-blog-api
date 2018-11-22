package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.Article;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ArticleMapper {

    @InsertProvider(type = ArticleSqlProvider.class, method = "insert")
    public int add(Article article);

    class ArticleSqlProvider extends BaseSQLProvider<Article> {
        @Override
        protected String table() {
            return "T_ARTICLE";
        }

        @Override
        protected void doInsert(Article article, SQL sql) {
            sql.VALUES("title", "#{title}")
                    .VALUES("tags", "#{tags}")
                    .VALUES("type", "#{type}")
                    .VALUES("abstract", "#{abstractContent}")
                    .VALUES("content", "#{content}")
                    .VALUES("raw_file_link", "#{rawFileLink}");
        }
    }
}
