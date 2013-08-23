/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package org.oss.pdfreporter.engine.base;

import java.io.Serializable;

import org.oss.pdfreporter.engine.JRConstants;
import org.oss.pdfreporter.engine.JRQuery;
import org.oss.pdfreporter.engine.JRQueryChunk;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.query.QueryExecuterFactory;
import org.oss.pdfreporter.engine.util.JRCloneUtils;
import org.oss.pdfreporter.engine.util.JRQueryParser;



/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseQuery.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class JRBaseQuery implements JRQuery, Serializable
{
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	private JRQueryChunk[] chunks;
	
	protected String language = QueryExecuterFactory.QUERY_LANGUAGE_SQL;


	/**
	 *
	 */
	protected JRBaseQuery()
	{
	}


	/**
	 *
	 */
	protected JRBaseQuery(JRQuery query, JRBaseObjectFactory factory)
	{
		factory.put(query, this);
		
		/*   */
		JRQueryChunk[] jrChunks = query.getChunks();
		if (jrChunks != null && jrChunks.length > 0)
		{
			chunks = new JRQueryChunk[jrChunks.length];
			for(int i = 0; i < chunks.length; i++)
			{
				chunks[i] = factory.getQueryChunk(jrChunks[i]);
			}
		}
		
		language = query.getLanguage();
	}
		

	/**
	 *
	 */
	public JRQueryChunk[] getChunks()
	{
		return this.chunks;
	}


	/**
	 *
	 */
	public String getText()
	{
		return JRQueryParser.instance().asText(getChunks());
	}


	public String getLanguage()
	{
		return language;
	}
	

	/**
	 * 
	 */
	public Object clone() 
	{
		JRBaseQuery clone = null;

		try
		{
			clone = (JRBaseQuery)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new JRRuntimeException(e);
		}
		
		clone.chunks = JRCloneUtils.cloneArray(chunks);

		return clone;
	}
	
	
}
