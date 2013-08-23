package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.JRException;

public class SynchronSubreportRunner implements JRSubreportRunner {

	private final JRFillSubreport fillSubreport;
	private final JRBaseFiller subreportFiller;
	private JRSubreportRunResult result = null;
	private boolean filling = true;
	
	public SynchronSubreportRunner(JRFillSubreport fillSubreport, JRBaseFiller subreportFiller) {
		super();
		this.fillSubreport = fillSubreport;
		this.subreportFiller = subreportFiller;
	}

	@Override
	public boolean isFilling() {
		return filling;
	}

	@Override
	public JRSubreportRunResult start() throws JRException {
		fillReport();
		return result;
	}

	
	private void fillReport() {
		if (result==null) {			
			try
			{
				fillSubreport.fillSubreport();
				filling = false;
				//subreportFiller.notifyAll();
				result = new JRSubreportRunResult(true,null);
			}
			catch (Throwable t) //NOPMD
			{
				filling = false;
				result = new JRSubreportRunResult(true,t);
			}		
		}
	}
	
	@Override
	public JRSubreportRunResult resume() throws JRException {
		fillReport();
		return result;
	}

	@Override
	public void reset() throws JRException {
	}

	@Override
	public void cancel() throws JRException {
	}

	@Override
	public void suspend() throws JRException {
	}

}
