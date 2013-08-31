/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.engine.fill;

import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Thread-based {@link net.sf.jasperreports.engine.fill.JRSubreportRunner JRSubreportRunner}
 * implementation.
 * <p>
 * The subreport fill is launched in a new thread which coordinates suspend/resume actions with
 * the master thread.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ThreadExecutorSubreportRunner.java 5747 2012-10-30 12:51:36Z lucianc $
 */
public class ThreadExecutorSubreportRunner extends AbstractThreadSubreportRunner
{
	
	private static final Logger logger = Logger.getLogger(ThreadExecutorSubreportRunner.class.getName());

	private Executor threadExecutor;
	private boolean filling;
	
	public ThreadExecutorSubreportRunner(JRFillSubreport fillSubreport, JRBaseFiller subreportFiller,
			Executor threadExecutor)
	{
		super(fillSubreport, subreportFiller);
		this.threadExecutor = threadExecutor;
	}

	public boolean isFilling()
	{
		return filling;
	}

	@Override
	protected void doStart()
	{
		filling = true;
		
		if (logger.isLoggable(Level.FINEST))
		{
			logger.finest("Fill " + subreportFiller.fillerId + ": starting");
		}
		
		threadExecutor.execute(this);
	}

	public void reset()
	{
		filling = false;
	}
}
