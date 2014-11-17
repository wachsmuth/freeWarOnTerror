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
package freeWarOnTerror.abClasses;

import freeWarOnTerror.Countries.CountryIndia;
import freeWarOnTerror.Countries.CountryIran;
import freeWarOnTerror.Countries.CountryIsrael;
import freeWarOnTerror.Countries.CountryPakistan;
import freeWarOnTerror.Countries.CountryPhilippines;
import freeWarOnTerror.Countries.CountryUSA;
import freeWarOnTerror.Deck;
import freeWarOnTerror.Game;
import static freeWarOnTerror.Game.getAllCountries;
import static freeWarOnTerror.Game.getCountry;
import static freeWarOnTerror.Game.getDrawPile;
import freeWarOnTerror.MuslimCountry;
import freeWarOnTerror.NonMuslimCountry;
import freeWarOnTerror.cards.Abbas;
import freeWarOnTerror.cards.AbuSayyaf;
import freeWarOnTerror.cards.AlAnbar;
import freeWarOnTerror.cards.AlAzhar;
import freeWarOnTerror.cards.AlIttihadAlIslami;
import freeWarOnTerror.cards.AlJazeera;
import freeWarOnTerror.cards.Amerithrax;
import freeWarOnTerror.cards.AnbarAwakening;
import freeWarOnTerror.cards.AnsarAlIslam;
import freeWarOnTerror.cards.AxisOfEvil;
import freeWarOnTerror.cards.Backlash;
import freeWarOnTerror.cards.BenazirBhutto;
import freeWarOnTerror.cards.BhuttoShot;
import freeWarOnTerror.cards.BinLadin;
import freeWarOnTerror.cards.CTR;
import freeWarOnTerror.cards.CovertAction;
import freeWarOnTerror.cards.Darfur;
import freeWarOnTerror.cards.EthiopiaStrikes;
import freeWarOnTerror.cards.EuroIslam;
import freeWarOnTerror.cards.ExKGB;
import freeWarOnTerror.cards.FATA;
import freeWarOnTerror.cards.FREs;
import freeWarOnTerror.cards.ForeignFighters;
import freeWarOnTerror.cards.FormerSovietUnion;
import freeWarOnTerror.cards.GTMO;
import freeWarOnTerror.cards.GazaWar;
import freeWarOnTerror.cards.GazaWithdrawal;
import freeWarOnTerror.cards.HaririKilled;
import freeWarOnTerror.cards.Hijab;
import freeWarOnTerror.cards.HizbUtTahrir;
import freeWarOnTerror.cards.Homegrown;
import freeWarOnTerror.cards.IEDs;
import freeWarOnTerror.cards.IndoPakistaniTalks;
import freeWarOnTerror.cards.JemaahIslamiya;
import freeWarOnTerror.cards.Kashmir;
import freeWarOnTerror.cards.KemalistRepublic;
import freeWarOnTerror.cards.KingAbdullah;
import freeWarOnTerror.cards.Kosovo;
import freeWarOnTerror.cards.LebanonWar;
import freeWarOnTerror.cards.MartyrdomOperation;
import freeWarOnTerror.cards.MassTurnout;
import freeWarOnTerror.cards.MoroTalks;
import freeWarOnTerror.cards.MossadAndShinBet;
import freeWarOnTerror.cards.Musharraf;
import freeWarOnTerror.cards.NEST;
import freeWarOnTerror.cards.OilPriceSpike;
import freeWarOnTerror.cards.Opium;
import freeWarOnTerror.cards.PakistaniOffensive;
import freeWarOnTerror.cards.PatriotAct;
import freeWarOnTerror.cards.Pirates;
import freeWarOnTerror.cards.Predator;
import freeWarOnTerror.cards.Quartet;
import freeWarOnTerror.cards.RegionalAlQaeda;
import freeWarOnTerror.cards.Saddam;
import freeWarOnTerror.cards.SaddamCaptured;
import freeWarOnTerror.cards.Saleh;
import freeWarOnTerror.cards.Sanctions;
import freeWarOnTerror.cards.SchroederAndChirac;
import freeWarOnTerror.cards.Sharia;
import freeWarOnTerror.cards.Sistani;
import freeWarOnTerror.cards.SpecialForces;
import freeWarOnTerror.cards.Taliban;
import freeWarOnTerror.cards.TonyBlair;
import freeWarOnTerror.cards.USElection;
import freeWarOnTerror.cards.UyghurJihad;
import freeWarOnTerror.cards.VieiradeMelloSlain;
import freeWarOnTerror.cards.Wahhabism;
import freeWarOnTerror.cards.Wiretapping;
import freeWarOnTerror.cards.Zawahiri;
import freeWarOnTerror.helpers.CountryLookup;
import java.util.ArrayList;

