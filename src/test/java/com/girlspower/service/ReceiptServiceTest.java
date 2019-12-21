package com.girlspower.service;

import com.girlspower.domain.Receipt;
import com.girlspower.repos.ReceiptRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReceiptServiceTest {
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ReceiptService receiptService;
    @Test
    public void testFilteredReceipts() throws Exception {
        Receipt receipt = receiptRepository.findByTitle("Отварной рис");
        assertThat(receiptService.getFilteredReceipts("Рис"), contains(receipt));
        receipt = receiptRepository.findByMainProducts("Курица");
        assertThat(receiptService.getFilteredReceipts("Курица"), contains(receipt));
    }
}
