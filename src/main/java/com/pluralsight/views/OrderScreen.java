package com.pluralsight.views;

import com.pluralsight.IO.UI;

public class OrderScreen {

    public void start() {

        boolean ordering = true;

        while (ordering) {

            Display.showOrderMenu();

            String input =
                    UI.userInputString();

            switch (input) {

                case "1" -> addHoodie();

                case "2" -> addBeanie();

                case "3" -> addToteBag();

                case "4" -> {

                    checkout();

                    ordering = false;
                }

                case "0" -> {

                    Display.showSuccess(
                            "Order cancelled."
                    );

                    ordering = false;
                }

                default -> Display.showError(
                        "Invalid option."
                );
            }
        }
    }

    private void addHoodie() {

        Display.showSuccess(
                "Launching hoodie builder..."
        );

        // TODO:
        // HoodieScreen or hoodie creation flow
    }

    private void addBeanie() {

        Display.showSuccess(
                "Beanie added."
        );

        // TODO:
        // order.addItem(new Beanie());
    }

    private void addToteBag() {

        Display.showSuccess(
                "Tote bag added."
        );

        // TODO:
        // order.addItem(new ToteBag());
    }

    private void checkout() {

        Display.showSuccess(
                "Generating receipt..."
        );

        // TODO:
        // ReceiptScreen
        // Receipt generation
    }
}