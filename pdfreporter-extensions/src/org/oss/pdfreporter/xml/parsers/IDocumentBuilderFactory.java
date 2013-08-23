package org.oss.pdfreporter.xml.parsers;



public interface IDocumentBuilderFactory {

	/**
	 * Creates a new instance of a {@link IDocumentBuilder}
	 * using the currently configured parameters.
	 *
	 * @return A new instance of a DocumentBuilder.
	 *
	 * @throws ParserConfigurationException if a DocumentBuilder
	 *   cannot be created which satisfies the configuration requested.
	 */

	IDocumentBuilder newDocumentBuilder() throws ParserConfigurationException;

	/**
	 * Specifies that the parser produced by this code will
	 * provide support for XML namespaces. By default the value of this is set
	 * to <code>false</code>
	 *
	 * @param awareness true if the parser produced will provide support
	 *                  for XML namespaces; false otherwise.
	 */

	void setNamespaceAware(boolean awareness);

	/**
	 * Specifies that the parser produced by this code will
	 * validate documents as they are parsed. By default the value of this
	 * is set to <code>false</code>.
	 * 
	 * <p>
	 * Note that "the validation" here means
	 * <a href="http://www.w3.org/TR/REC-xml#proc-types">a validating
	 * parser</a> as defined in the XML recommendation.
	 * In other words, it essentially just controls the DTD validation.
	 * (except the legacy two properties defined in JAXP 1.2.)
	 * </p>
	 * 
	 * <p>
	 * To use modern schema languages such as W3C XML Schema or
	 * RELAX NG instead of DTD, you can configure your parser to be
	 * a non-validating parser by leaving the {@link #setValidating(boolean)}
	 * method <code>false</code>, then use the { #setSchema(Schema)}
	 * method to associate a schema to a parser.
	 * </p>
	 * 
	 * @param validating true if the parser produced will validate documents
	 *                   as they are parsed; false otherwise.
	 */

	void setValidating(boolean validating);

	/**
	 * Specifies that the parsers created by this  factory must eliminate
	 * whitespace in element content (sometimes known loosely as
	 * 'ignorable whitespace') when parsing XML documents (see XML Rec
	 * 2.10). Note that only whitespace which is directly contained within
	 * element content that has an element only content model (see XML
	 * Rec 3.2.1) will be eliminated. Due to reliance on the content model
	 * this setting requires the parser to be in validating mode. By default
	 * the value of this is set to <code>false</code>.
	 *
	 * @param whitespace true if the parser created must eliminate whitespace
	 *                   in the element content when parsing XML documents;
	 *                   false otherwise.
	 */

	void setIgnoringElementContentWhitespace(boolean whitespace);

	/**
	 * Specifies that the parser produced by this code will
	 * expand entity reference nodes. By default the value of this is set to
	 * <code>true</code>
	 *
	 * @param expandEntityRef true if the parser produced will expand entity
	 *                        reference nodes; false otherwise.
	 */

	void setExpandEntityReferences(boolean expandEntityRef);

	/**
	 * <p>Specifies that the parser produced by this code will
	 * ignore comments. By default the value of this is set to <code>false
	 * </code>.</p>
	 * 
	 * @param ignoreComments <code>boolean</code> value to ignore comments during processing
	 */

	void setIgnoringComments(boolean ignoreComments);

	/**
	 * Specifies that the parser produced by this code will
	 * convert CDATA nodes to Text nodes and append it to the
	 * adjacent (if any) text node. By default the value of this is set to
	 * <code>false</code>
	 *
	 * @param coalescing  true if the parser produced will convert CDATA nodes
	 *                    to Text nodes and append it to the adjacent (if any)
	 *                    text node; false otherwise.
	 */

	void setCoalescing(boolean coalescing);

	/**
	 * Indicates whether or not the factory is configured to produce
	 * parsers which are namespace aware.
	 *
	 * @return  true if the factory is configured to produce parsers which
	 *          are namespace aware; false otherwise.
	 */

	boolean isNamespaceAware();

	/**
	 * Indicates whether or not the factory is configured to produce
	 * parsers which validate the XML content during parse.
	 *
	 * @return  true if the factory is configured to produce parsers
	 *          which validate the XML content during parse; false otherwise.
	 */

	boolean isValidating();

	/**
	 * Indicates whether or not the factory is configured to produce
	 * parsers which ignore ignorable whitespace in element content.
	 *
	 * @return  true if the factory is configured to produce parsers
	 *          which ignore ignorable whitespace in element content;
	 *          false otherwise.
	 */

	boolean isIgnoringElementContentWhitespace();

	/**
	 * Indicates whether or not the factory is configured to produce
	 * parsers which expand entity reference nodes.
	 *
	 * @return  true if the factory is configured to produce parsers
	 *          which expand entity reference nodes; false otherwise.
	 */

	boolean isExpandEntityReferences();

	/**
	 * Indicates whether or not the factory is configured to produce
	 * parsers which ignores comments.
	 *
	 * @return  true if the factory is configured to produce parsers
	 *          which ignores comments; false otherwise.
	 */

