package com.girlspower.controller;

import com.girlspower.domain.Receipt;
import com.girlspower.repos.ReceiptRepository;
import com.girlspower.service.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

@Controller
public class ReceiptController {
    private final ReceiptRepository receiptRepository;
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptRepository receiptRepository, ReceiptService receiptService) {
        this.receiptRepository = receiptRepository;
        this.receiptService = receiptService;
    }

    @GetMapping("/receipts")
    public String showReceiptsPage(Map<String, Object> model) {
        Iterable<Receipt> receipts = receiptRepository.findAll();
        model.put("receiptsList", receipts);
        return "/receipts";
    }

    @GetMapping("/receiptPage")
    public String currentReceipt(@RequestParam Long receiptId, Map<String, Object> model) {
        model.put("receiptInfo", receiptService.getReceiptInfo(receiptId));
        return "receiptPage";
    }

    @PostMapping("/filter")
    public ModelAndView findReceipts(@RequestParam String search) {
        Set<Receipt> currentReceipts = receiptService.getFilteredReceipts(search);
        ModelAndView modelAndView = new ModelAndView("receipts");
        modelAndView.addObject("receiptsList", currentReceipts);
        return modelAndView;
    }
}
