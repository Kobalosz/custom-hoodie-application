package com.pluralsight.models;

import com.pluralsight.abstracts.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final List<OrderItem> items =
            new ArrayList<>();

    private final LocalDateTime createdAt =
            LocalDateTime.now();


// adding and removing my products to the order

    public void addItem(
            OrderItem item
    ) {

        if (item == null) {
            throw new IllegalArgumentException(
                    "Item cannot be null."
            );
        }

        items.add(item);
    }

    public boolean removeItem(
            OrderItem item
    ) {

        return items.remove(item);
    }

    public void clear() {

        items.clear();
    }


// Order info

    public List<OrderItem> getItems() {

        return List.copyOf(items);
    }

    public int getItemCount() {

        return items.size();
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public double getTotal() {

        return items.stream()
                .mapToDouble(
                        OrderItem::getPrice
                )
                .sum();
    }

// just a formatted output of the order

    public String getDescription() {

        if (items.isEmpty()) {
            return "No items in order.";
        }

        StringBuilder description =
                new StringBuilder();

        description.append(
                "ORDER SUMMARY\n"
        );

        for (int i = 0;
             i < items.size();
             i++) {

            OrderItem item =
                    items.get(i);

            description.append(
                            i + 1
                    )
                    .append(". ")
                    .append(
                            item.getDescription()
                    )
                    .append(" - $")
                    .append(
                            String.format(
                                    "%.2f",
                                    item.getPrice()
                            )
                    )
                    .append("\n");
        }

        description.append(
                "\nTOTAL: $"
        ).append(
                String.format(
                        "%.2f",
                        getTotal()
                )
        );

        return description.toString();
    }
}