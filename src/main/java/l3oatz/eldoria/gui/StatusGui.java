package l3oatz.eldoria.gui;

import l3oatz.eldoria.EORReference;
import l3oatz.eldoria.capabilities.PlayerDataCapability;
import l3oatz.eldoria.capabilities.api.IPlayerData;
import l3oatz.eldoria.network.NetworkHandler;
import l3oatz.eldoria.network.server.PacketUpStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import utilities.button.ButtMinus;
import utilities.button.ButtPlus;

public class StatusGui extends GuiScreen
{
	private ResourceLocation texture = new ResourceLocation(EORReference.MODID, "textures/gui/status_gui.png");
    /** The old x position of the mouse pointer */
    private float oldMouseX;
    /** The old y position of the mouse pointer */
    private float oldMouseY;
    private int str;
    private int sta;
    private int dex;
    private int ints;

    public StatusGui(EntityPlayer player)
    {
    }

    @Override
    public void initGui()
    {
        
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Player Status", this.width / 2, 20, 0xFFFFFF);
        int w = this.width / 2;
	    int h = this.height / 2;
	    this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
	    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	    Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
	    //TextureSetting.setblur();
	    drawTexturedModalRect(w - 128, h - 117, 0, 0, 256, 256);

        // ดึงข้อมูลจาก PlayerData
        // เข้าถึงผู้เล่นในฝั่งไคลเอนต์
        EntityPlayer player = Minecraft.getMinecraft().player;
        IPlayerData dtPlayer = PlayerDataCapability.getPlayerData(player);
        //PlayerStats playerStats = PlayerStats.get(player);

        // แสดงข้อมูลระดับและค่าต่างๆ
        if (dtPlayer != null)
        {
            
            this.drawString(this.fontRenderer, "§eอาชีพ§3:", w - 113 , h + 25, 0xFFFFFF);
            if (dtPlayer.getPlayerClass().equalsIgnoreCase("Slayer")) {
                this.drawString(this.fontRenderer, "§dSlayer", w - 82 , h - 75, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§fVargrant", w - 82 , h + 25, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fLevel§3:", w - 113, h + 40, 0xFFFFFF);
            this.drawString(this.fontRenderer, "§e" + dtPlayer.getLevel(), w - 82, h + 40, 0xFFFFFF);
            this.drawString(this.fontRenderer, "§fGuild: " + dtPlayer.getExp() + "/" + 100 + (dtPlayer.getLevel() * 10), w - 113 , h + 55, 0xFFFFFF);
            
            
            
            this.drawString(this.fontRenderer, "§fพลังโจมตี :", w - 15, h - 85, 0xFFFFFF);
            if (this.str <= 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getAttack() + " §7(§a+" + this.str * 5 + "§7)", w + 75, h - 85, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getAttack(), w + 75, h - 85, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fป้องกัน :", w - 15, h - 75, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getDefend(), w + 75, h - 75, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getDefend(), w + 75, h - 75, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fAccuracy :", w - 15, h - 65, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getAccuracy(), w + 75, h - 65, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getAccuracy(), w + 75, h - 65, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fEvasion :", w - 15, h - 55, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getEvasion(), w + 75, h - 55, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getEvasion(), w + 75, h - 55, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fCriticalRate :", w - 15, h - 45, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getCriRate(), w + 75, h - 45, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getCriRate(), w + 75, h - 45, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fCriticalDamage :", w - 15, h - 35, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getCriDamage(), w + 75, h - 35, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getCriDamage(), w + 75, h - 35, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fResisCriRate :", w - 15, h - 25, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getReCriRate(), w + 75, h - 25, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getReCriRate(), w + 75, h - 25, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fResisCriDamage :", w - 15, h - 15, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getReCriDamage(), w + 75, h - 15, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getReCriDamage(), w + 75, h - 15, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fHealth :", w - 15, h - 5, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getMaxHealth(), w + 75, h - 5, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getMaxHealth(), w + 75, h - 5, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fMana :", w - 15, h + 5, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getMaxMana(), w + 75, h + 5, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getMaxMana(), w + 75, h + 5, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fRegenHealth :", w - 15, h + 15, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getRegenHP(), w + 75, h + 15, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getRegenHP(), w + 75, h + 15, 0xFFFFFF);
            }
            this.drawString(this.fontRenderer, "§fRegenMana :", w - 15, h + 25, 0xFFFFFF);
            if (this.str > 0) {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getRegenMP(), w + 75, h + 25, 0xFFFFFF);
            } else {
            	this.drawString(this.fontRenderer, "§e" + dtPlayer.getRegenMP(), w + 75, h + 25, 0xFFFFFF);
            }
            
            this.mc.fontRenderer.drawString("§fPoint : §6" + dtPlayer.getPoint(), w + 27, h + 44, 0);
    	    
    	    this.mc.fontRenderer.drawString("§fSTR", w - 15, h + 56, 0);
    	    if (this.str > 0) {
    	    	this.mc.fontRenderer.drawString("§b" + dtPlayer.getStr() + " §7(§a+" + this.str + "§7)", w + 10, h + 56, 0);
    	    } else {
    	    	this.mc.fontRenderer.drawString("§b" + dtPlayer.getStr(), w + 10, h + 56, 0);
    	    }
    	    
    	    this.mc.fontRenderer.drawString("§fSTA", w - 15, h + 68, 0);
    	    if (this.sta > 0) {
    	    	this.mc.fontRenderer.drawString("§b" + dtPlayer.getSta() + " §7(§a + " + this.sta + "§7)", w + 10, h + 68, 0);
    	    } else {
    	    	this.mc.fontRenderer.drawString("§b" + dtPlayer.getSta(), w + 10, h + 68, 0);
    	    }
    	    
    	    this.mc.fontRenderer.drawString("§fDEX", w - 15, h + 81, 0);
    	    if (this.dex > 0) {
    	    	this.mc.fontRenderer.drawString("§b" + dtPlayer.getDex() + " §7(§a + " + this.dex + "§7)", w + 10, h + 81, 0);
    	    } else {
    		    this.mc.fontRenderer.drawString("§b" + dtPlayer.getDex(), w + 10, h + 81, 0);
    	    }
    	    
    	    this.mc.fontRenderer.drawString("§fINT", w - 13, h + 93, 0);
    	    if (this.ints > 0) {
    	    	this.mc.fontRenderer.drawString("§b" + dtPlayer.getInts() + " §7(§a + " + this.ints + "§7)", w + 10, h + 93, 0);
    	    } else {
    		    this.mc.fontRenderer.drawString("§b" + dtPlayer.getInts(), w + 10, h + 93, 0);
    	    }
            
            
            //this.drawString(this.fontRenderer, healthText, w, h, 0xFFFFFF);
            
            //this.drawCenteredString(this.fontRenderer, player.getHealth() + "/" + player.getMaxHealth(), this.width / 2, 100, 0xFFFFFF);
            //this.drawCenteredString(this.fontRenderer, defenseText, this.width / 2, 80, 0xFFFFFF);

            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        	drawEntityOnScreen(k + 80, l + 150, 30, (float)(k + 51) - this.oldMouseX, (float)(l + 75 - 50) - this.oldMouseY, this.mc.player);
        }
        else
        {
            this.drawCenteredString(this.fontRenderer, "No Player Data", this.width / 2, 40, TextFormatting.RED.getColorIndex());
        }

