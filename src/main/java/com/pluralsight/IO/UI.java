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


    public static <T extends Enum<T>>
    T selectEnumWithConfirmation(
            Class<T> enumClass
    ) {

        while (true) {

            Display.showEnumOptions(
                    enumClass
            );

            T selection =
                    userInputEnum(
                            enumClass
                    );

            boolean confirmed =
                    confirmChoice(
                            formatEnum(
                                    selection.name()
                            )
                    );

            if (confirmed) {
                return selection;
            }
        }
    }


    private static String formatEnum(
            String text
    ) {

        return text
                .toLowerCase()
                .replace("_", " ");
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

    public static boolean confirmChoice(
            String selection
    ) {

        while (true) {

            System.out.println();
            System.out.println(
                    "Selected: "
                            + selection
            );

            System.out.println(
                    "1 - Confirm"
            );

            System.out.println(
                    "0 - Re-select"
            );

            Display.promptArrow();

            String input =
                    userInputString();

            switch (input) {

                case "1" -> {
                    return true;
                }

                case "0" -> {
                    return false;
                }

                default ->
                        Display.showError(
                                "Invalid option."
                        );
            }
        }
    }
}