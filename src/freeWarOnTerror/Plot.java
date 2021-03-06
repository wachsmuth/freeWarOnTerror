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

import static freeWarOnTerror.Game.getTrack;
import freeWarOnTerror.abClasses.Location;
import freeWarOnTerror.abClasses.Moveable;
import static freeWarOnTerror.helpers.CONSTANTS.WMD;

/**
 *
 * @author Emil
 */
public class Plot extends Moveable implements Comparable<Plot> {

    private final int type; //Type 1, 2, 3 = strength; Variable WMD is 4
    private boolean faceUp;
    private boolean backlash = false;

    public Plot(Location l, int type) {
        super(l);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public void remove() {
        if (type == 4) {
            getLocation().remove(this);
        } else {
            move(getTrack());
            setBacklash(false);
        }
    }

    public boolean isBacklash() {
        return backlash;
    }

    public void setBacklash(boolean backlash) {
        this.backlash = backlash;
    }

    @Override
    public String toString(){
        if (type == WMD){
            return "Plot type: WMD";
        }
        return "Plot type: " + type;
    }
    
    @Override
    public int compareTo(Plot other){
        return other.getType()-type;
    }
}
