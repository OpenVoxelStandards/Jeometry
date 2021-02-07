/*
 * Jeometry: The Open Voxel Standards (OVS) geometry library for Java.
 * Copyright (c) 2021 DocW
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see
 * <https://www.gnu.org/licenses/>.
 */

package io.github.openvoxelstandards.jeometry.zones;

import io.github.openvoxelstandards.jeometry.indices.HorizontalIndex;
import io.github.openvoxelstandards.jeometry.indices.ThreeIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RegStreetGrid extends RegGrid {
    int streetSize, streetWidth, streetSizeUpper;

    public RegStreetGrid(int gridSize, int streetSize) {
        super(gridSize);
        if (streetSize < 1)
            throw new IllegalArgumentException(String.format(
                "expected a positive integer for streetSize, got %s instead", streetSize));
        this.streetSize = streetSize;
        this.streetWidth = 2 * this.streetSize;
        this.streetSizeUpper = this.gridSize - this.streetSize - 1;
    }

    public int getStreetSize() {
        return this.streetSize;
    }

    public int getStreetWidth() {
        return this.streetWidth;
    }

    @Nullable Integer street(int coord) {
        int gridCoord = Math.floorMod(coord, this.gridSize);
        if (gridCoord < this.streetSize)
            return gridCoord;
        else if (this.streetSizeUpper < gridCoord)
            return gridCoord + 1;
        return null;
    }

    public @NotNull RegStreetGridID getGridID(@NotNull HorizontalIndex horizontalIndex) {
        return new RegStreetGridID(
            Math.floorDiv(horizontalIndex.getX(), this.gridSize),
            Math.floorDiv(horizontalIndex.getY(), this.gridSize),
            this.street(horizontalIndex.getX()),
            this.street(horizontalIndex.getY())
        );
    }

    public @NotNull RegStreetGridID getGridID(@NotNull ThreeIndex threeIndex) {
        return this.getGridID((HorizontalIndex) threeIndex);
    }

    public static class RegStreetGridID extends RegGridID {
        @Nullable Integer xStreet, yStreet;

        RegStreetGridID(int x, int y, @Nullable Integer xStreet, @Nullable Integer yStreet) {
            super(x, y);
            this.xStreet = xStreet;
            this.yStreet = yStreet;
        }

        public boolean inIntersection() {
            return this.inXStreet() && this.inYStreet();
        }

        public boolean inStreet() {
            return this.inXStreet() || this.inYStreet();
        }

        public boolean inXStreet() {
            return this.xStreet != null;
        }

        public boolean inYStreet() {
            return this.yStreet != null;
        }

        public @Nullable Integer getXStreet() {
            return this.xStreet;
        }

        public @Nullable Integer getYStreet() {
            return this.yStreet;
        }
    }
}
