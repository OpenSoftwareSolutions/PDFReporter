/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.sql.factory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.oss.pdfreporter.sql.IBlob;
import org.oss.pdfreporter.sql.SQLException;


public class BlobImpl implements IBlob {

	private final InputStream in;
	private boolean consumed = false;
	
	
	BlobImpl(InputStream is) {
		super();
		this.in = is;
	}

	BlobImpl(byte[] bytes) {
		this(new ByteArrayInputStream(bytes));
	}
	
	@Override
	public InputStream getInputStream() throws SQLException {
		try {
			if (!consumed) {
				return in;
			}
			throw new SQLException("Data already consumed.");
		} finally {
			consumed = true;
		}
	}

	@Override
	public byte[] getBytes() throws SQLException {
		try {
			if (!consumed) {				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = in.read(buffer);
				while (len != -1) {
					out.write(buffer, 0, len);
					len = in.read(buffer);
				}	
				in.close();
				return out.toByteArray();
			}
			throw new SQLException("Data already consumed.");
		} catch (IOException e) {
			throw new SQLException(e.getMessage());
		} finally {
			consumed = true;
		}
	}

}
