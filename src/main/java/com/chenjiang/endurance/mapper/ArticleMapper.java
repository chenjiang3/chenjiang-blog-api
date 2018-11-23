package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.Article;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ArticleMapper {

    @InsertProvider(type = ArticleSqlProvider.class, method = "insert")
    public int add(Article article);

    @SelectProvider(type = ArticleSqlProvider.class, method = "queryList")
    public List<Article> articleList(Map<String, Object> params);

    @SelectProvider(type = ArticleSqlProvider.class, method = "getById")
    public Article getById(Integer id);

    @UpdateProvider(type = ArticleSqlProvider.class, method = "access")
    int increaseAccess(Integer id);

    class ArticleSqlProvider extends BaseSQLProvider<Article> {

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
                    "create_time as createTime " +
                    "from T_ARTICLE WHERE 1=1");
            System.out.println("查询sql == " + sql.toString());
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
                    .VALUES("create_time", "#{createTime}");
        }
    }
}
