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
package org.oss.uses.org.oss.jshuntingyard.evaluator;


public class BooleanArgument extends AbstractFunctionElementArgument<Boolean> implements Comparable<BooleanArgument> {

	public final static BooleanArgument TRUE = new BooleanArgument(Boolean.TRUE);
	public final static BooleanArgument FALSE = new BooleanArgument(Boolean.FALSE);

	private final Boolean booleanValue;

	BooleanArgument(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	@Override
	public org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElementArgument.ArgumentType getType() {
		return FunctionElementArgument.ArgumentType.BOOLEAN;
	}

	@Override
	public Boolean getValue() {
		return booleanValue;
	}

	@Override
	public int compareTo(BooleanArgument o) {
		return booleanValue.compareTo(o.getValue());
	}


}
