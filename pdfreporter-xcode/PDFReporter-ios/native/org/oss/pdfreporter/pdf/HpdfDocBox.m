//
//  HpdfDocBox.m
//  JasperReportiOS
//
//  Created by Fr3e on 6/3/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "HpdfDocBox.h"


void error_handler (HPDF_STATUS error_no, HPDF_STATUS detail_no, void *user_data)
{
    printf ("libharu ERROR: error_no=%04X, detail_no=%d\n", (unsigned int) error_no, (int) detail_no);
}

@implementation HpdfDocBox


- (id)init {
    self = [super init];
    if(self){
        mHpdf_Doc = HPDF_New(error_handler, NULL);
        if (!HPDF_HasDoc(mHpdf_Doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
        HPDF_UseUTFEncodings(mHpdf_Doc);
        HPDF_SetCurrentEncoder(mHpdf_Doc, "UTF-8");
        
    }
    return self;
}

- (HPDF_Doc)getHpdfDoc {
    if (!HPDF_HasDoc(mHpdf_Doc)) @throw [NSException exceptionWithName:@"HPDF_NEW" reason:@"!has doc" userInfo:nil];
    return mHpdf_Doc;
}

- (void)dispose {
    HPDF_Free(mHpdf_Doc);
    mHpdf_Doc = nil;
}

+(HpdfDocBox*)GetDocBoxFromSession:(OrgOssPdfreporterRegistrySession*)session
{
    HpdfDocBox *docBox = (HpdfDocBox*)[session getWithNSString:@"hpdf_doc"];
    if(!docBox) {
        docBox = [[HpdfDocBox alloc] init];
        [session putWithNSString:@"hpdf_doc" withOrgOssPdfreporterRegistryISessionObject:docBox];
    }
    return docBox;
}

@end
