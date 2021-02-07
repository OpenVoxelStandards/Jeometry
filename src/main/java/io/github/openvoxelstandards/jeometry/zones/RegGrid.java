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

public class RegGrid implements ZoneGrid {
    int gridSize;

    public RegGrid(int gridSize) {
        if (gridSize < 1)
            throw new IllegalArgumentException(String.format(
                "expected a positive integer for gridSize, got %s instead", gridSize));
        this.gridSize = gridSize;
    }

    public int getGridSize() {
        return this.gridSize;
    }

    public @NotNull RegGridID getGridID(@NotNull HorizontalIndex horizontalIndex) {
        return new RegGridID(
            Math.floorDiv(horizontalIndex.getX(), this.gridSize),
            Math.floorDiv(horizontalIndex.getY(), this.gridSize)
        );
    }

    public @NotNull RegGridID getGridID(@NotNull ThreeIndex threeIndex) {
        return this.getGridID((HorizontalIndex) threeIndex);
    }

    public static class RegGridID implements ZoneID {
        int x, y;

        RegGridID(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return this.x;
        }

        int getY() {
            return this.y;
        }
    }
}
