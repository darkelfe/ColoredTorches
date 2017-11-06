package coloredtorches.registers;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Items registry.
 * 
 * @author Julien Rosset
 */
public class Items
{	
	public static void init()
	{}
	
	private static <T extends Item> T register(T item)
	{
		GameRegistry.register(item);		
		return item;
	}
}
