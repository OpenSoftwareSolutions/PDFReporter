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
package org.oss.pdfreporter.xml.parsers.impl;

import java.util.ArrayList;
import java.util.List;

import org.oss.pdfreporter.uses.org.w3c.dom.DOMException;
import org.oss.pdfreporter.uses.org.w3c.dom.Document;
import org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap;
import org.oss.pdfreporter.uses.org.w3c.dom.Node;
import org.oss.pdfreporter.uses.org.w3c.dom.NodeList;


public class NodeImpl extends NotImplNode {

	private final Document owner;
	private final short type;
	private final String name, value;
	private final NodeListImpl children;
	private final NamedNodeMap attributes;
	private Node parent = null;
	
	NodeImpl(Document owner, short type, String name, String value) {
		super();
		this.owner = owner;
		this.type = type;
		this.name = name;
		this.value = value;
		this.children = new NodeListImpl(this);
		this.attributes = new NamedNodeMapImpl(owner);
	}

	@Override
	public NamedNodeMap getAttributes() {
		return attributes;
	}
	
	@Override
	public boolean hasAttributes() {
		return attributes.getLength() > 0;
	}
	
	@Override
	public String getNodeName() {
		return name;
	}

	@Override
	public String getNodeValue() throws DOMException {
		return value;
	}

	@Override
	public short getNodeType() {
		return type;
	}

	@Override
	public NodeList getChildNodes() {
		return children;
	}

	@Override
	public boolean hasChildNodes() {
		return children.hasChildNodes();
	}

	@Override
	public Node appendChild(Node newChild) throws DOMException {
		return children.add(newChild);
	}

	
	@Override
	public Node getFirstChild() {
		return children.getFirstChild();
	}

	@Override
	public Node getLastChild() {
		return children.getLastChild();
	}

	@Override
	public Node getPreviousSibling() {
		if (parent instanceof NodeImpl) {
			return ((NodeImpl)parent).children.previous(this);
		}
		return null;
	}

	@Override
	public Node getNextSibling() {
		if (parent instanceof NodeImpl) {
			return ((NodeImpl)parent).children.next(this);
		}
		return null;
	}

	@Override
	public Document getOwnerDocument() {
		return owner;
	}

	@Override
	public Node getParentNode() {
		return parent;
	}

	private static class NodeListImpl implements NodeList {

		private final List<Node> delegate = new ArrayList<Node>();
		private final Node parent;
		
		NodeListImpl(Node parent) {
			this.parent = parent;
		}
		
		@Override
		public Node item(int index) {
			return delegate.get(index);
		}

		@Override
		public int getLength() {
			return delegate.size();
		}
		
		Node add(Node child) {
			delegate.add(child);
			if (child instanceof NodeImpl) {
				((NodeImpl)child).parent = parent;
			}
			return child;
		}
		
		Node getFirstChild() {
			return delegate.isEmpty() ? null : delegate.get(0);
		}
		
		Node getLastChild() {
			return delegate.isEmpty() ? null : delegate.get(delegate.size()-1);
		}
		
		Node previous(Node node) {
			int i = delegate.indexOf(node) - 1;
			return i < 0 ? null : delegate.get(i);
		}
		
		Node next(Node node) {
			int i = delegate.indexOf(node) + 1;
			return i < delegate.size() ? delegate.get(i) : null;
		}
		
		boolean hasChildNodes() {
			return !delegate.isEmpty();
		}
	}
	@Override
	public String toString() {
		return "[" + name + ": " + value + "]";
	}
	
}