/**
 *
 * @author Emil
 */
public abstract class Scenario {

    private final ArrayList<Country> countries;
    private final Deck deck;
    private final ArrayList<Country> schengenCountries;
    private final ArrayList<Country> schengenBorderCountries;
    private final String name;

    public Scenario(String name) {
        this.name = name;
        countries = getAllCountries();
        deck = getDrawPile();
        schengenCountries = new ArrayList<>();
        schengenBorderCountries = new ArrayList<>();
    }

    public void create() {
        createWorld();
        createLists();
        createConnections();
        createDeck();
    }

    public String getName() {
        return name;
    }

    protected void createWorld() {
        for (CountryLookup c : CountryLookup.values()) {
            //Special countries
            switch (c) {
                case UNITEDSTATES:
                    countries.add(new CountryUSA(c));
                    break;
                case IRAN:
                    countries.add(new CountryIran(c));
                    break;
                case PHILIPPINES:
                    countries.add(new CountryPhilippines(c));
                    break;
                case ISRAEL:
                    countries.add(new CountryIsrael(c));
                    break;
                case INDIA:
                    countries.add(new CountryIndia(c));
                    break;
                case PAKISTAN:
                    countries.add(new CountryPakistan(c));
                    break;
            }
            
            if (c.isMuslimCountry() == true) {
                countries.add(new MuslimCountry(c));
            } else {
                if (c.isMuslimCountry() == false) {
                    countries.add(new NonMuslimCountry(c));
                }
            }
        }
//sample code, WIP
        //Muslim countries
        /*
         countries.add(new MuslimCountry("Somalia", CONSTANTS.SOMALIA, 1, false, false));
         countries.add(new MuslimCountry("Sudan", CONSTANTS.SUDAN, 1, true, false));
         countries.add(new MuslimCountry("Yemen", CONSTANTS.YEMEN, 1, false, true));
         countries.add(new MuslimCountry("Saudi Arabia", CONSTANTS.SAUDIARABIA, 3, true, true));
         countries.add(new MuslimCountry("Jordan", CONSTANTS.CountryLookup.JORDAN, 1, false, false));
         countries.add(new MuslimCountry("Iraq", CONSTANTS.CountryLookup.IRAQ, 3, true, true));
         countries.add(new MuslimCountry("Gulf States", CONSTANTS.GULFSTATES, 3, true, true));
         countries.add(new CountryPakistan("Pakistan", CONSTANTS.CountryLookup.PAKISTAN, 2, false, true));
         countries.add(new MuslimCountry("getCountry(CountryLookup.AFGHANISTAN)", CONSTANTS.getCountry(CountryLookup.AFGHANISTAN), 1, false, true));
         countries.add(new MuslimCountry("Central Asia", CONSTANTS.CountryLookup.CENTRALASIA, 2, false, false));
         countries.add(new MuslimCountry("Lebanon", CONSTANTS.CountryLookup.LEBANON, 1, false, true));
         countries.add(new MuslimCountry("Turkey", CONSTANTS.CountryLookup.TURKEY, 2, false, true));
         countries.add(new MuslimCountry("Syria", CONSTANTS.CountryLookup.SYRIA, 2, false, false));
         countries.add(new MuslimCountry("Indonesia/Malaysia", CONSTANTS.INDONESIA, 3, true, false));
         countries.add(new MuslimCountry("getCountry(CountryLookup.EGYPT)", CONSTANTS.getCountry(CountryLookup.EGYPT), 3, false, false));
         countries.add(new MuslimCountry("Libya", CONSTANTS.LIBYA, 1, true, false));
         countries.add(new MuslimCountry("Algeria/Tunisia", CONSTANTS.ALGERIA, 1, true, false));
         countries.add(new MuslimCountry("Morocco", CONSTANTS.MOROCCO, 2, false, false));
         //Non-Muslim countries
         countries.add(new NonMuslimCountry("Canada", CONSTANTS.CountryLookup.CANADA, 1, 1, false));
         countries.add(new NonMuslimCountry("United Kingdom", CONSTANTS.CountryLookup.UNITEDKINGDOM, 1, 3, false));
         countries.add(new NonMuslimCountry("Russia", CONSTANTS.CountryLookup.RUSSIA, 2, 2, false));
         countries.add(new NonMuslimCountry("Caucasus", CONSTANTS.CountryLookup.CAUCASUS, 2, 2, false));
         countries.add(new NonMuslimCountry("China", CONSTANTS.CHINA, 2, 2, false));
         countries.add(new NonMuslimCountry("Thailand", CONSTANTS.THAILAND, 2, 2, false));
         countries.add(new NonMuslimCountry("Kenya/Tanzania", CONSTANTS.KENYA, 2, 2, false));
         //countries.add(new CountryIndia("India", CONSTANTS.INDIA, 1, 1, false));
         countries.add(new CountryIndia(CountryLookup.INDIA));
         countries.add(new NonMuslimCountry("Serbia", CONSTANTS.CountryLookup.SERBIA, 1, 1, false));
         //Schengen
         countries.add(new NonMuslimCountry("Scandinavia", CONSTANTS.SCANDINAVIA, 1, 1, true));
         countries.add(new NonMuslimCountry("Germany", CONSTANTS.GERMANY, 1, 1, true));
         countries.add(new NonMuslimCountry("Benelux", CONSTANTS.BENELUX, 1, 1, true));
         countries.add(new NonMuslimCountry("Italy", CONSTANTS.ITALY, 1, 1, true));
         countries.add(new NonMuslimCountry("Eastern Europe", CONSTANTS.EASTERNEUROPE, 1, 1, true));
         countries.add(new NonMuslimCountry("France", CONSTANTS.FRANCE, 1, 2, true));
         countries.add(new NonMuslimCountry("Spain", CONSTANTS.SPAIN, 1, 2, true));
         //Other
         countries.add(new CountryIran("Iran", CONSTANTS.CountryLookup.IRAN));
         countries.add(new CountryIsrael("Israel", CONSTANTS.CountryLookup.ISRAEL, 1));
         countries.add(new CountryPhilippines("Philippines", CONSTANTS.PHILIPPINES, 2, 3));
         countries.add(new CountryUSA("United States", CountryLookup.UNITEDSTATES, 1));
         */
    }

