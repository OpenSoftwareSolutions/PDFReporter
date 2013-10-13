package org.oss.pdfreporter.engine.fill;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRRuntimeException;

/**
 * A subreport runner implementation that avoids notifyAll() and wait() other threads.
 * Had to remove the synchronized statement on the fill methods of concrete fillers,
 * as this would deadlock execution without waking up threads.
 * @author donatmuller, 2013, last change 3:36:24 PM
 * 
 */
public class WaitingSubreportRunner extends JRSubreportRunnable implements JRSubreportRunner {

	private final static Logger logger = Logger.getLogger(WaitingSubreportRunner.class.getName());
	
	
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


	
	public WaitingSubreportRunner(JRFillSubreport fillSubreport, JRBaseFiller subreportFiller) {
		super(fillSubreport);
		this.subreportFiller = subreportFiller;
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
			logger.finest("Fill " + subreportFiller.fillerId + ": starting "+ Thread.currentThread().getName());
		}
		new Thread(this,"Fillerthread: " + subreportFiller.fillerId).start();
		return waitResult();
	}

	protected JRSubreportRunResult waitResult() {
		while (!isStarted() || (isRunning() && !suspended)) {
			try {
				logger.finest("Fill " + subreportFiller.fillerId + ": waiting for fill result");
				Thread.sleep(50);  // Perhaps we really have just to wait till started
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
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notifying to continue");
		}
		suspended = false;
		// PROBABLY WE NEED TO WAIT HERE TILL THE PAGE HAS RENDERED
		return waitResult();
	}

	@Override
	public void reset() throws JRException {
		filling = false;
	}

	@Override
	public void cancel() throws JRException {
		throw new JRException("UNSUPPORTED CANCEL OPERATION CALLED");
	}

	@Override
	public void suspend() throws JRException {
		if (suspended) {
			throw new JRException("ILLEGAL STATE ALREADY SUSPENDED");
		}
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notifying on suspend");
		}
		suspended = true;
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": waiting to continue");
		}
		while (suspended) {
			try {
				logger.finest("Fill " + subreportFiller.fillerId + ": waiting for fill result");
				Thread.sleep(50);  // Perhaps we really have just to wait till started
			} catch (InterruptedException e) {
				throw new JRRuntimeException("Error encountered while waiting on the report filling thread.",e);
			}
		};
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notified to continue");
		}
	}

	@Override
	public void run() {
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": enter run " + Thread.currentThread().getName());
		}
		super.run();
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": notifying of completion");
		}
	}

}
