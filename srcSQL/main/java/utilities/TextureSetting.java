package utilities;

import net.minecraft.client.renderer.GlStateManager;

public class TextureSetting
{
	public static void setblur()
	{
		GlStateManager.glTexParameteri(3553, 10241, 9729);
		GlStateManager.glTexParameteri(3553, 10240, 9729);
	}
  
	public static void setPixel()
	{
		GlStateManager.glTexParameteri(3553, 10241, 9728);
		GlStateManager.glTexParameteri(3553, 10240, 9728);
	}
}