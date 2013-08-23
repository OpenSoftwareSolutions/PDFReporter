package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.Deduplicable;

public interface IJRFillContext {

	public <T extends Deduplicable> T deduplicate(T object);
}
