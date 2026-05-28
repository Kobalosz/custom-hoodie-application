package com.pluralsight.views;

import com.pluralsight.DTOs.HoodieDTO;
import com.pluralsight.IO.UI;
import com.pluralsight.enumerations.*;
import com.pluralsight.models.Beanie;
import com.pluralsight.models.Hoodie;
import com.pluralsight.models.Order;
import com.pluralsight.models.ToteBag;
import com.pluralsight.storage.ReceiptWriter;

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

        Type type =
                UI.selectEnumWithConfirmation(
                        Type.class
                );


        Size size =
                UI.selectEnumWithConfirmation(
                        Size.class
                );

        Material material =
                UI.selectEnumWithConfirmation(
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
                    UI.selectEnumWithConfirmation(
                            Design.class
                    );

            Display.showEnumOptions(
                    DesignLocation.class
            );

            DesignLocation location =
                    UI.selectEnumWithConfirmation(
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
                UI.selectEnumWithConfirmation(
                        Design.class
                );

        Display.showEnumOptions(
                DesignLocation.class
        );

        DesignLocation location =
                UI.selectEnumWithConfirmation(
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
        if(hoodie == null){
            Display.showError("Something went wrong!");
            return;
        }

        order.addItem(hoodie);

        Display.showHoodieSummary(hoodie.getInfo());

        Display.showSuccess("Your hoodie has been added to the order!");
    }

    private void addBeanie() {

        Material material =
                UI.selectEnumWithConfirmation(
                        Material.class
                );

        Beanie beanie =
                new Beanie(material);

        System.out.println();
        System.out.println(
                "1 - Add Design"
        );
        System.out.println(
                "0 - Skip"
        );

        Display.promptArrow();

        String input =
                UI.userInputString();

        if (input.equals("1")) {

            Design design =
                    UI.selectEnumWithConfirmation(
                            Design.class
                    );

            beanie.addDesign(
                    design
            );
        }

        Display.showSuccess(
                "Beanie Preview"
        );

        System.out.println(
                beanie.getDescription()
        );

        System.out.printf(
                "$%.2f%n",
                beanie.getPrice()
        );

        if (
                UI.confirmChoice(
                        "Add beanie to order?"
                )
        ) {

            order.addItem(
                    beanie
            );

            Display.showSuccess(
                    "Beanie added!"
            );
        }
    }

    private void addToteBag() {

        ToteBag toteBag =
                new ToteBag();

        System.out.println(
                toteBag.getDescription()
        );

        System.out.printf(
                "$%.2f%n",
                toteBag.getPrice()
        );

        if (
                UI.confirmChoice(
                        "Add tote bag to order?"
                )
        ) {

            order.addItem(
                    toteBag
            );

            Display.showSuccess(
                    "Tote bag added!"
            );
        }
    }

    private void checkout() {

        Receipt receipt =
                Receipt.fromOrder(
                        order
                );

        ReceiptWriter.writeReceipt(
                receipt
        );

        System.out.println(
                receipt.getFormattedReceipt()
        );

        Display.showSuccess(
                "Receipt saved!"
        );
    }
}