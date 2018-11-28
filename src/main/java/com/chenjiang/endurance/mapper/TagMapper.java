package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.Tag;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface TagMapper {

    @InsertProvider(type = TagSqlProvider.class, method = "insert")
    int add(Tag tag);

    @SelectProvider(type = TagSqlProvider.class, method = "listSql")
    List<Tag> list();

    class TagSqlProvider extends BaseSQLProvider<Tag> {
        @Override
        protected String table() {
            return "T_TAGS";
        }

        @Override
        protected void doInsert(Tag tag, SQL sql) {
            sql.VALUES("name", "#{name}")
                    .VALUES("deleted", "#{deleted}");
        }

        public String listSql() {
            return new SQL().SELECT("id")
                    .SELECT("name")
                    .SELECT("deleted")
                    .SELECT("type")
                    .FROM(table()).toString();
        }
    }

}
