package l3oatz.eldoria.gui.ingame.dataframe;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

public class ThreadUtils {
	private final Queue<FutureTask<?>> scheduledTasks = Queues.<FutureTask<?>>newArrayDeque();
	private final Thread mcThread = Thread.currentThread();

	public <V> ListenableFuture<V> addScheduledTask(Callable<V> callableToSchedule) {
		Validate.notNull(callableToSchedule);

		if (this.isCallingFromMinecraftThread()) {
			try {
				return Futures.<V>immediateFuture(callableToSchedule.call());
			} catch (Exception exception) {
				return Futures.immediateFailedCheckedFuture(exception);
			}
		} else {
			ListenableFutureTask<V> listenablefuturetask = ListenableFutureTask.<V>create(callableToSchedule);

			synchronized (this.scheduledTasks) {
				this.scheduledTasks.add(listenablefuturetask);
				return listenablefuturetask;
			}
		}
	}

	public ListenableFuture<Object> addScheduledTask(Runnable runnableToSchedule) {
		Validate.notNull(runnableToSchedule);
		return this.<Object>addScheduledTask(Executors.callable(runnableToSchedule));
	}

	public boolean isCallingFromMinecraftThread() {
		return Thread.currentThread() == this.mcThread;
	}
}