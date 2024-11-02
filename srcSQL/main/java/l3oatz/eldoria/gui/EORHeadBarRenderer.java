package l3oatz.eldoria.gui;

import l3oatz.eldoria.EORReference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class EORHeadBarRenderer {

    // กำหนด ResourceLocation สำหรับ texture ของพื้นหลัง
    private static final ResourceLocation HEALTH_BAR_BACKGROUND = new ResourceLocation(EORReference.MODID, "textures/gui/bar.png");
    private static final ResourceLocation HEALTH_BAR_FILL = new ResourceLocation(EORReference.MODID, "textures/gui/bar_fill.png");

    @SubscribeEvent
    public void onRenderPlayer(RenderPlayerEvent.Post event)
    {
    	EntityPlayerSP player = (EntityPlayerSP)event.getEntityPlayer();

        if ((player instanceof EntityPlayerSP))
        {
	        double x = event.getX();
	        double y = event.getY(); // วางตำแหน่งเหนือหัวของผู้เล่น
	        double z = event.getZ();
	
	        renderforPlayer(player, x, y, z);
        }
    }

    public void renderforPlayer(EntityLivingBase player, double x, double y, double z)
    {
        Minecraft mc = Minecraft.getMinecraft();
        float health = player.getHealth();
        float maxHealth = player.getMaxHealth();

        // วาดหลอดเลือดเฉพาะเมื่อ health น้อยกว่าค่าสูงสุด
        //if (health < maxHealth)
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) x, (float)y + player.height + 0.5F, (float) z);
            GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            GlStateManager.scale(-0.025F, -0.025F, 0.025F); // ปรับขนาดให้พอดี

            // เปิดใช้งานการวาด alpha blending
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();

            // วาดพื้นหลังหลอดเลือดจากไฟล์ texture
            mc.getTextureManager().bindTexture(HEALTH_BAR_BACKGROUND);
            drawTexturedBar(-20, 0, 40, 5); // วาดพื้นหลังของหลอดเลือด

            // วาดส่วนของหลอดเลือด (สีเขียว) ตามสัดส่วนของค่าพลังชีวิต
            mc.getTextureManager().bindTexture(HEALTH_BAR_FILL);
            float healthRatio = health / maxHealth;
            drawTexturedBar(-20, 0, (int) (40 * healthRatio), 5);

            // ปิดการใช้งาน alpha blending
            GlStateManager.enableDepth();
            GlStateManager.disableBlend();

            GlStateManager.popMatrix();
        }
    }

    private void drawTexturedBar(int x, int y, int width, int height)
    {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0); GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(0, 1); GL11.glVertex2f(x, y + height);
        GL11.glTexCoord2f(1, 1); GL11.glVertex2f(x + width, y + height);
        GL11.glTexCoord2f(1, 0); GL11.glVertex2f(x + width, y);
        GL11.glEnd();
    }
}