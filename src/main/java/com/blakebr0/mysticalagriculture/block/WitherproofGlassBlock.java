package com.blakebr0.mysticalagriculture.block;

import com.blakebr0.cucumber.block.BaseGlassBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class WitherproofGlassBlock extends BaseGlassBlock {
    public WitherproofGlassBlock() {
        super(Material.GLASS, p -> p
            .strength(18.0F, 2000.0F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()
        );
    }

    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion explosion) { }

    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }
}
