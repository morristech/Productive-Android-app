package co.infinum.productive.dagger.module;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import dagger.Module;
import dagger.Provides;

/**
 * Created by noxqs on 10.11.15..
 */

/*
* this should be used only for testing purposes, if run on a device it will result in NetworkOnMainThreadException
*/

@Module
public class SynchronousExecutorsModule {

    class SychronousExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }


    class SynchronousExecutorService implements ExecutorService {

        @Override
        public void execute(Runnable command) {
            command.run();
        }

        @Override
        public void shutdown() {

        }

        @NonNull
        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        @Override
        public boolean isShutdown() {
            return false;
        }

        @Override
        public boolean isTerminated() {
            return false;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @NonNull
        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return null;
        }

        @NonNull
        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return null;
        }

        @NonNull
        @Override
        public Future<?> submit(Runnable task) {
            return null;
        }

        @NonNull
        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return null;
        }

        @NonNull
        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
                throws InterruptedException {
            return null;
        }

        @NonNull
        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            return null;
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
                throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    }

    @Provides
    public ExecutorService provideHttpExecutorService() {
        return new SynchronousExecutorService();
    }


    @Provides
    public Executor provideCallbackExecutor() {
        return new SychronousExecutor();
    }

}
