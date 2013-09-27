//
//  Image.h
//  JasperReportiOS
//
//  Created by Fr3e on 5/31/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "org/oss/pdfreporter/image/IImage.h"
#import "ImageBox.h"
#import "hpdf.h"
@class OrgOssPdfreporterImageImageManager;

@interface Image : NSObject <OrgOssPdfreporterImageIImage>
{
    NSString *mResourcePath;
    HPDF_Image mHpdf_Image;
    float mScale;
    float mQuality;
    OrgOssPdfreporterImageImageManager *mManager;
}

- (id)initWithFile:(NSString*)filename manager:(OrgOssPdfreporterImageImageManager*)manager;
- (id)initWithRecompressedFile:(NSString*)filename quality:(float)quality scale:(float)scale manager:(OrgOssPdfreporterImageImageManager*)manager;
@end
