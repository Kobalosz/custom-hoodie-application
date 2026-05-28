package com.pluralsight.IO;

import com.pluralsight.views.Display;

import java.util.Scanner;

public class UI {

    private static final Scanner scanner =
            new Scanner(System.in);

    public static String userInputString() {
        return scanner.nextLine()
                .strip()
                .toLowerCase();
    }

    public static double userInputDouble() {

        try {
            return Double.parseDouble(
                    scanner.nextLine().strip()
            );

        } catch (NumberFormatException e) {

            Display.showError(
                    "Invalid response, enter a number."
            );

            return userInputDouble();
        }
    }

    public static int userInputInt() {

        try {
            return Integer.parseInt(
                    scanner.nextLine().strip()
            );

        } catch (NumberFormatException e) {

            Display.showError(
                    "Invalid response, enter a number."
            );

            return userInputInt();
        }
    }

    public static <T extends Enum<T>>
    T userInputEnum(Class<T> enumClass) {

        T[] options =
                enumClass.getEnumConstants();

        while (true) {

            try {

                int choice =
                        Integer.parseInt(
                                scanner.nextLine()
                                        .strip()
                        );

                if (choice >= 1
                        && choice <= options.length) {

                    return options[
                            choice - 1
                            ];
                }

                Display.showError(
                        "Please select a valid option."
                );

            } catch (
                    NumberFormatException e
            ) {

                Display.showError(
                        "Please enter a number."
                );
            }
        }
    }
}