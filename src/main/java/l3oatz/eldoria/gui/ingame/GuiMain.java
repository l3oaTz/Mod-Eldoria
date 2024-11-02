package l3oatz.eldoria.gui.ingame;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import utilities.RenderFullscreenGui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import l3oatz.eldoria.gui.ingame.dataframe.DataFrame;
import l3oatz.eldoria.gui.ingame.dataframe.TimeTick;

@SideOnly(Side.CLIENT)
public class GuiMain extends GuiScreen implements GuiYesNoCallback
{
	//private static ResourceLocation texture = new ResourceLocation("pirateth:textures/gui/maingui.png");
	private static ResourceLocation buttonplay = new ResourceLocation("pirateth:textures/gui/menu_icon1.png");

	public void handleKeyboardInput() throws IOException
	{
		super.handleKeyboardInput();
	}
  
	public void initGui()
	{
	    //int w = this.width / 2;
	    //int h = this.height / 2;
	    super.initGui();
	    Keyboard.enableRepeatEvents(true);
	    int j = this.height / 4 + 48;
	    
	    this.buttonList.clear();
	    this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options")));
	    //this.buttonList.add(new GuiButtonflat(0, this.width / 2 - 69, this.height / 2 + 56, 160, 45, ""));
	}
  
	protected void keyTyped(char par1, int par2) throws IOException
	{
		super.keyTyped(par1, par2);
	}
  
	public void updateScreen() {}
  
	public void onGuiClosed()
	{
		super.onGuiClosed();
	}
  
	@SuppressWarnings("static-access")
	public void drawScreen(int par1, int par2, float par3)
	{
	    //int w = this.width / 2;
	    //int h = this.height / 2;
	    RenderFullscreenGui.drawbgFrame(2.78f, 2.76F, 0,(int) (TimeTick.instance.ticktime % DataFrame.instance.numfile));

	    /*if(texture != null)
		{
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			mc.getTextureManager().bindTexture(texture);
			Tessellator tessellator2 = Tessellator.instance;
			tessellator2.startDrawingQuads();
			tessellator2.addVertexWithUV(0.0D, (double)this.height, -90.0D, 0.0D, 1.0D);
			tessellator2.addVertexWithUV((double)this.width, (double)this.height, -90.0D, 1.0D, 1.0D);
			tessellator2.addVertexWithUV((double)this.width, 0.0D, -90.0D, 1.0D, 0.0D);
			tessellator2.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
			tessellator2.draw();
			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}*/
	    
	    this.mc.getTextureManager().bindTexture(GuiMain.buttonplay);
	    //drawTexturedModalRect(this.width/2-118, this.height/2-50, 0, 0, 256, 200);
	    
	    super.drawScreen(par1, par2, par3);
	}
  
	public boolean doesGuiPauseGame()
	{
		return false;
	}
  
	protected void mouseClicked(int x, int y, int btn) throws IOException
	{
		super.mouseClicked(x, y, btn);
	}
  
	public void confirmClicked(boolean p_73878_1_, int p_73878_2_)
	{
	    if ((p_73878_1_) && (p_73878_2_ == 12))
	    {
	    	ISaveFormat isaveformat = this.mc.getSaveLoader();
	    	isaveformat.flushCache();
	    	isaveformat.deleteWorldDirectory("Demo_World");
	    	this.mc.displayGuiScreen(this);
	    }
	    /*else if (p_73878_2_ == 13)
	    {
	    	if (p_73878_1_)
	    	{
		        try
		        {
		        	Class oclass = Class.forName("java.awt.Desktop");
		        	Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
		        	oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI(this.web) });
		        }
		        catch (Throwable localThrowable) {}
	    	}
	    	this.mc.displayGuiScreen(this);
	    }
	    else if (p_73878_2_ == 14)
	    {
	    	if (p_73878_1_)
	    	{
		        try
		        {
		        	Class oclass = Class.forName("java.awt.Desktop");
		        	Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
		        	oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI(this.refill) });
		        }
		        catch (Throwable localThrowable1) {}
	    	}
	    	this.mc.displayGuiScreen(this);
	    }
	    else if (p_73878_2_ == 15)
	    {
	    	if (p_73878_1_)
	    	{
		        try
		        {
		        	Class oclass = Class.forName("java.awt.Desktop");
		        	Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
		        	oclass.getMethod("browse", new Class[] { URI.class }).invoke(object, new Object[] { new URI(this.register) });
		        }
		        catch (Throwable localThrowable2) {}
	    	}
	    	this.mc.displayGuiScreen(this);
	    }*/
	}
  
	public void actionPerformed(GuiButton button)
	{
	    if (button.id == 0)
	    {
	    	ServerData serverData = new ServerData(null, "127.0.0.1:25565", allowUserInput);
	    	FMLClientHandler.instance().setupServerList();
	    	FMLClientHandler.instance().connectToServer(this, serverData);
	    }
	    /*if (button.id == 1)
	    {
	    	ConfirmOpenLink guiconfirmopenlink = new ConfirmOpenLink(this, this.register, 13, true);
	    	guiconfirmopenlink.disableSecurityWarning();
	    	this.mc.displayGuiScreen(guiconfirmopenlink);
	    }
	    if (button.id == 2) {
	    	this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
	    }
	    if (button.id == 3)
	    {
	    	ConfirmOpenLink guiconfirmopenlink2 = new ConfirmOpenLink(this, this.refill, 14, true);
	    	guiconfirmopenlink2.disableSecurityWarning();
	    	this.mc.displayGuiScreen(guiconfirmopenlink2);
	    }*/
	}
}