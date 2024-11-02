package utilities.button;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiButtonflat extends GuiButton
{
	protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
	protected int width;
	protected int height;
	public int xPosition;
	public int yPosition;
	public String displayString;
	public int id;
	public boolean enabled;
	public boolean drawButton;
	protected boolean field_82253_i;
	private int alpha;
	
	public GuiButtonflat(int par1, int par2, int par3, String par4Str)
	{
		this(par1, par2, par3, 200, 20, par4Str);
	}
	
	public GuiButtonflat(int par1, int par2, int par3, int par4, int par5, String par6Str)
	{
	    super(par1, par2, par3, par4, par5, par6Str);
	    this.width = 200;
	    this.height = 20;
	    this.enabled = true;
	    this.drawButton = true;
	    this.id = par1;
	    this.xPosition = par2;
	    this.yPosition = par3;
	    this.width = par4;
	    this.height = par5;
	    this.displayString = par6Str;
	}
	
	public int getHoverState(boolean par1)
	{
		byte b0 = 1;
		if (!this.enabled) {
			b0 = 0;
		} else if (par1) {
			b0 = 2;
		}
		return b0;
	}
	
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		if (this.drawButton)
		{
			FontRenderer fontrenderer = par1Minecraft.fontRenderer;
			par1Minecraft.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_82253_i = ((par2 >= this.xPosition) && (par3 >= this.yPosition) && (par2 < this.xPosition + this.width) && (par3 < this.yPosition + this.height));
			@SuppressWarnings("unused")
			int k = getHoverState(this.field_82253_i);
			mouseDragged(par1Minecraft, par2, par3);
			int l = 14737632;
			if (!this.enabled) {
				l = -6250336;
			} else if (this.field_82253_i) {
				l = 16777120;
			}
			if ((isInside(par2, par3)) && (this.enabled)) {
				this.alpha = 30;
			} else {
				this.alpha = 0;
			}
			Color boxColor = new Color(0, 0, 0, this.alpha);
			drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
			drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, boxColor.getRGB());
		}
	}
	
	public boolean isInside(int mx, int my)
	{
		return (mx >= this.xPosition) && (my >= this.yPosition) && (mx <= this.xPosition + this.width) && (my <= this.yPosition + this.height);
	}
	
	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {}
	
	public void mouseReleased(int par1, int par2) {}
	
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
	{
		return (this.enabled) && (this.drawButton) && (par2 >= this.xPosition) && (par3 >= this.yPosition) && (par2 < this.xPosition + this.width) && (par3 < this.yPosition + this.height);
	}
	
	public boolean func_82252_a()
	{
		return this.field_82253_i;
	}
  
	public void func_82251_b(int par1, int par2) {}
}