//
//  Parser.h
//  XMLReaderProject
//
//  Created by Daniel Novak on 5/15/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/xml/parsers/AbstractXmlParser.h"
#import "org/oss/pdfreporter/xml/parsers/XMLEntityResolver.h"

@interface LibXmlReaderParser : OrgOssPdfreporterXmlParsersAbstractXmlParser {
@public
    id<OrgOssPdfreporterXmlParsersXMLEntityResolver> entityResolver2;
}



@end
