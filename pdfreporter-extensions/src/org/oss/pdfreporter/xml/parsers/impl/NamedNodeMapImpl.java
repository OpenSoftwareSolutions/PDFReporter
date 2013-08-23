package org.oss.pdfreporter.xml.parsers.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;


/**
 * There is no index assigned to a Node so every change of the underlying map may change a nodes index position.<br>
 * As this is a read only implementation once the document is parsed the index positions do not change anymore<br>
 * The index has no correlation with the order attributes are defined in the xml<br>
 * @author donatmuller, 2013, last change 11:02:44 PM
 * 
 */
public class NamedNodeMapImpl implements NamedNodeMap {
	
	private Map<String, Node> map;
	private Document document;
	
	public NamedNodeMapImpl(Document document) {
		map = new HashMap<String, Node>();
		this.document = document;
	}
	
	@Override
	public Node getNamedItem(String name) {
		return map.get(name);
	}

	@Override
	public Node setNamedItem(Node arg) throws DOMException {
		if(arg == null) throw new NullPointerException();
		if(arg.getOwnerDocument() != document) throw new DOMException(DOMException.WRONG_DOCUMENT_ERR, "");
		return map.put(arg.getNodeName(), arg);
	}

	@Override
	public Node removeNamedItem(String name) throws DOMException {
		Node node = map.remove(name);
		if(node == null) throw new DOMException(DOMException.NOT_FOUND_ERR, "");
		return node;
	}

	@Override
	public Node item(int index) {
		Iterator<Node> it = map.values().iterator();
		int count = 0;
		while(it.hasNext()) {
			Node node = it.next();
			if(count++ == index) {
				return node;
			}
		}
		return null;
	}

	@Override
	public int getLength() {
		return map.size();
	}

	@Override
	public Node getNamedItemNS(String namespaceURI, String localName)
			throws DOMException {
		throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "");
	}

	@Override
	public Node setNamedItemNS(Node arg) throws DOMException {
		throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "");
	}

	@Override
	public Node removeNamedItemNS(String namespaceURI, String localName)
			throws DOMException {
		throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "");
	}

}
