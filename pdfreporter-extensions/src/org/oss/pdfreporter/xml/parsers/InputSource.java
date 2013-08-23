package org.oss.pdfreporter.xml.parsers;

import java.io.InputStream;
import java.io.Reader;

import org.oss.pdfreporter.xml.parsers.IInputSource;


public class InputSource implements IInputSource {

	private InputStream byteStream = null;
    private Reader characterStream = null;
	
	public InputSource(InputStream byteStream) {
		this.byteStream = byteStream;
	}
	public InputSource(Reader characterStream) {
		this.characterStream = characterStream;
	}
	@Override
	public void setByteStream(InputStream byteStream) {
		this.byteStream = byteStream;
	}

	@Override
	public InputStream getByteStream() {
		return byteStream;
	}
	@Override
	public void setCharacterStream(Reader characterStream) {
		this.characterStream = characterStream;
	}
	@Override
	public Reader getCharacterStream() {
		return characterStream;
	}

}
