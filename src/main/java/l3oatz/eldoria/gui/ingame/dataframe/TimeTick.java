package l3oatz.eldoria.gui.ingame.dataframe;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTick extends TimerTask
{
	public static TimeTick instance;

	public TimeTick()
	{
		instance = this;
		Timer timercd = new Timer();
		this.setCooldown(0);
		timercd.schedule(this, 0, 80);
	}

	public int ticktime = -1;
	public boolean isStop=false;
	public int getCooldown() {
		return ticktime;
	}

	public void setCooldown(int cooldown)
	{
		this.ticktime = cooldown;
	}

	public void run()
	{
		ticktime++;
		if (isStop) {
			cancel();
			instance=null;
		}
	}
}