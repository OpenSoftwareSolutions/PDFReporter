package org.oss.pdfreporter.engine.fill;

public class WaitingSubreportRunnerFactory implements JRSubreportRunnerFactory {

	@Override
	public JRSubreportRunner createSubreportRunner(
			JRFillSubreport fillSubreport, JRBaseFiller subreportFiller) {
		return new WaitingSubreportRunner(fillSubreport, subreportFiller);
	}

}
