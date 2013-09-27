//
//  Image.m
//  JasperReportiOS
//
//  Created by Fr3e on 5/31/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "Image.h"
#import "HpdfDocBox.h"
#import "ImageManager.h"
#import "org/oss/pdfreporter/registry/ApiRegistry.h"
#import "org/oss/pdfreporter/image/ImageFactory.h"
#import <UIKit/UIKit.h>

@implementation Image

- (id)initWithFile:(NSString*)filename manager:(OrgOssPdfreporterImageImageManager*)manager {
    self = [super init];
    if (self) {
        mResourcePath = filename;
        mScale = 1;
        mQuality = 1;
        mManager = manager;
    }
    return self;
}

- (id)initWithRecompressedFile:(NSString*)filename quality:(float)quality scale:(float)scale manager:(OrgOssPdfreporterImageImageManager*)manager {
    self = [super init];
    if (self) {
        mResourcePath = filename;
        mScale = quality;
        mQuality = scale;
        mManager = manager;
    }
    return self;
}

-(id<OrgOssPdfreporterImageIImageManager>)getImageManager
{
    return mManager;
}

- (void)loadImage {
    HpdfDocBox *docBox = [HpdfDocBox GetDocBoxFromSession:[[OrgOssPdfreporterRegistryApiRegistry getImageFactory] getSession]];
    if(mScale!=1 || mQuality != 1)
    {
        UIImage *image = [[UIImage alloc] initWithContentsOfFile:mResourcePath];
        if(mScale != 1){
            image = [[UIImage alloc] initWithCGImage:[image CGImage] scale:mScale orientation:UIImageOrientationUp];
        }
        NSData *data = UIImageJPEGRepresentation(image, mQuality);
        const unsigned char *cData = [data bytes];
        unsigned int size = [data length] / sizeof(unsigned char);
        mHpdf_Image = HPDF_LoadJpegImageFromMem([docBox getHpdfDoc], cData , size);
    }
    else
    {
        NSString *ext = [mResourcePath pathExtension];
        const char *cPath = [mResourcePath UTF8String];
        
        if([ext compare:@"png"] == NSOrderedSame)
        {
            mHpdf_Image = HPDF_LoadPngImageFromFile([docBox getHpdfDoc], cPath);
        }
        else if ( [ext compare:@"jpg"] == NSOrderedSame || [ext compare:@"jpeg"] == NSOrderedSame )
        {
            mHpdf_Image = HPDF_LoadJpegImageFromFile([docBox getHpdfDoc], cPath);
        }
        else if ( [ext compare:@"gif"] == NSOrderedSame )
        {
            UIImage *image = [[UIImage alloc] initWithContentsOfFile:mResourcePath];
            NSData *data = UIImagePNGRepresentation(image);
            const unsigned char *cData = [data bytes];
            unsigned int size = [data length] / sizeof(unsigned char);
            mHpdf_Image = HPDF_LoadPngImageFromMem([docBox getHpdfDoc], cData , size);
        }
        else
        {
            @throw [NSException exceptionWithName:@"Image" reason:@"Unsupported extension" userInfo:nil];
        }
    }
    
    if(mHpdf_Image == NULL)
        @throw [NSException exceptionWithName:@"Image" reason:@"mHpdf_Image is null" userInfo:nil];
    
}

-(void)checkImage {
    if(mHpdf_Image == nil)
    {
        [self loadImage];
    }
}

- (int)getWidth {
    [self checkImage];
    return HPDF_Image_GetWidth(mHpdf_Image);
}

- (int)getHeight {
    [self checkImage];
    return HPDF_Image_GetHeight(mHpdf_Image);
}

- (NSString *)getResourcePath {
    return mResourcePath;
}

- (id)getPeer {
    [self checkImage];
    return [[ImageBox alloc] initWithHpdfImage:mHpdf_Image];;
}


@end