    protected void createLists() {
        for (Country c : countries) {
            if (c instanceof NonMuslimCountry) {
                NonMuslimCountry x = (NonMuslimCountry) c;
                if (x.getSchengen()) {
                    schengenCountries.add(x);
                }
            }
        }

        for (Country c : schengenCountries) {
            Game.getSchengenCountries().add((NonMuslimCountry) c);
        }
        schengenBorderCountries.add(getCountry(CountryLookup.CANADA));
        schengenBorderCountries.add(getCountry(CountryLookup.UNITEDKINGDOM));
        schengenBorderCountries.add(getCountry(CountryLookup.UNITEDSTATES));
        schengenBorderCountries.add(getCountry(CountryLookup.SERBIA));
        schengenBorderCountries.add(getCountry(CountryLookup.RUSSIA));
        schengenBorderCountries.add(getCountry(CountryLookup.TURKEY));
        schengenBorderCountries.add(getCountry(CountryLookup.LEBANON));
        schengenBorderCountries.add(getCountry(CountryLookup.LIBYA));
        schengenBorderCountries.add(getCountry(CountryLookup.ALGERIA));
        schengenBorderCountries.add(getCountry(CountryLookup.MOROCCO));

    }

    protected void createConnections() {
        //United States
        getCountry(CountryLookup.UNITEDSTATES).addAdjacentCountry(getCountry(CountryLookup.CANADA));
        getCountry(CountryLookup.UNITEDSTATES).addAdjacentCountry(getCountry(CountryLookup.PHILIPPINES));
        getCountry(CountryLookup.UNITEDSTATES).addAdjacentCountry(getCountry(CountryLookup.UNITEDKINGDOM));
        getCountry(CountryLookup.UNITEDSTATES).addAdjacentCountries(schengenCountries);
        //Canada
        getCountry(CountryLookup.CANADA).addAdjacentCountries(schengenCountries);
        getCountry(CountryLookup.CANADA).addAdjacentCountry(getCountry(CountryLookup.UNITEDKINGDOM));
        getCountry(CountryLookup.CANADA).addAdjacentCountry(getCountry(CountryLookup.UNITEDSTATES));
        //United Kingdom
        getCountry(CountryLookup.UNITEDKINGDOM).addAdjacentCountry(getCountry(CountryLookup.UNITEDSTATES));
        getCountry(CountryLookup.UNITEDKINGDOM).addAdjacentCountry(getCountry(CountryLookup.CANADA));
        getCountry(CountryLookup.UNITEDKINGDOM).addAdjacentCountries(schengenCountries);
        //All Schengen countries
        for (Country c : schengenCountries) {
            c.addAdjacentCountries(schengenCountries);
            c.addAdjacentCountries(schengenBorderCountries);
        }
        //Russia
        getCountry(CountryLookup.RUSSIA).addAdjacentCountries(schengenCountries);
        getCountry(CountryLookup.RUSSIA).addAdjacentCountry(getCountry(CountryLookup.TURKEY));
        getCountry(CountryLookup.RUSSIA).addAdjacentCountry(getCountry(CountryLookup.SERBIA));
        getCountry(CountryLookup.RUSSIA).addAdjacentCountry(getCountry(CountryLookup.CAUCASUS));
        getCountry(CountryLookup.RUSSIA).addAdjacentCountry(getCountry(CountryLookup.CENTRALASIA));
        //Serbia
        getCountry(CountryLookup.SERBIA).addAdjacentCountry(getCountry(CountryLookup.TURKEY));
        getCountry(CountryLookup.SERBIA).addAdjacentCountry(getCountry(CountryLookup.RUSSIA));
        getCountry(CountryLookup.SERBIA).addAdjacentCountries(schengenCountries);
        //Turkey
        getCountry(CountryLookup.TURKEY).addAdjacentCountry(getCountry(CountryLookup.SERBIA));
        getCountry(CountryLookup.TURKEY).addAdjacentCountry(getCountry(CountryLookup.RUSSIA));
        getCountry(CountryLookup.TURKEY).addAdjacentCountries(schengenCountries);
        getCountry(CountryLookup.TURKEY).addAdjacentCountry(getCountry(CountryLookup.IRAN));
        getCountry(CountryLookup.TURKEY).addAdjacentCountry(getCountry(CountryLookup.SYRIA));
        getCountry(CountryLookup.TURKEY).addAdjacentCountry(getCountry(CountryLookup.CAUCASUS));
        //Caucasus
        getCountry(CountryLookup.CAUCASUS).addAdjacentCountry(getCountry(CountryLookup.RUSSIA));
        getCountry(CountryLookup.CAUCASUS).addAdjacentCountry(getCountry(CountryLookup.TURKEY));
        getCountry(CountryLookup.CAUCASUS).addAdjacentCountry(getCountry(CountryLookup.CENTRALASIA));
        getCountry(CountryLookup.CAUCASUS).addAdjacentCountry(getCountry(CountryLookup.IRAN));
        //Central Asia
        getCountry(CountryLookup.CENTRALASIA).addAdjacentCountry(getCountry(CountryLookup.RUSSIA));
        getCountry(CountryLookup.CENTRALASIA).addAdjacentCountry(getCountry(CountryLookup.CAUCASUS));
        getCountry(CountryLookup.CENTRALASIA).addAdjacentCountry(getCountry(CountryLookup.IRAN));
        getCountry(CountryLookup.CENTRALASIA).addAdjacentCountry((getCountry(CountryLookup.AFGHANISTAN)));
        //Syria
        getCountry(CountryLookup.SYRIA).addAdjacentCountry(getCountry(CountryLookup.TURKEY));
        getCountry(CountryLookup.SYRIA).addAdjacentCountry(getCountry(CountryLookup.LEBANON));
        getCountry(CountryLookup.SYRIA).addAdjacentCountry(getCountry(CountryLookup.IRAQ));
        getCountry(CountryLookup.SYRIA).addAdjacentCountry(getCountry(CountryLookup.JORDAN));
        //Lebanon
        getCountry(CountryLookup.LEBANON).addAdjacentCountry(getCountry(CountryLookup.ISRAEL));
        getCountry(CountryLookup.SYRIA).addAdjacentCountry(getCountry(CountryLookup.SYRIA));
        getCountry(CountryLookup.LEBANON).addAdjacentCountries(schengenCountries);
        //Iran
        getCountry(CountryLookup.IRAN).addAdjacentCountry(getCountry(CountryLookup.TURKEY));
        getCountry(CountryLookup.IRAN).addAdjacentCountry(getCountry(CountryLookup.CAUCASUS));
        getCountry(CountryLookup.IRAN).addAdjacentCountry(getCountry(CountryLookup.CENTRALASIA));
        getCountry(CountryLookup.IRAN).addAdjacentCountry(getCountry(CountryLookup.AFGHANISTAN));
        getCountry(CountryLookup.IRAN).addAdjacentCountry(getCountry(CountryLookup.PAKISTAN));
        getCountry(CountryLookup.IRAN).addAdjacentCountry(getCountry(CountryLookup.GULFSTATES));
        getCountry(CountryLookup.IRAN).addAdjacentCountry(getCountry(CountryLookup.IRAQ));
        //getCountry(CountryLookup.AFGHANISTAN)
        getCountry(CountryLookup.AFGHANISTAN).addAdjacentCountry(getCountry(CountryLookup.IRAN));
        getCountry(CountryLookup.AFGHANISTAN).addAdjacentCountry(getCountry(CountryLookup.PAKISTAN));
        getCountry(CountryLookup.AFGHANISTAN).addAdjacentCountry(getCountry(CountryLookup.CENTRALASIA));
        //Iraq
        getCountry(CountryLookup.IRAQ).addAdjacentCountry(getCountry(CountryLookup.IRAN));
        getCountry(CountryLookup.IRAQ).addAdjacentCountry(getCountry(CountryLookup.GULFSTATES));
        getCountry(CountryLookup.IRAQ).addAdjacentCountry(getCountry(CountryLookup.SAUDIARABIA));
        getCountry(CountryLookup.IRAQ).addAdjacentCountry(getCountry(CountryLookup.JORDAN));
        getCountry(CountryLookup.IRAQ).addAdjacentCountry(getCountry(CountryLookup.SYRIA));
        getCountry(CountryLookup.IRAQ).addAdjacentCountry(getCountry(CountryLookup.TURKEY));
        //Jordan
        getCountry(CountryLookup.JORDAN).addAdjacentCountry(getCountry(CountryLookup.SYRIA));
        getCountry(CountryLookup.JORDAN).addAdjacentCountry(getCountry(CountryLookup.IRAQ));
        getCountry(CountryLookup.JORDAN).addAdjacentCountry(getCountry(CountryLookup.ISRAEL));
        getCountry(CountryLookup.JORDAN).addAdjacentCountry(getCountry(CountryLookup.SAUDIARABIA));
        //Israel
        getCountry(CountryLookup.ISRAEL).addAdjacentCountry(getCountry(CountryLookup.JORDAN));
        getCountry(CountryLookup.ISRAEL).addAdjacentCountry(getCountry(CountryLookup.LIBYA));
        getCountry(CountryLookup.ISRAEL).addAdjacentCountry(getCountry(CountryLookup.EGYPT));
        //Saudi Arabia
        getCountry(CountryLookup.SAUDIARABIA).addAdjacentCountry(getCountry(CountryLookup.JORDAN));
        getCountry(CountryLookup.SAUDIARABIA).addAdjacentCountry(getCountry(CountryLookup.IRAQ));
        getCountry(CountryLookup.SAUDIARABIA).addAdjacentCountry(getCountry(CountryLookup.GULFSTATES));
        getCountry(CountryLookup.SAUDIARABIA).addAdjacentCountry(getCountry(CountryLookup.YEMEN));
        //Yemen
        getCountry(CountryLookup.YEMEN).addAdjacentCountry(getCountry(CountryLookup.SAUDIARABIA));
        getCountry(CountryLookup.YEMEN).addAdjacentCountry(getCountry(CountryLookup.SOMALIA));
        //Gulf States
        getCountry(CountryLookup.GULFSTATES).addAdjacentCountry(getCountry(CountryLookup.SAUDIARABIA));
        getCountry(CountryLookup.GULFSTATES).addAdjacentCountry(getCountry(CountryLookup.IRAQ));
        getCountry(CountryLookup.GULFSTATES).addAdjacentCountry(getCountry(CountryLookup.IRAN));
        getCountry(CountryLookup.GULFSTATES).addAdjacentCountry(getCountry(CountryLookup.PAKISTAN));
        //Pakistan
        getCountry(CountryLookup.PAKISTAN).addAdjacentCountry(getCountry(CountryLookup.GULFSTATES));
        getCountry(CountryLookup.PAKISTAN).addAdjacentCountry(getCountry(CountryLookup.IRAN));
        getCountry(CountryLookup.PAKISTAN).addAdjacentCountry(getCountry(CountryLookup.AFGHANISTAN));
        getCountry(CountryLookup.PAKISTAN).addAdjacentCountry(getCountry(CountryLookup.INDIA));
        getCountry(CountryLookup.PAKISTAN).addAdjacentCountry(getCountry(CountryLookup.INDONESIA));
        //China
        getCountry(CountryLookup.CHINA).addAdjacentCountry(getCountry(CountryLookup.CENTRALASIA));
        getCountry(CountryLookup.CHINA).addAdjacentCountry(getCountry(CountryLookup.THAILAND));
        //India
        getCountry(CountryLookup.INDIA).addAdjacentCountry(getCountry(CountryLookup.PAKISTAN));
        getCountry(CountryLookup.INDIA).addAdjacentCountry(getCountry(CountryLookup.INDONESIA));
        //Thailand
        getCountry(CountryLookup.THAILAND).addAdjacentCountry(getCountry(CountryLookup.CHINA));
        getCountry(CountryLookup.THAILAND).addAdjacentCountry(getCountry(CountryLookup.INDONESIA));
        getCountry(CountryLookup.THAILAND).addAdjacentCountry(getCountry(CountryLookup.PHILIPPINES));
        //Indonesia/Malaysia
        getCountry(CountryLookup.INDONESIA).addAdjacentCountry(getCountry(CountryLookup.THAILAND));
        getCountry(CountryLookup.INDONESIA).addAdjacentCountry(getCountry(CountryLookup.INDIA));
        getCountry(CountryLookup.INDONESIA).addAdjacentCountry(getCountry(CountryLookup.PAKISTAN));
        getCountry(CountryLookup.INDONESIA).addAdjacentCountry(getCountry(CountryLookup.PHILIPPINES));
        //Philippines
        getCountry(CountryLookup.PHILIPPINES).addAdjacentCountry(getCountry(CountryLookup.THAILAND));
        getCountry(CountryLookup.PHILIPPINES).addAdjacentCountry(getCountry(CountryLookup.INDONESIA));
        getCountry(CountryLookup.PHILIPPINES).addAdjacentCountry(getCountry(CountryLookup.UNITEDSTATES));
        //Somalia
        getCountry(CountryLookup.SOMALIA).addAdjacentCountry(getCountry(CountryLookup.YEMEN));
        getCountry(CountryLookup.SOMALIA).addAdjacentCountry(getCountry(CountryLookup.KENYA));
        getCountry(CountryLookup.SOMALIA).addAdjacentCountry(getCountry(CountryLookup.SUDAN));
        //Kenya/Tanzania
        getCountry(CountryLookup.KENYA).addAdjacentCountry(getCountry(CountryLookup.SUDAN));
        getCountry(CountryLookup.KENYA).addAdjacentCountry(getCountry(CountryLookup.SOMALIA));
        //Sudan
        getCountry(CountryLookup.SUDAN).addAdjacentCountry(getCountry(CountryLookup.EGYPT));
        getCountry(CountryLookup.SUDAN).addAdjacentCountry(getCountry(CountryLookup.SOMALIA));
        getCountry(CountryLookup.SUDAN).addAdjacentCountry(getCountry(CountryLookup.KENYA));
        getCountry(CountryLookup.SUDAN).addAdjacentCountry(getCountry(CountryLookup.LIBYA));
        //getCountry(CountryLookup.EGYPT)
        getCountry(CountryLookup.EGYPT).addAdjacentCountry(getCountry(CountryLookup.LIBYA));
        getCountry(CountryLookup.EGYPT).addAdjacentCountry(getCountry(CountryLookup.SUDAN));
        getCountry(CountryLookup.EGYPT).addAdjacentCountry(getCountry(CountryLookup.ISRAEL));
        //Libya
        getCountry(CountryLookup.LIBYA).addAdjacentCountry(getCountry(CountryLookup.EGYPT));
        getCountry(CountryLookup.LIBYA).addAdjacentCountry(getCountry(CountryLookup.SUDAN));
        getCountry(CountryLookup.LIBYA).addAdjacentCountry(getCountry(CountryLookup.ALGERIA));
        getCountry(CountryLookup.LIBYA).addAdjacentCountries(schengenCountries);
        //Algeria/Tunisia
        getCountry(CountryLookup.ALGERIA).addAdjacentCountry(getCountry(CountryLookup.LIBYA));
        getCountry(CountryLookup.ALGERIA).addAdjacentCountry(getCountry(CountryLookup.MOROCCO));
        getCountry(CountryLookup.ALGERIA).addAdjacentCountries(schengenCountries);
        //Morocco
        getCountry(CountryLookup.MOROCCO).addAdjacentCountry(getCountry(CountryLookup.ALGERIA));
        getCountry(CountryLookup.MOROCCO).addAdjacentCountries(schengenCountries);
    }

