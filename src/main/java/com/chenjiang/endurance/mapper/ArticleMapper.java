package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.Article;
import org.apache.ibatis.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ArticleMapper {

    @InsertProvider(type = ArticleSqlProvider.class, method = "insert")
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS id", keyProperty = "id", before = false, resultType = int.class)
    public int add(Article article);

    @SelectProvider(type = ArticleSqlProvider.class, method = "queryList")
    public List<Article> articleList(Map<String, Object> params);

    @SelectProvider(type = ArticleSqlProvider.class, method = "getById")
    public Article getById(Integer id);

    @UpdateProvider(type = ArticleSqlProvider.class, method = "access")
    int increaseAccess(Integer id);

    class ArticleSqlProvider extends BaseSQLProvider<Article> {

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        public String access(Integer id) {
            return "update T_ARTICLE set access=(access + 1) where id=#{id}";
        }

        public String getById(Integer id) {
            return "select " +
                    "id, " +
                    "title, " +
                    "tags, " +
                    "type, " +
                    "abstract as abstractContent, " +
                    "content, " +
                    "raw_file_link as rawFileLink, " +
                    "access, " +
                    "source, " +
                    "author, " +
                    "create_time as createTime " +
                    "from T_ARTICLE where id = #{id}";
        }

        public String queryList(final Map<String, Object> params) {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, " +
                    "title, " +
                    "tags, " +
                    "type, " +
                    "abstract as abstractContent, " +
                    "content, " +
                    "raw_file_link as rawFileLink, " +
                    "access, " +
                    "source, " +
                    "author, " +
                    "create_time as createTime " +
                    "from T_ARTICLE WHERE 1=1");
            logger.debug("查询sql == " + sql.toString());
            return sql.toString();
        }

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
                    .VALUES("raw_file_link", "#{rawFileLink}")
                    .VALUES("access", "#{access}")
                    .VALUES("source", "#{source}")
                    .VALUES("author", "#{author}")
                    .VALUES("create_time", "#{createTime}");
        }
    }
}
