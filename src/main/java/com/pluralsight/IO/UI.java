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

        while (true) {

            String input = scanner.nextLine()
                    .strip()
                    .toUpperCase();

            try {
                return Enum.valueOf(
                        enumClass,
                        input
                );

            } catch (IllegalArgumentException e) {

                Display.showError(
                        "Invalid selection."
                );

                Display.showEnumOptions(enumClass);
            }
        }
    }
}