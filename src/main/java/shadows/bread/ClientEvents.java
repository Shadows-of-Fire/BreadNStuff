package shadows.bread;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = BreadNStuff.MODID, value = Side.CLIENT)
public class ClientEvents {

	@SubscribeEvent
	public static void models(ModelRegistryEvent e) {
		BreadNStuff.items.forEach(ClientEvents::model);
		model(Item.getItemFromBlock(BreadNStuff.TOAST_BLOCK));
		model(Item.getItemFromBlock(BreadNStuff.CHEESE_TOAST_BLOCK));
		model(Item.getItemFromBlock(BreadNStuff.GARLIC_TOAST_BLOCK));
		model(Item.getItemFromBlock(BreadNStuff.GARLIC_CHEESE_TOAST_BLOCK));
	}

	static void model(Item... items) {
		for (Item i : items)
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
	}

}
