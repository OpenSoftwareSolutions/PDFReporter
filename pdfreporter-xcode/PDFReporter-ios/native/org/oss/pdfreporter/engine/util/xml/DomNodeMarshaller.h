//
//  DomNodeMarshaller.h
//  PDFReporter-ios
//
//  Created by Martin Krasnocka on 29/12/14.
//  Copyright (c) 2014 Open Software Solutions GmbH. All rights reserved.
//

#import <Foundation/Foundation.h>
@protocol OrgOssPdfreporterUsesOrgW3cDomNode;
@protocol OrgOssPdfreporterUsesOrgW3cDomNodeList;

@interface DomNodeMarshaller : NSObject

+ (NSData *)dataFromDomNode:(id<OrgOssPdfreporterUsesOrgW3cDomNode>)domNode;
+ (id<OrgOssPdfreporterUsesOrgW3cDomNodeList>)nodeListFromXPathResultList:(NSArray *)xPathResultList;

@end
