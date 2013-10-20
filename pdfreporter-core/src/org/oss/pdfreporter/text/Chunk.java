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
