package ch.awae.trektech.entities;

import ch.awae.trektech.EnumPlasmaTypes;
import ch.awae.trektech.Properties;
import ch.modjam.generic.GenericTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class ATileEntityPlasmaSystem extends GenericTileEntity
		implements IPlasmaConnection {

	@Override
	public final void tick() {
		for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
			this.transferPlasma(direction);
		}
		this.customTick();
	}

	private void transferPlasma(ForgeDirection direction) {
		TileEntity entity = this.worldObj.getTileEntity(this.xCoord
				+ direction.offsetX, this.yCoord + direction.offsetY,
				this.zCoord + direction.offsetZ);
		if (!(entity instanceof IPlasmaConnection))
			return;
		IPlasmaConnection other = (IPlasmaConnection) entity;
		ForgeDirection opposite = direction.getOpposite();
		for (EnumPlasmaTypes plasma : EnumPlasmaTypes.VALID) {
			if (!(this.connectsToPlasmaConnection(plasma, direction) && other
					.connectsToPlasmaConnection(plasma, opposite)))
				continue;
			// calculate particle flow
			int ownCount = this.getParticleCount(plasma, direction);
			int othCount = other.getParticleCount(plasma, opposite);
			float ownPpB = this.getParticlesPerBar(plasma, direction);
			float othPpB = other.getParticlesPerBar(plasma, opposite);
			int dCount = (int) ((ownPpB * othCount - othPpB * ownCount) / (ownPpB + othPpB));
			// crop transfer rate
			if (dCount > Properties.PLASMA_TRANSFER_SPEED)
				dCount = Properties.PLASMA_TRANSFER_SPEED;
			else if (dCount < -Properties.PLASMA_TRANSFER_SPEED)
				dCount = -Properties.PLASMA_TRANSFER_SPEED;
			// apply particle flow
			this.applyParticleFlow(plasma, direction, dCount);
			other.applyParticleFlow(plasma, opposite, -dCount);
		}
	}

	public abstract void customTick();
}
