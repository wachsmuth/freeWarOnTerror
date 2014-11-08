/*
 * Copyright (C) 2014 Emil
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package freeWarOnTerror;

import static freeWarOnTerror.Game.setDecksLeft;
import static freeWarOnTerror.Game.startGame;
import static freeWarOnTerror.Options.getVariants;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;

/**
 *
 * @author Emil
 */
public class Menu {

    public static void mainMenu() {
        int i = inputLoop("free War on Terror", "New game", "Exit");
        if (i == 1) {
            chooseScenario();
        } else {
            System.exit(0);
        }
    }

    public static void chooseScenario() {
        int i = inputLoop("Choose a scenario", "Let's Roll!", "You Can Call Me Al", "Choose number of reshuffles", "Other options", "Exit");
        if (i < 3) {
            startGame(i - 1);
        } else if (i == 3) {
            chooseReshuffles();
        } else if (i == 4)  {
            chooseVariants();
        } else {
            System.exit(0);
        }
    }

    public static void chooseReshuffles() {
        int i = inputLoop("Choose number of reshuffles", "None", "One", "Two");
        if (i == 1) {
            setDecksLeft(0);
        } else if (i == 2) {
            setDecksLeft(1);
        } else {
            setDecksLeft(2);
        }
        chooseScenario();

    }

    public static void chooseVariants() {
        System.out.println("Enable/disable a variant: ");
        inputLoop(getVariants()).invertEnabled();
        chooseScenario();
    }

}
