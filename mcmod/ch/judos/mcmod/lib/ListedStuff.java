package ch.judos.mcmod.lib;

import java.util.ArrayList;

import net.minecraftforge.oredict.OreDictionary;

public class ListedStuff {
	private static boolean initialized;
	private static ArrayList<String> ores;

	public static ArrayList<String> getOreNames() {
		initialize();
		return ores;
	}

	private static void initialize() {
		if (initialized)
			return;
		String[] names = OreDictionary.getOreNames();
		ores = new ArrayList<String>(); 
		for(String ore : names) {
			if (ore.startsWith("ore"))
				ores.add(ore);
		}
	}
}