        this.buttonList.clear();
        
        this.buttonList.add(new ButtPlus(1, w + 55, h + 55, 10, 10, ""));
		this.buttonList.add(new ButtMinus(2, w + 67, h + 55, 10, 10, ""));
		
		this.buttonList.add(new ButtPlus(3, w + 55, h + 67, 10, 10, ""));
		this.buttonList.add(new ButtMinus(4, w + 67, h + 67, 10, 10, ""));
		
		this.buttonList.add(new ButtPlus(5, w + 55, h + 79, 10, 10, ""));
		this.buttonList.add(new ButtMinus(6, w + 67, h + 79, 10, 10, ""));
		
		this.buttonList.add(new ButtPlus(7, w + 55, h + 91, 10, 10, ""));
		this.buttonList.add(new ButtMinus(8, w + 67, h + 91, 10, 10, ""));
		
		this.buttonList.add(new GuiButton(9, w + 80, h + 73, 44, 20, "§fยืนยัน"));
	    //this.buttonList.add(new ButtReset(10, w + 110, h + 91, 10, 10, ""));
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void actionPerformed(GuiButton button)
    {
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	IPlayerData dtPlayer = player.getCapability(PlayerDataCapability.PLAYER_DATA_CAPABILITY, null);
        if (button.id == 0) { // ถ้ากดปุ่ม Close
        	this.mc.setIngameFocus();
        	player.closeScreen();
        }
        else if (button.id == 1)
		{
			if ((this.str < dtPlayer.getPoint()) && (this.str < dtPlayer.getPoint() - (this.sta - this.dex - this.ints)))
			{
				++this.str;
			}
		}
        else if (button.id == 2)
		{
			if (this.str > 0) {
        		--this.str;
        	}
		}
		else if (button.id == 3)
		{
			if (this.sta < dtPlayer.getPoint() && this.sta < dtPlayer.getPoint()-this.str-this.dex-this.ints) {
				++this.sta;
			}
		}
		else if (button.id == 4)
		{
			if (this.sta > 0) {
        		--this.sta;
        	}
		}
		else if (button.id == 5)
		{
			if (this.dex < dtPlayer.getPoint() && this.dex < dtPlayer.getPoint()-this.str-this.sta-this.ints) {
				++this.dex;
			}
		}
		else if (button.id == 6)
		{
			if (this.dex > 0) {
        		--this.dex;
        	}
		}
		else if (button.id == 7)
		{
			if (this.ints < dtPlayer.getPoint() && this.ints < dtPlayer.getPoint()-this.str-this.sta-this.dex) {
				++this.ints;
			}
		}
		else if (button.id == 8)
		{
			if (this.ints > 0) {
        		--this.ints;
        	}
		}
		else if (button.id == 9)
		{
			NetworkHandler.sendToServer(new PacketUpStatus(0, this.str, this.sta, this.dex, this.ints, this.mc.player.getName()));
			this.str = 0;
        	this.sta = 0;
        	this.dex = 0;
        	this.ints = 0;
		}
		else if (button.id == 10)
		{
			this.str = 0;
        	this.sta = 0;
        	this.dex = 0;
        	this.ints = 0;
		}
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