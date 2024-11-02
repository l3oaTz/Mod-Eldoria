package net.gobbob.mobends.data;

import java.util.HashMap;

import net.gobbob.mobends.client.event.DataUpdateHandler;
import net.gobbob.mobends.client.model.IModelPart;
import net.gobbob.mobends.client.model.ModelBox;
import net.gobbob.mobends.client.model.ModelPart;
import net.gobbob.mobends.client.model.ModelPartChildExtended;
import net.gobbob.mobends.client.model.ModelPartExtended;
import net.gobbob.mobends.client.model.ModelPartTransform;
import net.gobbob.mobends.client.renderer.SwordTrail;
import net.gobbob.mobends.util.SmoothVector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public abstract class BipedEntityData extends LivingEntityData
{
	/*
	 * These models need to be represented only
	 * as transforms, because that's the only thing
	 * that needs to persist between frames.
	 */
	
	public ModelPartTransform head;
    public ModelPartTransform headwear;
    public ModelPartTransform body;
    public ModelPartTransform rightArm;
    public ModelPartTransform leftArm;
    public ModelPartTransform rightLeg;
    public ModelPartTransform leftLeg;
    public ModelPartTransform rightForeArm;
    public ModelPartTransform leftForeArm;
    public ModelPartTransform rightForeLeg;
    public ModelPartTransform leftForeLeg;
	
    public SmoothVector3f renderOffset;
    public SmoothVector3f renderRotation;
    public SmoothVector3f renderRightItemRotation;
    public SmoothVector3f renderLeftItemRotation;
	
    public SwordTrail swordTrail;
    
	public BipedEntityData(Entity entity)
	{
		super(entity);
	}

	@Override
	public void initModelPose()
	{
		this.head = new ModelPartTransform();
		this.headwear = new ModelPartTransform();
		this.body = new ModelPartTransform();
		this.rightArm = new ModelPartTransform();
		this.leftArm = new ModelPartTransform();
		this.rightLeg = new ModelPartTransform();
		this.leftLeg = new ModelPartTransform();
		this.rightForeArm = new ModelPartTransform();
		this.leftForeArm = new ModelPartTransform();
		this.rightForeLeg = new ModelPartTransform();
		this.leftForeLeg = new ModelPartTransform();
		
		this.renderOffset = new SmoothVector3f();
		this.renderRotation = new SmoothVector3f();
		this.renderRightItemRotation = new SmoothVector3f();
		this.renderLeftItemRotation = new SmoothVector3f();
		
		this.swordTrail = new SwordTrail();
		
		nameToPartMap = new HashMap<String, Object>();
        nameToPartMap.put("body", body);
        nameToPartMap.put("head", head);
        nameToPartMap.put("leftArm", leftArm);
        nameToPartMap.put("rightArm", rightArm);
        nameToPartMap.put("leftLeg", leftLeg);
        nameToPartMap.put("rightLeg", rightLeg);
        nameToPartMap.put("leftForeArm", leftForeArm);
        nameToPartMap.put("rightForeArm", rightForeArm);
        nameToPartMap.put("leftForeLeg", leftForeLeg);
        nameToPartMap.put("rightForeLeg", rightForeLeg);
        nameToPartMap.put("renderRotation", renderRotation);
        nameToPartMap.put("renderRightItemRotation", renderRightItemRotation);
        nameToPartMap.put("renderLeftItemRotation", renderLeftItemRotation);
		
		this.body.position.set(0F, 12F, 0F);
		this.head.position.set(0F, -12F, 0F);
		this.rightArm.position.set(-5F, -10F, 0F);
		this.leftArm.position.set(5F, -10f, 0f);
		this.rightLeg.position.set(-1.9F, 12.0F, 0.0F);
		this.leftLeg.position.set(1.9F, 12.0F, 0.0F);
		this.rightForeArm.position.set(0F, 4F, 2F);
		this.leftForeArm.position.set(0F, 4F, 2F);
		this.leftForeLeg.position.set(0, 6.0F, -2.0F);
		this.rightForeLeg.position.set(0, 6.0F, -2.0F);
	}

	@Override
	public void updateParts(float ticksPerFrame)
	{
		this.head.update(ticksPerFrame);
		this.headwear.update(ticksPerFrame);
		this.body.update(ticksPerFrame);
		this.rightArm.update(ticksPerFrame);
		this.leftArm.update(ticksPerFrame);
		this.rightLeg.update(ticksPerFrame);
		this.leftLeg.update(ticksPerFrame);
		this.rightForeArm.update(ticksPerFrame);
		this.leftForeArm.update(ticksPerFrame);
		this.rightForeLeg.update(ticksPerFrame);
		this.leftForeLeg.update(ticksPerFrame);
		
		this.renderOffset.update(ticksPerFrame);
		this.renderRotation.update(ticksPerFrame);
		this.renderRightItemRotation.update(ticksPerFrame);
		this.renderLeftItemRotation.update(ticksPerFrame);
		
		this.swordTrail.update(ticksPerFrame);
	}
}
