package org.oss.pdfreporter.progress;

public interface IProgressHandler {
	public enum ProgressState {
		LOADING,
		COMPILING,
		FILLING,
		EXPORTING
	}
	
	void progress(ProgressState state, float percentComplete, long timeElapsed);
}
