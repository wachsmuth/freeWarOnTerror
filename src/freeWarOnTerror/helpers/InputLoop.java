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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static sun.plugin2.os.windows.FLASHWINFO.size;

/**
 *
 * @author Wengel
 */
public class InputLoop {

    public static int inputLoop(int... args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Valid input is: " + Arrays.toString(args));
        int userInput;
        inputloop:
        while (true) {
            try {
                System.out.print("Enter your input: ");
                userInput = Integer.parseInt(inputScanner.nextLine());
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

    public static String inputLoop(String question, ArrayList<String> options) {
        String answerString; 
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(question);
        System.out.println("Valid input is: ");
        int j = 0;
        int[] noOfOptions = new int[options.size()];
        for (String s : options){
            noOfOptions[j] = j+1;
            j++;
            System.out.println(j + ": " + s);
        }
        int userInput;
        inputloop:
        while (true) {
            try {
                System.out.print("Enter your input: ");
                userInput = Integer.parseInt(inputScanner.nextLine());
                for (int i : noOfOptions) {
                    if (userInput == i) {
                        answerString = options.get(i);
                        break inputloop;
                    }
                }
                System.out.println("Not on the list of permitted input."); //If wrong number
            } catch (NumberFormatException e) {
                System.out.println("Input is not of correct type"); //this prints if 
                //Do nothing. We keep trying.
            }
        }
        return answerString;
    }
    
}
