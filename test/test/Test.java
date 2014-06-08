package test;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = Test.MODID, version = Test.VERSION)
public class Test {

	public static final String MODID = "Test";
	public static final String VERSION = "0.1";
	
	public static CreativeTabs tabCustom = new CreativeTabs("tabTest") {
		
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Items.diamond;
		}
	};
	
	public static BlockTestInventory blockTestInventory = new BlockTestInventory();
	public static BlockPlasmaTest blockPlasmaTest = new BlockPlasmaTest();
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		//GameRegistry.registerBlock(blockTestInventory, "testInventory");
		//GameRegistry.registerBlock(blockPlasmaTest, "plasmaTest");
		//GameRegistry.registerTileEntity(TileEntityTestInventory.class, "testEnt");
		//GameRegistry.registerTileEntity(TileEntityPlasma.class, "plasmaEnt");
	}
	
}