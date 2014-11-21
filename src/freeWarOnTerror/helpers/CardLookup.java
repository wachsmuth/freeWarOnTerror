/*
 * Copyright (C) 2014 Wengel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation , either version 3 of the License , or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful ,
 * but WITHOUT ANY WARRANTY , without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not , see <http://www.gnu.org/licenses/>.
 */
package freeWarOnTerror.helpers;

import static freeWarOnTerror.helpers.CountryLookup.INDIA;
import static freeWarOnTerror.helpers.CountryLookup.IRAQ;
import static freeWarOnTerror.helpers.CountryLookup.ISRAEL;
import static freeWarOnTerror.helpers.CountryLookup.PAKISTAN;
import static freeWarOnTerror.helpers.CountryLookup.PHILIPPINES;
import static freeWarOnTerror.helpers.CountryLookup.SOMALIA;
import static freeWarOnTerror.helpers.CountryLookup.SYRIA;
import static freeWarOnTerror.helpers.CountryLookup.UNITEDSTATES;
import static freeWarOnTerror.helpers.CountryLookup.YEMEN;

/**
 *
 * @author Wengel
 */
public enum CardLookup {
//US

    BACKLASH("Backlash", 1, 2, false, false),
    BIOMETRICS("Biometrics", 1, 2, false, false),
    CTR("CTR", 1, 2, false, false),
    MOROTALKS("Moro Talks", 1, 2, true, true, PHILIPPINES),
    NEST("NEST", 1, 2, true, true, UNITEDSTATES),
    SANCTIONS("Sanctions", 1, 2, false, false),
    SPECIALFORCES("Special Forces", 1, 2, false, false),
    ABBAS("Abbas", 2, 2, true, true, ISRAEL),
    ALAZHAR("Al-Azhar", 2, 2, false, false),
    ANBARAWAKENING("Anbar Awakening", 2, 2, false, true, IRAQ, SYRIA),
    COVERTACTION("Covert Action", 2, 2, false, false),
    ETHIOPIASTRIKES("Ethiopia Strikes", 2, 2, true, false),
    EUROISLAM("Euro-Islam", 2, 2, true, false),
    FSB,
    INTELCOMMUNITY,
    KEMALISTREPUBLIC("Kemalist Republic", 2, 2, false, false),
    KINGABDULLAH("King Abdullah", 2, 2, true, false),
    LETSROLL,
    MOSSADANDSHINBET("Mossad & Shin Bet", 2, 2, false, false),
    PREDATOR("Predator", 2, 2, false, false),
    QUARTET("Quartet", 2, 2, false, false),
    SADDAMCAPTURED("Saddam Captured", 2, 2, true, true, IRAQ),
    SHARIA("Sharia", 2, 2, false, false),
    TONYBLAIR("Tony Blair", 2, 2, true, false),
    UNNATIONBUILDING,
    WIRETAPPING("Wiretapping", 2, 2, false, true),
    BACKCHANNEL,
    BENAZIRBHUTTO("Benazir Bhutto", 3, 2, true, true, PAKISTAN),
    ENHANCEDMEASURES,
    HIJAB("Hijab", 3, 2, true, false),
    INDOPAKISTANITALKS("Indo-Pakistani Talks", 3, 2, true, true, INDIA, PAKISTAN),
    IRAQIWMD,
    LIBYANDEAL,
    LIBYANWMD,
    MASSTURNOUT("Mass Turnout", 3, 2, false, false),
    NATO,
    PAKISTANIOFFENSIVE("Pakistani Offensive", 3, 2, false, false),
    PATRIOTACT("Patriot Act", 3, 2, true, true, UNITEDSTATES),
    RENDITIONS,
    SAFERNOW("Safer Now", 3, 2, false, false),
    SISTANI("Sistani", 3, 2, false, false),
    THEDOOROFITJIHADWASCLOSEDUS,
    //Jihadist
    ADAMGADAHN,
    ALITTIHADALISLAMI("Al-Ittihad al-Islami", 1, 3, true, false),
    ANSARALISLAM("Ansar al-Islam", 1, 3, true, false),
    FRES("FREs", 1, 3, false, false),
    IEDS("IEDs", 1, 3, false, false),
    MADRASSAS,
    MOQTADAALSADR,
    UYGHURJIHAD("Uyghur Jihad", 1, 3, true, false),
    VIEIRADEMELLOSLAIN("Vieira de Mello Slain", 1, 3, true, true),
    ABUSAYYAF("Abu Sayyaf", 2, 3, true, true, PHILIPPINES),
    ALANBAR("Al-Anbar", 2, 3, true, true, SYRIA, IRAQ),
    AMERITHRAX("Amerithrax", 2, 3, false, false),
    BHUTTOSHOT("Bhutto Shot", 2, 3, true, true, PAKISTAN), //Debug is this right??

    DETAINEERELEASE,
    EXKGB("Ex-KGB", 2, 3, false, false),
    GAZAWAR("Gaza War", 2, 3, false, false),
    HARIRIKILLED("Hariri Killed", 2, 3, true, false),
    HEU,
    HOMEGROWN("Homegrown", 2, 3, false, false),
    ISLAMICJIHADUNION,
    JEMAAHISLAMIYA("Jemaah Islamiya", 2, 3, false, false),
    KAZAKHSTRAIN,
    LASHKARETAYYIBA,
    LOOSENUKE,
    OPIUM("Opium", 2, 3, false, false),
    PIRATES("Pirates", 2, 3, true, true, YEMEN, SOMALIA),
    SCHENGENVISAS,
    SCHROEDERANDCHIRAC("Schroeder & Chirac", 2, 3, true, false),
    ABUGHURAYB,
    ALJAZEERA("Al Jazeera", 3, 3, false, false),
    AXISOFEVIL("\"Axis of Evil\"", 3, 3, false, false),
    CLEANOPERATIVES,
    FATA("FATA", 3, 3, false, true, PAKISTAN),
    FOREIGNFIGHTERS("Foreign Fighters", 3, 3, false, false),
    JIHADISTVIDEOS,
    KASHMIR("Kashmir", 3, 3, false, false),
    LEAK,
    LEBANONWAR("Lebanon War", 3, 3, false, false),
    MARTYRDOMOPERATION("Martyrdom Operation", 3, 3, false, false),
    QUAGMIRE,
    REGIONALALQAEDA("Regional al-Qaeda", 3, 3, false, false),
    SADDAM("Saddam", 3, 3, false, false),
    TALIBAN("Taliban", 3, 3, false, false),
    THEDOOROFITJIHADWASCLOSEDJIHADIST,
    WAHHABISM("Wahhabism", 3, 3, false, false),
    //Unassociated
    DANISHCARTOONS,
    FATWA,
    GAZAWITHDRAWAL("Gaza Withdrawal", 1, 1, true, false),
    HAMASELECTED,
    HIZBUTTAHRIR0,
    KOSOVO1,
    FORMERSOVIETUNION2,
    HIZBALLAH3,
    IRANCARD4,
    JAYSHALMAHDI6,
    KURDISTAN7,
    MUSHARRAF8,
    TORABORA9,
    ZARQAWI0,
    ZAWAHIRI1,
    BINLADIN2,
    DARFUR3,
    GTMO4,
    HAMBALI5,
    KSM6,
    OILPRICESPIKE7,
    SALEH9,
    //Automatic events
    USELECTION0;

    private CardLookup() {

    }

    private CardLookup(String name, int ops, int alignment, Boolean removedAfterPlay, boolean mark) {

    }

    private CardLookup(String name, int ops, int alignment, Boolean removedAfterPlay, boolean mark, CountryLookup... lookups) {

    }

}
