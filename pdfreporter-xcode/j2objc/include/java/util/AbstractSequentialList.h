//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/util/AbstractSequentialList.java
//

#ifndef _JavaUtilAbstractSequentialList_H_
#define _JavaUtilAbstractSequentialList_H_

@protocol JavaUtilCollection;
@protocol JavaUtilIterator;
@protocol JavaUtilListIterator;

#include "J2ObjC_header.h"
#include "java/util/AbstractList.h"

@interface JavaUtilAbstractSequentialList : JavaUtilAbstractList {
}

- (instancetype)init;

- (void)addWithInt:(jint)location
            withId:(id)object;

- (jboolean)addAllWithInt:(jint)location
   withJavaUtilCollection:(id<JavaUtilCollection>)collection;

- (id)getWithInt:(jint)location;

- (id<JavaUtilIterator>)iterator;

- (id<JavaUtilListIterator>)listIteratorWithInt:(jint)location;

- (id)removeWithInt:(jint)location;

- (id)setWithInt:(jint)location
          withId:(id)object;


@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilAbstractSequentialList)

CF_EXTERN_C_BEGIN
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilAbstractSequentialList)

#endif // _JavaUtilAbstractSequentialList_H_
