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
import io.github.openvoxelstandards.geometry.indices.TwoInd;
import io.github.openvoxelstandards.geometry.positions.HorizontalPosition;
import io.github.openvoxelstandards.geometry.positions.TwoPos;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Rectangle implements BaseSpace {
    int sizeX, sizeY;
    @NotNull TwoInd lowerBoundInd, upperBoundInd;
    @NotNull TwoPos lowerBoundPos, upperBoundPos;

    @Contract(pure = true)
    public Rectangle(int sizeX, int sizeY) {
        if (sizeX < 1)
            throw new IllegalArgumentException(String.format(
                "expected a positive integer for sizeX, got %s instead", sizeX));
        if (sizeY < 1)
            throw new IllegalArgumentException(String.format(
                "expected a positive integer for sizeY, got %s instead", sizeY));
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.lowerBoundInd = new TwoInd(-this.sizeX, -this.sizeY);
        this.upperBoundInd = new TwoInd(this.sizeX, this.sizeY);
        this.lowerBoundPos = new TwoPos(-this.sizeX, -this.sizeY);
        this.upperBoundPos = new TwoPos(this.sizeX + 1, this.sizeY + 1);
    }

    @Override
    public boolean isInside(@NotNull HorizontalIndex horizontalIndex) {
        return this.lowerBoundInd.getX() <= horizontalIndex.getX() &&
            this.lowerBoundInd.getY() <= horizontalIndex.getY() &&
            horizontalIndex.getX() <= this.upperBoundInd.getX() &&
            horizontalIndex.getY() <= this.upperBoundInd.getY();
    }

    @Override
    public boolean isInside(@NotNull HorizontalPosition horizontalDisplacement) {
        return this.lowerBoundPos.getX() <= horizontalDisplacement.getX() &&
            this.lowerBoundPos.getY() <= horizontalDisplacement.getY() &&
            horizontalDisplacement.getX() <= this.upperBoundPos.getX() &&
            horizontalDisplacement.getY() <= this.upperBoundPos.getY();
    }
}
