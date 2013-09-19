package org.oss.pdfreporter.engine.fill;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRRuntimeException;

public class StatefullThreadExecutorSubreportRunner extends JRSubreportRunnable implements JRSubreportRunner {
	private final static Logger logger = Logger.getLogger(StatefullThreadExecutorSubreportRunner.class.getName());

	private final JRBaseFiller subreportFiller;
	/**
	 * True after entering start method
	 * filling
	 */
	private boolean filling = false;
	/**
	 * suspended
	 */
	private boolean suspended = false;

	/**
	 * threadExecutor
	 */
	private Executor threadExecutor;

	
	public StatefullThreadExecutorSubreportRunner(JRFillSubreport fillSubreport, JRBaseFiller subreportFiller, Executor threadExecutor) {
		super(fillSubreport);
		this.subreportFiller = subreportFiller;
		this.threadExecutor = threadExecutor;
	}

	@Override
	public boolean isFilling() {
		return filling;
	}

	@Override
	public JRSubreportRunResult start() throws JRException {
		filling = true;
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": starting");
		}
		threadExecutor.execute(this);	
		return waitResult();
	}

	protected JRSubreportRunResult waitResult() {
		while (!isStarted() || (isRunning() && !suspended)) {
			try {
				logger.finest("Fill " + subreportFiller.fillerId + ": waiting for fill result");
				subreportFiller.wait(50);
			} catch (InterruptedException e) {
				throw new JRRuntimeException("Error encountered while waiting on the report filling thread.",e);
			}
		};
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notified of fill result");
		}
		return runResult();
	}
	
	
	@Override
	public JRSubreportRunResult resume() throws JRException {
		suspended = false;
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notifying to continue");
		}
		return waitResult();
	}

	@Override
	public void reset() throws JRException {
		filling = false;
	}

	@Override
	public void cancel() throws JRException {
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notifying to continue on cancel");
		}
	}

	@Override
	public synchronized void suspend() throws JRException {
		suspended = true;
		int parentPageCount = subreportFiller.parentFiller.getCurrentPageCount();
//		if (logger.isLoggable(Level.FINEST)) {
//			logger.finest("Filler: " + subreportFiller.getFillerId()
//					+ " (Report: "
//					+ subreportFiller.getJasperReport().getName() + "), Page: "
//					+ subreportFiller.getCurrentPageCount()
//					+ " is waiting for parent Filler: "
//					+ subreportFiller.parentFiller.getFillerId() + " (Report: "
//					+ subreportFiller.parentFiller.getJasperReport().getName()
//					+ ") to finish Page: " + parentPageCount);
//		}
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notifying on suspend");
		}
		while(subreportFiller.parentFiller.getCurrentPageCount()<=parentPageCount && suspended) {
			try {
				logger.finest("Fill " + subreportFiller.fillerId + ": waiting to continue");
				subreportFiller.wait(50);
			} catch (InterruptedException e) {
				throw new JRRuntimeException("Error encountered while waiting on the report filling thread.",e);
			}			
		}
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notified to continue");
		}
//		if (logger.isLoggable(Level.FINEST)) {
//			parentPageCount = subreportFiller.parentFiller
//					.getCurrentPageCount();
//			logger.finest("Filler: " + subreportFiller.getFillerId()
//					+ " (Report: "
//					+ subreportFiller.getJasperReport().getName() + "), Page: "
//					+ subreportFiller.getCurrentPageCount()
//					+ " continues for parent Filler: "
//					+ subreportFiller.parentFiller.getFillerId() + " (Report: "
//					+ subreportFiller.parentFiller.getJasperReport().getName()
//					+ ") on Page: " + parentPageCount);
//		}
	}

	@Override
	public void run() {
		super.run();
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notifying of completion");
		}
	}
	

}
