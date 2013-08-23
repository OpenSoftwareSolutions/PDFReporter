package org.oss.pdfreporter.text;

public class Chunk {
	String val;
	int mode;
	int param;
	
	public Chunk(String val, int mode) {
		this.val = val;
		this.mode = mode;
	}
	
	public Chunk(String val, int mode, int param) {
		this.val = val;
		this.mode = mode;
		this.param = param;
	}
	
}