/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.progress;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.extensions.ExtensionsEnvironment;
import org.oss.pdfreporter.progress.IProgressHandler.ProgressState;


/**
 * A Progressmanager that keeps track of elapsed time and percentage completion.<br>
 * IProgressHandler can be registered via Extensions that will be notified on progress.<br>
 * Progress is indicated when either more than 250ms have elapsed or if completion advanced for 5% or when done.<br>
 * TODO that we do not have to extend signatures to pass the progress manager into workload classes we should find a way to get it when needed from environment, perhaps it should be an extension it self.
 * @author donatmuller, 2013, last change 12:22:04 AM
 * 
 */
public class ProgressManager {
	private final static Logger logger = Logger.getLogger(ProgressManager.class.getName());
	private final static long LOG_TRIGGER_TIME_INTERVALL = 250;
	private final static int LOG_TRIGGER_PERCENT_INTERVALL = 5;
	private final IProgressHandler handler;
	private final ProgressState state;
	private final int maxRecords, logTriggerRecordsInterval;
	private final long startTime;
	private long timeElapsed;
	private int recordsComplete;
	
	public ProgressManager(ProgressState state, int maxRecords) {
		List<IProgressHandler> handlers = ExtensionsEnvironment.getExtensionsRegistry().getExtensions(IProgressHandler.class);
		this.handler = handlers.isEmpty() ? null : handlers.get(0);
		this.state = state;
		this.maxRecords = maxRecords;
		this.logTriggerRecordsInterval = maxRecords * LOG_TRIGGER_PERCENT_INTERVALL / 100;
		this.recordsComplete = 0;
		this.startTime = System.currentTimeMillis();
	}
	
	public ProgressManager(ProgressState state) {
		this(state,-1);
	}
	
	public void progress() {
		progress(false);
	}
	
	public void progress(int recordCount) {
		if (maxRecords==-1) {
			throw new RuntimeException("Cannot calculate percentage without knowing total record count.");
		}
		if (recordCount - recordsComplete >= logTriggerRecordsInterval) {
			recordsComplete = recordCount; 
			progress(true);
		}
	}
	
	public void done() {
		recordsComplete = maxRecords;
		timeElapsed = System.currentTimeMillis() - startTime;
		notifyProgress();
	}
	
	private void progress(boolean force) {
		long elapsed = System.currentTimeMillis() - startTime;
		if (elapsed - timeElapsed >= LOG_TRIGGER_TIME_INTERVALL || force) {
			timeElapsed = elapsed;
			notifyProgress();
		}
	}
	
	private void notifyProgress() {
		float percentComplete = 100f * recordsComplete / maxRecords;
		if (handler!=null) {
			handler.progress(state, percentComplete, timeElapsed);
		}
		if (logger.isLoggable(Level.FINER)) {		
			String stateString = state.toString();
			logger.finer("Progress " + state + String.format("%1$" + (10 - stateString.length()) + "c %2$,.2f%% done, time elapsed: %3$TM:%3$TS.%3$TL", ':',percentComplete, timeElapsed));
		}
	}
}
