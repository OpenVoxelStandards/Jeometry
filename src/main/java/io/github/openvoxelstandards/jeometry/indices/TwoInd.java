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

package io.github.openvoxelstandards.jeometry.indices;

import org.jetbrains.annotations.NotNull;

public class TwoInd implements HorizontalIndex {
    int x, y;

    public TwoInd(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public TwoInd(@NotNull VoxelIndex voxelIndex) {
        this(voxelIndex.getX(), voxelIndex.getY());
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }
}
