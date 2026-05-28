package com.pluralsight.views;

import com.pluralsight.DTOs.HoodieDTO;
import com.pluralsight.IO.UI;
import com.pluralsight.abstracts.OrderItem;
import com.pluralsight.enumerations.*;
import com.pluralsight.models.*;
import com.pluralsight.storage.ReceiptWriter;

import java.util.ArrayList;
import java.util.List;

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

                case "4" -> viewOrder();

                case "5" -> removeItem();

                case "6" -> clearOrder();

                case "7" -> {

                    checkout();

                    ordering = false;
                }

                case "0" -> {

                    Display.showSuccess(
                            "Order cancelled."
                    );

                    ordering = false;
                }

                default ->
                        Display.showError(
                                "Invalid option."
                        );
            }
        }
    }

    private void addHoodie() {

        boolean choosing =
                true;

        while (choosing) {

            Display.showHoodieChoiceMenu();

            String input =
                    UI.userInputString();

            switch (input) {

                case "1" -> {

                    addPresetHoodie();
                    choosing = false;
                }

                case "2" -> {

                    launchCustomHoodieBuilder();
                    choosing = false;
                }

                case "0" -> choosing = false;

                default ->
                        Display.showError(
                                "Invalid option."
                        );
            }
        }
    }

    private void launchCustomHoodieBuilder(){
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

    // Preset hoodies

    private void addPresetHoodie() {

        Display.showPresetHoodieMenu();

        String input =
                UI.userInputString();

        Hoodie hoodie =
                switch (input) {

                    case "1" ->
                            HoodiePresets
                                    .classicPullover();

                    case "2" ->
                            HoodiePresets
                                    .premiumStreetwear();

                    case "3" ->
                            HoodiePresets
                                    .winterHeavyweight();

                    case "4" ->
                            HoodiePresets
                                    .luxuryEdition();

                    default -> null;
                };

        if (hoodie == null) {

            Display.showError(
                    "Invalid selection."
            );

            return;
        }

        Display.showHoodieSummary(
                hoodie.getInfo()
        );

        if (
                UI.confirmChoice(
                        "Add hoodie to order?"
                )
        ) {

            confirmHoodie(
                    hoodie
            );
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

        IO.println();
        IO.println(
                "1 - Add Design"
        );
        IO.println(
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

        IO.println(
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

        IO.println(
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

    //

    private void viewOrder() {

        System.out.println();

        System.out.println(
                order.getDescription()
        );

        System.out.println();
    }

    private void removeItem() {

        if (order.isEmpty()) {

            Display.showError(
                    "Nothing to remove."
            );

            return;
        }

        List<OrderItem> items =
                order.getItems();

        System.out.println();
        System.out.println(
                "REMOVE ITEM"
        );
        System.out.println();

        for (int i = 0;
             i < items.size();
             i++) {

            OrderItem item =
                    items.get(i);

            System.out.printf(
                    "%d - %s ($%.2f)%n",

                    i + 1,

                    item.getDescription(),

                    item.getPrice()
            );
        }

        System.out.println();
        System.out.println(
                "0 - Cancel"
        );

        String input =
                UI.userInputString();

        try {

            int choice =
                    Integer.parseInt(
                            input
                    );

            if (choice == 0) {
                return;
            }

            OrderItem selected =
                    items.get(
                            choice - 1
                    );

            order.removeItem(
                    selected
            );

            Display.showSuccess(
                    "Item removed."
            );

        } catch (
                Exception e
        ) {

            Display.showError(
                    "Invalid selection."
            );
        }
    }

    private void clearOrder() {

        if (order.isEmpty()) {

            Display.showError(
                    "Order already empty."
            );

            return;
        }

        boolean confirmed =
                UI.confirmChoice(
                        "Clear order?"
                );

        if (!confirmed) {
            return;
        }

        order.clear();

        Display.showSuccess(
                "Order cleared."
        );
    }

    private void checkout() {

        if (order.isEmpty()) {

            Display.showError(
                    "Why'd you come here if you can't afford anything brokie? This isn't a charity."
            );

            return;
        }

        Display.showLoadingSequence(
                "CHECKOUT",
                "Calculating totals...",
                "Bundling items...",
                "Printing receipt..."
        );

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