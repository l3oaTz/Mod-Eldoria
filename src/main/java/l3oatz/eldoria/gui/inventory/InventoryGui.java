package l3oatz.eldoria.gui.inventory;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import l3oatz.eldoria.EORReference;
import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import utilities.Stringint;
import utilities.TextureSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class InventoryGui extends GuiContainer
{
	public ResourceLocation texture01 = new ResourceLocation(EORReference.MODID, "textures/gui/playerinv_gui.png");
	public ResourceLocation texture02 = new ResourceLocation(EORReference.MODID, "textures/gui/inventory02_gui.png");
	@SuppressWarnings("unused")
	private final InventoryInv inventory;
	/** The old x position of the mouse pointer */
    private float oldMouseX;
    /** The old y position of the mouse pointer */
    private float oldMouseY;
	private EntityPlayer player;
	
	public InventoryGui(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryInv inventoryCustom)
	{
		super(new InventoryData(player, inventoryPlayer, inventoryCustom));
		this.inventory = inventoryCustom;
		this.xSize = 320;
		this.ySize = 220;
	}
	
	protected void keyTyped(char c, int keyCode) throws IOException
	{
		super.keyTyped(c, keyCode);
	}
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {}
	
	public void initGui()
	{
	    super.initGui();
	}
	
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY)
	{
		int w = this.width / 2;
	    int h = this.height / 2;
	    int i = this.guiLeft;
	    int j = this.guiTop;
	    this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    GL11.glEnable(3042);

	    IPlayerData pData = PlayerDataCapability.getPlayerData(player);
		
	    Minecraft.getMinecraft().renderEngine.bindTexture(this.texture01);
	    TextureSetting.setblur();
	    drawTexturedModalRect(w - 130, h - 117, 1, 0, 256, 226);

	    if (pData != null)
	    {
	    	String coin = Stringint.value(Integer.valueOf(pData.getCoin()));
		    String cash = Stringint.value(Integer.valueOf(pData.getCash()));
		    this.mc.fontRenderer.drawString("" + coin, (w + 120) - this.mc.fontRenderer.getStringWidth(coin), h + 67, 0);
		    this.mc.fontRenderer.drawString("" + cash, (w + 120) - this.mc.fontRenderer.getStringWidth(cash), h + 81, 0);
	    }
	    
	    
	    GL11.glDisable(3042);
	    
	    this.buttonList.clear();
	    
	    //this.buttonList.add(new GuiButtonflat(1, w + 2, h - 90, 47, 19, ""));
	    //this.buttonList.add(new GuiButtonflat(2, w + 2, h - 72, 47, 19, ""));
	    /*this.buttonList.add(new GuiButtonflat(3, w + 2, h - 54, 47, 19, ""));
	    this.buttonList.add(new GuiButtonflat(4, w + 2, h - 35, 47, 19, ""));*/
	    //this.buttonList.add(new GuiButtons(100, w+153, h-117, 10, 10, ""));
	    
	    drawEntityOnScreen(i + 80, j + 150, 30, (float)(i + 51) - this.oldMouseX, (float)(j + 75 - 50) - this.oldMouseY, this.mc.player);

	    //GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    /*ItemStack slotpet = px.playerinvIN.getStackInSlot(6);
	    if((slotpet != null) && (slotpet.getItem() == RegPet.petEgg))
	    {
	    	EntityPetEgg e = new EntityPetEgg(this.mc.thePlayer.getEntityWorld());
	    	renderModelPlayer(k + 110, l + 65, 30, k + 51, l + 75, e, 0.0F);
	    }*/
	}
	
	protected void actionPerformed(GuiButton bn)
	{
		EntityPlayer player = this.player;
		IPlayerData status = PlayerDataCapability.getPlayerData(player);
		if (bn.id == 0) {
			this.mc.setIngameFocus();
		}
		/*if (bn.id == 1) {
			this.mc.setIngameFocus();
			//this.mc.displayGuiScreen(new GuiStatus(this.mc.thePlayer));
			data.skillupdate = true;
			PacketDispatcher.sendToServer(new PacketOpenGui(MainMod.GUI_SKILL));
			//player.openGui(MainMod.instance, MainMod.GUI_SKILL, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		if (bn.id == 2) {
			this.mc.setIngameFocus();
			PacketDispatcher.sendToServer(new PacketOpenGui(MainMod.GUI_STATUS));
			//PacketDispatcher.sendToServer(new PacketOpenGui(2));
			//this.mc.setIngameFocus();
			//player.openGui(MainMod.instance, 1, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		if (bn.id == 3) {
			
		}
		if (bn.id == 5) {
			this.mc.setIngameFocus();
			//this.mc.thePlayer.sendChatMessage("/dark openshop");
			//player.openGui(MainMod.instance, MainMod.GUI_EXCHANGE, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
			//PacketDispatcher.sendToServer(new PacketShop(3, player.getCommandSenderName()));
		}
		if (bn.id == 6)
		{
			if (data.isMarry) {
				PacketDispatcher.sendToServer(new PacketOpenGui(1));
			} else {
				Msg.sys(this.mc.thePlayer, "\u0E04\u0E38\u0E13\u0E22\u0E31\u0E07\u0E44\u0E21\u0E48\u0E44\u0E14\u0E49\u0E41\u0E15\u0E48\u0E07\u0E07\u0E32\u0E19");
			}
		}
		else if (bn.id == 100) {
			this.mc.setIngameFocus();
		}*/
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, 50.0F);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
        ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
        ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}