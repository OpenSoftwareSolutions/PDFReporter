package org.oss.pdfreporter.engine.fill;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.fill.ThreadPoolSubreportRunnerFactory.ExecutorServiceDisposable;
import org.oss.pdfreporter.engine.fill.ThreadPoolSubreportRunnerFactory.SubreportsThreadFactory;

public class StatefullThreadExecutorSubreportRunnerFactory implements JRSubreportRunnerFactory {

	private static final String THREAD_POOL_KEY = "net.sf.jasperreports.engine.fill.JRThreadSubreportRunner.ThreadPool";
	private static final Logger logger = Logger.getLogger(StatefullThreadExecutorSubreportRunnerFactory.class.getName());
	
	@Override
	public JRSubreportRunner createSubreportRunner(
			JRFillSubreport fillSubreport, JRBaseFiller subreportFiller) {
		
		JRFillContext fillContext = subreportFiller.getFillContext();
		ExecutorServiceDisposable executor = (ExecutorServiceDisposable) fillContext.getFillCache(THREAD_POOL_KEY);
		if (executor == null)
		{
			ExecutorService threadExecutor = createThreadExecutor(fillContext);
			executor = new ExecutorServiceDisposable(threadExecutor);
			fillContext.setFillCache(THREAD_POOL_KEY, executor);
		}

		return new StatefullThreadExecutorSubreportRunner(fillSubreport, subreportFiller,executor.getExecutorService());
	}
	
	protected ExecutorService createThreadExecutor(JRFillContext fillContext)
	{
		SubreportsThreadFactory threadFactory = new SubreportsThreadFactory(fillContext);
		ExecutorService threadExecutor = Executors.newCachedThreadPool(threadFactory);
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("created subreports thread executor " + threadExecutor 
					+ " for " + fillContext.getMasterFiller().getJasperReport().getName());
		}
		return threadExecutor;
	}
	

}
