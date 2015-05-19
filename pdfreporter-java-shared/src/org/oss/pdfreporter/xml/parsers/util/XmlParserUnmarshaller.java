/*******************************************************************************
 * Copyright (c) 2015 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH
 ******************************************************************************/
package org.oss.pdfreporter.xml.parsers.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.oss.pdfreporter.xml.parsers.IDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.IDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.IInputSource;
import org.oss.pdfreporter.xml.parsers.XMLEntityResolver;
import org.oss.pdfreporter.xml.parsers.XMLErrorHandler;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingAttr;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingCDATASection;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingComment;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDOMConfiguration;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDOMImplementation;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDOMStringList;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDocument;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDocumentFragment;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingDocumentType;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingElement;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingEntityReference;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingEntityResolver;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingErrorHandler;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingInputSource;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingNamedNodeMap;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingNode;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingNodeList;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingProcessingInstruction;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingText;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingTypeInfo;
import org.oss.pdfreporter.xml.parsers.wrapper.DelegatingUserDataHandler;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingAttr;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingCDATASection;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingComment;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDOMConfiguration;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDOMImplementation;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDOMStringList;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDocument;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDocumentBuilder;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDocumentBuilderFactory;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDocumentFragment;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingDocumentType;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingElement;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingEntityReference;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingEntityResolver;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingErrorHandler;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingInputSource;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingNamedNodeMap;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingNode;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingNodeList;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingProcessingInstruction;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingText;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingTypeInfo;
import org.oss.pdfreporter.xml.parsers.wrapper.UnmarshallingUserDataHandler;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMStringList;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;


