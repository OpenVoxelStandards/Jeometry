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
import io.github.openvoxelstandards.geometry.indices.ThreeInd;
import io.github.openvoxelstandards.geometry.indices.ThreeIndex;
import io.github.openvoxelstandards.geometry.indices.TwoInd;
import io.github.openvoxelstandards.geometry.indices.VoxelIndex;
import io.github.openvoxelstandards.geometry.positions.HorizontalPosition;
import io.github.openvoxelstandards.geometry.positions.ThreePos;
import io.github.openvoxelstandards.geometry.positions.ThreePosition;
import io.github.openvoxelstandards.geometry.positions.TwoPos;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public interface BaseSpace {
    @Contract(pure = true)
    default double normManhattan(@NotNull HorizontalIndex horizontalDisplacement) {
        return Math.abs(horizontalDisplacement.getX()) + Math.abs(horizontalDisplacement.getY());
    }

    @Contract(pure = true)
    default double normManhattan(@NotNull ThreeIndex threeIndex) {
        return this.normManhattan((HorizontalIndex) threeIndex) + Math.abs(threeIndex.getZ());
    }

    @Contract(pure = true)
    default double normManhattan(@NotNull HorizontalPosition horizontalDisplacement) {
        return Math.abs(horizontalDisplacement.getX()) + Math.abs(horizontalDisplacement.getY());
    }

    @Contract(pure = true)
    default double normManhattan(@NotNull ThreePosition threeDisplacement) {
        return this.normManhattan((HorizontalPosition) threeDisplacement) + Math.abs(threeDisplacement.getZ());
    }

    @Contract(pure = true)
    default double normEuclidean(@NotNull HorizontalIndex horizontalIndex) {
        return Math.hypot(horizontalIndex.getX(), horizontalIndex.getY());
    }

    @Contract(pure = true)
    default double normEuclidean(@NotNull ThreeIndex threeIndex) {
        return Math.sqrt(threeIndex.getX() * threeIndex.getX() + threeIndex.getY() * threeIndex.getY() + threeIndex.getZ() * threeIndex.getZ());
    }

    @Contract(pure = true)
    default double normEuclidean(@NotNull HorizontalPosition horizontalDisplacement) {
        return Math.hypot(horizontalDisplacement.getX(), horizontalDisplacement.getY());
    }

    @Contract(pure = true)
    default double normEuclidean(@NotNull ThreePosition threeDisplacement) {
        return Math.sqrt(threeDisplacement.getX() * threeDisplacement.getX() + threeDisplacement.getY() * threeDisplacement.getY() + threeDisplacement.getZ() * threeDisplacement.getZ());
    }

    @Contract(pure = true)
    default double normInfinity(@NotNull HorizontalIndex horizontalIndex) {
        return Math.max(horizontalIndex.getX(), horizontalIndex.getY());
    }

    @Contract(pure = true)
    default double normInfinity(@NotNull ThreeIndex threeIndex) {
        return Math.max(this.normInfinity((HorizontalPosition) threeIndex), threeIndex.getZ());
    }

    @Contract(pure = true)
    default double normInfinity(@NotNull HorizontalPosition horizontalDisplacement) {
        return Math.max(horizontalDisplacement.getX(), horizontalDisplacement.getY());
    }

    @Contract(pure = true)
    default double normInfinity(@NotNull ThreePosition threeDisplacement) {
        return Math.max(this.normInfinity((HorizontalPosition) threeDisplacement), threeDisplacement.getZ());
    }

    default double distanceEuclidean(@NotNull HorizontalIndex a, @NotNull HorizontalIndex b) {
        return this.normEuclidean(this.sub(b, a));
    }

    default double distanceEuclidean(@NotNull ThreeIndex a, @NotNull ThreeIndex b) {
        return this.normEuclidean(this.sub(b, a));
    }

    default double distanceEuclidean(@NotNull HorizontalPosition a, @NotNull HorizontalPosition b) {
        return this.normEuclidean(this.sub(b, a));
    }

    default double distanceEuclidean(@NotNull ThreePosition a, @NotNull ThreePosition b) {
        return this.normEuclidean(this.sub(b, a));
    }

    boolean isInside(@NotNull HorizontalIndex horizontalIndex);

    default boolean isInside(@NotNull ThreeIndex threeIndex) {
        return this.isInside((HorizontalIndex) threeIndex);
    }

    boolean isInside(@NotNull HorizontalPosition horizontalDisplacement);

    default boolean isInside(@NotNull ThreePosition threeDisplacement) {
        return this.isInside((HorizontalPosition) threeDisplacement);
    }

    default @NotNull HorizontalIndex add(@NotNull HorizontalIndex a, @NotNull HorizontalIndex b) {
        return new TwoInd(a.getX() + b.getX(), a.getY() + b.getY());
    }

    default @NotNull HorizontalIndex add(@NotNull HorizontalIndex a, @NotNull ThreeIndex b) {
        return this.add(a, (HorizontalIndex) b);
    }

    default @NotNull ThreeIndex add(@NotNull ThreeIndex a, @NotNull HorizontalIndex b) {
        return new ThreeInd(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ());
    }

    default @NotNull ThreeIndex add(@NotNull ThreeIndex a, @NotNull ThreeIndex b) {
        return new ThreeInd(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    default @NotNull HorizontalPosition add(@NotNull HorizontalPosition a, @NotNull HorizontalPosition b) {
        return new TwoPos(a.getX() + b.getX(), a.getY() + b.getY());
    }

    default @NotNull HorizontalPosition add(@NotNull HorizontalPosition a, @NotNull ThreePosition b) {
        return this.add(a, (HorizontalPosition) b);
    }

    default @NotNull ThreePosition add(@NotNull ThreePosition a, @NotNull HorizontalPosition b) {
        return new ThreePos(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ());
    }

    default @NotNull ThreePosition add(@NotNull ThreePosition a, @NotNull ThreePosition b) {
        return new ThreePos(a.getX() + b.getX(), a.getY() + b.getY(), a.getZ() + b.getZ());
    }

    default @NotNull HorizontalIndex sub(@NotNull HorizontalIndex a, @NotNull HorizontalIndex b) {
        return new TwoInd(a.getX() - b.getX(), a.getY() - b.getY());
    }

    default @NotNull HorizontalIndex sub(@NotNull HorizontalIndex a, @NotNull VoxelIndex b) {
        return this.sub(a, (HorizontalIndex) b);
    }

    default @NotNull ThreeIndex sub(@NotNull ThreeIndex a, @NotNull HorizontalIndex b) {
        return new ThreeInd(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ());
    }

    default @NotNull ThreeIndex sub(@NotNull ThreeIndex a, @NotNull ThreeIndex b) {
        return new ThreeInd(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }

    default @NotNull HorizontalPosition sub(@NotNull HorizontalPosition a, @NotNull HorizontalPosition b) {
        return new TwoPos(a.getX() - b.getX(), a.getY() - b.getY());
    }

    default @NotNull HorizontalPosition sub(@NotNull HorizontalPosition a, @NotNull ThreePosition b) {
        return this.sub(a, (HorizontalPosition) b);
    }

    default @NotNull ThreePosition sub(@NotNull ThreePosition a, @NotNull HorizontalPosition b) {
        return new ThreePos(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ());
    }

    default @NotNull ThreePosition sub(@NotNull ThreePosition a, @NotNull ThreePosition b) {
        return new ThreePos(a.getX() - b.getX(), a.getY() - b.getY(), a.getZ() - b.getZ());
    }
}
