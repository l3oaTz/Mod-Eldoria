package l3oatz.eldoria.gui;

//PlayerInteractionGUI.java
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

public class PlayerInteractionGU extends GuiScreen {
	private EntityPlayer targetPlayer;
	
	public PlayerInteractionGU(EntityPlayer targetPlayer) {
		this.targetPlayer = targetPlayer;
	}
	
	@Override
	public void initGui() {
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height / 2 - 30, 100, 20, "Add Friend"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 50, this.height / 2, 100, 20, "Whisper"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 50, this.height / 2 + 30, 100, 20, "Invite to Guild"));
		this.buttonList.add(new GuiButton(3, this.width / 2 - 50, this.height / 2 + 60, 100, 20, "Invite to Party"));
	}

 @Override
 protected void actionPerformed(GuiButton button) {
     switch (button.id) {
         case 0:
             sendFriendRequest(targetPlayer);
             break;
         case 1:
             sendWhisper(targetPlayer);
             break;
         case 2:
             sendGuildInvite(targetPlayer);
             break;
         case 3:
             sendPartyInvite(targetPlayer);
             break;
     }
     this.mc.displayGuiScreen(null);
 }

 private void sendFriendRequest(EntityPlayer target) {
     // Implement packet to send friend request
     mc.player.sendMessage(new TextComponentString("Friend request sent to " + target.getName()));
 }

 private void sendWhisper(EntityPlayer target) {
     // Implement packet to whisper
     mc.player.sendMessage(new TextComponentString("Whisper sent to " + target.getName()));
 }

 private void sendGuildInvite(EntityPlayer target) {
     // Implement packet to send guild invite
     mc.player.sendMessage(new TextComponentString("Guild invite sent to " + target.getName()));
 }

 private void sendPartyInvite(EntityPlayer target) {
     // Implement packet to send party invite
     mc.player.sendMessage(new TextComponentString("Party invite sent to " + target.getName()));
 }
}