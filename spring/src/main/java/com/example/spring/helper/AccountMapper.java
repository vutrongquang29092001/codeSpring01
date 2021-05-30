package com.example.spring.helper;

import com.example.spring.entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setAccountID(resultSet.getString("accountID"));
        account.setAvatar(resultSet.getString("avatar"));
        account.setDisplay(resultSet.getString("display"));
        account.setEmail(resultSet.getString("email"));
        account.setPassword(resultSet.getString("password"));
        account.setRole(resultSet.getString("Role"));
        return account;
    }
}
