package lj.vgm.core.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeDirection;

public class DirectionHelper {
    public static ForgeDirection getEntityLookDirection(EntityLiving entityLiving) {
        ForgeDirection direction = null;
        int pitch = MathHelper.floor_double(entityLiving.rotationPitch * 3.0F / 180.0F + 0.5D);
        int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        if(pitch <= -1) direction = ForgeDirection.UP;
        else if(pitch >= 1) direction = ForgeDirection.DOWN;
        else {
            if (facing == 0) {
                direction = ForgeDirection.SOUTH;
            }
            else if (facing == 1) {
                direction = ForgeDirection.WEST;
            }
            else if (facing == 2) {
                direction = ForgeDirection.NORTH;
            }
            else if (facing == 3) {
                direction = ForgeDirection.EAST;
            }
        }
        return direction;
        //TODO Add Yaw only function
    }
}
