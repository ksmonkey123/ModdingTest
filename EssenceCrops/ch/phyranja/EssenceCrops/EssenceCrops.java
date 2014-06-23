package ch.phyranja.EssenceCrops;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ch.phyranja.EssenceCrops.items.*;
import ch.phyranja.EssenceCrops.lib.*;
import ch.phyranja.EssenceCrops.plants.*;
import ch.phyranja.EssenceCrops.world.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * if you need to add dependencies:<br>
 * dependencies="required-after:"modid"@["version"]"
 */
@SuppressWarnings("javadoc")
@Mod(modid = References.MOD_ID, version = References.VERSION)
public class EssenceCrops {
	@SidedProxy(clientSide = References.Client, serverSide = References.Common)
	public static CommonProxy proxy;

	public static CreativeTabs modTab;
	
	public static NeutralEssenceSeed neutralSeed;
	public static NeutralEssencePlant neutralPlant;
	public static NeutralEssencePetal neutralPetal;

	@Mod.Instance("essencecrops")
	public static EssenceCrops instance;
		
	/**
	 * @param e  
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println("PreInit EssenceCrops");
		createCreativeTab();
		
		addPetals();
		addPlants();
		addSeeds();
		

		proxy.registerRenderInformation();
	}
	
	private void addPetals() {
		neutralPetal = new NeutralEssencePetal();
		GameRegistry.registerItem(neutralPetal, Names.NeutralEPetal);
	}

	private void addPlants() {
		neutralPlant=new NeutralEssencePlant();
		GameRegistry.registerBlock(neutralPlant, Names.NeutralEP);
		
	}

	private void addSeeds() {

		neutralSeed = new NeutralEssenceSeed();
		GameRegistry.registerItem(neutralSeed, Names.NeutralES);
		
	}

	/**
	 * @param e  
	 */
	@EventHandler
	public void init(FMLInitializationEvent e) {
		System.out.println("Init EssenceCrops");
		
	}

	/**
	 * @param e  
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		System.out.println("PostInit EssenceCrops");

	}
	
	
	private static void createCreativeTab() {
		modTab = new CreativeTabs("EssenceCrops") {
			@Override
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return EssenceCrops.neutralSeed;
			}

			@Override
			@SideOnly(Side.CLIENT)
			public String getTabLabel() {
				return "EssenceCrops";
			}

			@Override
			@SideOnly(Side.CLIENT)
			public String getTranslatedTabLabel() {
				return "EssenceCrops";
			}
		};
	}
}
