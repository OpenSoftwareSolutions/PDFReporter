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

import org.oss.pdfreporter.uses.net.sourceforge.jeval.function.FunctionException;

/**
 * This interface can be implement with a custom resolver and set onto the
 * Evaluator class. It will then be used to resolve variables when they are
 * replaced in an expression as it gets evaluated. Varaibles resolved by the
 * resolved will override any varibles that exist in the variable map of an
 * Evaluator instance.
 */
public interface VariableResolver {

    /**
     * Returns a variable value for the specified variable name.
     *
     * @param variableName
     *            The name of the variable to return the variable value for.
     *
     * @return A variable value for the specified variable name. If the variable
     *         name can not be resolved, then null should be returned.
     *         
     * @throws Can throw a FunctionException if needed.
     */
    public String resolveVariable(String variableName) throws FunctionException;
}
