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
import freeWarOnTerror.cards.AbuSayyaf;
import freeWarOnTerror.cards.AlAnbar;
import freeWarOnTerror.cards.AlAzhar;
import freeWarOnTerror.cards.AlIttihadAlIslami;
import freeWarOnTerror.cards.Amerithrax;
import freeWarOnTerror.cards.AnbarAwakening;
import freeWarOnTerror.cards.AnsarAlIslam;
import freeWarOnTerror.cards.BenazirBhutto;
import freeWarOnTerror.cards.BhuttoShot;
import freeWarOnTerror.cards.CTR;
import freeWarOnTerror.cards.CovertAction;
import freeWarOnTerror.cards.Darfur;
import freeWarOnTerror.cards.EuroIslam;
import freeWarOnTerror.cards.ExKGB;
import freeWarOnTerror.cards.FATA;
import freeWarOnTerror.cards.FREs;
import freeWarOnTerror.cards.FormerSovietUnion;
import freeWarOnTerror.cards.GazaWar;
import freeWarOnTerror.cards.HaririKilled;
import freeWarOnTerror.cards.Hijab;
import freeWarOnTerror.cards.HizbUtTahrir;
import freeWarOnTerror.cards.Homegrown;
import freeWarOnTerror.cards.IEDs;
import freeWarOnTerror.cards.JemaahIslamiya;
import freeWarOnTerror.cards.KemalistRepublic;
import freeWarOnTerror.cards.KingAbdullah;
import freeWarOnTerror.cards.Kosovo;
import freeWarOnTerror.cards.LebanonWar;
import freeWarOnTerror.cards.MassTurnout;
import freeWarOnTerror.cards.MoroTalks;
import freeWarOnTerror.cards.MossadAndShinBet;
import freeWarOnTerror.cards.NEST;
import freeWarOnTerror.cards.PakistaniOffensive;
import freeWarOnTerror.cards.PatriotAct;
import freeWarOnTerror.cards.Pirates;
import freeWarOnTerror.cards.RegionalAlQaeda;
import freeWarOnTerror.cards.Saddam;
import freeWarOnTerror.cards.SaddamCaptured;
import freeWarOnTerror.cards.Sanctions;
import freeWarOnTerror.cards.SchroederAndChirac;
import freeWarOnTerror.cards.Sistani;
import freeWarOnTerror.cards.USElection;
import freeWarOnTerror.cards.UyghurJihad;
import freeWarOnTerror.cards.VieiradeMelloSlain;
import freeWarOnTerror.cards.Wahhabism;
import freeWarOnTerror.helpers.CONSTANTS;
import static freeWarOnTerror.helpers.CONSTANTS.AFGHANISTAN;
import static freeWarOnTerror.helpers.CONSTANTS.ALGERIA;
import static freeWarOnTerror.helpers.CONSTANTS.CANADA;
import static freeWarOnTerror.helpers.CONSTANTS.CAUCASUS;
import static freeWarOnTerror.helpers.CONSTANTS.CENTRALASIA;
import static freeWarOnTerror.helpers.CONSTANTS.CHINA;
import static freeWarOnTerror.helpers.CONSTANTS.EGYPT;
import static freeWarOnTerror.helpers.CONSTANTS.GULFSTATES;
import static freeWarOnTerror.helpers.CONSTANTS.INDIA;
import static freeWarOnTerror.helpers.CONSTANTS.INDONESIA;
import static freeWarOnTerror.helpers.CONSTANTS.IRANCOUNTRY;
import static freeWarOnTerror.helpers.CONSTANTS.IRAQ;
import static freeWarOnTerror.helpers.CONSTANTS.ISRAEL;
import static freeWarOnTerror.helpers.CONSTANTS.JORDAN;
import static freeWarOnTerror.helpers.CONSTANTS.KENYA;
import static freeWarOnTerror.helpers.CONSTANTS.LEBANON;
import static freeWarOnTerror.helpers.CONSTANTS.LIBYA;
import static freeWarOnTerror.helpers.CONSTANTS.MOROCCO;
import static freeWarOnTerror.helpers.CONSTANTS.PAKISTAN;
import static freeWarOnTerror.helpers.CONSTANTS.PHILIPPINES;
import static freeWarOnTerror.helpers.CONSTANTS.RUSSIA;
import static freeWarOnTerror.helpers.CONSTANTS.SAUDIARABIA;
import static freeWarOnTerror.helpers.CONSTANTS.SERBIA;
import static freeWarOnTerror.helpers.CONSTANTS.SOMALIA;
import static freeWarOnTerror.helpers.CONSTANTS.SUDAN;
import static freeWarOnTerror.helpers.CONSTANTS.SYRIA;
import static freeWarOnTerror.helpers.CONSTANTS.THAILAND;
import static freeWarOnTerror.helpers.CONSTANTS.TURKEY;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDKINGDOM;
import static freeWarOnTerror.helpers.CONSTANTS.UNITEDSTATES;
import static freeWarOnTerror.helpers.CONSTANTS.YEMEN;
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

    private void createWorld() { //sample code, WIP
        //Muslim countries
        countries.add(new MuslimCountry("Somalia", CONSTANTS.SOMALIA, 1, false, false));
        countries.add(new MuslimCountry("Sudan", CONSTANTS.SUDAN, 1, true, false));
        countries.add(new MuslimCountry("Yemen", CONSTANTS.YEMEN, 1, false, true));
        countries.add(new MuslimCountry("Saudi Arabia", CONSTANTS.SAUDIARABIA, 3, true, true));
        countries.add(new MuslimCountry("Jordan", CONSTANTS.JORDAN, 1, false, false));
        countries.add(new MuslimCountry("Iraq", CONSTANTS.IRAQ, 3, true, true));
        countries.add(new MuslimCountry("Gulf States", CONSTANTS.GULFSTATES, 3, true, true));
        countries.add(new CountryPakistan("Pakistan", CONSTANTS.PAKISTAN, 2, false, true));
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
        countries.add(new CountryIran("Iran", CONSTANTS.IRANCOUNTRY));
        countries.add(new CountryIsrael("Israel", CONSTANTS.ISRAEL, 1));
        countries.add(new CountryPhilippines("Philippines", CONSTANTS.PHILIPPINES, 2, 3));
        countries.add(new CountryUSA("United States", UNITEDSTATES, 1));

    }

    private void createLists() {
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
        schengenBorderCountries.add(getCountry(CANADA));
        schengenBorderCountries.add(getCountry(UNITEDKINGDOM));
        schengenBorderCountries.add(getCountry(UNITEDSTATES));
        schengenBorderCountries.add(getCountry(SERBIA));
        schengenBorderCountries.add(getCountry(RUSSIA));
        schengenBorderCountries.add(getCountry(TURKEY));
        schengenBorderCountries.add(getCountry(LEBANON));
        schengenBorderCountries.add(getCountry(LIBYA));
        schengenBorderCountries.add(getCountry(ALGERIA));
        schengenBorderCountries.add(getCountry(MOROCCO));

    }

    private void createConnections() {
        //United States
        getCountry(UNITEDSTATES).addAdjacentCountry(getCountry(CANADA));
        getCountry(UNITEDSTATES).addAdjacentCountry(getCountry(PHILIPPINES));
        getCountry(UNITEDSTATES).addAdjacentCountry(getCountry(UNITEDKINGDOM));
        getCountry(UNITEDSTATES).addAdjacentCountries(schengenCountries);
        //Canada
        getCountry(CANADA).addAdjacentCountries(schengenCountries);
        getCountry(CANADA).addAdjacentCountry(getCountry(UNITEDKINGDOM));
        getCountry(CANADA).addAdjacentCountry(getCountry(UNITEDSTATES));
        //United Kingdom
        getCountry(UNITEDKINGDOM).addAdjacentCountry(getCountry(UNITEDSTATES));
        getCountry(UNITEDKINGDOM).addAdjacentCountry(getCountry(CANADA));
        getCountry(UNITEDKINGDOM).addAdjacentCountries(schengenCountries);
        //All Schengen countries
        for (Country c : schengenCountries) {
            c.addAdjacentCountries(schengenCountries);
            c.addAdjacentCountries(schengenBorderCountries);
        }
        //Russia
        getCountry(RUSSIA).addAdjacentCountries(schengenCountries);
        getCountry(RUSSIA).addAdjacentCountry(getCountry(TURKEY));
        getCountry(RUSSIA).addAdjacentCountry(getCountry(SERBIA));
        getCountry(RUSSIA).addAdjacentCountry(getCountry(CAUCASUS));
        getCountry(RUSSIA).addAdjacentCountry(getCountry(CENTRALASIA));
        //Serbia
        getCountry(SERBIA).addAdjacentCountry(getCountry(TURKEY));
        getCountry(SERBIA).addAdjacentCountry(getCountry(RUSSIA));
        getCountry(SERBIA).addAdjacentCountries(schengenCountries);
        //Turkey
        getCountry(TURKEY).addAdjacentCountry(getCountry(SERBIA));
        getCountry(TURKEY).addAdjacentCountry(getCountry(RUSSIA));
        getCountry(TURKEY).addAdjacentCountries(schengenCountries);
        getCountry(TURKEY).addAdjacentCountry(getCountry(IRANCOUNTRY));
        getCountry(TURKEY).addAdjacentCountry(getCountry(SYRIA));
        getCountry(TURKEY).addAdjacentCountry(getCountry(CAUCASUS));
        //Caucasus
        getCountry(CAUCASUS).addAdjacentCountry(getCountry(RUSSIA));
        getCountry(CAUCASUS).addAdjacentCountry(getCountry(TURKEY));
        getCountry(CAUCASUS).addAdjacentCountry(getCountry(CENTRALASIA));
        getCountry(CAUCASUS).addAdjacentCountry(getCountry(IRANCOUNTRY));
        //Central Asia
        getCountry(CENTRALASIA).addAdjacentCountry(getCountry(RUSSIA));
        getCountry(CENTRALASIA).addAdjacentCountry(getCountry(CAUCASUS));
        getCountry(CENTRALASIA).addAdjacentCountry(getCountry(IRANCOUNTRY));
        getCountry(CENTRALASIA).addAdjacentCountry(getCountry(AFGHANISTAN));
        //Syria
        getCountry(SYRIA).addAdjacentCountry(getCountry(TURKEY));
        getCountry(SYRIA).addAdjacentCountry(getCountry(LEBANON));
        getCountry(SYRIA).addAdjacentCountry(getCountry(IRAQ));
        getCountry(SYRIA).addAdjacentCountry(getCountry(JORDAN));
        //Lebanon
        getCountry(LEBANON).addAdjacentCountry(getCountry(ISRAEL));
        getCountry(SYRIA).addAdjacentCountry(getCountry(SYRIA));
        getCountry(LEBANON).addAdjacentCountries(schengenCountries);
        //Iran
        getCountry(IRANCOUNTRY).addAdjacentCountry(getCountry(TURKEY));
        getCountry(IRANCOUNTRY).addAdjacentCountry(getCountry(CAUCASUS));
        getCountry(IRANCOUNTRY).addAdjacentCountry(getCountry(CENTRALASIA));
        getCountry(IRANCOUNTRY).addAdjacentCountry(getCountry(AFGHANISTAN));
        getCountry(IRANCOUNTRY).addAdjacentCountry(getCountry(PAKISTAN));
        getCountry(IRANCOUNTRY).addAdjacentCountry(getCountry(GULFSTATES));
        getCountry(IRANCOUNTRY).addAdjacentCountry(getCountry(IRAQ));
        //Afghanistan
        getCountry(AFGHANISTAN).addAdjacentCountry(getCountry(IRANCOUNTRY));
        getCountry(AFGHANISTAN).addAdjacentCountry(getCountry(PAKISTAN));
        getCountry(AFGHANISTAN).addAdjacentCountry(getCountry(CENTRALASIA));
        //Iraq
        getCountry(IRAQ).addAdjacentCountry(getCountry(IRANCOUNTRY));
        getCountry(IRAQ).addAdjacentCountry(getCountry(GULFSTATES));
        getCountry(IRAQ).addAdjacentCountry(getCountry(SAUDIARABIA));
        getCountry(IRAQ).addAdjacentCountry(getCountry(JORDAN));
        getCountry(IRAQ).addAdjacentCountry(getCountry(SYRIA));
        getCountry(IRAQ).addAdjacentCountry(getCountry(TURKEY));
        //Jordan
        getCountry(JORDAN).addAdjacentCountry(getCountry(SYRIA));
        getCountry(JORDAN).addAdjacentCountry(getCountry(IRAQ));
        getCountry(JORDAN).addAdjacentCountry(getCountry(ISRAEL));
        getCountry(JORDAN).addAdjacentCountry(getCountry(SAUDIARABIA));
        //Israel
        getCountry(ISRAEL).addAdjacentCountry(getCountry(JORDAN));
        getCountry(ISRAEL).addAdjacentCountry(getCountry(LIBYA));
        getCountry(ISRAEL).addAdjacentCountry(getCountry(EGYPT));
        //Saudi Arabia
        getCountry(SAUDIARABIA).addAdjacentCountry(getCountry(JORDAN));
        getCountry(SAUDIARABIA).addAdjacentCountry(getCountry(IRAQ));
        getCountry(SAUDIARABIA).addAdjacentCountry(getCountry(GULFSTATES));
        getCountry(SAUDIARABIA).addAdjacentCountry(getCountry(YEMEN));
        //Yemen
        getCountry(YEMEN).addAdjacentCountry(getCountry(SAUDIARABIA));
        getCountry(YEMEN).addAdjacentCountry(getCountry(SOMALIA));
        //Gulf States
        getCountry(GULFSTATES).addAdjacentCountry(getCountry(SAUDIARABIA));
        getCountry(GULFSTATES).addAdjacentCountry(getCountry(IRAQ));
        getCountry(GULFSTATES).addAdjacentCountry(getCountry(IRANCOUNTRY));
        getCountry(GULFSTATES).addAdjacentCountry(getCountry(PAKISTAN));
        //Pakistan
        getCountry(PAKISTAN).addAdjacentCountry(getCountry(GULFSTATES));
        getCountry(PAKISTAN).addAdjacentCountry(getCountry(IRANCOUNTRY));
        getCountry(PAKISTAN).addAdjacentCountry(getCountry(AFGHANISTAN));
        getCountry(PAKISTAN).addAdjacentCountry(getCountry(INDIA));
        getCountry(PAKISTAN).addAdjacentCountry(getCountry(INDONESIA));
        //China
        getCountry(CHINA).addAdjacentCountry(getCountry(CENTRALASIA));
        getCountry(CHINA).addAdjacentCountry(getCountry(THAILAND));
        //India
        getCountry(INDIA).addAdjacentCountry(getCountry(PAKISTAN));
        getCountry(INDIA).addAdjacentCountry(getCountry(INDONESIA));
        //Thailand
        getCountry(THAILAND).addAdjacentCountry(getCountry(CHINA));
        getCountry(THAILAND).addAdjacentCountry(getCountry(INDONESIA));
        getCountry(THAILAND).addAdjacentCountry(getCountry(PHILIPPINES));
        //Indonesia/Malaysia
        getCountry(INDONESIA).addAdjacentCountry(getCountry(THAILAND));
        getCountry(INDONESIA).addAdjacentCountry(getCountry(INDIA));
        getCountry(INDONESIA).addAdjacentCountry(getCountry(PAKISTAN));
        getCountry(INDONESIA).addAdjacentCountry(getCountry(PHILIPPINES));
        //Philippines
        getCountry(PHILIPPINES).addAdjacentCountry(getCountry(THAILAND));
        getCountry(PHILIPPINES).addAdjacentCountry(getCountry(INDONESIA));
        getCountry(PHILIPPINES).addAdjacentCountry(getCountry(UNITEDSTATES));
        //Somalia
        getCountry(SOMALIA).addAdjacentCountry(getCountry(YEMEN));
        getCountry(SOMALIA).addAdjacentCountry(getCountry(KENYA));
        getCountry(SOMALIA).addAdjacentCountry(getCountry(SUDAN));
        //Kenya/Tanzania
        getCountry(KENYA).addAdjacentCountry(getCountry(SUDAN));
        getCountry(KENYA).addAdjacentCountry(getCountry(SOMALIA));
        //Sudan
        getCountry(SUDAN).addAdjacentCountry(getCountry(EGYPT));
        getCountry(SUDAN).addAdjacentCountry(getCountry(SOMALIA));
        getCountry(SUDAN).addAdjacentCountry(getCountry(KENYA));
        getCountry(SUDAN).addAdjacentCountry(getCountry(LIBYA));
        //Egypt
        getCountry(EGYPT).addAdjacentCountry(getCountry(LIBYA));
        getCountry(EGYPT).addAdjacentCountry(getCountry(SUDAN));
        getCountry(EGYPT).addAdjacentCountry(getCountry(ISRAEL));
        //Libya
        getCountry(LIBYA).addAdjacentCountry(getCountry(EGYPT));
        getCountry(LIBYA).addAdjacentCountry(getCountry(SUDAN));
        getCountry(LIBYA).addAdjacentCountry(getCountry(ALGERIA));
        getCountry(LIBYA).addAdjacentCountries(schengenCountries);
        //Algeria/Tunisia
        getCountry(ALGERIA).addAdjacentCountry(getCountry(LIBYA));
        getCountry(ALGERIA).addAdjacentCountry(getCountry(MOROCCO));
        getCountry(ALGERIA).addAdjacentCountries(schengenCountries);
        //Morocco
        getCountry(MOROCCO).addAdjacentCountry(getCountry(ALGERIA));
        getCountry(MOROCCO).addAdjacentCountries(schengenCountries);
    }

    public void createDeck() { //sample code, WIP
        deck.addCard(new AbuSayyaf());
        deck.addCard(new AlAnbar());
        deck.addCard(new AlAzhar());
        deck.addCard(new AlIttihadAlIslami());
        deck.addCard(new Amerithrax());
        deck.addCard(new AnbarAwakening());
        deck.addCard(new AnsarAlIslam());
        deck.addCard(new BenazirBhutto());
        deck.addCard(new BhuttoShot());
        deck.addCard(new CovertAction());
        deck.addCard(new CTR());
        deck.addCard(new Darfur());
        deck.addCard(new EuroIslam());
        deck.addCard(new ExKGB());
        deck.addCard(new FATA());
        deck.addCard(new FormerSovietUnion());
        deck.addCard(new FREs());
        deck.addCard(new GazaWar());
        deck.addCard(new HaririKilled());
        deck.addCard(new Hijab());
        deck.addCard(new HizbUtTahrir());
        deck.addCard(new Homegrown());
        deck.addCard(new IEDs());
        deck.addCard(new JemaahIslamiya());
        deck.addCard(new KemalistRepublic());
        deck.addCard(new KingAbdullah());
        deck.addCard(new Kosovo());
        deck.addCard(new LebanonWar());
        deck.addCard(new MassTurnout());
        deck.addCard(new MoroTalks());
        deck.addCard(new MossadAndShinBet());
        deck.addCard(new NEST());
        deck.addCard(new PakistaniOffensive());
        deck.addCard(new PatriotAct());
        deck.addCard(new Pirates());
        deck.addCard(new RegionalAlQaeda());
        deck.addCard(new Saddam());
        deck.addCard(new SaddamCaptured());
        deck.addCard(new Sanctions());
        deck.addCard(new Sanctions());
        deck.addCard(new SchroederAndChirac());
        deck.addCard(new Sistani());
        deck.addCard(new USElection());
        deck.addCard(new UyghurJihad());
        deck.addCard(new VieiradeMelloSlain());
        deck.addCard(new Wahhabism());
    }

    //--------------------------------ABSTRACT METHODS----------------------------------------------
    public abstract void setup();

}
