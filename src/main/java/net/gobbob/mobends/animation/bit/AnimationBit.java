package net.gobbob.mobends.animation.bit;

import net.gobbob.mobends.animation.layer.AnimationLayer;
import net.gobbob.mobends.client.model.IBendsModel;
import net.gobbob.mobends.data.EntityData;
import net.minecraft.entity.EntityLivingBase;

public abstract class AnimationBit
{
	/*
	 * The layer that this bit is performed by.
	 * Used to callback, e.g. when the animation is finished.
	 * */
	protected AnimationLayer layer;
	
	/*
	 * Called by the AnimationLayer before it plays
	 * this bit.
	 */
	public void setupForPlay(AnimationLayer layer, EntityData entityData)
	{
		this.layer = layer;
		this.onPlay(entityData);
	}
	
	/*
	 * Called by setupForPlay to setup the beginning of
	 * this animation bit.
	 */
	public void onPlay(EntityData entityData) {}
	
	/*
	 * Called by an AnimationLayer to perform a continuous
	 * animation.
	 */
	public abstract void perform(EntityData entityData);
	
	/*
	 * Returns the actions currently being performed
	 * by the entityData. Used by BendsPacks
	 */
	public abstract String[] getActions(EntityData entityData);
}