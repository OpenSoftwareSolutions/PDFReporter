//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/krasnocka/Downloads/j2objc-master/junit/build_result/java/org/junit/Test.java
//

#ifndef _OrgJunitTest_H_
#define _OrgJunitTest_H_

@class IOSClass;

#include "J2ObjC_header.h"
#include "java/lang/Throwable.h"
#include "java/lang/annotation/Annotation.h"

@protocol OrgJunitTest < JavaLangAnnotationAnnotation >

@property (readonly) IOSClass *expected;
@property (readonly) jlong timeout;

@end

@interface OrgJunitTest : NSObject < OrgJunitTest > {
 @private
  IOSClass *expected_;
  jlong timeout_;
}

- (instancetype)initWithExpected:(IOSClass *)expected__ withTimeout:(jlong)timeout__;

+ (IOSClass *)expectedDefault;
+ (jlong)timeoutDefault;

@end

J2OBJC_EMPTY_STATIC_INIT(OrgJunitTest)

J2OBJC_TYPE_LITERAL_HEADER(OrgJunitTest)

#define OrgJunitTest_None_serialVersionUID 1LL

@interface OrgJunitTest_None : JavaLangThrowable {
}

@end

J2OBJC_EMPTY_STATIC_INIT(OrgJunitTest_None)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(OrgJunitTest_None, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(OrgJunitTest_None)

#endif // _OrgJunitTest_H_
