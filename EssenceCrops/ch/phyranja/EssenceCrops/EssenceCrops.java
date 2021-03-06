package ch.phyranja.EssenceCrops;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import ch.modjam.generic.RegistryUtil;
import ch.phyranja.EssenceCrops.blocks.*;
import ch.phyranja.EssenceCrops.entities.TileEntityExtractor;
import ch.phyranja.EssenceCrops.essences.*;
import ch.phyranja.EssenceCrops.items.*;
import ch.phyranja.EssenceCrops.lib.*;
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
	
	public static EssenceExtractor extractor;
	
	public static EssenceSeed[] seeds=new EssenceSeed[EssenceType.values().length];
	public static EssencePlant[] plants=new EssencePlant[EssenceType.values().length];
	public static EssencePetal[] petals=new EssencePetal[EssenceType.values().length];
	public static BigEssenceCapsule[] bigCapsules=new BigEssenceCapsule[EssenceType.values().length];
	public static SmallEssenceCapsule[] smallCapsules=new SmallEssenceCapsule[EssenceType.values().length];
	public static EssenceFluid[] fluids=new EssenceFluid[EssenceType.values().length];
	public static BlockEssenceFluid[] fluidBlocks=new BlockEssenceFluid[EssenceType.values().length];

	@Mod.Instance("essencecrops")
	public static EssenceCrops instance;
		
	/**
	 * @param e  
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		System.out.println("PreInit EssenceCrops");
		createCreativeTab();
		
		Names.initNames();
		
		addItems();
		addPlants();
		addMachines();
		addFluids();
		

		proxy.registerRenderInformation();
	}
	
	private void addFluids() {
		for(EssenceType essence: EssenceType.values()){
			fluids[essence.ordinal()]=new EssenceFluid(Names.essenceFluids[essence.ordinal()], essence);
			FluidRegistry.registerFluid(fluids[essence.ordinal()]);
		
			fluidBlocks[essence.ordinal()]=new BlockEssenceFluid(fluids[essence.ordinal()], essence);
			GameRegistry.registerBlock(fluidBlocks[essence.ordinal()], Names.essenceFluidBlocks[essence.ordinal()]);
			
		
		}
		
	}

	private void addMachines() {
		extractor=new EssenceExtractor();
		RegistryUtil.registerBlock(extractor, TileEntityExtractor.class);
	
		
	}

	private void addItems() {
		
		for(EssenceType essence: EssenceType.values()){
			petals[essence.ordinal()]=new EssencePetal(essence);
			GameRegistry.registerItem(petals[essence.ordinal()], Names.petals[essence.ordinal()]);
		
			seeds[essence.ordinal()]=new EssenceSeed(essence);
			GameRegistry.registerItem(seeds[essence.ordinal()], Names.seeds[essence.ordinal()]);
		
			bigCapsules[essence.ordinal()]=new BigEssenceCapsule(essence);
			GameRegistry.registerItem(bigCapsules[essence.ordinal()], Names.bigCapsules[essence.ordinal()]);
		
			smallCapsules[essence.ordinal()]=new SmallEssenceCapsule(essence);
			GameRegistry.registerItem(smallCapsules[essence.ordinal()], Names.smallCapsules[essence.ordinal()]);
		
			
		
		}
	}

	private void addPlants() {
		
		for(EssenceType essence: EssenceType.values()){
			plants[essence.ordinal()]=new EssencePlant(essence);
			GameRegistry.registerBlock(plants[essence.ordinal()], Names.plants[essence.ordinal()]);
		}
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
				return EssenceCrops.seeds[0];
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
