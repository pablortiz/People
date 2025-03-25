package com.company.people.service;

import com.company.people.entity.Price;
import com.company.people.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> getPrice(LocalDateTime applicationDate, int productId, int brandId) {
        return priceRepository.findPriceByDateProductBrand(applicationDate, productId, brandId);
    }
   
}