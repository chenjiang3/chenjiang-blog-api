package com.chenjiang.endurance.mapper;

import com.chenjiang.endurance.common.BaseSQLProvider;
import com.chenjiang.endurance.common.SQL;
import com.chenjiang.endurance.entity.Customer;
import com.chenjiang.endurance.entity.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface CustomerMapper {

    @InsertProvider(type = CustomerSqlProvider.class, method = "insert")
    public String add(Customer customer);

    class CustomerSqlProvider extends BaseSQLProvider<Customer> {

        @Override
        protected String table() {
            return "T_CUSTOMER";
        }

        @Override
        protected void doInsert(Customer customer, SQL sql) {
            sql.VALUES("name", "#{name}")
                    .VALUES("contacts", "#{contacts}")
                    .VALUES("mobile", "#{mobile}")
                    .VALUES("address", "#{address}")
                    .VALUES("comment", "comment");
        }
    }
}
