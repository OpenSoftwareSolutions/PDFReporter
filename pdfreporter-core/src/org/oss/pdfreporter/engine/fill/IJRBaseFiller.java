package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.JRDefaultStyleProvider;

public interface IJRBaseFiller extends JRDefaultStyleProvider{

	public abstract IJRFillContext getFillContext();
}
