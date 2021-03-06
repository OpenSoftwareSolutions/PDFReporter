//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/nio/channels/spi/SelectorProvider.java
//

#ifndef _JavaNioChannelsSpiSelectorProvider_H_
#define _JavaNioChannelsSpiSelectorProvider_H_

@class JavaNioChannelsDatagramChannel;
@class JavaNioChannelsPipe;
@class JavaNioChannelsServerSocketChannel;
@class JavaNioChannelsSocketChannel;
@class JavaNioChannelsSpiAbstractSelector;
@protocol JavaNioChannelsChannel;

#include "J2ObjC_header.h"

@interface JavaNioChannelsSpiSelectorProvider : NSObject {
}

- (instancetype)init;

+ (JavaNioChannelsSpiSelectorProvider *)provider;

- (JavaNioChannelsDatagramChannel *)openDatagramChannel;

- (JavaNioChannelsPipe *)openPipe;

- (JavaNioChannelsSpiAbstractSelector *)openSelector;

- (JavaNioChannelsServerSocketChannel *)openServerSocketChannel;

- (JavaNioChannelsSocketChannel *)openSocketChannel;

- (id<JavaNioChannelsChannel>)inheritedChannel;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaNioChannelsSpiSelectorProvider)

CF_EXTERN_C_BEGIN

FOUNDATION_EXPORT JavaNioChannelsSpiSelectorProvider *JavaNioChannelsSpiSelectorProvider_provider();

FOUNDATION_EXPORT JavaNioChannelsSpiSelectorProvider *JavaNioChannelsSpiSelectorProvider_provider__;
J2OBJC_STATIC_FIELD_GETTER(JavaNioChannelsSpiSelectorProvider, provider__, JavaNioChannelsSpiSelectorProvider *)
J2OBJC_STATIC_FIELD_SETTER(JavaNioChannelsSpiSelectorProvider, provider__, JavaNioChannelsSpiSelectorProvider *)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaNioChannelsSpiSelectorProvider)

#endif // _JavaNioChannelsSpiSelectorProvider_H_
