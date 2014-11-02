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

import static freeWarOnTerror.Game.startGame;
import static freeWarOnTerror.helpers.InputLoop.inputLoop;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public class Menu {
    
    public static void mainMenu(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("New game");
        strings.add("Exit");
        int i = inputLoop("free War on Terror", strings);
        if (i == 1){
            chooseScenario();
        }
        else {
            System.exit(0);
        }
    }
    
    public static void chooseScenario(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Let's Roll!");
        strings.add("Exit");
        int i = inputLoop("Choose a scenario", strings);
        if (i == 1){
            startGame(1);
        }
        else {
            System.exit(0);
        }
    }
    
}
