//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/net/PlainSocketImpl.java
//

#ifndef _JavaNetPlainSocketImpl_H_
#define _JavaNetPlainSocketImpl_H_

@class DalvikSystemCloseGuard;
@class IOSByteArray;
@class JavaIoFileDescriptor;
@class JavaNetInetAddress;
@class JavaNetProxy;
@class JavaNetSocketAddress;
@class JavaNetSocks4Message;

#include "J2ObjC_header.h"
#include "java/io/InputStream.h"
#include "java/io/OutputStream.h"
#include "java/net/SocketImpl.h"

@interface JavaNetPlainSocketImpl : JavaNetSocketImpl {
}

- (instancetype)initWithJavaIoFileDescriptor:(JavaIoFileDescriptor *)fd;

- (instancetype)initWithJavaNetProxy:(JavaNetProxy *)proxy;

- (instancetype)init;

- (instancetype)initWithJavaIoFileDescriptor:(JavaIoFileDescriptor *)fd
                                     withInt:(jint)localport
                      withJavaNetInetAddress:(JavaNetInetAddress *)addr
                                     withInt:(jint)port;

- (void)acceptWithJavaNetSocketImpl:(JavaNetSocketImpl *)newImpl;

- (void)initLocalPortWithInt:(jint)localPort OBJC_METHOD_FAMILY_NONE;

- (void)initRemoteAddressAndPortWithJavaNetInetAddress:(JavaNetInetAddress *)remoteAddress
                                               withInt:(jint)remotePort OBJC_METHOD_FAMILY_NONE;

- (jint)available;

- (void)bindWithJavaNetInetAddress:(JavaNetInetAddress *)address
                           withInt:(jint)port;

- (void)close;

- (void)connectWithNSString:(NSString *)aHost
                    withInt:(jint)aPort;

- (void)connectWithJavaNetInetAddress:(JavaNetInetAddress *)anAddr
                              withInt:(jint)aPort;

- (void)createWithBoolean:(jboolean)streaming;

- (void)dealloc;

- (JavaIoInputStream *)getInputStream;

- (id)getOptionWithInt:(jint)option;

- (JavaIoOutputStream *)getOutputStream;

- (void)listenWithInt:(jint)backlog;

- (void)setOptionWithInt:(jint)option
                  withId:(id)value;

- (void)socksAccept;

- (void)shutdownInput;

- (void)shutdownOutput;

- (void)connectWithJavaNetSocketAddress:(JavaNetSocketAddress *)remoteAddr
                                withInt:(jint)timeout;

- (jboolean)supportsUrgentData;

- (void)sendUrgentDataWithInt:(jint)value;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaNetPlainSocketImpl)

CF_EXTERN_C_BEGIN

FOUNDATION_EXPORT JavaNetInetAddress *JavaNetPlainSocketImpl_lastConnectedAddress_;
J2OBJC_STATIC_FIELD_GETTER(JavaNetPlainSocketImpl, lastConnectedAddress_, JavaNetInetAddress *)
J2OBJC_STATIC_FIELD_SETTER(JavaNetPlainSocketImpl, lastConnectedAddress_, JavaNetInetAddress *)

FOUNDATION_EXPORT jint JavaNetPlainSocketImpl_lastConnectedPort_;
J2OBJC_STATIC_FIELD_GETTER(JavaNetPlainSocketImpl, lastConnectedPort_, jint)
J2OBJC_STATIC_FIELD_REF_GETTER(JavaNetPlainSocketImpl, lastConnectedPort_, jint)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaNetPlainSocketImpl)

@interface JavaNetPlainSocketImpl_PlainSocketInputStream : JavaIoInputStream {
}

- (instancetype)initWithJavaNetPlainSocketImpl:(JavaNetPlainSocketImpl *)socketImpl;

- (jint)available;

- (void)close;

- (jint)read;

- (jint)readWithByteArray:(IOSByteArray *)buffer
                  withInt:(jint)byteOffset
                  withInt:(jint)byteCount;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaNetPlainSocketImpl_PlainSocketInputStream)

CF_EXTERN_C_BEGIN
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaNetPlainSocketImpl_PlainSocketInputStream)

@interface JavaNetPlainSocketImpl_PlainSocketOutputStream : JavaIoOutputStream {
}

- (instancetype)initWithJavaNetPlainSocketImpl:(JavaNetPlainSocketImpl *)socketImpl;

- (void)close;

- (void)writeWithInt:(jint)oneByte;

- (void)writeWithByteArray:(IOSByteArray *)buffer
                   withInt:(jint)offset
                   withInt:(jint)byteCount;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaNetPlainSocketImpl_PlainSocketOutputStream)

CF_EXTERN_C_BEGIN
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaNetPlainSocketImpl_PlainSocketOutputStream)

#endif // _JavaNetPlainSocketImpl_H_
