/*
 * Copyright 2002-2007 Robert Breidecker.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.oss.pdfreporter.uses.net.sourceforge.jeval;

/**
 * Contains constants used by classes in this package.
 */
public class EvaluationConstants {

	/**
	 * The single quote character.
	 */
	public static final char SINGLE_QUOTE = '\'';

	/**
	 * The double quote character.
	 */
	public static final char DOUBLE_QUOTE = '"';

	/**
	 * The open brace character.
	 */
	public static final char OPEN_BRACE = '{';

	/**
	 * The closed brace character.
	 */
	public static final char CLOSED_BRACE = '}';

	/**
	 * The pound sign character.
	 */
	public static final char POUND_SIGN = '#';

	/**
	 * The open variable string.
	 */
	public static final String OPEN_VARIABLE = String.valueOf(POUND_SIGN)
			+ String.valueOf(OPEN_BRACE);

	/**
	 * The closed brace string.
	 */
	public static final String CLOSED_VARIABLE = String.valueOf(CLOSED_BRACE);

	/**
	 * The true value for a Boolean string.
	 */
	public static final String BOOLEAN_STRING_TRUE = "1.0";

	/**
	 * The false value for a Boolean string.
	 */
	public static final String BOOLEAN_STRING_FALSE = "0.0";
	
	/**
	 * The comma character.
	 */
	public static final char COMMA = ',';
	
	/**
	 * The function argument separator.
	 */
	public static final char FUNCTION_ARGUMENT_SEPARATOR = COMMA;
}
