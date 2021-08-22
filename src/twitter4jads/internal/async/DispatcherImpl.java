package twitter4jads.internal.async;

import twitter4jads.conf.Configuration;
import twitter4jads.internal.logging.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @since Twitter4J 2.1.2
 */
final class DispatcherImpl implements Dispatcher {
    private ExecuteThread[] threads;
    private final List<Runnable> q = new LinkedList<Runnable>();

    public DispatcherImpl(Configuration conf) {
        threads = new ExecuteThread[conf.getAsyncNumThreads()];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ExecuteThread("Twitter4J Async Dispatcher", this, i);
            threads[i].setDaemon(true);
            threads[i].start();
        }
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (active) {
                    shutdown();
                }
            }
        });
    }

    @Override
    public synchronized void invokeLater(Runnable task) {
        synchronized (q) {
            q.add(task);
        }
        synchronized (ticket) {
            ticket.notify();
        }
    }

    final Object ticket = new Object();

    public Runnable poll() {
        while (active) {
            synchronized (q) {
                if (q.size() > 0) {
                    Runnable task = q.remove(0);
                    if (task != null) {
                        return task;
                    }
                }
            }
            synchronized (ticket) {
                try {
                    ticket.wait();
                } catch (InterruptedException ignore) {
                }
            }
        }
        return null;
    }

    private boolean active = true;

    @Override
    public synchronized void shutdown() {
        if (active) {
            active = false;
            for (ExecuteThread thread : threads) {
                thread.shutdown();
            }
            synchronized (ticket) {
                ticket.notify();
            }
        }
    }
}

class ExecuteThread extends Thread {
    private static Logger logger = Logger.getLogger(ExecuteThread.class);
    DispatcherImpl q;

    ExecuteThread(String name, DispatcherImpl q, int index) {
        super(name + "[" + index + "]");
        this.q = q;
    }

    public void shutdown() {
        alive = false;
    }

    private boolean alive = true;

    public void run() {
        while (alive) {
            Runnable task = q.poll();
            if (task != null) {
                try {
                    task.run();
                } catch (Exception ex) {
                    logger.error("Got an exception while running a task:", ex);
                }
            }
        }
    }
}
