//
//  DomNodeMarshaller.m
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 29/12/14.
//  Copyright (c) 2014 Open Software Solutions GmbH. All rights reserved.
//

#import "DomNodeMarshaller.h"
#import "org/oss/pdfreporter/uses/org/w3c/dom/Node.h"
#import "org/oss/pdfreporter/uses/org/w3c/dom/NodeList.h"
#import "org/oss/pdfreporter/xml/parsers/impl/NodeImpl.h"
#import "org/oss/pdfreporter/xml/parsers/impl/ElementImpl.h"

@implementation DomNodeMarshaller

+ (NSData *)dataFromDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)domNode
{
    NSMutableString *output = [[NSMutableString alloc] init];
    [self appendNode:domNode output:output];
    return [output dataUsingEncoding:NSUTF8StringEncoding];
}

+ (id<OrgOssPdfreporterUsesOrgW3cDomNodeList>)nodeListFromXPathResultList:(NSArray *)xPathResultList
{
    OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *nodeList = [[OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl alloc] initWithOrgOssPdfreporterUsesOrgW3cDomNode:nil];
    [self appendXPathResultList:xPathResultList toParentNodeList:nodeList];
    return nodeList;
}


#pragma mark - private

+ (void)appendNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)domNode output:(NSMutableString *)output
{
    BOOL isRootNode = [domNode getNodeType] == OrgOssPdfreporterUsesOrgW3cDomNode_DOCUMENT_NODE;
    if (isRootNode) {
        [output appendFormat:@"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"];
    } else {
        [output appendFormat:@"<%@>", [domNode getNodeName]];
    }
    id<OrgOssPdfreporterUsesOrgW3cDomNodeList> childNodes = [domNode getChildNodes];
    for (int i = 0; i < [childNodes getLength]; i++) {
        id<OrgOssPdfreporterUsesOrgW3cDomNode> childNode = [childNodes itemWithInt:i];
        //
        if ([childNode getNodeType] == OrgOssPdfreporterUsesOrgW3cDomNode_ELEMENT_NODE && [childNode hasChildNodes]) {
            [self appendNode:childNode output:output];
        } else if ([childNode getNodeType] == OrgOssPdfreporterUsesOrgW3cDomNode_TEXT_NODE) {
            [output appendFormat:@"%@", [childNode getNodeValue]];
        } else {
            NSAssert(NO, @"nodeType not implemented");
        }
        //
    }
    if (!isRootNode) {
        [output appendFormat:@"</%@>\n", [domNode getNodeName]];
    }
}

+ (void)appendXPathResultList:(NSArray *)xPathResultList toParentNodeList:(OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *)nodeList
{
    [xPathResultList enumerateObjectsUsingBlock:^(NSDictionary *nodeInfo, NSUInteger idx, BOOL *stop) {
        OrgOssPdfreporterXmlParsersImplElementImpl *node = [[OrgOssPdfreporterXmlParsersImplElementImpl alloc] init];
        NSString *nodeName = nodeInfo[@"nodeName"];
        if (nodeName.length > 0) {
            node = [[OrgOssPdfreporterXmlParsersImplElementImpl alloc] initWithOrgOssPdfreporterUsesOrgW3cDomDocument:nil withShort:OrgOssPdfreporterUsesOrgW3cDomNode_ELEMENT_NODE withNSString:nodeName withNSString:nil];
        }
        [nodeList addWithOrgOssPdfreporterUsesOrgW3cDomNode:node];
        
        OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *childNodeList = [node getChildNodes];
        
        NSString *nodeContent = nodeInfo[@"nodeContent"];
        if (nodeContent.length > 0) {
            OrgOssPdfreporterXmlParsersImplElementImpl *textNode = [[OrgOssPdfreporterXmlParsersImplElementImpl alloc] initWithOrgOssPdfreporterUsesOrgW3cDomDocument:nil withShort:OrgOssPdfreporterUsesOrgW3cDomNode_TEXT_NODE withNSString:nil withNSString:nodeContent];
            [childNodeList addWithOrgOssPdfreporterUsesOrgW3cDomNode:textNode];
        }
        
        NSArray *nodeChildArray = nodeInfo[@"nodeChildArray"];
        if (nodeChildArray.count > 0) {
            [self appendXPathResultList:nodeChildArray toParentNodeList:childNodeList];
        }
        
    }];
}

//+ (void)appendXPathResultList:(NSArray *)xPathResultList toParentNodeList:(OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *)nodeList
//{
//    [xPathResultList enumerateObjectsUsingBlock:^(NSDictionary *nodeInfo, NSUInteger idx, BOOL *stop) {
//        OrgOssPdfreporterXmlParsersImplElementImpl *node = [[OrgOssPdfreporterXmlParsersImplElementImpl alloc] init];
//        NSString *nodeName = nodeInfo[@"nodeName"];
//        if (nodeName.length > 0) {
//            [node setName:nodeName];
//            [node setType:OrgOssPdfreporterUsesOrgW3cDomNode_ELEMENT_NODE];
//        }
//        [nodeList addWithOrgOssPdfreporterUsesOrgW3cDomNode:node];
//        
//        OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl *childNodeList = [[OrgOssPdfreporterXmlParsersImplNodeImpl_NodeListImpl alloc] initWithOrgOssPdfreporterUsesOrgW3cDomNode:nil];
//        [node setChildren:childNodeList];
//        
//        NSString *nodeContent = nodeInfo[@"nodeContent"];
//        if (nodeContent.length > 0) {
//            OrgOssPdfreporterXmlParsersImplElementImpl *textNode = [[OrgOssPdfreporterXmlParsersImplElementImpl alloc] init];
//            [textNode setValue:nodeContent];
//            [textNode setType:OrgOssPdfreporterUsesOrgW3cDomNode_TEXT_NODE];
//            [childNodeList addWithOrgOssPdfreporterUsesOrgW3cDomNode:textNode];
//        }
//        
//        NSArray *nodeChildArray = nodeInfo[@"nodeChildArray"];
//        if (nodeChildArray.count > 0) {
//            [self appendXPathResultList:nodeChildArray toParentNodeList:childNodeList];
//        }
//        
//    }];
//}

@end