    protected void createDeck() { //sample code, WIP
        deck.addCard(new Abbas());
        deck.addCard(new AbuSayyaf());
        deck.addCard(new AlAnbar());
        deck.addCard(new AlAzhar());
        deck.addCard(new AlIttihadAlIslami());
        deck.addCard(new AlJazeera());
        deck.addCard(new Amerithrax());
        deck.addCard(new AnbarAwakening());
        deck.addCard(new AnsarAlIslam());
        deck.addCard(new AxisOfEvil());
        deck.addCard(new Backlash());
        deck.addCard(new BenazirBhutto());
        deck.addCard(new BhuttoShot());
        deck.addCard(new BinLadin());
        deck.addCard(new CovertAction());
        deck.addCard(new CTR());
        deck.addCard(new Darfur());
        deck.addCard(new EthiopiaStrikes());
        deck.addCard(new EuroIslam());
        deck.addCard(new ExKGB());
        deck.addCard(new FATA());
        deck.addCard(new ForeignFighters());
        deck.addCard(new FormerSovietUnion());
        deck.addCard(new FREs());
        deck.addCard(new GazaWar());
        deck.addCard(new GazaWithdrawal());
        deck.addCard(new GTMO());
        deck.addCard(new HaririKilled());
        deck.addCard(new Hijab());
        deck.addCard(new HizbUtTahrir());
        deck.addCard(new Homegrown());
        deck.addCard(new IEDs());
        deck.addCard(new IndoPakistaniTalks());
        deck.addCard(new JemaahIslamiya());
        deck.addCard(new Kashmir());
        deck.addCard(new KemalistRepublic());
        deck.addCard(new KingAbdullah());
        deck.addCard(new Kosovo());
        deck.addCard(new LebanonWar());
        deck.addCard(new MartyrdomOperation());
        deck.addCard(new MartyrdomOperation());
        deck.addCard(new MartyrdomOperation());
        deck.addCard(new MassTurnout());
        deck.addCard(new MoroTalks());
        deck.addCard(new MossadAndShinBet());
        deck.addCard(new Musharraf());
        deck.addCard(new NEST());
        deck.addCard(new OilPriceSpike());
        deck.addCard(new OilPriceSpike());
        deck.addCard(new Opium());
        deck.addCard(new PakistaniOffensive());
        deck.addCard(new PatriotAct());
        deck.addCard(new Pirates());
        deck.addCard(new Predator());
        deck.addCard(new Predator());
        deck.addCard(new Predator());
        deck.addCard(new Quartet());
        deck.addCard(new RegionalAlQaeda());
        deck.addCard(new Saddam());
        deck.addCard(new SaddamCaptured());
        deck.addCard(new Saleh());
        deck.addCard(new Sanctions());
        deck.addCard(new Sanctions());
        deck.addCard(new SchroederAndChirac());
        deck.addCard(new Sharia());
        deck.addCard(new Sistani());
        deck.addCard(new SpecialForces());
        deck.addCard(new SpecialForces());
        deck.addCard(new SpecialForces());
        deck.addCard(new Taliban());
        deck.addCard(new TonyBlair());
        deck.addCard(new USElection());
        deck.addCard(new UyghurJihad());
        deck.addCard(new VieiradeMelloSlain());
        deck.addCard(new Wahhabism());
        deck.addCard(new Wiretapping());
        deck.addCard(new Zawahiri());
    }

    //--------------------------------ABSTRACT METHODS----------------------------------------------
    public abstract void setup();

}
