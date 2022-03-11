package com.svegon.capi.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class InteractionUtil {
    private InteractionUtil() {
        throw new UnsupportedOperationException();
    }

    public static BlockHitResult targetBlock(@NotNull final WorldView world, @NotNull final Vec3d eyePos,
                                             @NotNull final BlockPos blockPos) {
        final BlockState state = world.getBlockState(blockPos);
        final VoxelShape shape = state.getOutlineShape(world, blockPos);

        if (shape.getBoundingBoxes().stream().anyMatch(box -> box.contains(eyePos))) {
            return new BlockHitResult(eyePos, RotationUtil.getFacing(eyePos), blockPos, true);
        }

        Optional<Vec3d> optional = shape.getClosestPointTo(eyePos);

        if (optional.isEmpty()) {
            return BlockHitResult.createMissed(Vec3d.ofCenter(blockPos), Direction.DOWN, blockPos);
        }

        Vec3d collisionPoint = optional.get();
        Direction side = Direction.fromRotation(RotationUtil.lookVector(eyePos, collisionPoint).x);

        return new BlockHitResult(collisionPoint, side, blockPos, false);
    }

    public static BlockHitResult targetBlock(@NotNull final WorldView world, @NotNull final Entity entity,
                                             @NotNull final BlockPos blockPos) {
        return targetBlock(world, entity.getEyePos(), blockPos);
    }
}
