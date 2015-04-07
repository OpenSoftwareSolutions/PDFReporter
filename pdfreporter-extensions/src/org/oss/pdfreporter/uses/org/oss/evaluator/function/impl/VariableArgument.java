/**
 * Copyright [2015] [Open Software Solutions GmbH]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.oss.pdfreporter.uses.org.oss.evaluator.function.impl;

import org.oss.pdfreporter.uses.org.oss.evaluator.function.FunctionArgument;

@SuppressWarnings("rawtypes")
public class VariableArgument implements FunctionArgument {

	private final String variableName;
	private Object value;

	VariableArgument(String variableName) {
		super();
		this.variableName = variableName;
	}

	@Override
	public String getString() {
		return variableName;
	}

	@Override
	public ArgumentType getType() {
		if (value instanceof Integer) {
			return ArgumentType.INTEGER;
		}
		if (value instanceof Double) {
			return ArgumentType.DOUBLE;
		}
		if (value instanceof String) {
			return ArgumentType.STRING;
		}
		if (value instanceof Object) {
			return ArgumentType.OBJECT;
		}
		if (value instanceof Boolean) {
			return ArgumentType.BOOLEAN;
		}
		return ArgumentType.NULL;
	}

	@Override
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return variableName;
	}

}
