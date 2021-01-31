/*
 * Geometry: The Open Voxel Standards (OVS) geometry library.
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

package io.github.openvoxelstandards.geometry.spaces;

import io.github.openvoxelstandards.geometry.indices.HorizontalIndex;
import io.github.openvoxelstandards.geometry.positions.HorizontalPosition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Disk implements BaseSpace {
    int sizeX, sizeY;
    int sizeXSquared, sizeYSquared;

    @Contract(pure = true)
    public Disk(int sizeX, int sizeY) {
        if (sizeX < 1)
            throw new IllegalArgumentException(String.format(
                "expected a positive integer for sizeX, got %s instead", sizeX));
        if (sizeY < 1)
            throw new IllegalArgumentException(String.format(
                "expected a positive integer for sizeY, got %s instead", sizeY));
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeXSquared = Disk.square(this.sizeX);
        this.sizeYSquared = Disk.square(this.sizeY);
    }

    @Contract(pure = true)
    static int square(int value) {
        return value * value;
    }

    @Contract(pure = true)
    static double square(double value) {
        return value * value;
    }

    boolean isInside(double x, double y) {
        return Disk.square(x) / this.sizeXSquared + Disk.square(y) / this.sizeYSquared <= 1D;
    }

    @Override
    public boolean isInside(@NotNull HorizontalIndex horizontalIndex) {
        int x = horizontalIndex.getX();
        int y = horizontalIndex.getY();
        if (x < 0)
            x -= 1;
        if (y < 0)
            y -= 1;
        return this.isInside(x, y);
    }

    @Override
    public boolean isInside(@NotNull HorizontalPosition horizontalDisplacement) {
        double x = horizontalDisplacement.getX();
        double y = horizontalDisplacement.getY();
        return Disk.square(x) / this.sizeXSquared + Disk.square(y) / this.sizeYSquared <= 1F;
    }
}