public class XmlParserUnmarshaller {
	public static Node getNode(org.oss.pdfreporter.uses.org.w3c.dom.Node node) {
		if (node==null) return null;
		if (node instanceof DelegatingNode) {
			return ((DelegatingNode)node).getDelegate();			
		}
		return new UnmarshallingNode(node);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.Node getNode(Node node) {
		if (node==null) return null;
		if (node instanceof UnmarshallingNode) {
			return ((UnmarshallingNode)node).getDelegate();			
		}
		return new DelegatingNode(node);
	}
	
	public static NodeList getNodeList(org.oss.pdfreporter.uses.org.w3c.dom.NodeList nodeList) {
		if (nodeList==null) return null;
		if (nodeList instanceof DelegatingNodeList) {
			return ((DelegatingNodeList)nodeList).getDelegate();
		}
		return new UnmarshallingNodeList(nodeList);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.NodeList getNodeList(NodeList nodeList) {
		if (nodeList==null) return null;
		if (nodeList instanceof UnmarshallingNodeList) {
			return ((UnmarshallingNodeList)nodeList).getDelegate();
		}
		return new DelegatingNodeList(nodeList);
	}
	
	public static NamedNodeMap getNamedNodeMap(org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap namedNodeMap) {
		if (namedNodeMap==null) return null;
		if (namedNodeMap instanceof DelegatingNamedNodeMap) {
			return ((DelegatingNamedNodeMap)namedNodeMap).getDelegate();			
		}
		return new UnmarshallingNamedNodeMap(namedNodeMap);
	}

	public static org.oss.pdfreporter.uses.org.w3c.dom.NamedNodeMap getNamedNodeMap(NamedNodeMap namedNodeMap) {
		if (namedNodeMap==null) return null;
		if (namedNodeMap instanceof UnmarshallingNamedNodeMap) {
			return ((UnmarshallingNamedNodeMap)namedNodeMap).getDelegate();			
		}
		return new DelegatingNamedNodeMap(namedNodeMap);
	}
	
	public static UserDataHandler getUserDataHandler(org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler node) {
		if (node==null) return null;
		if (node instanceof DelegatingUserDataHandler) {
			return ((DelegatingUserDataHandler)node).getDelegate();			
		}
		return new UnmarshallingUserDataHandler(node);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.UserDataHandler getUserDataHandler(UserDataHandler node) {
		if (node==null) return null;
		if (node instanceof UnmarshallingUserDataHandler) {
			return ((UnmarshallingUserDataHandler)node).getDelegate();			
		}
		return new DelegatingUserDataHandler(node);
	}
	
	public static DocumentType getDocumentType(org.oss.pdfreporter.uses.org.w3c.dom.DocumentType documentType) {
		if (documentType==null) return null;
		if (documentType instanceof DelegatingDocumentType) {
			return ((DelegatingDocumentType)documentType).getDelegate();			
		}
		return new UnmarshallingDocumentType(documentType);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.DocumentType getDocumentType(DocumentType documentType) {
		if (documentType==null) return null;
		if (documentType instanceof UnmarshallingDocumentType) {
			return ((UnmarshallingDocumentType)documentType).getDelegate();			
		}
		return new DelegatingDocumentType(documentType);
	}
	
	public static Document getDocument(org.oss.pdfreporter.uses.org.w3c.dom.Document document) {
		if (document==null) return null;
		if (document instanceof DelegatingDocument) {
			return ((DelegatingDocument)document).getDelegate();			
		}
		return new UnmarshallingDocument(document);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.Document getDocument(Document document) {
		if (document==null) return null;
		if (document instanceof UnmarshallingDocument) {
			return ((UnmarshallingDocument)document).getDelegate();			
		}
		return new DelegatingDocument(document);
	}
	
	
	public static DOMImplementation getDOMImplementation(org.oss.pdfreporter.uses.org.w3c.dom.DOMImplementation domImplementation) {
		if (domImplementation==null) return null;
		if (domImplementation instanceof DelegatingDOMImplementation) {
			return ((DelegatingDOMImplementation)domImplementation).getDelegate();			
		}
		return new UnmarshallingDOMImplementation(domImplementation);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.DOMImplementation getDOMImplementation(DOMImplementation domImplementation) {
		if (domImplementation==null) return null;
		if (domImplementation instanceof UnmarshallingDOMImplementation) {
			return ((UnmarshallingDOMImplementation)domImplementation).getDelegate();			
		}
		return new DelegatingDOMImplementation(domImplementation);
	}
	
	public static Element getElement(org.oss.pdfreporter.uses.org.w3c.dom.Element element) {
		if (element==null) return null;
		if (element instanceof DelegatingElement) {
			return ((DelegatingElement)element).getDelegate();			
		}
		return new UnmarshallingElement(element);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.Element getElement(Element element) {
		if (element==null) return null;
		if (element instanceof UnmarshallingElement) {
			return ((UnmarshallingElement)element).getDelegate();			
		}
		return new DelegatingElement(element);
	}

	public static DocumentFragment getDocumentFragment(org.oss.pdfreporter.uses.org.w3c.dom.DocumentFragment documentFragment) {
		if (documentFragment==null) return null;
		if (documentFragment instanceof DelegatingDocumentFragment) {
			return ((DelegatingDocumentFragment)documentFragment).getDelegate();			
		}
		return new UnmarshallingDocumentFragment(documentFragment);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.DocumentFragment getDocumentFragment(DocumentFragment documentFragment) {
		if (documentFragment==null) return null;
		if (documentFragment instanceof UnmarshallingDocumentFragment) {
			return ((UnmarshallingDocumentFragment)documentFragment).getDelegate();			
		}
		return new DelegatingDocumentFragment(documentFragment);
	}
	
	public static Text getText(org.oss.pdfreporter.uses.org.w3c.dom.Text text) {
		if (text==null) return null;
		if (text instanceof DelegatingText) {
			return ((DelegatingText)text).getDelegate();			
		}
		return new UnmarshallingText(text);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.Text getText(Text text) {
		if (text==null) return null;
		if (text instanceof UnmarshallingText) {
			return ((UnmarshallingText)text).getDelegate();			
		}
		return new DelegatingText(text);
	}
	
	public static Comment getComment(org.oss.pdfreporter.uses.org.w3c.dom.Comment comment) {
		if (comment==null) return null;
		if (comment instanceof DelegatingComment) {
			return ((DelegatingComment)comment).getDelegate();			
		}
		return new UnmarshallingComment(comment);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.Comment getComment(Comment comment) {
		if (comment==null) return null;
		if (comment instanceof UnmarshallingComment) {
			return ((UnmarshallingComment)comment).getDelegate();			
		}
		return new DelegatingComment(comment);
	}
	
	public static CDATASection getCDATASection(org.oss.pdfreporter.uses.org.w3c.dom.CDATASection cDATASection) {
		if (cDATASection==null) return null;
		if (cDATASection instanceof DelegatingCDATASection) {
			return ((DelegatingCDATASection)cDATASection).getDelegate();			
		}
		return new UnmarshallingCDATASection(cDATASection);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.CDATASection getCDATASection(CDATASection cDATASection) {
		if (cDATASection==null) return null;
		if (cDATASection instanceof UnmarshallingCDATASection) {
			return ((UnmarshallingCDATASection)cDATASection).getDelegate();			
		}
		return new DelegatingCDATASection(cDATASection);
	}
	
	public static ProcessingInstruction getProcessingInstruction(org.oss.pdfreporter.uses.org.w3c.dom.ProcessingInstruction processingInstruction) {
		if (processingInstruction==null) return null;
		if (processingInstruction instanceof DelegatingProcessingInstruction) {
			return ((DelegatingProcessingInstruction)processingInstruction).getDelegate();			
		}
		return new UnmarshallingProcessingInstruction(processingInstruction);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.ProcessingInstruction getProcessingInstruction(ProcessingInstruction processingInstruction) {
		if (processingInstruction==null) return null;
		if (processingInstruction instanceof UnmarshallingProcessingInstruction) {
			return ((UnmarshallingProcessingInstruction)processingInstruction).getDelegate();			
		}
		return new DelegatingProcessingInstruction(processingInstruction);
	}
	
	public static Attr getAttr(org.oss.pdfreporter.uses.org.w3c.dom.Attr attr) {
		if (attr==null) return null;
		if (attr instanceof DelegatingAttr) {
			return ((DelegatingAttr)attr).getDelegate();			
		}
		return new UnmarshallingAttr(attr);
	}

	public static org.oss.pdfreporter.uses.org.w3c.dom.Attr getAttr(Attr attr) {
		if (attr==null) return null;
		if (attr instanceof UnmarshallingAttr) {
			return ((UnmarshallingAttr)attr).getDelegate();			
		}
		return new DelegatingAttr(attr);
	}
	
	public static EntityReference getEntityReference(org.oss.pdfreporter.uses.org.w3c.dom.EntityReference entityReference) {
		if (entityReference==null) return null;
		if (entityReference instanceof DelegatingEntityReference) {
			return ((DelegatingEntityReference)entityReference).getDelegate();			
		}
		return new UnmarshallingEntityReference(entityReference);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.EntityReference getEntityReference(EntityReference entityReference) {
		if (entityReference==null) return null;
		if (entityReference instanceof UnmarshallingEntityReference) {
			return ((UnmarshallingEntityReference)entityReference).getDelegate();			
		}
		return new DelegatingEntityReference(entityReference);
	}
	
	public static DOMConfiguration getDOMConfiguration(org.oss.pdfreporter.uses.org.w3c.dom.DOMConfiguration domConfiguration) {
		if (domConfiguration==null) return null;
		if (domConfiguration instanceof DelegatingDOMConfiguration) {
			return ((DelegatingDOMConfiguration)domConfiguration).getDelegate();			
		}
		return new UnmarshallingDOMConfiguration(domConfiguration);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.DOMConfiguration getDOMConfiguration(DOMConfiguration domConfiguration) {
		if (domConfiguration==null) return null;
		if (domConfiguration instanceof UnmarshallingDOMConfiguration) {
			return ((UnmarshallingDOMConfiguration)domConfiguration).getDelegate();			
		}
		return new DelegatingDOMConfiguration(domConfiguration);
	}
	
	public static TypeInfo getTypeInfo(org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo typeInfo) {
		if (typeInfo==null) return null;
		if (typeInfo instanceof DelegatingTypeInfo) {
			return ((DelegatingTypeInfo)typeInfo).getDelegate();			
		}
		return new UnmarshallingTypeInfo(typeInfo);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.TypeInfo getTypeInfo(TypeInfo typeInfo) {
		if (typeInfo==null) return null;
		if (typeInfo instanceof UnmarshallingTypeInfo) {
			return ((UnmarshallingTypeInfo)typeInfo).getDelegate();			
		}
		return new DelegatingTypeInfo(typeInfo);
	}
	
	public static DOMStringList getDOMStringList(org.oss.pdfreporter.uses.org.w3c.dom.DOMStringList domStringList) {
		if (domStringList==null) return null;
		if (domStringList instanceof DelegatingDOMStringList) {
			return ((DelegatingDOMStringList)domStringList).getDelegate();			
		}
		return new UnmarshallingDOMStringList(domStringList);
	}
	
	public static org.oss.pdfreporter.uses.org.w3c.dom.DOMStringList getDOMStringList(DOMStringList domStringList) {
		if (domStringList==null) return null;
		if (domStringList instanceof UnmarshallingDOMStringList) {
			return ((UnmarshallingDOMStringList)domStringList).getDelegate();			
		}
		return new DelegatingDOMStringList(domStringList);
	}
	
	public static IDocumentBuilderFactory getDocumentBuilderFactory(DocumentBuilderFactory factory) {
		if (factory==null) return null;
		if (factory instanceof UnmarshallingDocumentBuilderFactory) {
			return ((UnmarshallingDocumentBuilderFactory)factory).getDelegate();			
		}
		return new DelegatingDocumentBuilderFactory(factory);
	}
	
	public static DocumentBuilderFactory getDocumentBuilderFactory(IDocumentBuilderFactory factory) {
		if (factory==null) return null;
		if (factory instanceof DelegatingDocumentBuilderFactory) {
			return ((DelegatingDocumentBuilderFactory)factory).getDelegate();			
		}
		return new UnmarshallingDocumentBuilderFactory(factory);
	}
	
	public static DocumentBuilder getDocumentBuilder(IDocumentBuilder builder) {
		if (builder==null) return null;
		if (builder instanceof DelegatingDocumentBuilder) {
			return ((DelegatingDocumentBuilder)builder).getDelegate();			
		}
		return new UnmarshallingDocumentBuilder(builder);
	}
	
	public static IDocumentBuilder getDocumentBuilder(DocumentBuilder builder) {
		if (builder==null) return null;
		if (builder instanceof UnmarshallingDocumentBuilder) {
			return ((UnmarshallingDocumentBuilder)builder).getDelegate();			
		}
		return new DelegatingDocumentBuilder(builder);
	}
	
	public static InputSource getInputSource(IInputSource is) {
		if (is==null) return null;
		if (is instanceof DelegatingInputSource) {
			return ((DelegatingInputSource)is).getDelegate();			
		}
		return new UnmarshallingInputSource(is);
	}
	
	public static IInputSource getInputSource(InputSource is) {
		if (is==null) return null;
		if (is instanceof UnmarshallingInputSource) {
			return ((UnmarshallingInputSource)is).getDelegate();			
		}
		return new DelegatingInputSource(is);
	}
	
	public static EntityResolver getEntityResolver(XMLEntityResolver resolver) {
		if (resolver==null) return null;
		if (resolver instanceof DelegatingEntityResolver) {
			return ((DelegatingEntityResolver)resolver).getDelegate();			
		}
		return new UnmarshallingEntityResolver(resolver);
	}
	public static XMLEntityResolver getEntityResolver(EntityResolver resolver) {
		if (resolver==null) return null;
		if (resolver instanceof UnmarshallingEntityResolver) {
			return ((UnmarshallingEntityResolver)resolver).getDelegate();			
		}
		return new DelegatingEntityResolver(resolver);
	}
	
	public static ErrorHandler getErrorHandler(XMLErrorHandler handler) {
		if (handler==null) return null;
		if (handler instanceof DelegatingErrorHandler) {
			return ((DelegatingErrorHandler)handler).getDelegate();			
		}
		return new UnmarshallingErrorHandler(handler);
	}
	
	public static XMLErrorHandler getErrorHandler(ErrorHandler handler) {
		if (handler==null) return null;
		if (handler instanceof UnmarshallingErrorHandler) {
			return ((UnmarshallingErrorHandler)handler).getDelegate();			
		}
		return new DelegatingErrorHandler(handler);
	}
}
