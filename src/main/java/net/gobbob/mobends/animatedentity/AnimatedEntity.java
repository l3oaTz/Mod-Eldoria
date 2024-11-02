package net.gobbob.mobends.animatedentity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.gobbob.mobends.animatedentity.alterentry.AlterEntry;
import net.gobbob.mobends.animatedentity.previewer.PlayerPreviewer;
import net.gobbob.mobends.animatedentity.previewer.Previewer;
import net.gobbob.mobends.client.mutators.Mutator;
import net.gobbob.mobends.client.mutators.PlayerMutator;
import net.gobbob.mobends.client.mutators.ZombieMutator;
import net.gobbob.mobends.client.renderer.entity.RenderBendsSpectralArrow;
import net.gobbob.mobends.client.renderer.entity.RenderBendsTippedArrow;
import net.gobbob.mobends.util.BendsLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class AnimatedEntity
{
	public static HashMap<String, AnimatedEntity> animatedEntities = new HashMap<String, AnimatedEntity>();

	private String name;
	private String displayName;
	private List<AlterEntry> alterEntries = new ArrayList<AlterEntry>();
	private String[] alterableParts;

	public Class<? extends Entity> entityClass;
	public Class<? extends Mutator> mutatorClass;
	public Previewer previewer;

	public AnimatedEntity(String id, String displayName, Class<? extends Entity> entityClass,
			Class<? extends Mutator> mutatorClass, String[] alterableParts)
	{
		this.name = id;
		this.displayName = displayName;
		this.entityClass = entityClass;
		this.mutatorClass = mutatorClass;
		this.alterableParts = alterableParts;
		this.addAlterEntry(new AlterEntry(this, displayName));
	}

	public List<AlterEntry> getAlredEntries()
	{
		return this.alterEntries;
	}

	public AlterEntry getAlterEntry(int index)
	{
		return this.alterEntries.get(index);
	}

	public String[] getAlterableParts()
	{
		return alterableParts;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public String getName()
	{
		return name;
	}

	public Previewer getPreviewer()
	{
		return this.previewer;
	}

	public AnimatedEntity addAlterEntry(AlterEntry alterEntry)
	{
		this.alterEntries.add(alterEntry);
		return this;
	}

	public AnimatedEntity setPreviewer(Previewer previewer)
	{
		this.previewer = previewer;
		return this;
	}

	public void applyMutation(RenderLivingBase<? extends EntityLivingBase> renderer, EntityLivingBase entity,
			float partialTicks)
	{
		try
		{
			Method method = this.mutatorClass.getMethod("apply", RenderLivingBase.class, EntityLivingBase.class, float.class);
			method.invoke(null, renderer, entity, partialTicks);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			System.err.println("The " + name + " animated entity's mutator didn't have a"
					+ "static 'apply' method, that fulfulled the parameter requirements.");
			e.printStackTrace();
		}
	}
	
	public void deapplyMutation(RenderLivingBase<? extends EntityLivingBase> renderer, EntityLivingBase entity)
	{
		try
		{
			Method method = this.mutatorClass.getMethod("deapply", RenderLivingBase.class, EntityLivingBase.class);
			method.invoke(null, renderer, entity);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			System.err.println("The " + name + " animated entity's mutator didn't have a"
					+ "static 'deapply' method, that fulfulled the parameter requirements.");
			e.printStackTrace();
		}
	}

	public static void register(Configuration config)
	{
		BendsLogger.info("Registering Animated Entities...");

		animatedEntities.clear();

		registerEntity(config,
				new AnimatedEntity("player", "Player", AbstractClientPlayer.class, PlayerMutator.class,
						new String[] { "head", "body", "leftArm", "rightArm", "leftForeArm", "rightForeArm", "leftLeg",
								"rightLeg", "leftForeLeg", "rightForeLeg", "totalRotation", "leftItemRotation",
								"rightItemRotation" }).setPreviewer(new PlayerPreviewer()));

		registerEntity(config,
				new AnimatedEntity("zombie", "Zombie", EntityZombie.class, ZombieMutator.class,
						new String[] { "head", "body", "leftArm", "rightArm", "leftForeArm", "rightForeArm", "leftLeg",
								"rightLeg", "leftForeLeg", "rightForeLeg" }));
		/*
		 * registerEntity(config, new AnimatedEntity("husk", "Husk", EntityHusk.class,
		 * new RenderBendsHusk(Minecraft.getMinecraft().getRenderManager()), new
		 * String[] {"head", "body", "leftArm", "rightArm", "leftForeArm",
		 * "rightForeArm", "leftLeg", "rightLeg", "leftForeLeg", "rightForeLeg"}));
		 * registerEntity(config, new AnimatedEntity("spider", "Spider",
		 * EntitySpider.class, new
		 * RenderBendsSpider(Minecraft.getMinecraft().getRenderManager()), new String[]
		 * {"head", "body", "neck", "leg1", "leg2", "leg3", "leg4", "leg5", "leg6",
		 * "leg7", "leg8", "foreLeg1", "foreLeg2", "foreLeg3", "foreLeg4", "foreLeg5",
		 * "foreLeg6", "foreLeg7", "foreLeg8"})); registerEntity(config, new
		 * AnimatedEntity("cave_spider", "Cave Spider", EntityCaveSpider.class, new
		 * RenderBendsCaveSpider(Minecraft.getMinecraft().getRenderManager()), new
		 * String[] {"head", "body", "neck", "leg1", "leg2", "leg3", "leg4", "leg5",
		 * "leg6", "leg7", "leg8", "foreLeg1", "foreLeg2", "foreLeg3", "foreLeg4",
		 * "foreLeg5", "foreLeg6", "foreLeg7", "foreLeg8"})); registerEntity(config, new
		 * AnimatedEntity("skeleton", "Skeleton", EntitySkeleton.class, new
		 * RenderBendsSkeleton(Minecraft.getMinecraft().getRenderManager()), new
		 * String[] {"head", "body", "leftArm", "rightArm", "leftForeArm",
		 * "rightForeArm", "leftLeg", "rightLeg", "leftForeLeg", "rightForeLeg"}));
		 * registerEntity(config, new AnimatedEntity("wither_skeleton",
		 * "Wither Skeleton", EntityWitherSkeleton.class, new
		 * RenderBendsWitherSkeleton(Minecraft.getMinecraft().getRenderManager()), new
		 * String[] {"head", "body", "leftArm", "rightArm", "leftForeArm",
		 * "rightForeArm", "leftLeg", "rightLeg", "leftForeLeg", "rightForeLeg"}));
		 * registerEntity(config, new AnimatedEntity("stray", "Stray",
		 * EntityStray.class, new
		 * RenderBendsStray(Minecraft.getMinecraft().getRenderManager()), new String[]
		 * {"head", "body", "leftArm", "rightArm", "leftForeArm", "rightForeArm",
		 * "leftLeg", "rightLeg", "leftForeLeg", "rightForeLeg"}));
		 */

		RenderingRegistry.registerEntityRenderingHandler(EntitySpectralArrow.class,
				new RenderBendsSpectralArrow(Minecraft.getMinecraft().getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTippedArrow.class,
				new RenderBendsTippedArrow(Minecraft.getMinecraft().getRenderManager()));
	}

	public static void registerEntity(Configuration config, AnimatedEntity animatedEntity)
	{
		BendsLogger.info("Registering " + animatedEntity.displayName);
		for (AlterEntry alterEntry : animatedEntity.alterEntries)
		{
			alterEntry.setAnimate(config.get("Animated", alterEntry.getName(), true).getBoolean());
		}
		animatedEntities.put(animatedEntity.name, animatedEntity);
	}

	public static AnimatedEntity get(String name)
	{
		return (AnimatedEntity) animatedEntities.get(name);
	}

	public static AnimatedEntity getByEntity(Entity entity)
	{
		for (AnimatedEntity animatedEntity : animatedEntities.values())
		{
			if (animatedEntity.entityClass.isInstance(entity))
			{
				return animatedEntity;
			}
		}
		return null;
	}
}
