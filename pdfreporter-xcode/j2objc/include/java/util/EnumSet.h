//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/util/EnumSet.java
//

#ifndef _JavaUtilEnumSet_H_
#define _JavaUtilEnumSet_H_

@class IOSClass;
@class IOSObjectArray;
@class JavaLangEnum;
@protocol JavaUtilCollection;

#include "J2ObjC_header.h"
#include "java/io/Serializable.h"
#include "java/util/AbstractSet.h"

#define JavaUtilEnumSet_serialVersionUID 1009687484059888093LL

@interface JavaUtilEnumSet : JavaUtilAbstractSet < NSCopying, JavaIoSerializable > {
 @public
  IOSClass *elementClass_;
}

- (instancetype)initWithIOSClass:(IOSClass *)cls;

+ (JavaUtilEnumSet *)noneOfWithIOSClass:(IOSClass *)elementType;

+ (JavaUtilEnumSet *)allOfWithIOSClass:(IOSClass *)elementType;

+ (JavaUtilEnumSet *)copyOfWithJavaUtilEnumSet:(JavaUtilEnumSet *)s OBJC_METHOD_FAMILY_NONE;

+ (JavaUtilEnumSet *)copyOfWithJavaUtilCollection:(id<JavaUtilCollection>)c OBJC_METHOD_FAMILY_NONE;

+ (JavaUtilEnumSet *)complementOfWithJavaUtilEnumSet:(JavaUtilEnumSet *)s;

- (void)complement;

+ (JavaUtilEnumSet *)ofWithJavaLangEnum:(JavaLangEnum *)e;

+ (JavaUtilEnumSet *)ofWithJavaLangEnum:(JavaLangEnum *)e1
                       withJavaLangEnum:(JavaLangEnum *)e2;

+ (JavaUtilEnumSet *)ofWithJavaLangEnum:(JavaLangEnum *)e1
                       withJavaLangEnum:(JavaLangEnum *)e2
                       withJavaLangEnum:(JavaLangEnum *)e3;

+ (JavaUtilEnumSet *)ofWithJavaLangEnum:(JavaLangEnum *)e1
                       withJavaLangEnum:(JavaLangEnum *)e2
                       withJavaLangEnum:(JavaLangEnum *)e3
                       withJavaLangEnum:(JavaLangEnum *)e4;

+ (JavaUtilEnumSet *)ofWithJavaLangEnum:(JavaLangEnum *)e1
                       withJavaLangEnum:(JavaLangEnum *)e2
                       withJavaLangEnum:(JavaLangEnum *)e3
                       withJavaLangEnum:(JavaLangEnum *)e4
                       withJavaLangEnum:(JavaLangEnum *)e5;

+ (JavaUtilEnumSet *)ofWithJavaLangEnum:(JavaLangEnum *)start
                  withJavaLangEnumArray:(IOSObjectArray *)others;

+ (JavaUtilEnumSet *)rangeWithJavaLangEnum:(JavaLangEnum *)start
                          withJavaLangEnum:(JavaLangEnum *)end;

- (void)setRangeWithJavaLangEnum:(JavaLangEnum *)start
                withJavaLangEnum:(JavaLangEnum *)end;

- (JavaUtilEnumSet *)clone;

- (jboolean)isValidTypeWithIOSClass:(IOSClass *)cls;

- (id)writeReplace;


@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilEnumSet)

J2OBJC_FIELD_SETTER(JavaUtilEnumSet, elementClass_, IOSClass *)

CF_EXTERN_C_BEGIN

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_noneOfWithIOSClass_(IOSClass *elementType);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_allOfWithIOSClass_(IOSClass *elementType);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_copyOfWithJavaUtilEnumSet_(JavaUtilEnumSet *s);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_copyOfWithJavaUtilCollection_(id<JavaUtilCollection> c);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_complementOfWithJavaUtilEnumSet_(JavaUtilEnumSet *s);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_ofWithJavaLangEnum_(JavaLangEnum *e);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_ofWithJavaLangEnum_withJavaLangEnum_(JavaLangEnum *e1, JavaLangEnum *e2);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_ofWithJavaLangEnum_withJavaLangEnum_withJavaLangEnum_(JavaLangEnum *e1, JavaLangEnum *e2, JavaLangEnum *e3);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_ofWithJavaLangEnum_withJavaLangEnum_withJavaLangEnum_withJavaLangEnum_(JavaLangEnum *e1, JavaLangEnum *e2, JavaLangEnum *e3, JavaLangEnum *e4);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_ofWithJavaLangEnum_withJavaLangEnum_withJavaLangEnum_withJavaLangEnum_withJavaLangEnum_(JavaLangEnum *e1, JavaLangEnum *e2, JavaLangEnum *e3, JavaLangEnum *e4, JavaLangEnum *e5);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_ofWithJavaLangEnum_withJavaLangEnumArray_(JavaLangEnum *start, IOSObjectArray *others);

FOUNDATION_EXPORT JavaUtilEnumSet *JavaUtilEnumSet_rangeWithJavaLangEnum_withJavaLangEnum_(JavaLangEnum *start, JavaLangEnum *end);

J2OBJC_STATIC_FIELD_GETTER(JavaUtilEnumSet, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilEnumSet)

#define JavaUtilEnumSet_SerializationProxy_serialVersionUID 362491234563181265LL

@interface JavaUtilEnumSet_SerializationProxy : NSObject < JavaIoSerializable > {
}

@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilEnumSet_SerializationProxy)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaUtilEnumSet_SerializationProxy, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilEnumSet_SerializationProxy)

#endif // _JavaUtilEnumSet_H_
