package org.oss.pdfreporter.uses.org.apache.digester.impl;

import org.oss.pdfreporter.uses.org.apache.digester.IDigester;
import org.oss.pdfreporter.uses.org.apache.digester.IRule;
import org.oss.pdfreporter.xml.parsers.IAttributes;


public class Rule implements IRule {
    
    public Rule() {}

    /**
     * The Digester with which this Rule is associated.
     */
    protected IDigester digester = null;


    /**
     * The namespace URI for which this Rule is relevant, if any.
     */
    protected String namespaceURI = null;

    @Override
	public IDigester getDigester() {
        return (this.digester);
    }
    
    /**
     * Set the <code>Digester</code> with which this <code>Rule</code> is associated.
     */
    @Override
	public void setDigester(IDigester digester) {
        this.digester = digester;
    }

    /**
     * Return the namespace URI for which this Rule is relevant, if any.
     */
    @Override
	public String getNamespaceURI() {
        return (this.namespaceURI);
    }


    /**
     * Set the namespace URI for which this Rule is relevant, if any.
     *
     * @param namespaceURI Namespace URI for which this Rule is relevant,
     *  or <code>null</code> to match independent of namespace.
     */
    @Override
	public void setNamespaceURI(String namespaceURI) {
        this.namespaceURI = namespaceURI;
    }


    // --------------------------------------------------------- Public Methods


    /**
     * This method is called when the beginning of a matching XML element
     * is encountered.
     *
     * @param attributes The attribute list of this element
     * @deprecated Use the {@link #begin(String,String,IAttributes) begin}
     *   method with <code>namespace</code> and <code>name</code>
     *   parameters instead.
     */
    @Deprecated
    public void begin(IAttributes attributes) throws Exception {

        // The default implementation does nothing

    }


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
    @Override
	public void begin(String namespace, String name, IAttributes attributes)
        throws Exception {

        begin(attributes);

    }


    /**
     * This method is called when the body of a matching XML element
     * is encountered.  If the element has no body, this method is
     * called with an empty string as the body text.
     *
     * @param text The text of the body of this element
     * @deprecated Use the {@link #body(String,String,String) body} method
     *   with <code>namespace</code> and <code>name</code> parameters
     *   instead.
     */
    @Deprecated
    public void body(String text) throws Exception {

        // The default implementation does nothing

    }


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
    public void body(String namespace, String name, String text)
        throws Exception {

        body(text);

    }


    /**
     * This method is called when the end of a matching XML element
     * is encountered.
     * 
     * @deprecated Use the {@link #end(String,String) end} method with 
     *   <code>namespace</code> and <code>name</code> parameters instead.
     */
    @Deprecated
    public void end() throws Exception {

        // The default implementation does nothing

    }


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
    @Override
	public void end(String namespace, String name)
        throws Exception {

        end();

    }


    /**
     * This method is called after all parsing methods have been
     * called, to allow Rules to remove temporary data.
     */
    @Override
	public void finish() throws Exception {

        // The default implementation does nothing

    }
}
