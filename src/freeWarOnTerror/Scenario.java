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

import freeWarOnTerror.abClasses.Country;
import java.util.ArrayList;
import freeWarOnTerror.helpers.*;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDSTATES;

/**
 *
 * @author Emil
 */
public class Scenario {
    
    private final ArrayList<Country> countries;
    private final Deck deck;
    
    public Scenario(Game game){
        countries = Game.getAllCountries();
        deck = Game.getDrawPile();
        createWorld();
    }
    
    private void createWorld(){ //sample code, WIP
        //Muslim countries
        countries.add(new MuslimCountry("Somalia", CONSTANTS.SOMALIA, 1, false, false));
        countries.add(new MuslimCountry("Sudan", CONSTANTS.SUDAN, 1, true, false));
        countries.add(new MuslimCountry("Yemen", CONSTANTS.YEMEN, 1, false, true));
        countries.add(new MuslimCountry("Saudi Arabia", CONSTANTS.SAUDIARABIA, 3, true, true));
        countries.add(new MuslimCountry("Jordan", CONSTANTS.JORDAN, 1, false, false));
        countries.add(new MuslimCountry("Iraq", CONSTANTS.IRAQ, 3, true, true));
        countries.add(new MuslimCountry("Gulf States", CONSTANTS.GULFSTATES, 3, true, true));
        countries.add(new MuslimCountry("Pakistan", CONSTANTS.PAKISTAN, 2, false, true));
        countries.add(new MuslimCountry("Afghanistan", CONSTANTS.AFGHANISTAN, 1, false, true));
        countries.add(new MuslimCountry("Central Asia", CONSTANTS.CENTRALASIA, 2, false, false));
        countries.add(new MuslimCountry("Lebanon", CONSTANTS.LEBANON, 1, false, true));
        countries.add(new MuslimCountry("Turkey", CONSTANTS.TURKEY, 2, false, true));
        countries.add(new MuslimCountry("Syria", CONSTANTS.SYRIA, 2, false, false));
        countries.add(new MuslimCountry("Indonesia/Malaysia", CONSTANTS.INDONESIA, 3, true, false));
        countries.add(new MuslimCountry("Egypt", CONSTANTS.EGYPT, 3, false, false));
        countries.add(new MuslimCountry("Libya", CONSTANTS.LIBYA, 1, true, false));
        countries.add(new MuslimCountry("Algeria/Tunisia", CONSTANTS.ALGERIA, 1, true, false));
        countries.add(new MuslimCountry("Morocco", CONSTANTS.MOROCCO, 2, false, false));
        //Non-Muslim countries
        countries.add(new NonMuslimCountry("Canada", CONSTANTS.CANADA, 1, 1, false));
        countries.add(new NonMuslimCountry("United Kingdom", CONSTANTS.UNITEDKINGDOM, 1, 3, false));
        countries.add(new NonMuslimCountry("Russia", CONSTANTS.RUSSIA, 2, 2, false));
        countries.add(new NonMuslimCountry("Caucasus", CONSTANTS.CAUCASUS, 2, 2, false));
        countries.add(new NonMuslimCountry("China", CONSTANTS.CHINA, 2, 2, false));
        countries.add(new NonMuslimCountry("Thailand", CONSTANTS.THAILAND, 2, 2, false));
        countries.add(new NonMuslimCountry("Kenya/Tanzania", CONSTANTS.KENYA, 2, 2, false));
        countries.add(new NonMuslimCountry("India", CONSTANTS.INDIA, 1, 1, false));
        countries.add(new NonMuslimCountry("Serbia", CONSTANTS.SERBIA, 1, 1, false));
        //Schengen
        countries.add(new NonMuslimCountry("Scandinavia", CONSTANTS.SCANDINAVIA, 1, 1, true));
        countries.add(new NonMuslimCountry("Germany", CONSTANTS.GERMANY, 1, 1, true));
        countries.add(new NonMuslimCountry("Benelux", CONSTANTS.BENELUX, 1, 1, true));
        countries.add(new NonMuslimCountry("Italy", CONSTANTS.ITALY, 1, 1, true));
        countries.add(new NonMuslimCountry("Eastern Europe", CONSTANTS.EASTERNEUROPE, 1, 1, true));
        countries.add(new NonMuslimCountry("France", CONSTANTS.FRANCE, 1, 2, true));
        countries.add(new NonMuslimCountry("Spain", CONSTANTS.SPAIN, 1, 2, true));
        //Other
        countries.add(new CountryIran("Iran", CONSTANTS.IRAN));
        countries.add(new CountryIsrael("Israel", CONSTANTS.ISRAEL, 1));
        countries.add(new CountryPhilippines("Philippines", CONSTANTS.PHILIPPINES,2,3));
        countries.add(new CountryUSA("United States", UNITEDSTATES, 1));
//countries.add(new MuslimCountry("Yemen"))
    }
    
    public void createDeck(){ //sample code, WIP
        deck.addCard(new freeWarOnTerror.cards.Sanctions());
    }
}
