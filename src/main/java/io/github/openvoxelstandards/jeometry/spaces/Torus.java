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

public class Torus extends Rectangle implements BaseSpace {
    int doubleSizeX, doubleSizeY;

    @Contract(pure = true)
    public Torus(int sizeX, int sizeY) {
        super(sizeX, sizeY);
        this.doubleSizeX = 2 * this.sizeX;
        this.doubleSizeY = 2 * this.sizeY;
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
            Torus.wrap(index.getX(), this.doubleSizeX),
            Torus.wrap(index.getY(), this.doubleSizeY)
        ));
    }

    @Override
    public double distanceEuclidean(@NotNull ThreeIndex a, @NotNull ThreeIndex b) {
        @NotNull ThreeIndex index = this.sub(b, a);
        return super.normEuclidean(new ThreeInd(
            Torus.wrap(index.getX(), this.doubleSizeX),
            Torus.wrap(index.getY(), this.doubleSizeY),
            index.getZ()
        ));
    }

    @Override
    public double distanceEuclidean(@NotNull HorizontalPosition a, @NotNull HorizontalPosition b) {
        @NotNull HorizontalPosition position = this.sub(b, a);
        return super.normEuclidean(new TwoPos(
            Torus.wrap(position.getX(), this.doubleSizeX),
            Torus.wrap(position.getY(), this.doubleSizeY)
        ));
    }

    @Override
    public double distanceEuclidean(@NotNull ThreePosition a, @NotNull ThreePosition b) {
        @NotNull ThreePosition position = this.sub(b, a);
        return super.normEuclidean(new ThreePos(
            Torus.wrap(position.getX(), this.doubleSizeX),
            Torus.wrap(position.getY(), this.doubleSizeY),
            position.getZ()
        ));
    }
}
