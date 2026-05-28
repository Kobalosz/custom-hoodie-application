package com.pluralsight.views;

import com.pluralsight.DTOs.HoodieDTO;
import com.pluralsight.IO.UI;
import com.pluralsight.enumerations.*;
import com.pluralsight.models.Hoodie;
import com.pluralsight.models.Order;

import java.util.ArrayList;

import static com.pluralsight.views.Display.showHoodieBuilderMenu;

public class OrderScreen {
    private final Order order = new Order();

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

        Display.showEnumOptions(
                Type.class
        );

        Type type =
                UI.userInputEnum(
                        Type.class
                );

        Display.showEnumOptions(
                Size.class
        );

        Size size =
                UI.userInputEnum(
                        Size.class
                );

        Display.showEnumOptions(
                Material.class
        );

        Material material =
                UI.userInputEnum(
                        Material.class
                );

        Hoodie hoodie =
                new Hoodie(
                        type,
                        size,
                        material,
                        new ArrayList<>()
                );

        boolean editing = true;

        while (editing) {

            Display.showHoodieSummary(
                    hoodie.getInfo()
            );

            showHoodieBuilderMenu();

            String input =
                    UI.userInputString();

            switch (input) {

                case "1" -> addDesign(
                        hoodie
                );

                case "2" -> removeDesign(
                        hoodie
                );

                case "3" -> {

                    Display.showHoodieSummary(
                            hoodie.getInfo()
                    );
                }

                case "4" -> {

                    confirmHoodie(
                            hoodie
                    );

                    editing = false;
                }

                case "0" -> {

                    Display.showError(
                            "Cancelled hoodie."
                    );

                    editing = false;
                }

                default ->
                        Display.showError(
                                "Invalid option."
                        );
            }
        }

    }

    private void addDesign(
            Hoodie hoodie
    ) {

        try {

            Display.showEnumOptions(
                    Design.class
            );

            Design design =
                    UI.userInputEnum(
                            Design.class
                    );

            Display.showEnumOptions(
                    DesignLocation.class
            );

            DesignLocation location =
                    UI.userInputEnum(
                            DesignLocation.class
                    );

            hoodie.addDesign(
                    design,
                    location
            );

            Display.showSuccess(
                    "Design added."
            );

        } catch (
                IllegalArgumentException e
        ) {

            Display.showError(
                    e.getMessage()
            );
        }
    }

    private void removeDesign(
            Hoodie hoodie
    ) {

        Display.showEnumOptions(
                Design.class
        );

        Design design =
                UI.userInputEnum(
                        Design.class
                );

        Display.showEnumOptions(
                DesignLocation.class
        );

        DesignLocation location =
                UI.userInputEnum(
                        DesignLocation.class
                );

        boolean removed =
                hoodie.removeDesign(
                        design,
                        location
                );

        if (removed) {

            Display.showSuccess(
                    "Design removed."
            );

        } else {

            Display.showError(
                    "Design not found."
            );
        }
    }

    //Here I'm just creating the DTO for use in the Receipt when I actually build it
    private void confirmHoodie(
            Hoodie hoodie
    ) {

        HoodieDTO dto =
                hoodie.getInfo();

        order.addItem(
                hoodie
        );

        // TODO:
        // receiptItems.add(dto);

        Display.showSuccess(
                "Hoodie added to order!"
        );
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