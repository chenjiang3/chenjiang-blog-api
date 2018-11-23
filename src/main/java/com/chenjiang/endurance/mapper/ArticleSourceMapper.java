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

    @InsertProvider(type = ArticleSourceSqlProvider.class, method = "insert")
    int add(ArticleSource articleSource);

    @SelectProvider(type = ArticleSourceSqlProvider.class, method = "queryList")
    List<ArticleSource> sourceList();

    @DeleteProvider(type = ArticleSourceSqlProvider.class, method = "deleteById")
    int deleteById(Integer id);

    class ArticleSourceSqlProvider extends BaseSQLProvider<ArticleSource> {
        @Override
        protected String table() {
            return "T_ARTICLE_SOURCE";
        }

        public String deleteById(Integer id) {
            return new SQL().DELETE_FROM(table()).WHERE("id=#{id}").toString();
        }

        public String queryList(final Map<String, Object> params) {
            StringBuffer sql = new StringBuffer();
            sql.append("select id, " +
                    "name " +
                    "from " +
                    this.table() +
                    " " +
                    "WHERE 1=1");
            System.out.println("查询sql == " + sql.toString());
            return sql.toString();
        }

        @Override
        protected void doInsert(ArticleSource articleSource, SQL sql) {
            sql.VALUES("name", "#{name}");
        }
    }

}
