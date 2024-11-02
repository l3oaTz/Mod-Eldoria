package net.gobbob.mobends.pack;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import net.gobbob.mobends.client.model.IBendsModel;
import net.gobbob.mobends.client.model.IModelPart;
import net.gobbob.mobends.client.model.ModelPart;
import net.gobbob.mobends.pack.BendsAction.EnumBoxProperty;
import net.gobbob.mobends.util.EnumAxis;
import net.gobbob.mobends.util.SmoothVector3f;

public class BendsCondition
{
	private String animationName;
	private List<BendsAction> actions;

	public BendsCondition(String animationName)
	{
		this.actions = new ArrayList<BendsAction>();
		this.animationName = animationName;
	}

	public void applyToModel(Object object, String anim, String model)
	{
		for (BendsAction action : this.actions)
		{
			if (!action.model.equalsIgnoreCase(model))
			{
				continue;
			}
			
			applyToPart(object, action);
		}
	}

	public void applyToModel(IBendsModel model)
	{
		for (BendsAction action : this.actions)
		{
			Object object = model.getPartForName(action.model);
			
			if(object != null)
			{
				applyToPart(object, action);
			}
		}
	}
	
	public void applyToPart(Object part, BendsAction action)
	{
		if (part instanceof SmoothVector3f)
		{
			SmoothVector3f vector = (SmoothVector3f) part;
			if (action.property == EnumBoxProperty.ROT)
			{
				vector.slideTo(action.axis,
							   action.getNumber(vector.getEnd(action.axis)),
							   action.smooth);
			}
		}
		else if (part instanceof IModelPart)
		{
			IModelPart box = (IModelPart) part;
			if (action.property == EnumBoxProperty.ROT ||
				action.property == EnumBoxProperty.PREROT)
			{
				SmoothVector3f rotation = action.property == EnumBoxProperty.ROT ? box.getRotation() : box.getPreRotation();
				
				rotation.slideTo(action.axis,
								 action.getNumber((rotation.getEnd(action.axis))),
								 action.smooth);
			}
			else if (action.property == EnumBoxProperty.SCALE)
			{
				Vector3f scale = box.getScale();
				
				if (action.axis == null || action.axis == EnumAxis.X)
					box.getScale().setX(action.getNumber(scale.x));
				if (action.axis == null || action.axis == EnumAxis.Y)
					box.getScale().setY(action.getNumber(scale.y));
				if (action.axis == null || action.axis == EnumAxis.Z)
					box.getScale().setZ(action.getNumber(scale.z));
			}
		}
	}

	public void addAction(BendsAction action)
	{
		this.actions.add(action);
	}

	public String getAnimationName()
	{
		return this.animationName;
	}

	public int getActionAmount()
	{
		return this.actions.size();
	}

	public BendsAction getAction(int a)
	{
		return this.actions.get(a);
	}
}
