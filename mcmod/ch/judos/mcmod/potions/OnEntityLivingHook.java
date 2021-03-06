package ch.judos.mcmod.potions;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import ch.judos.mcmod.MCMod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("javadoc")
public class OnEntityLivingHook {
    
    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event) {
        
        // if (!event.entity.worldObj.isRemote
        // && event.entityLiving instanceof EntityPlayer) {
        // System.out.println(event.entityLiving.getCommandSenderName());
        // }
        
        // entityLiving in fact refers to EntityLivingBase so to understand
        // everything about this part go to EntityLivingBase instead
        if (event.entityLiving.isPotionActive(MCMod.potionTest.id)) {
            if (event.entityLiving.worldObj.rand.nextInt(20) == 0) {
                if (!event.entityLiving.worldObj.isRemote) {
                    
                    EntityChicken chicken = new EntityChicken(
                            event.entityLiving.worldObj);
                    chicken.copyLocationAndAnglesFrom(event.entityLiving);
                    
                    chicken.copyLocationAndAnglesFrom(event.entityLiving);
                    event.entityLiving.worldObj.spawnEntityInWorld(chicken);
                }
                
                event.entityLiving.attackEntityFrom(DamageSource.generic, 1);
            }
            if (event.entityLiving.getActivePotionEffect(MCMod.potionTest)
                    .getDuration() == 0)
                event.entityLiving.removePotionEffect(MCMod.potionTest.id);
        }
    }
}
