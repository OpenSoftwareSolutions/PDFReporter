//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/nio/channels/SelectionKey.java
//

#ifndef _JavaNioChannelsSelectionKey_H_
#define _JavaNioChannelsSelectionKey_H_

@class JavaNioChannelsSelectableChannel;
@class JavaNioChannelsSelector;

#include "J2ObjC_header.h"

#define JavaNioChannelsSelectionKey_OP_ACCEPT 16
#define JavaNioChannelsSelectionKey_OP_CONNECT 8
#define JavaNioChannelsSelectionKey_OP_READ 1
#define JavaNioChannelsSelectionKey_OP_WRITE 4

@interface JavaNioChannelsSelectionKey : NSObject {
}

- (instancetype)init;

- (id)attachWithId:(id)anObject;

- (id)attachment;

- (void)cancel;

- (JavaNioChannelsSelectableChannel *)channel;

- (jint)interestOps;

- (JavaNioChannelsSelectionKey *)interestOpsWithInt:(jint)operations;

- (jboolean)isAcceptable;

- (jboolean)isConnectable;

- (jboolean)isReadable;

- (jboolean)isValid;

- (jboolean)isWritable;

- (jint)readyOps;

- (JavaNioChannelsSelector *)selector;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaNioChannelsSelectionKey)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaNioChannelsSelectionKey, OP_ACCEPT, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaNioChannelsSelectionKey, OP_CONNECT, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaNioChannelsSelectionKey, OP_READ, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaNioChannelsSelectionKey, OP_WRITE, jint)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaNioChannelsSelectionKey)

#endif // _JavaNioChannelsSelectionKey_H_