	boolean isIgnoringComments();

	/**
	 * Indicates whether or not the factory is configured to produce
	 * parsers which converts CDATA nodes to Text nodes and appends it to
	 * the adjacent (if any) Text node.
	 *
	 * @return  true if the factory is configured to produce parsers
	 *          which converts CDATA nodes to Text nodes and appends it to
	 *          the adjacent (if any) Text node; false otherwise.
	 */

	boolean isCoalescing();

	/**
	 * Allows the user to set specific attributes on the underlying
	 * implementation.
	 *
	 * @param name The name of the attribute.
	 * @param value The value of the attribute.
	 *
	 * @throws IllegalArgumentException thrown if the underlying
	 *   implementation doesn't recognize the attribute.
	 */
	void setAttribute(String name, Object value)
			throws IllegalArgumentException;

	/**
	 * Allows the user to retrieve specific attributes on the underlying
	 * implementation.
	 *
	 * @param name The name of the attribute.
	 *
	 * @return value The value of the attribute.
	 *
	 * @throws IllegalArgumentException thrown if the underlying
	 *   implementation doesn't recognize the attribute.
	 */
	Object getAttribute(String name) throws IllegalArgumentException;

	/**
	 * <p>Set a feature for this <code>DocumentBuilderFactory</code> and <code>DocumentBuilder</code>s created by this factory.</p>
	 * 
	 * <p>
	 * Feature names are fully qualified {@link java.net.URI}s.
	 * Implementations may define their own features.
	 * A {@link ParserConfigurationException} is thrown if this <code>DocumentBuilderFactory</code> or the
	 * <code>DocumentBuilder</code>s it creates cannot support the feature.
	 * It is possible for a <code>DocumentBuilderFactory</code> to expose a feature value but be unable to change its state.
	 * </p>
	 * 
	 * <p>
	 * All implementations are required to support the {@link javax.xml.XMLConstants#FEATURE_SECURE_PROCESSING} feature.
	 * When the feature is:</p>
	 * <ul>
	 *   <li>
	 *     <code>true</code>: the implementation will limit XML processing to conform to implementation limits.
	 *     Examples include enity expansion limits and XML Schema constructs that would consume large amounts of resources.
	 *     If XML processing is limited for security reasons, it will be reported via a call to the registered
	 *    {@link org.xml.sax.ErrorHandler#fatalError(XMLParseException exception)}.
	 *     See {@link  IDocumentBuilder#setErrorHandler(XMLErrorHandler errorHandler)}.
	 *   </li>
	 *   <li>
	 *     <code>false</code>: the implementation will processing XML according to the XML specifications without
	 *     regard to possible implementation limits.
	 *   </li>
	 * </ul>
	 * 
	 * @param name Feature name.
	 * @param value Is feature state <code>true</code> or <code>false</code>.
	 *  
	 * @throws ParserConfigurationException if this <code>DocumentBuilderFactory</code> or the <code>DocumentBuilder</code>s
	 *   it creates cannot support this feature.
	 * @throws NullPointerException If the <code>name</code> parameter is null.
	 */
	void setFeature(String name, boolean value)
			throws ParserConfigurationException;

	/**
	 * <p>Get the state of the named feature.</p>
	 * 
	 * <p>
	 * Feature names are fully qualified {@link java.net.URI}s.
	 * Implementations may define their own features.
	 * An {@link ParserConfigurationException} is thrown if this <code>DocumentBuilderFactory</code> or the
	 * <code>DocumentBuilder</code>s it creates cannot support the feature.
	 * It is possible for an <code>DocumentBuilderFactory</code> to expose a feature value but be unable to change its state.
	 * </p>
	 * 
	 * @param name Feature name.
	 * 
	 * @return State of the named feature.
	 * 
	 * @throws ParserConfigurationException if this <code>DocumentBuilderFactory</code>
	 *   or the <code>DocumentBuilder</code>s it creates cannot support this feature.
	 */
	boolean getFeature(String name) throws ParserConfigurationException;


	/**
	 * <p>Set state of XInclude processing.</p>
	 * 
	 * <p>If XInclude markup is found in the document instance, should it be
	 * processed as specified in <a href="http://www.w3.org/TR/xinclude/">
	 * XML Inclusions (XInclude) Version 1.0</a>.</p>
	 * 
	 * <p>XInclude processing defaults to <code>false</code>.</p>
	 * 
	 * @param state Set XInclude processing to <code>true</code> or
	 *   <code>false</code>
	 *
	 * @throws UnsupportedOperationException When implementation does not
	 *   override this method.
	 *
	 * @since 1.5
	 */
	void setXIncludeAware(boolean state);

	/**
	 * <p>Get state of XInclude processing.</p>
	 * 
	 * @return current state of XInclude processing
	 *
	 * @throws UnsupportedOperationException When implementation does not
	 *   override this method.
	 *
	 * @since 1.5
	 */
	boolean isXIncludeAware();

}