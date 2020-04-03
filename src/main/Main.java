package main;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Wei
 *
 */
public class Main extends JavaPlugin
{
	Record record;
	
	@Override
	public void onEnable()
	{
		getLogger().info("Loading config...");
		record = new Record("./plugins/BanItemPlus.txt");
		record.Load();

		getLogger().info("Registering events...");
		getServer().getPluginManager().registerEvents(new ItemUseListener(this, record), this);
		
		getLogger().info("Ready.");
		
		// TODO: 使用指令修改配置
	}
	
	@Override
	public void onDisable()
	{
		record.Save();
	}
}
