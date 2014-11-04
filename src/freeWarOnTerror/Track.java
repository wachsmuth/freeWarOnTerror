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

import freeWarOnTerror.abClasses.Location;

/**
 *
 * @author Emil
 */
public class Track extends Location {
    
    public Track(){
        for (int i = 0;i < 15;i++){
            add(new Cell(this));
            add(new Troop(this));
        }
        add(new Plot(this, 3));
        add(new Plot(this, 2));
        add(new Plot(this, 2));
        add(new Plot(this, 1));
        add(new Plot(this, 1));
        add(new Plot(this, 1));
    }
    
    public String getName(){
        return "Troop Track";
    }
}
