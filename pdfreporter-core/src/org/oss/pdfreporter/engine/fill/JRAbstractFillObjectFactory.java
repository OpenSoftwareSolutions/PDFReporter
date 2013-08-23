package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.JRAbstractObjectFactory;
import org.oss.pdfreporter.engine.JRGroup;

public abstract class JRAbstractFillObjectFactory extends JRAbstractObjectFactory {
	abstract protected JRGroup getGroup(JRGroup group);
}
