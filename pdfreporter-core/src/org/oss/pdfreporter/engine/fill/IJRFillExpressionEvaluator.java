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
package org.oss.pdfreporter.engine.fill;

import org.oss.pdfreporter.engine.JRException;
import org.oss.pdfreporter.engine.JRExpression;

/**
 * Fill time expression evaluators interface.
 * <p>
 * An instance of this interface is used at fill time by elements to
 * evaluate expressions.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRFillExpressionEvaluator.java 5180 2012-03-29 13:23:12Z teodord $
 */
public interface IJRFillExpressionEvaluator
{
	/**
	 * Evaluates an expression.
	 * 
	 * @param expression the expression to evaluate
	 * @param evaluationType one of {@link JRExpression#EVALUATION_DEFAULT},
	 * {@link JRExpression#EVALUATION_OLD}, {@link JRExpression#EVALUATION_ESTIMATED}
	 * @return the result
	 * @throws JRException
	 */
	public Object evaluate(JRExpression expression, byte evaluationType) throws JRException;
}
