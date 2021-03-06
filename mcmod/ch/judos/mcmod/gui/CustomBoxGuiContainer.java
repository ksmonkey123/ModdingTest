package ch.judos.mcmod.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import ch.judos.mcmod.lib.Names;
import ch.judos.mcmod.lib.References;
import ch.modjam.generic.gui.GenericGuiTEContainer;

/**
 * @author judos
 */
public class CustomBoxGuiContainer extends GenericGuiTEContainer {

	private CustomBoxTE	customBoxTE;

	/**
	 * @param inventory
	 * @param te
	 */
	public CustomBoxGuiContainer(InventoryPlayer inventory, CustomBoxTE te) {
		super(new CustomBoxContainer(inventory, te), te, inventory);
		this.customBoxTE = te;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.guiLeft + 140, this.guiTop + 30, 30, 20, "+"));
		this.buttonList.add(new GuiButton(1, this.guiLeft + 140, this.guiTop + 50, 30, 20, "-"));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
			case 0:
				this.customBoxTE.tryIncreaseSize();
				break;
			case 1:
				this.customBoxTE.tryDecreaseSize();
				break;
			default:
				break;
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		String s = StatCollector.translateToLocal("tile." + Names.CustomBox + ".name");
		int color = 4210752;
		this.fontRendererObj.drawString(s,
			this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, color);
		this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8,
			this.ySize - 96 + 2, color);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation(
			References.MOD_ID + ":textures/gui/" + Names.CustomBox + ".png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		for (int i = 1; i < this.customBoxTE.inventory.stack.length; i++)
			this.drawTexturedModalRect(this.guiLeft + 25 + 18 * i, this.guiTop + 41, 176, 0, 18, 18);
	}

}
