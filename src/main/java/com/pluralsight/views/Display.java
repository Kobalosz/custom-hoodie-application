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

        System.out.println();

        System.out.println(
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

        System.out.println();
    }


    // ───────────────────────────────────────────────
    // Menus
    // ───────────────────────────────────────────────

    public static void showHomeMenu() {

        System.out.println(DIVIDER);

        System.out.println(
                Colors.bold(
                        "  MAIN MENU",
                        Colors.YELLOW
                )
        );

        System.out.println(DIVIDER);

        menuOption("1", "Build Hoodie", Colors.CYAN);
        menuOption("2", "View Current Hoodie", Colors.GREEN);
        menuOption("3", "Save Hoodie", Colors.YELLOW);
        menuOption("X", "Exit", Colors.RED);

        System.out.println(THIN_DIV);

        promptArrow();
    }

    public static void showDesignMenu() {

        System.out.println(DIVIDER);

        System.out.println(
                Colors.bold(
                        "  DESIGN MENU",
                        Colors.YELLOW
                )
        );

        System.out.println(DIVIDER);

        menuOption("1", "Add Design", Colors.CYAN);
        menuOption("2", "Remove Design", Colors.RED);
        menuOption("H", "Back", Colors.YELLOW);

        System.out.println(THIN_DIV);

        promptArrow();
    }


    // ───────────────────────────────────────────────
    // Enum display helper
    // ───────────────────────────────────────────────

    public static <T extends Enum<T>>
    void showEnumOptions(Class<T> enumClass) {

        System.out.println(DIVIDER);

        System.out.println(
                Colors.bold(
                        "  AVAILABLE OPTIONS",
                        Colors.YELLOW
                )
        );

        System.out.println(DIVIDER);

        int index = 1;

        for (T value : enumClass.getEnumConstants()) {

            System.out.printf(
                    "%s  [%d]%s %s%n",
                    Colors.CYAN,
                    index++,
                    Colors.WHITE,
                    formatEnum(value.name())
            );
        }

        System.out.println(THIN_DIV);

        promptArrow();
    }


    // ───────────────────────────────────────────────
    // Hoodie summary
    // ───────────────────────────────────────────────

    public static void showHoodieSummary(
            HoodieDTO hoodie
    ) {

        System.out.println();

        System.out.println(DIVIDER);

        System.out.println(
                Colors.bold(
                        "  HOODIE SUMMARY",
                        Colors.YELLOW
                )
        );

        System.out.println(DIVIDER);

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

        System.out.println();

        System.out.println(
                Colors.bold(
                        "  Designs",
                        Colors.YELLOW
                )
        );

        System.out.println(THIN_DIV);

        if (hoodie.designs().isEmpty()) {

            System.out.println(
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

                System.out.print(
                        Colors.RESET
                );
            }
        }

        System.out.println(THIN_DIV);

        System.out.printf(
                "%s  TOTAL: $%.2f%s%n",
                Colors.BOLD + Colors.BRIGHT_GREEN,
                hoodie.price(),
                Colors.RESET
        );

        System.out.println(DIVIDER);

        System.out.println();
    }


    // ───────────────────────────────────────────────
    // Prompts
    // ───────────────────────────────────────────────

    public static void prompt(
            String label
    ) {

        System.out.print(
                Colors.CYAN
                        + "  "
                        + label
                        + ": "
                        + Colors.RESET
        );
    }

    public static void promptArrow() {

        System.out.print(
                Colors.BOLD
                        + Colors.YELLOW
                        + "  > "
                        + Colors.RESET
        );
    }


    // ───────────────────────────────────────────────
    // Feedback
    // ───────────────────────────────────────────────

    public static void showSuccess(
            String message
    ) {

        System.out.println();
        System.out.println(
                "  "
                        + Colors.success(
                        message
                )
        );
        System.out.println();
    }

    public static void showError(
            String message
    ) {

        System.out.println();
        System.out.println(
                "  "
                        + Colors.error(
                        message
                )
        );
        System.out.println();
    }


    // ───────────────────────────────────────────────
    // Private helpers
    // ───────────────────────────────────────────────

    private static void menuOption(
            String key,
            String label,
            String color
    ) {

        System.out.println(
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

        System.out.println(DIVIDER);

        System.out.println(
                Colors.bold(
                        "  ORDER MENU",
                        Colors.YELLOW
                )
        );

        System.out.println(DIVIDER);

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

        System.out.println(THIN_DIV);

        promptArrow();
    }
}