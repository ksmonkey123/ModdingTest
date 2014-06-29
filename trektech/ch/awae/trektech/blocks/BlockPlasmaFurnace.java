package ch.awae.trektech.blocks;

import ch.awae.trektech.TrekTech;
import ch.awae.trektech.entities.TileEntityPlasmaEnergizerLow;
import ch.awae.trektech.entities.TileEntityPlasmaFurnace;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author andreas
 *
 */
public class BlockPlasmaFurnace extends BlockContainer {
    
    public BlockPlasmaFurnace() {
        super(Material.rock);
        this.setCreativeTab(TrekTech.tabCustom);
        this.setBlockName("plasmaFurnace");
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityPlasmaFurnace();
    }
    
}
