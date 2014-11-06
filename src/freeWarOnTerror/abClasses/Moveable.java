

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

/**
 *
 * @author Emil
 */
public abstract class Moveable {

    Location myLocation;

    public Moveable(){
        //Empty constructor
    }
    public Moveable(Location myLocation) {
        this.myLocation = myLocation;
    }

    public Location getLocation() {
        return myLocation;
    }

    public void move(Location l) {
        if (myLocation == null) {
            myLocation = l;
            l.add(this);
        } else {
            myLocation.remove(this);
            myLocation = l;
            myLocation.add(this);
        }
    }

}
