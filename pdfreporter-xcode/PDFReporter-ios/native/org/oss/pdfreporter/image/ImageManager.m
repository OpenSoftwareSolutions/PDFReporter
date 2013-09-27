//
//  ImageManager.m
//  JasperReportiOS
//
//  Created by Fr3e on 5/31/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "ImageManager.h"
#import "Image.h"

@implementation OrgOssPdfreporterImageImageManager

- (id<OrgOssPdfreporterImageIImage>)loadImageInternalWithNSString:(NSString *)filePath
                                                   withFloat:(float)quality
                                                   withFloat:(float)scale_ {  
    id<OrgOssPdfreporterImageIImage> image = nil;
    if(quality>0 && scale_>0) {
        image = [[Image alloc] initWithRecompressedFile:filePath quality:quality scale:scale_ manager:self];
    }
    else {
        image = [[Image alloc] initWithFile:filePath manager:self];
    }
    return image;
}

- (void)disposeInternal {
}
@end
