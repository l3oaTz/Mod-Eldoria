package net.gobbob.mobends.event;

import net.gobbob.mobends.main.ModBase;
import net.gobbob.mobends.network.NetworkConfiguration;
import net.gobbob.mobends.network.msg.MessageClientConfigure;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerServer {
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(event.getEntity() == null || !(event.getEntity() instanceof EntityPlayerMP)) return;
		
		ModBase.instance.networkWrapper.sendTo(new MessageClientConfigure(NetworkConfiguration.instance.allowModelScaling), (EntityPlayerMP) event.getEntity());
	}
}
