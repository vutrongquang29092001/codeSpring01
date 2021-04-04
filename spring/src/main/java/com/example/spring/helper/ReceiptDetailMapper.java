package com.example.spring.helper;

import com.example.spring.dto.ReceiptDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptDetailMapper implements RowMapper {
    @Override
    public ReceiptDetail mapRow(ResultSet resultSet, int i) throws SQLException {
        ReceiptDetail receiptDetail = new ReceiptDetail();
        receiptDetail.setReceiptID(resultSet.getString("receiptID"));
        receiptDetail.setProductID(resultSet.getString("productID"));
        receiptDetail.setCount(resultSet.getInt("count"));
        return receiptDetail;
    }
}
