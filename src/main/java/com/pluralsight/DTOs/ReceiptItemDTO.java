package com.pluralsight.DTOs;



public record ReceiptItemDTO(
        String description,
        double price
) {}