package net.gobbob.mobends.data;

import java.util.Random;

import net.gobbob.mobends.animation.controller.ZombieController;
import net.gobbob.mobends.client.event.DataUpdateHandler;
import net.minecraft.entity.Entity;

public class ZombieData extends BipedEntityData
{
	public int currentWalkingState = 0;
	public float ticksBeforeStateChange = 0;

	public ZombieData(Entity entity)
	{
		super(entity);
		this.controller = new ZombieController();
	}

	@Override
	public void update(float partialTicks)
	{
		super.update(partialTicks);

		this.ticksBeforeStateChange -= DataUpdateHandler.ticksPerFrame;

		if (this.ticksBeforeStateChange <= 0)
		{
			Random random = new Random();
			this.currentWalkingState = random.nextInt(2);
			this.ticksBeforeStateChange = 80 + random.nextInt(20);
		}
	}

	@Override
	public void onTicksRestart()
	{
	}
}