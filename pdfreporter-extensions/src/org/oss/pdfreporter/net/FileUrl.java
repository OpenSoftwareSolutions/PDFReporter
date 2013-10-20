package org.oss.pdfreporter.net;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUrl implements IURL {

	private final File file;
	
	
	public FileUrl(File file) {
		super();
		this.file = file;
	}


	public FileUrl(String file) {
		this(new File(file));
	}
	
	@Override
	public InputStream openStream() throws IOException {
		return new FileInputStream(file);
	}


	@Override
	public String getPath() {
		return file.getAbsolutePath();
	}

}
