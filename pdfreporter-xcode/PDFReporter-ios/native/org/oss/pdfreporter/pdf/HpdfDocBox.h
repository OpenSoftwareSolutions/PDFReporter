//
//  HpdfDocBox.h
//  JasperReportiOS
//
//  Created by Fr3e on 6/3/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/registry/ISessionObject.h"
#import "hpdf.h"
#import "org/oss/pdfreporter/registry/Session.h"

@interface HpdfDocBox : NSObject <OrgOssPdfreporterRegistryISessionObject> {
    HPDF_Doc mHpdf_Doc;
}
- (id)init;
- (HPDF_Doc)getHpdfDoc;
+(HpdfDocBox*)GetDocBoxFromSession:(OrgOssPdfreporterRegistrySession*)session;
@end
