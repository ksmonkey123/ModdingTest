package ch.judos.at;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumChatFormatting;
import ch.judos.at.blocks.Station;
import ch.judos.at.lib.CommonProxy;
import ch.judos.at.lib.References;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * if you need to add dependencies, add the following in the @Mod() tag:<br>
 * dependencies="required-after:"modid"@["version"]"
 */
@Mod(modid = References.MOD_ID, version = References.VERSION, name = References.NAME)
@SuppressWarnings("javadoc")
public class ModMain {

	/**
	 * public instance of this mod
	 */
	@Mod.Instance(References.MOD_ID)
	public static ModMain		instance;

	@SidedProxy(clientSide = References.Client, serverSide = References.Common)
	public static CommonProxy	proxy;

	public static CreativeTabs	modTab;

	public static Station		station;

	/**
	 * @param e
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		setMetaData(e.getModMetadata());

		registerStation();

		createCreativeTab();
		proxy.registerRenderInformation();
	}

	private static void registerStation() {
		station = new Station();
	}

	private static void setMetaData(ModMetadata m) {
		m.modId = References.MOD_ID;
		m.name = EnumChatFormatting.GREEN + References.NAME;
		m.description = "A scrambled assembly line of tutorial informational implementation content.";
		m.version = References.VERSION;
		m.authorList.add("judos (judos@gmx.ch)");
		m.credits = "The Forge and FML guys, for making the example mod, Lunatrius for the mcmod.info file, LexManos for answering multiple requests, diesieben07 for answering numerous questions in the IRC";
		m.autogenerated = false;
	}

	private static void createCreativeTab() {
		modTab = new CreativeTabs(References.NAME) {
			@Override
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return new ItemBlock(ModMain.station);
			}

			@Override
			@SideOnly(Side.CLIENT)
			public String getTabLabel() {
				return "Aerial Transports";
			}

			@Override
			@SideOnly(Side.CLIENT)
			public String getTranslatedTabLabel() {
				return "Aerial Transports";
			}
		};
	}
}
