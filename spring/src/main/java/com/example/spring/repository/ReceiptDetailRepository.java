package com.example.spring.repository;

import com.example.spring.entity.Receipt;
import com.example.spring.helper.ReceiptDetailMapper;
import com.example.spring.helper.ReceiptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ReceiptDetailRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Receipt> getAll(){
        String sql = "SELECT * FROM Receipt WHERE deleted = 0;";
        String sql1 = "SELECT * FROM ReceiptDetail WHERE receiptID = ? ;";
        List<Receipt> list = jdbcTemplate.query(sql,new ReceiptMapper());
        for(int i = 0; i< list.size();i++){
            String id = list.get(i).getReceiptID();
            list.get(i).setReceiptDetaillist(jdbcTemplate.query(sql1,new ReceiptDetailMapper(),new Object[]{id})) ;
        }
        return list;
    }
    public Boolean Create(Receipt receipt){
        String id= UUID.randomUUID().toString();
        String sql0 = "INSERT INTO Receipt(receiptID,accountID,total_money,status) VALUES(?,?,?,?);";
        String sql = "INSERT INTO ReceiptDetail(receiptID,productID,count) VALUES(?,?,?) ;" ;
        for(int i = 0; i < receipt.getReceiptDetaillist().size(); i++){
            Object param[] = new Object[3];
            receipt.getReceiptDetaillist().get(i).setReceiptID(id);
            param[0] =  receipt.getReceiptDetaillist().get(i).getReceiptID();
            param[1] =  receipt.getReceiptDetaillist().get(i).getProductID();
            param[2] =  receipt.getReceiptDetaillist().get(i).getCount();
            jdbcTemplate.update(sql,param);

        }
        jdbcTemplate.update(sql0,id,receipt.getAccountID(),receipt.getTotal_money(),receipt.getStatus());
        return true;
    }
}
