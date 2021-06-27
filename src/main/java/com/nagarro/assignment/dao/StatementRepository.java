package com.nagarro.assignment.dao;

import com.nagarro.assignment.models.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StatementRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Statement> findByAccountId(int accountId) {
        String sql = "select ID,account_id,DateValue(Replace(datefield,'.','/') ) as datefield ,amount from statement where account_id=?";
        return template.query(sql, new StatementRowMapper(), accountId);
    }



    private class StatementRowMapper implements RowMapper<Statement> {

        @Override
        public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Statement(rs.getInt("id"), rs.getInt("account_id"), rs.getDate("datefield"), rs.getDouble("amount"));
        }
    }
}
