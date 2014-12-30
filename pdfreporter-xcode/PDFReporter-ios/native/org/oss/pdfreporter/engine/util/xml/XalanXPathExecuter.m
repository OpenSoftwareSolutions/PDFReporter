//
//  XalanXPathExecuter.m
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 15/12/14.
//  Copyright (c) 2014 Open Software Solutions GmbH. All rights reserved.
//

#import "XalanXPathExecuter.h"
#import "DomNodeMarshaller.h"
#import "XPathQuery.h"
#import "org/oss/pdfreporter/xml/parsers/impl/NodeImpl.h"
//#import "org/oss/pdfreporter/xml/parsers/impl/ElementImpl.h"

@implementation XalanXPathExecuter
{
    NSString *_selectExpression;
}

- (id<OrgOssPdfreporterUsesOrgW3cDomNodeList>)selectNodeListWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)contextNode withNSString:(NSString *)expression
{
    _selectExpression = expression;
    NSData *xmlData = [DomNodeMarshaller dataFromDomNode:contextNode];
    NSArray *resultList = PerformXMLXPathQuery(xmlData, expression);
    return [DomNodeMarshaller nodeListFromXPathResultList:resultList];
}

- (id)selectObjectWithOrgOssPdfreporterUsesOrgW3cDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)contextNode withNSString:(NSString *)expression
{
    expression = [self buildXPathExpression:expression];
    NSData *xmlData = [DomNodeMarshaller dataFromDomNode:contextNode];
    NSArray *resultList = PerformXMLXPathQuery(xmlData, expression);
    id<OrgOssPdfreporterUsesOrgW3cDomNodeList> nodeList = [DomNodeMarshaller nodeListFromXPathResultList:resultList];
    if ([nodeList getLength] > 0) {
//        NSLog(@"%@", expression);
        id<OrgOssPdfreporterUsesOrgW3cDomNode> node = [nodeList itemWithInt:0];
        return node;
    } else {
        return nil;
    }
}

#pragma mark - private

- (NSString *)buildXPathExpression:(NSString *)expression
{
    if (_selectExpression) {
        return [NSString stringWithFormat:@"%@/%@", _selectExpression, expression];
    } else {
        return expression;
    }
}


@end
