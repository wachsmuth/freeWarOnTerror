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

import static freeWarOnTerror.helpers.CardAlignment.AUTO;
import static freeWarOnTerror.helpers.CardAlignment.JIHAD;
import static freeWarOnTerror.helpers.CardAlignment.NEUTRAL;
import static freeWarOnTerror.helpers.CardAlignment.USA;
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
//US

    BACKLASH("Backlash", 1, USA, false, false),
    BIOMETRICS("Biometrics", 1, USA, false, false),
    CTR("CTR", 1, USA, false, false),
    MOROTALKS("Moro Talks", 1, USA, true, true, PHILIPPINES),
    NEST("NEST", 1, USA, true, true, UNITEDSTATES),
    SANCTIONS("Sanctions", 1, USA, false, false),
    SPECIALFORCES("Special Forces", 1, USA, false, false),
    ABBAS("Abbas", 2, USA, true, true, ISRAEL),
    ALAZHAR("Al-Azhar", 2, USA, false, false),
    ANBARAWAKENING("Anbar Awakening", 2, USA, false, true, IRAQ, SYRIA),
    COVERTACTION("Covert Action", 2, USA, false, false),
    ETHIOPIASTRIKES("Ethiopia Strikes", 2, USA, true, false),
    EUROISLAM("Euro-Islam", 2, USA, true, false),
    FSB,
    INTELCOMMUNITY,
    KEMALISTREPUBLIC("Kemalist Republic", 2, USA, false, false),
    KINGABDULLAH("King Abdullah", 2, USA, true, false),
    LETSROLL,
    MOSSADANDSHINBET("Mossad & Shin Bet", 2, USA, false, false),
    PREDATOR("Predator", 2, USA, false, false),
    QUARTET("Quartet", 2, USA, false, false),
    SADDAMCAPTURED("Saddam Captured", 2, USA, true, true, IRAQ),
    SHARIA("Sharia", 2, USA, false, false),
    TONYBLAIR("Tony Blair", 2, USA, true, false),
    UNNATIONBUILDING,
    WIRETAPPING("Wiretapping", 2, USA, false, true),
    BACKCHANNEL,
    BENAZIRBHUTTO("Benazir Bhutto", 3, USA, true, true, PAKISTAN),
    ENHANCEDMEASURES,
    HIJAB("Hijab", 3, USA, true, false),
    INDOPAKISTANITALKS("Indo-Pakistani Talks", 3, USA, true, true, INDIA, PAKISTAN),
    IRAQIWMD,
    LIBYANDEAL,
    LIBYANWMD,
    MASSTURNOUT("Mass Turnout", 3, USA, false, false),
    NATO,
    PAKISTANIOFFENSIVE("Pakistani Offensive", 3, USA, false, false),
    PATRIOTACT("Patriot Act", 3, USA, true, true, UNITEDSTATES),
    RENDITIONS,
    SAFERNOW("Safer Now", 3, USA, false, false),
    SISTANI("Sistani", 3, USA, false, false),
    THEDOOROFITJIHADWASCLOSEDUS("The Door of Itjihad Was Closed", 3, USA, false, false),
    //Jihadist
    ADAMGADAHN,
    ALITTIHADALISLAMI("Al-Ittihad al-Islami", 1, JIHAD, true, false),
    ANSARALISLAM("Ansar al-Islam", 1, JIHAD, true, false),
    FRES("FREs", 1, JIHAD, false, false),
    IEDS("IEDs", 1, JIHAD, false, false),
    MADRASSAS,
    MOQTADAALSADR,
    UYGHURJIHAD("Uyghur Jihad", 1, JIHAD, true, false),
    VIEIRADEMELLOSLAIN("Vieira de Mello Slain", 1, JIHAD, true, true),
    ABUSAYYAF("Abu Sayyaf", 2, JIHAD, true, true, PHILIPPINES),
    ALANBAR("Al-Anbar", 2, JIHAD, true, true, SYRIA, IRAQ),
    AMERITHRAX("Amerithrax", 2, JIHAD, false, false),
    BHUTTOSHOT("Bhutto Shot", 2, JIHAD, true, true, PAKISTAN), //Debug is this right??

    DETAINEERELEASE,
    EXKGB("Ex-KGB", 2, JIHAD, false, false),
    GAZAWAR("Gaza War", 2, JIHAD, false, false),
    HARIRIKILLED("Hariri Killed", 2, JIHAD, true, false),
    HEU,
    HOMEGROWN("Homegrown", 2, JIHAD, false, false),
    ISLAMICJIHADUNION,
    JEMAAHISLAMIYA("Jemaah Islamiya", 2, JIHAD, false, false),
    KAZAKHSTRAIN,
    LASHKARETAYYIBA("Lashkar-e-Tayyiba", 2, JIHAD, false, false),
    LOOSENUKE,
    OPIUM("Opium", 2, JIHAD, false, false),
    PIRATES("Pirates", 2, JIHAD, true, true, YEMEN, SOMALIA),
    SCHENGENVISAS,
    SCHROEDERANDCHIRAC("Schroeder & Chirac", 2, JIHAD, true, false),
    ABUGHURAYB,
    ALJAZEERA("Al Jazeera", 3, JIHAD, false, false),
    AXISOFEVIL("\"Axis of Evil\"", 3, JIHAD, false, false),
    CLEANOPERATIVES,
    FATA("FATA", 3, JIHAD, false, true, PAKISTAN),
    FOREIGNFIGHTERS("Foreign Fighters", 3, JIHAD, false, false),
    JIHADISTVIDEOS,
    KASHMIR("Kashmir", 3, JIHAD, false, false),
    LEAK,
    LEBANONWAR("Lebanon War", 3, JIHAD, false, false),
    MARTYRDOMOPERATION("Martyrdom Operation", 3, JIHAD, false, false),
    QUAGMIRE,
    REGIONALALQAEDA("Regional al-Qaeda", 3, JIHAD, false, false),
    SADDAM("Saddam", 3, JIHAD, false, false),
    TALIBAN("Taliban", 3, JIHAD, false, false),
    THEDOOROFITJIHADWASCLOSEDJIHADIST,
    WAHHABISM("Wahhabism", 3, JIHAD, false, false),
    //Unassociated
    DANISHCARTOONS,
    FATWA,
    GAZAWITHDRAWAL("Gaza Withdrawal", 1, NEUTRAL, true, false),
    HAMASELECTED,
    HIZBUTTAHRIR("Hizb Ut-Tahrir", 1, NEUTRAL, false, false),
    KOSOVO("Kosovo", 1, NEUTRAL, false, false),
    FORMERSOVIETUNION("Former Soviet Union", 2, NEUTRAL, false, false),
    HIZBALLAH,
    IRAN,
    JAYSHALMAHDI,
    KURDISTAN,
    MUSHARRAF("Musharraf", 2, NEUTRAL, false, false),
    TORABORA,
    ZARQAWI,
    ZAWAHIRI("Zawahiri", 2, NEUTRAL, false, false),
    BINLADIN("Bin Ladin", 3, NEUTRAL, false, false),
    DARFUR("Darfur", 3, NEUTRAL, false, false),
    GTMO("GTMO", 3, NEUTRAL, false, false),
    HAMBALI("Hambali", 3, NEUTRAL, false, false),
    KSM,
    OILPRICESPIKE("Oil Price Spike", 3, NEUTRAL, false, false),
    SALEH("Saleh", 3, NEUTRAL, false, false),
    //Automatic events
    USELECTION("US Election", 3, AUTO, false, false);

    private String name;
    private int ops;
    private CardAlignment alignment;
    private boolean removedAfterPlay;
    boolean mark;
    CountryLookup[] lookUps;

    private CardLookup() {
    }

    private CardLookup(String name, int ops, CardAlignment alignment, Boolean removedAfterPlay, boolean mark) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.mark = mark;
        lookUps = null;
    }

    private CardLookup(String name, int ops, CardAlignment alignment, Boolean removedAfterPlay, boolean mark, CountryLookup... lookups) {
        this.name = name;
        this.ops = ops;
        this.alignment = alignment;
        this.removedAfterPlay = removedAfterPlay;
        this.mark = mark;
        this.lookUps = lookups;
    }

    public String getName() {
        return name;
    }

    public int getOps() {
        return ops;
    }

    public CardAlignment getAlignment() {
        return alignment;
    }

    public boolean isRemovedAfterPlay() {
        return removedAfterPlay;
    }

    public boolean isMark() {
        return mark;
    }

    public CountryLookup[] getLookUps() {
        return lookUps;
    }
}
