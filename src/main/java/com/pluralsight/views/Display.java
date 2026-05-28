package com.pluralsight.views;

import com.pluralsight.DTOs.HoodieDTO;
import com.pluralsight.models.DesignCustomization;
import com.pluralsight.util.Colors;

public class Display {

    // ───────────────────────────────────────────────
    // Shared formatting
    // ───────────────────────────────────────────────

    private static final int WIDTH = 52;

    private static final String DIVIDER =
            Colors.CYAN
                    + "  "
                    + "═".repeat(WIDTH)
                    + Colors.RESET;

    private static final String THIN_DIV =
            Colors.DIM
                    + "  "
                    + "─".repeat(WIDTH)
                    + Colors.RESET;


    // ───────────────────────────────────────────────
    // Application header
    // ───────────────────────────────────────────────

    public static void showAppHeader() {

        IO.println();

        IO.println(
                Colors.BOLD + Colors.CYAN +

                        "  ╔════════════════════════════════════════════════════╗\n" +
                        "  ║                                                    ║\n" +
                        "  ║        ██╗  ██╗ ██████╗  ██████╗ ██████╗ ██╗       ║\n" +
                        "  ║        ██║  ██║██╔═══██╗██╔═══██╗██╔══██╗██║       ║\n" +
                        "  ║        ███████║██║   ██║██║   ██║██║  ██║██║       ║\n" +
                        "  ║        ██╔══██║██║   ██║██║   ██║██║  ██║██║       ║\n" +
                        "  ║        ██║  ██║╚██████╔╝╚██████╔╝██████╔╝██║       ║\n" +
                        "  ║        ╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═════╝ ╚═╝       ║\n" +
                        "  ║                                                    ║\n" +
                        "  ║              C U S T O M   H O O D I E             ║\n" +
                        "  ║                   by IDK Tech                      ║\n" +
                        "  ║                                                    ║\n" +
                        "  ╚════════════════════════════════════════════════════╝"

                        + Colors.RESET
        );

        IO.println();
    }


    // ───────────────────────────────────────────────
    // Menus
    // ───────────────────────────────────────────────

    public static void showHomeMenu() {

        IO.println(DIVIDER);

        IO.println(
                Colors.bold(
                        "  MAIN MENU",
                        Colors.YELLOW
                )
        );

        IO.println(DIVIDER);

        menuOption("1", "Build Hoodie", Colors.CYAN);
        menuOption("2", "View Current Hoodie", Colors.GREEN);
        menuOption("3", "Save Hoodie", Colors.YELLOW);
        menuOption("X", "Exit", Colors.RED);

        IO.println(THIN_DIV);

        promptArrow();
    }

    public static void showDesignMenu() {

        IO.println(DIVIDER);

        IO.println(
                Colors.bold(
                        "  DESIGN MENU",
                        Colors.YELLOW
                )
        );

        IO.println(DIVIDER);

        menuOption("1", "Add Design", Colors.CYAN);
        menuOption("2", "Remove Design", Colors.RED);
        menuOption("H", "Back", Colors.YELLOW);

        IO.println(THIN_DIV);

        promptArrow();
    }


// helper function to make my use of enums actually work lol

    public static <T extends Enum<T>>
    void showEnumOptions(
            Class<T> enumClass
    ) {

        IO.println(DIVIDER);

        IO.println(
                Colors.bold(
                        "  AVAILABLE OPTIONS",
                        Colors.YELLOW
                )
        );

        IO.println(DIVIDER);

        T[] options =
                enumClass.getEnumConstants();

        for (int i = 0;
             i < options.length;
             i++) {

            System.out.printf(
                    "%s  [%d]%s %s%n",

                    Colors.CYAN,
                    i + 1,
                    Colors.WHITE,

                    formatEnum(
                            options[i].name()
                    )
            );
        }

        IO.println(THIN_DIV);

        promptArrow();
    }


