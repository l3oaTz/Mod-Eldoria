package l3oatz.eldoria.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

import java.util.UUID;

import l3oatz.eldoria.EORReference;

public class ModifierHandler {

    public static final UUID MODIFIER_ID_HEALTH = UUID.fromString("c0bef565-35f6-4dc5-bb4c-3644c382e6ce");
    public static final String MODIFIER_NAME_HEALTH = EORReference.MODID + ".HealthModifier";

    private static void setModifier(IAttributeInstance attr, UUID id, String name, double amount, int op) {
        if (attr == null)
            return;

        // Calculate the difference for the modifier.
        double normalValue = attr.getBaseValue();
        double difference = amount - normalValue;

        // Get current and new modifier.
        AttributeModifier mod = attr.getModifier(id);
        AttributeModifier newMod = new AttributeModifier(id, name, difference, op);

        // Remove the old, apply the new.
        if (mod != null)
            attr.removeModifier(mod);
        attr.applyModifier(newMod);
    }

    public static void setMaxHealth(EntityLivingBase entity, double amount, int op) {
        /*if (amount <= 0) {
        	MainMod.logHelper.warn("ModifierHandler.setMaxHealth: amount <= 0!");
            return;
        }*/

        float originalHealth = entity.getHealth();
        IAttributeInstance attr = entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
        //noinspection ConstantConditions
        if (attr != null) {
            setModifier(attr, MODIFIER_ID_HEALTH, MODIFIER_NAME_HEALTH, amount, op);
            entity.setHealth(originalHealth);
        }
    }

    public static double getHealthModifier(EntityLivingBase entity) {
        IAttributeInstance attr = entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
        //noinspection ConstantConditions
        if (attr == null) {
            return 0;
        }
        AttributeModifier mod = attr.getModifier(MODIFIER_ID_HEALTH);
        return mod != null ? mod.getAmount() : 0;
    }
}