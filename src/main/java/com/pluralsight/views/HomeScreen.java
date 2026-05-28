package com.pluralsight.views;

import com.pluralsight.IO.UI;
import com.pluralsight.abstracts.OrderItem;

public class HomeScreen {

    public void start() {

        boolean running = true;

        while (running) {

            showMenu();

            String input = UI.userInputString();

            switch (input) {

                case "1" -> addHoodie();

                case "2" -> addBeanie();

                case "3" -> addToteBag();

                case "4" -> viewOrder();

                case "5" -> removeItem();

                case "6" -> clearOrder();

                case "7" -> {

                    checkout();
                    ordering = false;
                }

                case "0" -> {

                    Display.showSuccess(
                            "Order cancelled."
                    );

                    ordering = false;
                }

                default ->
                        Display.showError(
                                "Invalid option."
                        );
            }
        }
    }

    private void showMenu() {

        Display.showAppHeader();

        IO.println(
                "  "
                        + "1 - New Order"
        );

        IO.println(
                "  "
                        + "0 - Exit"
        );

        IO.println();

        Display.promptArrow();
    }

    private void viewOrder() {

        IO.println();

        IO.println(
                order.getDescription()
        );

        IO.println();
    }

    private void removeItem() {

        if (order.isEmpty()) {

            Display.showError(
                    "Nothing to remove."
            );

            return;
        }

        List<OrderItem> items =
                order.getItems();

        IO.println();
        IO.println(
                "REMOVE ITEM"
        );

        for (int i = 0;
             i < items.size();
             i++) {

            OrderItem item =
                    items.get(i);

            System.out.printf(
                    "%d - %s ($%.2f)%n",

                    i + 1,

                    item.getDescription(),

                    item.getPrice()
            );
        }

        IO.println(
                "0 - Cancel"
        );

        String input =
                UI.userInputString();

        try {

            int choice =
                    Integer.parseInt(
                            input
                    );

            if (choice == 0) {
                return;
            }

            OrderItem selected =
                    items.get(
                            choice - 1
                    );

            order.removeItem(
                    selected
            );

            Display.showSuccess(
                    "Item removed."
            );

        } catch (
                Exception e
        ) {

            Display.showError(
                    "Invalid selection."
            );
        }
    }

}