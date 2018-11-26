package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.ArticleSource;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ArticleSourceMapper {

    @SelectProvider(type = ArticleSourceSqlProvider.class, method = "getById")
    ArticleSource getById(Integer id);

    @InsertProvider(type = ArticleSourceSqlProvider.class, method = "insert")
    int add(ArticleSource articleSource);

    @SelectProvider(type = ArticleSourceSqlProvider.class, method = "queryList")
    List<ArticleSource> sourceList();

    @DeleteProvider(type = ArticleSourceSqlProvider.class, method = "update2")
    int deleteById(ArticleSource source);

    class ArticleSourceSqlProvider extends BaseSQLProvider<ArticleSource> {
        @Override
        protected String table() {
            return "T_ARTICLE_CLASSIFY";
        }

        public String getById(Integer id) {
            StringBuffer sb = new StringBuffer();
            sb.append("select id, name, is_deleted as isDeleted, type from " + table() + " where id = " + id);
            return sb.toString();
        }

        public String update2(ArticleSource source) {
            StringBuffer sb = new StringBuffer();
            sb.append("update " + table() + " " +
                    "set name = '" + source.getName() + "', " +
                    "type = " + source.getType() + ", " +
                    "is_deleted = " + source.isDeleted() + " " +
                    "where id = " + source.getId());
            System.out.println(sb.toString());
            return sb.toString();
        }

        public String queryList(final Map<String, Object> params) {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, " +
                    "name, " +
                    "type, " +
                    "is_deleted as isDeleted " +
                    "from " +
                    this.table() +
                    " " +
                    "WHERE 1=1 and " +
                    "is_deleted != true");
            System.out.println("查询sql == " + sql.toString());
            return sql.toString();
        }

        @Override
        protected void doInsert(ArticleSource articleSource, SQL sql) {
            sql.VALUES("name", "#{name}")
                    .VALUES("type", "#{type}");
        }
    }

}
