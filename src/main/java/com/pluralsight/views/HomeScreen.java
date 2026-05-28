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

        System.out.println(
                "  "
                        + "1 - New Order"
        );

        System.out.println(
                "  "
                        + "0 - Exit"
        );

        System.out.println();

        Display.promptArrow();
    }
}