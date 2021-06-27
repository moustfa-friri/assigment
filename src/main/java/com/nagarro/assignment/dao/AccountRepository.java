package com.nagarro.assignment.dao;

import com.nagarro.assignment.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AccountRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Account> findAll(){

        return template.query("select ID,account_type,account_number from account", new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Account(resultSet.getInt("id"),resultSet.getString("account_type"),resultSet.getString("account_number"));
            }
        });
    }
}
