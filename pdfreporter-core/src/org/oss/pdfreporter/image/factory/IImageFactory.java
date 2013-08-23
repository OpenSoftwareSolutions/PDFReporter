package org.oss.pdfreporter.image.factory;

import org.oss.pdfreporter.image.IImageManager;
import org.oss.pdfreporter.registry.ISessionCapable;

public interface IImageFactory extends ISessionCapable {
	IImageManager getImageManager();
}
