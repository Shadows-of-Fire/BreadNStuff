package shadows.bread;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockGarlic extends BlockCrops {

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(BreadNStuff.GARLIC);
	}

	@Override
	protected Item getCrop() {
		return BreadNStuff.GARLIC;
	}

	@Override
	protected Item getSeed() {
		return BreadNStuff.GARLIC;
	}

}
