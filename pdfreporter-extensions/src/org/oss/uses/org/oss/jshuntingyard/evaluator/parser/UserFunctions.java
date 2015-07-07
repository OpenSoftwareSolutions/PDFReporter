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
package org.oss.uses.org.oss.jshuntingyard.evaluator.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.oss.uses.org.oss.jshuntingyard.evaluator.FunctionElement;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.logic.AndOperator;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.logic.NotOperator;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.logic.OrOperator;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive.Add;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive.Divide;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive.Modulo;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive.Multiply;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive.Power;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.primitive.Subtract;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.relational.EqualTo;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.relational.GreaterThan;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.relational.GreaterThanOrEqualTo;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.relational.LessThan;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.relational.LessThanOrEqualTo;
import org.oss.uses.org.oss.jshuntingyard.evaluator.operator.relational.NotEqualTo;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Abs;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Acos;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Asin;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Atan;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Atan2;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Ceil;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Cos;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Exp;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Floor;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.IEEEremainder;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Log;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Max;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Min;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Random;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Rint;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Round;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Sin;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Sqrt;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.Tan;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.ToDegrees;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.math.ToRadians;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.CharAt;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.CompareTo;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.CompareToIgnoreCase;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Concat;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Contains;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.EndsWith;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Equals;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.EqualsIgnoreCase;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.IndexOf;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.LastIndexOf;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Length;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Like;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Matches;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.NumberFormat;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Replace;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.StartsWith;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.Substring;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.ToLowerCase;
import org.oss.uses.org.oss.jshuntingyard.evaluator.userfunction.string.ToUpperCase;

public class UserFunctions {

	public static Collection<FunctionElement> get() {
		Collection<FunctionElement>  functions = new ArrayList<FunctionElement>();
		// Primitiv
		functions.add(new Add());
		functions.add(new Subtract());
		functions.add(new Multiply());
		functions.add(new Divide());
		functions.add(new Power());
		functions.add(new Modulo());
		// Math
		functions.add(new Round());
		functions.add(new Max());
		functions.add(new Min());
		functions.add(new Abs());
		functions.add(new Acos());
		functions.add(new Cos());
		functions.add(new Asin());
		functions.add(new Atan());
		functions.add(new Atan2());
		functions.add(new Ceil());
		functions.add(new Exp());
		functions.add(new Floor());
		functions.add(new IEEEremainder());
		functions.add(new Log());
		functions.add(new Random());
		functions.add(new Rint());
		functions.add(new Sin());
		functions.add(new Sqrt());
		functions.add(new Tan());
		functions.add(new ToDegrees());
		functions.add(new ToRadians());

		// Relational
		functions.add(new EqualTo());
		functions.add(new NotEqualTo());
		functions.add(new LessThan());
		functions.add(new GreaterThan());
		functions.add(new LessThanOrEqualTo());
		functions.add(new GreaterThanOrEqualTo());
		// Logic
		functions.add(new AndOperator());
		functions.add(new OrOperator());
		functions.add(new NotOperator());
		// String
		functions.add(new Length());
		functions.add(new Substring());
		functions.add(new CharAt());
		functions.add(new CompareTo());
		functions.add(new CompareToIgnoreCase());
		functions.add(new Equals());
		functions.add(new EqualsIgnoreCase());
		functions.add(new Concat());
		functions.add(new Contains());
		functions.add(new EndsWith());
		functions.add(new StartsWith());
		functions.add(new Replace());
		functions.add(new ToUpperCase());
		functions.add(new ToLowerCase());
		functions.add(new IndexOf());
		functions.add(new LastIndexOf());
		functions.add(new Like());
		functions.add(new Matches());
		functions.add(new NumberFormat());
		return functions;
	}

}
