package com.example.spring.entity;

import com.example.spring.dto.ReceiptDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private String receiptID;
    private String accountID;
    private List<ReceiptDetail> receiptDetaillist;
    private int total_money;
    private String status;
}
