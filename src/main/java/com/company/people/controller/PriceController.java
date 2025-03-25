package com.company.people.controller;

import com.company.people.entity.Price;
import com.company.people.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/price")
    public ResponseEntity<?> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam int productId,
            @RequestParam int brandId) {

        Optional<Price> price = priceService.getPrice(applicationDate, productId, brandId);
        if (price.isPresent()) {
            return ResponseEntity.ok(price.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Price not found for the given parameters."));
        }
    }

    static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}