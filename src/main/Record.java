package main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.*;
import org.yaml.snakeyaml.Yaml;

/*
 * @author Wei
 *
 */
public class Record {
	
	public Record(String fileName)
	{
		this.fileName = fileName;
		maxEnchantmentLevel = new HashMap<Enchantment, Integer>();
	}
	
	public Integer getMaxEnchantmentLevel(Enchantment e)
	{
		if (maxEnchantmentLevel.containsKey(e))
			return maxEnchantmentLevel.get(e);
		else
			return Integer.MAX_VALUE;
	}
	
	public void Load()
	{
		Map<String, Object> res = LoadYaml(fileName);
		if (res == null)
			return;
		
		System.out.println("File found.");
		Map<String, Integer> maxEnchantmentLevelString = (Map<String, Integer>)(res.get("maxEnchantmentLevel"));
		if (maxEnchantmentLevelString != null)
			for (Map.Entry<String, Integer> e : maxEnchantmentLevelString.entrySet())
			{
				System.out.println(e.getKey() + ": " + e.getValue().toString());
				maxEnchantmentLevel.put(Enchantment.getByKey(NamespacedKey.minecraft(e.getKey())), e.getValue());
			}
	}

	public void Save()
	{
	}
	
	private static Map<String, Object> LoadYaml(String fileName)
	{
        Yaml yaml = new Yaml();
        try
        {
			return yaml.loadAs(new FileInputStream(fileName), Map.class);
        } catch (FileNotFoundException e)
        {
    		return new HashMap<String, Object>();
        }
	}
	
	private String fileName;
	private Map<Enchantment, Integer> maxEnchantmentLevel;
}
