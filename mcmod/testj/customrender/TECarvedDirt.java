package testj.customrender;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import testj.lib.Names;
import testj.lib.References;
import ch.modjam.generic.GenericTileEntity;

public class TECarvedDirt extends GenericTileEntity implements IConnecting, ICanalConnection {
	
	public TECarvedDirt() {
		
	}

	@Override
	public void tick() {
	}

	@Override
	public void writeNBT(NBTTagCompound tag) {
	}

	@Override
	public void readNBT(NBTTagCompound tag) {
	}

	public static String getTexture() {
		return References.MOD_ID + ":textures/blocks/"+Names.CarvedDirt+".png";
	}

	public boolean connectsTo(ForgeDirection dir) {
		TileEntity t = this.worldObj.getTileEntity(this.xCoord + dir.offsetX,
				this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
		if (t == null || !(t instanceof ICanalConnection))
			return false;
		ICanalConnection te = (ICanalConnection) t;
		return te.acceptsLiquid()
				|| te.providesLiquid();
	}

	@Override
	public boolean acceptsLiquid() {
		return true;
	}

	@Override
	public boolean providesLiquid() {
		return false;
	}

	@Override
	public int getMaxLiquidAmount() {
		return 100;
	}

	@Override
	public int getCurrentLiquidAmount() {
		return 0;
	}

	@Override
	public int fillLiquid(int amount) {
		return 0;
	}

}
