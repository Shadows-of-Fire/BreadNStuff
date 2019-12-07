package shadows.bread;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = BreadNStuff.MODID, name = BreadNStuff.MODID, version = BreadNStuff.VERSION)
public class BreadNStuff {

	public static final String MODID = "breadnstuff";
	public static final String NAME = "Bread'n'Stuff";
	public static final String VERSION = "2.0.0";

	public static final ItemFood TOAST = new ItemFood(5, 1, false);
	public static final Item DOUGH = new Item();
	public static final Item CHEESE = new Item();
	public static final Item CHEESE_DOUGH = new Item();
	public static final ItemFood CHEESE_BREAD = new ItemFood(10, 0.6F, false);
	public static final ItemFood CHEESE_TOAST = new ItemFood(10, 0.8F, false);

	public static final Item GARLIC_DOUGH = new Item();
	public static final Item GARLIC_CHEESE_DOUGH = new Item();
	public static final ItemFood GARLIC_BREAD = new ItemFood(5, 0.8F, false);
	public static final ItemFood GARLIC_TOAST = new ItemFood(5, 1.2F, false);
	public static final ItemFood GARLIC_CHEESE_BREAD = new ItemFood(10, 0.7F, false);
	public static final ItemFood GARLIC_CHEESE_TOAST = new ItemFood(10, 0.9F, false);
	public static final ItemFood ROASTED_GARLIC = new ItemFood(3, 0.66F, false);
	public static final ItemFood BLACK_GARLIC = new ItemFood(3, 0.66F, false).setPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 600), 1).setAlwaysEdible();

	public static final BlockToast TOAST_BLOCK = new BlockToast(TOAST);
	public static final BlockToast CHEESE_TOAST_BLOCK = new BlockToast(CHEESE_TOAST);
	public static final BlockToast GARLIC_TOAST_BLOCK = new BlockToast(GARLIC_TOAST);
	public static final BlockToast GARLIC_CHEESE_TOAST_BLOCK = new BlockToast(GARLIC_CHEESE_TOAST);
	public static final BlockGarlic GARLIC_CROP = new BlockGarlic();
	public static final ItemSeedFood GARLIC = new ItemSeedFood(2, 0.5F, GARLIC_CROP, Blocks.FARMLAND);

	public static final CreativeTabs TAB = new CreativeTabs(MODID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(TOAST);
		}
	};

	public static boolean cheeseReturnsBucket = false;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		Configuration c = new Configuration(e.getSuggestedConfigurationFile());
		cheeseReturnsBucket = c.getBoolean("Cheese Returns Bucket", "general", false, "If cheese returns the bucket item when used.");
		if (c.hasChanged()) c.save();
	}

	@SubscribeEvent
	public void blocks(Register<Block> e) {
		//Formatter::off
		e.getRegistry().registerAll(
			TOAST_BLOCK.setRegistryName("toast_block").setTranslationKey(MODID + ".toast").setCreativeTab(TAB), 
			CHEESE_TOAST_BLOCK.setRegistryName("cheese_toast_block").setTranslationKey(MODID + ".cheese_toast").setCreativeTab(TAB),
			GARLIC_TOAST_BLOCK.setRegistryName("garlic_toast_block").setTranslationKey(MODID + ".garlic_toast").setCreativeTab(TAB), 
			GARLIC_CHEESE_TOAST_BLOCK.setRegistryName("garlic_cheese_toast_block").setTranslationKey(MODID + ".garlic_cheese_toast").setCreativeTab(TAB),
			GARLIC_CROP.setRegistryName("garlic").setTranslationKey(MODID + ".garlic").setCreativeTab(TAB)
		);
		//Formatter::on
	}

	static List<Item> items;

	@SubscribeEvent
	public void items(Register<Item> e) {
		//Formatter::off
		items = ImmutableList.of(
				TOAST.setRegistryName("toast"), 
				DOUGH.setRegistryName("dough"), 
				CHEESE.setRegistryName("cheese"), 
				CHEESE_DOUGH.setRegistryName("cheese_dough"), 
				CHEESE_BREAD.setRegistryName("cheese_bread"), 
				CHEESE_TOAST.setRegistryName("cheese_toast"),
				new ItemBlock(TOAST_BLOCK).setRegistryName(TOAST_BLOCK.getRegistryName()),
				new ItemBlock(CHEESE_TOAST_BLOCK).setRegistryName(CHEESE_TOAST_BLOCK.getRegistryName()),
				GARLIC_DOUGH.setRegistryName("garlic_dough"),
				GARLIC_CHEESE_DOUGH.setRegistryName("garlic_cheese_dough"),
				GARLIC_BREAD.setRegistryName("garlic_bread"),
				GARLIC_TOAST.setRegistryName("garlic_toast"),
				GARLIC_CHEESE_BREAD.setRegistryName("garlic_cheese_bread"),
				GARLIC_CHEESE_TOAST.setRegistryName("garlic_cheese_toast"),
				ROASTED_GARLIC.setRegistryName("roasted_garlic"),
				BLACK_GARLIC.setRegistryName("black_garlic"),
				GARLIC.setRegistryName("garlic"),
				new ItemBlock(GARLIC_TOAST_BLOCK).setRegistryName(GARLIC_TOAST_BLOCK.getRegistryName()),
				new ItemBlock(GARLIC_CHEESE_TOAST_BLOCK).setRegistryName(GARLIC_CHEESE_TOAST_BLOCK.getRegistryName()));
		//Formatter::on
		items.forEach(e.getRegistry()::register);
		items.forEach(i -> i.setCreativeTab(TAB));
		items.forEach(i -> i.setTranslationKey(MODID + "." + i.getRegistryName().getPath()));
		if (cheeseReturnsBucket) {
			CHEESE.setContainerItem(Items.BUCKET);
		}
	}

	@SubscribeEvent
	public void recipes(Register<IRecipe> e) {
		GameRegistry.addSmelting(new ItemStack(Items.MILK_BUCKET), new ItemStack(CHEESE), 3);
		GameRegistry.addSmelting(new ItemStack(Items.BREAD), new ItemStack(TOAST), 2);
		GameRegistry.addSmelting(new ItemStack(CHEESE_BREAD), new ItemStack(CHEESE_TOAST), 2);
		GameRegistry.addSmelting(new ItemStack(CHEESE_DOUGH), new ItemStack(CHEESE_BREAD), 2);
		GameRegistry.addSmelting(new ItemStack(DOUGH), new ItemStack(Items.BREAD), 2);
		GameRegistry.addSmelting(new ItemStack(GARLIC), new ItemStack(ROASTED_GARLIC), 1);
		GameRegistry.addSmelting(new ItemStack(GARLIC_DOUGH), new ItemStack(GARLIC_BREAD), 2);
		GameRegistry.addSmelting(new ItemStack(GARLIC_CHEESE_DOUGH), new ItemStack(GARLIC_CHEESE_BREAD), 2);
		GameRegistry.addSmelting(new ItemStack(GARLIC_BREAD), new ItemStack(GARLIC_TOAST), 2);
		GameRegistry.addSmelting(new ItemStack(GARLIC_CHEESE_BREAD), new ItemStack(GARLIC_CHEESE_TOAST), 2);
		MinecraftForge.addGrassSeed(new ItemStack(GARLIC), 2);
	}

	@SubscribeEvent
	public void click(RightClickBlock e) {
		ItemStack held = e.getEntityPlayer().getHeldItem(e.getHand());
		BlockPos pos = e.getPos();
		IBlockState state = e.getWorld().getBlockState(pos);
		if (state.getBlock() == Blocks.CAULDRON && state.getValue(BlockCauldron.LEVEL) > 0) {
			if (held.getItem() == Items.WHEAT) {
				held.shrink(1);
				e.getEntityPlayer().swingArm(e.getHand());
				ItemStack s = new ItemStack(DOUGH, 2);
				if (!e.getWorld().isRemote) if (!e.getEntityPlayer().inventory.addItemStackToInventory(s)) {
					Block.spawnAsEntity(e.getWorld(), e.getEntityPlayer().getPosition(), s);
				}
				e.getWorld().playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1, 1, false);
				e.setUseBlock(Result.ALLOW);
			} else if (held.getItem() == ROASTED_GARLIC) {
				held.shrink(1);
				e.getEntityPlayer().swingArm(e.getHand());
				ItemStack s = new ItemStack(BLACK_GARLIC);
				if (!e.getWorld().isRemote) if (!e.getEntityPlayer().inventory.addItemStackToInventory(s)) {
					Block.spawnAsEntity(e.getWorld(), e.getEntityPlayer().getPosition(), s);
				}
				e.getWorld().playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1, 1, false);
				e.setUseBlock(Result.ALLOW);
			}
		}
	}

}
