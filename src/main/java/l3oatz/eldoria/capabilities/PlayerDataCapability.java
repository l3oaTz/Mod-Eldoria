package l3oatz.eldoria.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;

import javax.annotation.Nullable;

import l3oatz.eldoria.EORReference;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.capabilities.capability.CapabilityProviderSerializable;
import l3oatz.eldoria.capabilities.util.CapabilityUtils;
import l3oatz.eldoria.capabilities.util.InjectionUtil;
import l3oatz.eldoria.network.NetworkHandler;
import l3oatz.eldoria.network.client.PlayerStatusSyncPacket;
import l3oatz.eldoria.utils.ModifierHandler;

public class PlayerDataCapability
{
    @CapabilityInject(IPlayerData.class)
    public static final Capability<IPlayerData> PLAYER_DATA_CAPABILITY = InjectionUtil.Null();

    public static final EnumFacing DEFAULT_FACING = null; // null 
    public static final ResourceLocation ID = new ResourceLocation(EORReference.MODID, "PlayerDataV1");

    // Capability
    public static void register()
    {
        CapabilityManager.INSTANCE.register(IPlayerData.class, new Capability.IStorage<IPlayerData>()
        {
            @Override
            public NBTBase writeNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side)
            {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setBoolean("setClass", instance.getSetClass());
                nbt.setString("playerClass", instance.getPlayerClass());
                nbt.setInteger("level", instance.getLevel());
                nbt.setInteger("exp", instance.getExp());
                nbt.setInteger("point", instance.getPoint());
                nbt.setInteger("coin", instance.getCoin());
                nbt.setInteger("cash", instance.getCash());
                nbt.setInteger("str", instance.getStr());
                nbt.setInteger("sta", instance.getSta());
                nbt.setInteger("dex", instance.getDex());
                nbt.setInteger("ints", instance.getInts());
                nbt.setFloat("health", instance.getMaxHealth());
                instance.CustomInventory().writeToNBT(nbt); // writeToNBT CustomInventory
                return nbt;
            }

            @Override
            public void readNBT(Capability<IPlayerData> capability, IPlayerData instance, EnumFacing side, NBTBase nbt)
            {
                NBTTagCompound compound = (NBTTagCompound)nbt;
                instance.setSetClass(compound.getBoolean("setClass"));
                instance.setPlayerClass(compound.getString("playerClass"));
                instance.setLevel(compound.getInteger("level"));
                instance.setExp(compound.getInteger("exp"));
                instance.setPoint(compound.getInteger("point"));
                instance.setCoin(compound.getInteger("coin"));
                instance.setCash(compound.getInteger("cash"));
                instance.setStr(compound.getInteger("str"));
                instance.setSta(compound.getInteger("sta"));
                instance.setDex(compound.getInteger("dex"));
                instance.setInts(compound.getInteger("ints"));
                instance.setMaxHealth(compound.getFloat("health"));
                instance.CustomInventory().readFromNBT(compound); // readFromNBT CustomInventory
            }
        }, PlayerData::new); // constructor PlayerStatus
    }

    @Nullable
    public static IPlayerData getPlayerData(final EntityPlayer entity)
    {
        return CapabilityUtils.getCapability(entity, PLAYER_DATA_CAPABILITY, DEFAULT_FACING);
    }
    
    public static void savePlayerData(final EntityPlayer entity)
    {
    	IPlayerData status = getPlayerData(entity);
        if (status != null)
        {
            NBTTagCompound nbt = new NBTTagCompound();
            PLAYER_DATA_CAPABILITY.getStorage().writeNBT(PLAYER_DATA_CAPABILITY, status, DEFAULT_FACING);
            // ResourceLocation 
            entity.getEntityData().setTag(ID.toString(), nbt); // ResourceLocation 
         // 
            System.out.println("Saving Player Data for " + entity.getName() + ": " + nbt.toString());
        }
    }
    
    public static void loadPlayerData(final EntityPlayer entity)
    {
        NBTTagCompound nbt = entity.getEntityData().getCompoundTag(ID.toString());
        IPlayerData status = getPlayerData(entity);
        if (status != null && nbt != null) {
            PLAYER_DATA_CAPABILITY.getStorage().readNBT(PLAYER_DATA_CAPABILITY, status, DEFAULT_FACING, nbt);
        }
    }
    
    public static void syncData(final EntityPlayer entity)
    {
    	IPlayerData status = getPlayerData(entity);
    	NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), (EntityPlayerMP)entity);
    }

    public static ICapabilityProvider createProvider(final IPlayerData playerStatus)
    {
        return new CapabilityProviderSerializable<>(PLAYER_DATA_CAPABILITY, DEFAULT_FACING, playerStatus);
    }

    @Mod.EventBusSubscriber(modid = EORReference.MODID)
    private static class EventHandler
    {
        @SubscribeEvent
        public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event)
        {
            if (event.getObject() instanceof EntityPlayer) {
                final PlayerData status = new PlayerData();
                event.addCapability(ID, createProvider(status));
            }
        }

        @SubscribeEvent
        public static void playerClone(final PlayerEvent.Clone event)
        {
        	if (!event.getEntityPlayer().world.isRemote)
        	{
	        	if (event.isWasDeath())
	        	{
		            final IPlayerData oldStatus = getPlayerData(event.getOriginal());
		            final IPlayerData newStatus = getPlayerData(event.getEntityPlayer());
		
		            if (newStatus != null && oldStatus != null)
		            {
		                newStatus.setPlayerClass(oldStatus.getPlayerClass());
		                newStatus.setLevel(oldStatus.getLevel());
		                newStatus.setExp(oldStatus.getExp());
		                newStatus.setPoint(oldStatus.getPoint());
		                newStatus.setStr(oldStatus.getStr());
		                newStatus.setSta(oldStatus.getSta());
		                newStatus.setDex(oldStatus.getDex());
		                newStatus.setInts(oldStatus.getInts());
		                newStatus.setMaxHealth(oldStatus.getMaxHealth());
		                System.out.println("Player clone data transferred.");
		            }
	        	}
        	}
        }

        @SubscribeEvent
        public static void onPlayerLogsIn(PlayerLoggedInEvent event)
        {
        	if (!event.player.world.isRemote)
        	{
	        	EntityPlayerMP player = (EntityPlayerMP)event.player;
	        	IPlayerData status = getPlayerData(player);
	
	            if (status != null)
	            {
	                // 
	                NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), player);
	                
	                float maxHealth = status.getMaxHealth();
	                ModifierHandler.setMaxHealth(player, maxHealth, 0);
	            }
        	}
        }

        @SubscribeEvent
        public static void onPlayerLogsOut(PlayerLoggedOutEvent event)
        {
        	if (!event.player.world.isRemote)
        	{
	        	EntityPlayerMP player = (EntityPlayerMP)event.player;
	            IPlayerData status = getPlayerData(player);
	            if (status != null)
	            {   
	                // writeNBT 
	                PLAYER_DATA_CAPABILITY.getStorage().writeNBT(PLAYER_DATA_CAPABILITY, status, DEFAULT_FACING);
	                
	                // 
	                NetworkHandler.sendTo(new PlayerStatusSyncPacket(status), player);
	                
	                System.out.println("Player data saved before logout: Level: " + status.getLevel());
	            }
        	}
        }
    }
}
