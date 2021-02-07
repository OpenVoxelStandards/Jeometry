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

package io.github.openvoxelstandards.jeometry.spaces;

import io.github.openvoxelstandards.jeometry.indices.HorizontalIndex;
import io.github.openvoxelstandards.jeometry.indices.ThreeInd;
import io.github.openvoxelstandards.jeometry.indices.ThreeIndex;
import io.github.openvoxelstandards.jeometry.indices.TwoInd;
import io.github.openvoxelstandards.jeometry.positions.HorizontalPosition;
import io.github.openvoxelstandards.jeometry.positions.ThreePos;
import io.github.openvoxelstandards.jeometry.positions.ThreePosition;
import io.github.openvoxelstandards.jeometry.positions.TwoPos;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Cylinder extends Rectangle implements BaseSpace {
    int doubleSizeX;

    @Contract(pure = true)
    public Cylinder(int sizeX, int sizeY) {
        super(sizeX, sizeY);
        this.doubleSizeX = 2 * this.sizeX;
    }

    @Contract(pure = true)
    static double wrap(double value, int doubleSize) {
        return Math.min(Math.abs(value), Math.abs(value - Math.copySign(doubleSize, value)));
    }

    @Contract(pure = true)
    static int wrap(int value, int doubleSize) {
        return Math.min(Math.abs(value), Math.abs(value - doubleSize * Integer.signum(value)));
    }

    @Override
    public double distanceEuclidean(@NotNull HorizontalIndex a, @NotNull HorizontalIndex b) {
        @NotNull HorizontalIndex index = this.sub(b, a);
        return super.normEuclidean(new TwoInd(
            Cylinder.wrap(index.getX(), this.doubleSizeX),
            index.getY()
        ));
    }

    @Override
    public double distanceEuclidean(@NotNull ThreeIndex a, @NotNull ThreeIndex b) {
        @NotNull ThreeIndex threeIndex = this.sub(b, a);
        return super.normEuclidean(new ThreeInd(
            Cylinder.wrap(threeIndex.getX(), this.doubleSizeX),
            threeIndex.getY(),
            threeIndex.getZ()
        ));
    }

    @Override
    public double distanceEuclidean(@NotNull HorizontalPosition a, @NotNull HorizontalPosition b) {
        @NotNull HorizontalPosition position = this.sub(b, a);
        return super.normEuclidean(new TwoPos(
            Cylinder.wrap(position.getX(), this.doubleSizeX),
            position.getY()
        ));
    }

    @Override
    public double distanceEuclidean(@NotNull ThreePosition a, @NotNull ThreePosition b) {
        @NotNull ThreePosition position = this.sub(b, a);
        return super.normEuclidean(new ThreePos(
            Cylinder.wrap(position.getX(), this.doubleSizeX),
            position.getY(),
            position.getZ()
        ));
    }
}