    public static void showHoodieSummary(
            HoodieDTO hoodie
    ) {

        IO.println();

        IO.println(DIVIDER);

        IO.println(
                Colors.bold(
                        "  HOODIE SUMMARY",
                        Colors.YELLOW
                )
        );

        IO.println(DIVIDER);

        System.out.printf(
                "%s  Type:%s %s%n",
                Colors.CYAN,
                Colors.RESET,
                hoodie.type()
        );

        System.out.printf(
                "%s  Size:%s %s%n",
                Colors.CYAN,
                Colors.RESET,
                hoodie.size()
        );

        System.out.printf(
                "%s  Material:%s %s%n",
                Colors.CYAN,
                Colors.RESET,
                hoodie.material()
        );

        IO.println();

        IO.println(
                Colors.bold(
                        "  Designs",
                        Colors.YELLOW
                )
        );

        IO.println(THIN_DIV);

        if (hoodie.designs().isEmpty()) {

            IO.println(
                    Colors.DIM
                            + "    No designs added."
                            + Colors.RESET
            );

        } else {

            for (DesignCustomization design
                    : hoodie.designs()) {

                System.out.printf(
                        "%s    • %s%s (%s)%n",
                        Colors.WHITE,
                        formatEnum(
                                design.getDesign().name()
                        ),
                        Colors.CYAN,
                        formatEnum(
                                design.getLocation().name()
                        )
                );

                IO.print(
                        Colors.RESET
                );
            }
        }

        IO.println(THIN_DIV);

        System.out.printf(
                "%s  TOTAL: $%.2f%s%n",
                Colors.BOLD + Colors.BRIGHT_GREEN,
                hoodie.price(),
                Colors.RESET
        );

        IO.println(DIVIDER);

        IO.println();
    }


// some prompt functions I can use to recieve input

    public static void prompt(
            String label
    ) {

        IO.print(
                Colors.CYAN
                        + "  "
                        + label
                        + ": "
                        + Colors.RESET
        );
    }

    public static void promptArrow() {

        IO.print(
                Colors.BOLD
                        + Colors.YELLOW
                        + "  > "
                        + Colors.RESET
        );
    }


// Some response messages

    public static void showSuccess(
            String message
    ) {

        IO.println();
        IO.println(
                "  "
                        + Colors.success(
                        message
                )
        );
        IO.println();
    }

    public static void showError(
            String message
    ) {

        IO.println();
        IO.println(
                "  "
                        + Colors.error(
                        message
                )
        );
        IO.println();
    }


// Helper functions

    private static void menuOption(
            String key,
            String label,
            String color
    ) {

        IO.println(
                color
                        + "  ["
                        + key
                        + "] "
                        + Colors.WHITE
                        + label
                        + Colors.RESET
        );
    }

    private static String formatEnum(
            String value
    ) {

        return value
                .toLowerCase()
                .replace("_", " ");
    }

    public static void showOrderMenu() {

        IO.println(DIVIDER);

        IO.println(
                Colors.bold(
                        "  ORDER MENU",
                        Colors.YELLOW
                )
        );

        IO.println(DIVIDER);

        menuOption(
                "1",
                "Add Hoodie",
                Colors.CYAN
        );

        menuOption(
                "2",
                "Add Accessory (Beanie)",
                Colors.GREEN
        );

        menuOption(
                "3",
                "Add Tote Bag",
                Colors.GREEN
        );

        menuOption(
                "4",
                "Checkout",
                Colors.YELLOW
        );

        menuOption(
                "0",
                "Cancel Order",
                Colors.RED
        );

        IO.println(THIN_DIV);

        promptArrow();
    }

    public static void showHoodieBuilderMenu() {

        IO.println(DIVIDER);

        IO.println(
                Colors.bold(
                        "  HOODIE BUILDER",
                        Colors.YELLOW
                )
        );

        IO.println(DIVIDER);

        menuOption(
                "1",
                "Add Design",
                Colors.GREEN
        );

        menuOption(
                "2",
                "Remove Design",
                Colors.RED
        );

        menuOption(
                "3",
                "Preview Hoodie",
                Colors.CYAN
        );

        menuOption(
                "4",
                "Confirm Hoodie",
                Colors.YELLOW
        );

        menuOption(
                "0",
                "Cancel",
                Colors.RED
        );

        IO.println(THIN_DIV);

        promptArrow();
    }


//    Here I'm creating that cool loading bar inspired by Nathan's project
//    I think he did it by having his method take a number of stops and an amount of time, then printing a "bar" after that delay has passed
//    Thankfully after playing around with threads this shouldn't be too crazy

    public static void showLoadingSequence(
            String title,
            String... tasks
    ) {

        IO.println();

        IO.println(
                Colors.bold(
                        "  " + title,
                        Colors.CYAN
                )
        );

        IO.println();

        int totalSteps =
                tasks.length;

        for (int i = 0;
             i < totalSteps;
             i++) {

            String task =
                    tasks[i];

            int percent =
                    ((i + 1) * 100)
                            / totalSteps;

            String bar =
                    "█".repeat(i + 1)
                            + "░".repeat(
                            totalSteps
                                    - (i + 1)
                    );

            IO.print(
                    "\r"
                            + Colors.CYAN
                            + "  "
                            + task
                            + " ["
                            + bar
                            + "] "
                            + percent
                            + "%"
                            + Colors.RESET
            );

            try {

                Thread.sleep(
                        3000
                );

            } catch (
                    InterruptedException e
            ) {

                Thread.currentThread()
                        .interrupt();
            }
        }

        IO.println();
        IO.println();
    }


}