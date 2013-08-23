package org.oss.pdfreporter.engine.fill;

public class SynchronSubreportRunnerFactory implements JRSubreportRunnerFactory {

	@Override
	public JRSubreportRunner createSubreportRunner(
			JRFillSubreport fillSubreport, JRBaseFiller subreportFiller) {
		return new SynchronSubreportRunner(fillSubreport, subreportFiller);
	}

}
