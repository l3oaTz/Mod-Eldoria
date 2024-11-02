package l3oatz.eldoria.gui;

import l3oatz.eldoria.EORReference;
import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EORInterfaceBarGui extends Gui
{
	private Minecraft mc;
    protected final RenderItem itemRenderer;
    /** The spectator GUI for this in-game GUI instance */
    protected final GuiSpectator spectatorGui;
	protected int xSize = 176;
	protected int ySize = 166;
	public int width;
	public int height;
	private float xSize_lo;
	private float ySize_lo;
	protected double prevPosX;
	protected double prevPosY;
	protected double prevPosZ;
	private ResourceLocation texture = new ResourceLocation(EORReference.MODID, "textures/gui/healthbar_gui.png");
	private ResourceLocation pet = new ResourceLocation(EORReference.MODID, "textures/gui/healthpetbar.png");
	private ResourceLocation hotbar = new ResourceLocation(EORReference.MODID, "textures/gui/interfacebar.png");
	
	public EORInterfaceBarGui(Minecraft mc)
	{
		this.mc = mc;
		this.itemRenderer = mc.getRenderItem();
		this.spectatorGui = new GuiSpectator(mc);
	}
	
	 public void renderGameOverlay(float partialTicks)
	 {
		EntityPlayer player = this.mc.player;
        IPlayerData status = PlayerDataCapability.getPlayerData(player);
		ScaledResolution scaledresolution = new ScaledResolution(this.mc);
		int k = (this.width - this.xSize) / 2;
	    int l = (this.height - this.ySize) / 2;
	    int width = scaledresolution.getScaledWidth();
	    int height = scaledresolution.getScaledHeight();
	    FontRenderer fontrenderer = this.mc.fontRenderer;
	    GlStateManager.enableBlend();
	    
	    GlStateManager.enableColorMaterial();
	    
        GlStateManager.pushMatrix();
        float scale1 = 1.0F;
        GlStateManager.scale(scale1, scale1, scale1);
        this.mc.renderEngine.bindTexture(this.texture);
	    //TextureSetting.setPixel();
	    drawTexturedModalRect(0, 0, 0, 0, 170, 67);
	    
	    float health = (int)(player.getHealth() / player.getMaxHealth() * 98.0F);
	    float gethp = (player.getHealth() / player.getMaxHealth());
	    int percentHP = (int)(gethp * 100);
	    if (percentHP >= 70) {
	    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	    } else if ((percentHP >= 50) && (percentHP < 70)) {
	    	GlStateManager.color(1.0F, 1.0F, 0.0F, 1.0F); //
	    } else if ((percentHP >= 30) && (percentHP < 50)) {
	    	GlStateManager.color(0.3F, 1.0F, 0.0F, 1.0F);
	    } else if ((percentHP >= 1) && (percentHP < 30)) {
	    	GlStateManager.color(0.55F, 0.9F, 0.0F, 1.0F);
	    } else {
	    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	    }
	    drawTexturedModalRect(56, 24, 0, 74, (int)health, 14);
    	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	    float mana = (int)((float)status.getMana() / status.getMaxMana() * 95.0F);
	    drawTexturedModalRect(41, 39, 0, 89, (int)mana, 15);
        GlStateManager.popMatrix();
        
        GlStateManager.pushMatrix();
        float scale = 0.8F;
        GlStateManager.scale(scale, scale, scale);
        this.drawString(fontrenderer, "§f" + player.getName(), 70, 17, 14315356);
	    this.drawCenteredString(fontrenderer, "§f" + status.getLevel(), 39, 64, 14315356);
	    this.drawCenteredString(fontrenderer, "§f" + (int)player.getHealth() + "/" + (int)player.getMaxHealth(), 125, 35, 14315356);
	    this.drawCenteredString(fontrenderer, "§f" + percentHP + "%", 125, 45, 14315356);
	    this.drawCenteredString(fontrenderer, "§f" + status.getMana() + "/" + status.getMaxMana(), 109, 55, 14315356);
        GlStateManager.popMatrix();
        
        
        //this.renderHotbar(scaledresolution, partialTicks);
	}
}