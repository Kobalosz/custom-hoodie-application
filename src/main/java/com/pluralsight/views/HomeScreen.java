package com.pluralsight.views;

import com.pluralsight.IO.UI;

public class HomeScreen {

    public void start() {

        boolean running = true;

        while (running) {

            showMenu();

            String input = UI.userInputString();

            switch (input) {

                case "1" -> {
                    OrderScreen orderScreen =
                            new OrderScreen();

                    orderScreen.start();
                }

                case "0" -> {
                    Display.showSuccess(
                            "Goodbye!"
                    );

                    running = false;
                }

                default -> Display.showError(
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
}