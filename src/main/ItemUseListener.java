package main;

import java.util.Map;

import org.bukkit.enchantments.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

/**
 * @author Wei
 *
 */
public class ItemUseListener implements Listener{
	
	Main main;
	Record record;
	
	public ItemUseListener(Main main, Record record)
	{
		this.main = main;
		this.record = record;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		String p = event.getPlayer().getName();
		String i = event.getItem().getType().name();
		String h = event.getHand().name();
		main.getLogger().info(p + " triggered event: " + i + ", " + h + ".");
		
		ItemStack item = event.getItem();
		Map<Enchantment, Integer> enchantments = item.getEnchantments();
		
		// 判断有没有附魔超过等级限制
		boolean flag = false;
		for (Map.Entry<Enchantment, Integer> e : enchantments.entrySet())
		{
			int a = e.getValue();
			int b = record.getMaxEnchantmentLevel(e.getKey());
			main.getLogger().info("Enchantment " + e.getKey().getKey().getKey() + ": " + a + " vs " + b + ".");
			if (a > b)
			{
				flag = true;
				break;
			}
		}
		
		if (flag)
		{
			main.getLogger().info("Cancelled.");
			event.setCancelled(true);
		}
	}
}
