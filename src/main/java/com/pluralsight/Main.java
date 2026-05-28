package com.pluralsight;

import com.pluralsight.views.Display;
import com.pluralsight.views.HomeScreen;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        Display.showLoadingSequence("Starting Application",
                "Loading assets...",
                "Kickstarting customization engine...",
                "Fun Fact: Contrary to popular belief, Imanuel is in fact super rad!",
                "Launching Storefront..."
                );
        HomeScreen app = new HomeScreen();
        app.start();
    }
}
