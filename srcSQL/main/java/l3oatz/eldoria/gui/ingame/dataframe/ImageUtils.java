package l3oatz.eldoria.gui.ingame.dataframe;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import net.minecraft.client.renderer.texture.DynamicTexture;

public class ImageUtils {
	public static ImageUtils instance;

	public ImageUtils() {
		instance = this;
	}

	public Map<Integer, DynamicTexture> images = new HashMap<Integer, DynamicTexture>();

	public void addTexture(int name,BufferedImage image) {
		if (image != null) {
			images.put(name, new DynamicTexture(image));
		}
	}

//	public boolean hasData(int name) {
//		return images.containsKey(name);
//	}

	public void bindTexture(int name) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, images.get(name).getGlTextureId());
	}

	public void update(int name) {
		images.get(name).updateDynamicTexture();
	}

	public int getId(int name) {
		return images.get(name).getGlTextureId();
	}

	public void loadWithExecutor(URL[] images) {
//		ExecutorService service = Executors.newCachedThreadPool();
//		List<ImageLoadingTask> tasks = new ArrayList<ImageLoadingTask>(images.length);
//		for (URL file : images) {
//			tasks.add(new ImageLoadingTask(file));
//		}
//		try {
//			List<Future<BufferedImage>> results = service.invokeAll(tasks);
//			for (int i = 0; i < images.length; i++) {
//				try {
//					addTexture(i, results.get(i).get());
//				} catch (ExecutionException e) {
//
//				}
//			}
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
//		service.shutdown();

		
		ExecutorService service = Executors.newFixedThreadPool(2);
	    List<ImageLoadingTask> tasks = new ArrayList<>(images.length);
	    for (URL file : images) {
	        tasks.add(new ImageLoadingTask(file));
	    }
	    try {
	        List<Future<BufferedImage>> results = service.invokeAll(tasks);
	        ArrayList<Future<BufferedImage>> list = Lists.newArrayList(results.iterator());
	        
	        for (int i = 0; i < list.size(); i++) {
				Future<BufferedImage> type = list.get(i);
				try {
	        		BufferedImage img = type.get();
	        		addTexture(i, img);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
	    } catch (InterruptedException ex) {
	        ex.printStackTrace();
	    }
	    service.shutdown();
	}

	public class ImageLoadingTask implements Callable<BufferedImage> {
		private URL file;

		public ImageLoadingTask(URL file) {
			this.file = file;
		}

		@Override
		public BufferedImage call() throws Exception {
			return ImageIO.read(file);
		}
	}

}