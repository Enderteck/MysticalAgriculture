package com.blakebr0.mysticalagriculture.items.tools;

import java.util.List;

import com.blakebr0.mysticalagriculture.MysticalAgriculture;
import com.blakebr0.mysticalagriculture.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSupremiumHoe extends ItemHoe {
	
	public ItemSupremiumHoe(String name, ToolMaterial material) {
		super(material);
		this.setUnlocalizedName("ma." + name);
		this.setRegistryName(name);
		this.setCreativeTab(MysticalAgriculture.tabMysticalAgriculture);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		if(ModConfig.confSneakHoeAOE){ tooltip.add("Hold " + Minecraft.getMinecraft().gameSettings.keyBindSneak.getDisplayName() +  " for \u00A7c3x3\u00A77."); }
		tooltip.add("Durability: \u00A7cUnlimited");
		tooltip.add("Charm Slot: \u00A7c\u00A7oEmpty");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {		
		if(!canHoe(stack, player, world, pos, facing) && !player.isSneaking()){
			return EnumActionResult.FAIL;
		}
		
		Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos.add(-1, 0, -1), pos.add(1, 0, 1));
		
		if(player.isSneaking() && ModConfig.confSneakHoeAOE){
			for(BlockPos aoePos : blocks){
				canHoe(stack, player, world, aoePos, facing);
			}
		} else {
			canHoe(stack, player, world, pos, facing);
		}
		return EnumActionResult.SUCCESS;
	}
	
	@SuppressWarnings("incomplete-switch")
	private boolean canHoe(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing face){
        if(!player.canPlayerEdit(pos.offset(face), face, stack)){
            return false;
        } else {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(stack, player, world, pos);
            if(hook != 0) return hook > 0 ? true : false;

            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if(face != EnumFacing.DOWN && world.isAirBlock(pos.up())){
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH){
                    this.setBlock(stack, player, world, pos, Blocks.FARMLAND.getDefaultState());
                    return true;
                }

                if (block == Blocks.DIRT){
                    switch ((BlockDirt.DirtType)iblockstate.getValue(BlockDirt.VARIANT)){
                        case DIRT:
                            this.setBlock(stack, player, world, pos, Blocks.FARMLAND.getDefaultState());
                            return true;
                        case COARSE_DIRT:
                            this.setBlock(stack, player, world, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return true;
                    }
                }
            }
            return false;
        }
	}
}