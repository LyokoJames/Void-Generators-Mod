package lj.vgm.tileentity;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import lj.vgm.block.ModBlocks;
import lj.vgm.core.util.ConduitSide;
import lj.vgm.core.util.ConduitState;
import lj.vgm.lib.Numbers;
import lj.vgm.lib.PacketStrings;
import lj.vgm.lib.Reference;
import lj.vgm.network.PacketHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityVoidConduit extends VoidEnergyConductor {
    
    public TileEntityVoidConduit() {
        super(Numbers.MAXIMUN_VOID_CONDUIT_ENERGY);
    }

    public void determineConnections(ForgeDirection dir) {
        setSingleInput(dir);
        setSingleOutput(dir.getOpposite());
        
        determineConnections();
    }
    
    public void determineConnections() {
        
        for (int i = 0; i < 6; i++) {
            TileEntity adjTe = worldObj.getBlockTileEntity(
                    xCoord+ForgeDirection.getOrientation(i).offsetX, 
                    yCoord+ForgeDirection.getOrientation(i).offsetY, 
                    zCoord+ForgeDirection.getOrientation(i).offsetZ);
            if (adjTe != null) {
                if (adjTe instanceof TileEntityVoidConduit) {
                    TileEntityVoidConduit adjVc = (TileEntityVoidConduit) adjTe;
                    if (adjVc.conduits
                            [ForgeDirection.getOrientation(i).getOpposite().ordinal()]
                                    .state == ConduitState.OUTPUT) {
                        setSingleInput(ForgeDirection.getOrientation(i));
                    }
                    if (adjVc.conduits
                            [ForgeDirection.getOrientation(i).getOpposite().ordinal()]
                                    .state == ConduitState.INPUT) {
                        setSingleOutput(ForgeDirection.getOrientation(i));
                    }
                }
            }
        }
    }
    
    @Override
    public void updateEntity() {
        System.out.println(voidEnergy);
        this.sendEnergyToOutputs(maxVoidEnergy);
    }
    
    
}
