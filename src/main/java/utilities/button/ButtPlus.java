package utilities.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;


import l3oatz.eldoria.EORReference;

public class ButtPlus extends GuiButton
{
	private static final ResourceLocation texture = new ResourceLocation(EORReference.MODID, "textures/gui/button.png");
	private Minecraft mc;
	public int xSize = 0;
	public int zSize = 0;
	
	public ButtPlus(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
	{
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.mc = Minecraft.getMinecraft(); // Minecraft instance
        this.xSize = 22;
		this.zSize = 50;
    }
	
	protected int getHoverState(boolean mouseOver)
    {
        int i = 1;
        if (!this.enabled) {
            i = 0;
        } else if (mouseOver) {
            i = 2;
        }
        return i;
    }
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible)
		{
			mc.getTextureManager().bindTexture(texture);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			if (i == 1) {
				drawTexturedModalRect(this.x, this.y, this.xSize, this.zSize, this.width, this.height);
			} else if (i == 2) {
				drawTexturedModalRect(this.x, this.y, this.xSize, this.zSize + 11, this.width, this.height);
			}
			mouseDragged(mc, mouseX, mouseY);
			@SuppressWarnings("unused")
			int l = 14737632;
			if (this.packedFGColour != 0)
			{
				l = this.packedFGColour;
			}
			else if (!this.enabled)
			{
				l = 10526880;
			}
			else if (this.hovered)
			{
				l = 16777120;
				if (i == 1) {
					drawCenteredString(this.mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, 0xFFFFFF);
				} else if (i == 2) {
					drawCenteredString(this.mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 30) / 2, 0xFFFFFF);
				}
			}
        }
	}
}