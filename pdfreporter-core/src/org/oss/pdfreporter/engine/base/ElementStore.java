package org.oss.pdfreporter.engine.base;

import org.oss.pdfreporter.engine.JRPrintElement;

interface ElementStore extends VirtualizablePageElements
{
	int size();

	JRPrintElement get(int index);

	boolean add(JRPrintElement element);

	boolean add(int index, JRPrintElement element);

	JRPrintElement set(int index, JRPrintElement element);

	JRPrintElement remove(int index);
	
	void dispose();
}