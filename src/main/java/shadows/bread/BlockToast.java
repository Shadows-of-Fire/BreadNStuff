package shadows.bread;

import net.minecraft.block.BlockCake;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockToast extends BlockCake {

	static double px = 1 / 16D;

	protected static final AxisAlignedBB[] CAKE_AABB = new AxisAlignedBB[] { new AxisAlignedBB(3 * px, 0.0D, 2 * px, 13 * px, 8 * px, 14 * px), new AxisAlignedBB(4 * px, 0.0D, 2 * px, 13 * px, 8 * px, 14 * px), new AxisAlignedBB(5 * px, 0.0D, 2 * px, 13 * px, 8 * px, 14 * px), new AxisAlignedBB(7 * px, 0.0D, 2 * px, 13 * px, 8 * px, 14 * px), new AxisAlignedBB(8 * px, 0.0D, 2 * px, 13 * px, 8 * px, 14 * px), new AxisAlignedBB(10 * px, 0.0D, 2 * px, 13 * px, 8 * px, 14 * px), new AxisAlignedBB(11 * px, 0.0D, 2 * px, 13 * px, 8 * px, 14 * px) };

	final ItemFood toast;

	public BlockToast(ItemFood toast) {
		setHardness(0.5F);
		setSoundType(SoundType.CLOTH);
		disableStats();
		this.toast = toast;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			return this.eatCake(worldIn, pos, state, playerIn);
		} else {
			ItemStack itemstack = playerIn.getHeldItem(hand);
			return this.eatCake(worldIn, pos, state, playerIn) || itemstack.isEmpty();
		}
	}

	protected boolean eatCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (!player.canEat(false)) {
			return false;
		} else {
			player.getFoodStats().addStats(toast.getHealAmount(ItemStack.EMPTY), toast.getSaturationModifier(ItemStack.EMPTY));
			int i = state.getValue(BITES);

			if (i < 6) {
				worldIn.setBlockState(pos, state.withProperty(BITES, Integer.valueOf(i + 1)), 3);
			} else {
				worldIn.setBlockToAir(pos);
			}

			return true;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CAKE_AABB[state.getValue(BITES)];
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

}
