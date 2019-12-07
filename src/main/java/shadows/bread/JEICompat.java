package shadows.bread;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEICompat implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		registry.addIngredientInfo(new ItemStack(BreadNStuff.DOUGH), VanillaTypes.ITEM, "info.breadnstuff.dough");
		registry.addIngredientInfo(new ItemStack(BreadNStuff.BLACK_GARLIC), VanillaTypes.ITEM, "info.breadnstuff.black_garlic");
	}

}
