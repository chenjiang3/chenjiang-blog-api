package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
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

    @Select("select count(id) from T_ARTICLE")
    public int total();

    @SelectProvider(type = ArticleSqlProvider.class, method = "queryList")
    public List<Article> articleList(RowBounds rowBounds);

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

        public String queryList(Map<String, Object> params) {
            SQL sql = new SQL();
            sql.SELECT("id")
                    .SELECT("title")
                    .SELECT("tags")
                    .SELECT("type")
                    .SELECT("abstract as abstractContent")
                    .SELECT("content")
                    .SELECT("raw_file_link as rawFileLink")
                    .SELECT("access")
                    .SELECT("source")
                    .SELECT("author")
                    .SELECT("create_time as createTime")
                    .FROM(table())
                    .WHERE("1=1");
            System.out.println("查询sql== " + sql.toString());
            return sql.toString();

//            StringBuffer sb = new StringBuffer();
//            sb.append(sql.toString());
//            sb.append(" ");
//            sb.append("limit " + limit + " ");
//            sb.append("offset " + offset + " ");
//            System.out.println("查询sql== " + sb.toString());
//            logger.debug("查询sql == " + sb.toString());
//            return sb.toString();
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
