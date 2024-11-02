package utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import l3oatz.eldoria.EORReference;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFullscreenGui
{
	public static void drawbgFrame(double p_73970_1_, double p_73970_2_, float p_73970_3_, int texture)
	{
		Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
		GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        GlStateManager.pushMatrix();

		TextureSetting.setblur();
		//ImageUtils.instance.bindTexture(texture);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(EORReference.MODID, "textures/frame/img" + texture + ".jpg"));
		tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        float f4 = 0.0F;
        tessellator.getBuffer().setTranslation(-0.74D, -0.78D, 0.0D);
        tessellator.getBuffer().pos(-1.0D, -1.0D, 1.0D).tex(0.0F + f4, 0.0F + f4).endVertex();
        tessellator.getBuffer().pos(p_73970_1_, -1.0D, 1.0D).tex(1.0F - f4, 0.0F + f4).endVertex();
        tessellator.getBuffer().pos(p_73970_1_, p_73970_2_, 1.0D).tex(1.0F - f4, 1.0F - f4).endVertex();
        tessellator.getBuffer().pos(-1.0D, p_73970_2_, 1.0D).tex(0.0F + f4, 1.0F - f4).endVertex();
		tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);

        GlStateManager.enableDepth();
        tessellator.getBuffer().setTranslation(0.0D, 0.0D, 0.0D);
	}
	
    public static void drawbg(double p_73970_1_, double p_73970_2_, float p_73970_3_, ResourceLocation texture)
    {
        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.pushMatrix();
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.pushMatrix();

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        TextureSetting.setblur();
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        float f4 = 0.0F;
        tessellator.getBuffer().setTranslation(-0.74D, -0.78D, 0.0D);
        tessellator.getBuffer().pos(-1.0D, -1.0D, 1.0D).tex(0.0F + f4, 0.0F + f4).endVertex();
        tessellator.getBuffer().pos(p_73970_1_, -1.0D, 1.0D).tex(1.0F - f4, 0.0F + f4).endVertex();
        tessellator.getBuffer().pos(p_73970_1_, p_73970_2_, 1.0D).tex(1.0F - f4, 1.0F - f4).endVertex();
        tessellator.getBuffer().pos(-1.0D, p_73970_2_, 1.0D).tex(0.0F + f4, 1.0F - f4).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);

        GlStateManager.enableDepth();
        tessellator.getBuffer().setTranslation(0.0D, 0.0D, 0.0D);
    }

    public static void drawbgtranslate(double p_73970_1_, double p_73970_2_, double tx, double ty, ResourceLocation texture)
    {
        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.pushMatrix();
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.pushMatrix();

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        TextureSetting.setblur();
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        float f4 = 0.0F;
        tessellator.getBuffer().setTranslation(tx, ty, 0.0D);
        tessellator.getBuffer().pos(-1.0D, -1.0D, 1.0D).tex(0.0F + f4, 0.0F + f4).endVertex();
        tessellator.getBuffer().pos(p_73970_1_, -1.0D, 1.0D).tex(1.0F - f4, 0.0F + f4).endVertex();
        tessellator.getBuffer().pos(p_73970_1_, p_73970_2_, 1.0D).tex(1.0F - f4, 1.0F - f4).endVertex();
        tessellator.getBuffer().pos(-1.0D, p_73970_2_, 1.0D).tex(0.0F + f4, 1.0F - f4).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);

        GlStateManager.enableDepth();
        tessellator.getBuffer().setTranslation(0.0D, 0.0D, 0.0D);
    }

    public static void drawbn(double xSize, double ySize, float translateX, float translateY, float xPic, float xPicEnd, float yPic, float yPicEnd, ResourceLocation texture)
    {
        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.pushMatrix();
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.loadIdentity();

        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GlStateManager.pushMatrix();

        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        TextureSetting.setblur();
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        float f4 = 0.0F;
        tessellator.getBuffer().setTranslation(translateX, translateY, 0.0D);
        tessellator.getBuffer().pos(-1.0D, -1.0D, 1.0D).tex(xPicEnd + f4, yPic + f4).endVertex();
        tessellator.getBuffer().pos(xSize, -1.0D, 1.0D).tex(xPic - f4, yPic + f4).endVertex();
        tessellator.getBuffer().pos(xSize, ySize, 1.0D).tex(xPic - f4, yPicEnd - f4).endVertex();
        tessellator.getBuffer().pos(-1.0D, ySize, 1.0D).tex(xPicEnd + f4, yPicEnd - f4).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(GL11.GL_PROJECTION);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(GL11.GL_MODELVIEW);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);

        GlStateManager.enableDepth();
        tessellator.getBuffer().setTranslation(0.0D, 0.0D, 0.0D);
    }
}