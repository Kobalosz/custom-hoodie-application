package com.pluralsight.views;

import com.pluralsight.DTOs.ReceiptItemDTO;
import com.pluralsight.models.Order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public record Receipt(

        String receiptNumber,
        LocalDateTime createdAt,
        List<ReceiptItemDTO> items,
        double total

) {

    public Receipt {

        items = List.copyOf(items);
    }

    public String getFormattedReceipt() {

        StringBuilder receipt =
                new StringBuilder();

        receipt.append(
                "====================================\n"
        );

        receipt.append(
                "         CUSTOM HOODIE RECEIPT\n"
        );

        receipt.append(
                "====================================\n"
        );

        receipt.append(
                "Receipt #: "
        ).append(
                receiptNumber
        ).append("\n");

        receipt.append(
                "Date: "
        ).append(
                createdAt.format(
                        DateTimeFormatter.ofPattern(
                                "MM/dd/yyyy HH:mm"
                        )
                )
        ).append("\n\n");

        for (ReceiptItemDTO item :
                items) {

            receipt.append(
                            item.description()
                    )
                    .append("\n$")

                    .append(
                            String.format(
                                    "%.2f",
                                    item.price()
                            )
                    )
                    .append("\n\n");
        }

        receipt.append(
                "------------------------------------\n"
        );

        receipt.append(
                "TOTAL: $"
        ).append(
                String.format(
                        "%.2f",
                        total
                )
        );

        return receipt.toString();
    }


    // Factory method

    public static Receipt fromOrder(
            Order order
    ) {

        List<ReceiptItemDTO> items =
                order.getItems()
                        .stream()

                        .map(item ->
                                new ReceiptItemDTO(
                                        item.getDescription(),
                                        item.getPrice()
                                )
                        )

                        .toList();

        return new Receipt(

                UUID.randomUUID()
                        .toString()
                        .substring(0, 8),

                LocalDateTime.now(),

                items,

                order.getTotal()
        );
    }
}
