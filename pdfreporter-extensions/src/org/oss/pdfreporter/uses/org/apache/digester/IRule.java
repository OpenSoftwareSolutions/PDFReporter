/*******************************************************************************
 * Copyright 2013 Open Software Solutions GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.oss.pdfreporter.uses.org.apache.digester;

import org.oss.pdfreporter.xml.parsers.IAttributes;



public interface IRule {

	/**
	 * Return the Digester with which this Rule is associated.
	 */
	IDigester getDigester();

	/**
	 * Set the <code>IDigester</code> with which this <code>Rule</code> is associated.
	 */
	void setDigester(IDigester digester);

	/**
	 * Return the namespace URI for which this Rule is relevant, if any.
	 */
	String getNamespaceURI();

	/**
	 * Set the namespace URI for which this Rule is relevant, if any.
	 *
	 * @param namespaceURI Namespace URI for which this Rule is relevant,
	 *  or <code>null</code> to match independent of namespace.
	 */
	void setNamespaceURI(String namespaceURI);

	/**
	 * This method is called when the beginning of a matching XML element
	 * is encountered. The default implementation delegates to the deprecated
	 * method {@link #begin(IAttributes) begin} without the 
	 * <code>namespace</code> and <code>name</code> parameters, to retain 
	 * backwards compatibility.
	 *
	 * @param namespace the namespace URI of the matching element, or an 
	 *   empty string if the parser is not namespace aware or the element has
	 *   no namespace
	 * @param name the local name if the parser is namespace aware, or just 
	 *   the element name otherwise
	 * @param attributes The attribute list of this element
	 * @since Digester 1.4
	 */
	void begin(String namespace, String name, IAttributes attributes)
			throws Exception;

	/**
	 * This method is called when the end of a matching XML element
	 * is encountered. The default implementation delegates to the deprecated
	 * method {@link #end end} without the 
	 * <code>namespace</code> and <code>name</code> parameters, to retain 
	 * backwards compatibility.
	 *
	 * @param namespace the namespace URI of the matching element, or an 
	 *   empty string if the parser is not namespace aware or the element has
	 *   no namespace
	 * @param name the local name if the parser is namespace aware, or just 
	 *   the element name otherwise
	 * @since Digester 1.4
	 */
	void end(String namespace, String name) throws Exception;

	
    /**
     * This method is called when the body of a matching XML element is 
     * encountered.  If the element has no body, this method is 
     * called with an empty string as the body text.
     * <p>
     * The default implementation delegates to the deprecated method 
     * {@link #body(String) body} without the <code>namespace</code> and
     * <code>name</code> parameters, to retain backwards compatibility.
     *
     * @param namespace the namespace URI of the matching element, or an 
     *   empty string if the parser is not namespace aware or the element has
     *   no namespace
     * @param name the local name if the parser is namespace aware, or just 
     *   the element name otherwise
     * @param text The text of the body of this element
     * @since Digester 1.4
     */
    public void body(String namespace, String name, String text) throws Exception;
	
	/**
	 * This method is called after all parsing methods have been
	 * called, to allow Rules to remove temporary data.
	 */
	void finish() throws Exception;

}
