package com.example.spring.service;

import com.example.spring.entity.Receipt;
import com.example.spring.repository.ReceiptDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {
    @Autowired
    ReceiptDetailRepository receiptDetailRepository;
    public List<Receipt> GetAll(){
        try {
            return receiptDetailRepository.getAll();
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    public Boolean Create(Receipt receipt){
        try {
            return receiptDetailRepository.Create(receipt);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
