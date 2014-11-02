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

import freeWarOnTerror.Cell;
import freeWarOnTerror.Plot;
import freeWarOnTerror.Troop;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emil
 */
public abstract class Location {

    private final ArrayList<Cell> cells = new ArrayList<>();
    private final List<Plot> plots = new ArrayList<>();
    private final List<Troop> troops = new ArrayList<>();

    public void add(Movable m) {
    }

    public void add(Plot p) {

    }

    public void remove(Plot p) {
    }

    public void remove(Movable m) {

    }

    public void add(Cell c) {

    }

    public void remove(Cell c) {
    }

    public void add(Troop t) {

    }

    public void remove(Troop t) {
    }
}
