package ch.judos.at.lib;

import ch.judos.at.station.StationRenderer;
import ch.judos.at.station.TEStation;
import ch.judos.at.station.entity.EntityGondola;
import ch.judos.at.station.entity.RendererGondola;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

@SuppressWarnings("javadoc")
public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderInformation() {

		ClientRegistry.bindTileEntitySpecialRenderer(TEStation.class, new StationRenderer());

		RenderingRegistry
			.registerEntityRenderingHandler(EntityGondola.class, new RendererGondola());

		// How to register item renderers:
		// MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MCMod.blockCarvedDirt),
		// new TECarvedDirtItemRenderer());

	}

}
