package com.girlspower.repos;

import com.girlspower.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Receipt findByTitle(String title);
    Receipt findByMainProducts(String mainProducts);
}
