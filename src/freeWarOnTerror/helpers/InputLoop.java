/*
 * Copyright (C) 2014 Wengel
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
package freeWarOnTerror.helpers;

import freeWarOnTerror.Game;
import static freeWarOnTerror.Game.getCountry;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.abClasses.Country;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Wengel
 */
public class InputLoop {

    public static int inputLoop(int... args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Valid input is: " + Arrays.toString(args));
        System.out.println("Other valid comamnds: status");
        int userInput;
        inputloop:
        while (true) {
            try {
                System.out.print("Enter your input: ");
                String userInputRaw = inputScanner.nextLine();
                if (extendedCommands(userInputRaw)) {
                    continue;
                }
                userInput = Integer.parseInt(userInputRaw);
                //Check for extended commands
                for (int i : args) {
                    if (userInput == i) {
                        break inputloop;
                    }
                }
                System.out.println("Not on the list of permitted input."); //If wrong number
            } catch (NumberFormatException e) {
                System.out.println("Input is not of correct type"); //this prints if 
                //Do nothing. We keep trying.
            }
        }
        return userInput;
    }

    public static int inputLoop(String question, String... options) {
        String answerString;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(question);
        System.out.println("Valid input is: ");
        int j = 0;
        int[] noOfOptions = new int[options.length];
        for (String s : options) {
            noOfOptions[j] = j + 1;
            j++;
            System.out.println(j + ": " + s);
        }
        int userInput;
        inputloop:
        while (true) {
            try {
                System.out.print("Enter your input: ");
                String userInputRaw = inputScanner.nextLine();
                if (extendedCommands(userInputRaw)) {
                    continue;
                }
                userInput = Integer.parseInt(userInputRaw);
                for (int i : noOfOptions) {
                    if (userInput == i) {
                        // answerString = options.get(i);
                        break inputloop;
                    }
                }
                System.out.println("Not on the list of permitted input."); //If wrong number
            } catch (NumberFormatException e) {
                System.out.println("Input is not of correct type"); //this prints if 
                //Do nothing. We keep trying.
            }
        }
        return userInput;
    }

    private static boolean extendedCommands(String userInput) {
        if (Game.getTurnNumber() == 0) {
            return false; //Game didn't start yet
        }
        if (userInput.equals("status")) {
            Game.printStatus();
            return true;
        }
        return false;
    }

    public static Country inputLoop(String question, List<? extends Country> countriesArray) {
        String[] countries = new String[countriesArray.size()];
        int i = 0;
        for (Country c : countriesArray) {
            countries[i] = c.getName();
            i++;
        }
        return countriesArray.get(inputLoop(question, countries) - 1);
    }

    public static <T> T inputLoop(List<T> stuff) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Valid input is: ");
        for (int i = 0; i < stuff.size(); i++) {
            System.out.println(i + " " + stuff.get(i));
        }
        int userInput;
        inputloop:
        while (true) {
            try {
                System.out.print("Enter your input: ");
                String userInputRaw = inputScanner.nextLine();
                userInput = Integer.parseInt(userInputRaw);
                //Check for extended commands
                if (userInput < stuff.size() && userInput >= 0) {
                    return stuff.get(userInput);
                }
                System.out.println("Not on the list of permitted input."); //If wrong number
            } catch (NumberFormatException e) {
                System.out.println("Input is not of correct type"); //this prints if 
                //Do nothing. We keep trying.
            }
        }
    }
    
    public static void setCountryPosture(CountryLookup country){
        setCountryPosture(getCountry(country));
    }
    
    public static void setCountryPosture(Country country){
        int input = inputLoop("Select " + country.getName() + "'s posture", "hard", "soft");
        NonMuslimCountry c = (NonMuslimCountry) country;
        if (input == 1){
            c.setPosture(1);
        }
        else {
            c.setPosture(-1);
        }
    }
}
