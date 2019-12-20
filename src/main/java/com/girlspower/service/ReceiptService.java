package com.girlspower.service;

import com.girlspower.domain.Receipt;
import com.girlspower.repos.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public Receipt getReceiptInfo(Long receiptId) {
        return receiptRepository.findById(receiptId).orElseGet(Receipt::new);
    }

    public Set<Receipt> getFilteredReceipts(String search) {
        Set<Receipt> receiptSet = new HashSet<>();
        List<Receipt> list = receiptRepository.findAll();
        for (Receipt r : list) {
            if (r.getMainProducts().contains(search) || r.getTitle().contains(search)) {
                receiptSet.add(r);
            }
        }
        return receiptSet;
    }
}
