package l3oatz.eldoria.gui.ingame.dataframe;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class DataFrame
{
	public static DataFrame instance;
	public static int numfile = 163 ;

	public DataFrame()
	{
		instance = this;
		URL[] u = new URL[numfile];
		for (int i = 0; i < u.length; i++)
		{
			try {
				u[i] = getClass().getClassLoader().getResource("assets/eldoria/textures/frame/img" + i + ".jpg").toURI().toURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.print(u[i]);
		}
		ImageUtils.instance.loadWithExecutor(u);
	}
}