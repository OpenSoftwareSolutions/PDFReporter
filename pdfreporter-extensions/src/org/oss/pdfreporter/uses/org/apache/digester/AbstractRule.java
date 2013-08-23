package org.oss.pdfreporter.uses.org.apache.digester;

import org.oss.pdfreporter.xml.parsers.IAttributes;
import org.xml.sax.Attributes;



public class AbstractRule implements IRule {
    /**
     * The Digester with which this Rule is associated.
     */
    protected IDigester digester = null;
    
    /**
     * The namespace URI for which this Rule is relevant, if any.
     */
    protected String namespaceURI = null;

	protected AbstractRule(IDigester digester) {
		this.digester = digester;
	}

    protected AbstractRule() {
    	this(null);
    }
	
	@Override
	public IDigester getDigester() {
		return digester;
	}

	@Override
	public void setDigester(IDigester digester) {
		this.digester = digester;
	}

	@Override
	public String getNamespaceURI() {
		return namespaceURI;
	}

	@Override
	public void setNamespaceURI(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}

	@Override
	public void begin(String namespace, String name, IAttributes attributes)
			throws Exception {
        begin(attributes);
	}

	@Override
	public void end(String namespace, String name) throws Exception {
		end();
	}

	@Override
	public void body(String namespace, String name, String text)
			throws Exception {
	       body(text);
		
	}
	
	@Override
	public void finish() throws Exception {
	}
	
    /**
     * This method is called when the end of a matching XML element
     * is encountered.
     * 
     * @deprecated Use the {@link #end(String,String) end} method with 
     *   <code>namespace</code> and <code>name</code> parameters instead.
     */
    @Deprecated
    protected void end() throws Exception {

        // The default implementation does nothing

    }
    
    /**
     * This method is called when the beginning of a matching XML element
     * is encountered.
     *
     * @param attributes The attribute list of this element
     * @deprecated Use the {@link #begin(String,String,Attributes) begin}
     *   method with <code>namespace</code> and <code>name</code>
     *   parameters instead.
     */
    @Deprecated
    protected void begin(IAttributes attributes) throws Exception {

        // The default implementation does nothing

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
    protected void body(String text) throws Exception {

        // The default implementation does nothing

    }


}
