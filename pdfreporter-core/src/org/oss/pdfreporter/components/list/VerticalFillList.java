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
package org.oss.pdfreporter.components.list;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRRuntimeException;
import org.oss.pdfreporter.engine.component.FillPrepareResult;
import org.oss.pdfreporter.engine.fill.JRFillObjectFactory;


/**
 * Vertical fill list component implementation.
 *
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: VerticalFillList.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class VerticalFillList extends BaseFillList
{

	private static final Logger log = Logger.getLogger(VerticalFillList.class.getName());

	private final FillListContents listContents;

	public VerticalFillList(ListComponent component, JRFillObjectFactory factory) throws JRException
	{
		super(component, factory);

		JRFillObjectFactory datasetFactory = new JRFillObjectFactory(factory,
				createDatasetExpressionEvaluator());
		this.listContents = new FillListContents(component.getContents(), datasetFactory);
	}

	public FillPrepareResult prepare(int availableHeight)
	{
		createPrintFrame();
		try
		{
			boolean hadData = false;
			boolean overflow = false;

			if (filling)
			{
				// continuing after an overflow
				if (log.isLoggable(Level.FINE))
				{
					log.fine("Continuing list after overflow");
				}

				hadData = true;
				overflow = fillContents(availableHeight);
			}
			else
			{
				if (log.isLoggable(Level.FINE))
				{
					log.fine("Starting list rendering");
				}

				if (fillStarted)
				{
					// if already started and finished, rewind the data source
					if (log.isLoggable(Level.FINE))
					{
						log.fine("List reprinted, rewinding data source");
					}

					datasetRun.rewind();
				}

				datasetRun.start();
				fillStarted = true;
			}

			while(!overflow && datasetRun.next())
			{
				hadData = true;

				listContents.evaluateContents();
				overflow = fillContents(availableHeight);
			}

			if (overflow)
			{
				if (log.isLoggable(Level.FINE))
				{
					log.fine("List has overflowed");
				}

				// set the filling flag so that we know that we are continuing
				filling = true;
				return FillPrepareResult.printStretch(availableHeight, overflow);
			}
			else
			{
				// list has completed;

				if (log.isLoggable(Level.FINE))
				{
					log.fine("List has completed rendering");
				}

				filling = false;
				datasetRun.end();

				if (!hadData)
				{
					//if no data, set as no print
					return FillPrepareResult.NO_PRINT_NO_OVERFLOW;
				}

				return FillPrepareResult.printStretch(printFrame.getHeight(), false);
			}
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
	}

	protected boolean fillContents(int availableHeight) throws JRException
	{
		boolean overflow;
		int contentsAvailableHeight = availableHeight - printFrame.getHeight();
		if (contentsAvailableHeight < listContents.getHeight())
		{
			overflow = true;
		}
		else
		{
			listContents.prepare(contentsAvailableHeight);
			listContents.finalizeElementPositions();
			listContents.fillElements(
					new AppendingPrintElementContainer(printFrame));

			overflow = listContents.willOverflow();
		}
		return overflow;
	}
}
