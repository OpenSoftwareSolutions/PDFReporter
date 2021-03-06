//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/util/concurrent/PriorityBlockingQueue.java
//

#ifndef _JavaUtilConcurrentPriorityBlockingQueue_H_
#define _JavaUtilConcurrentPriorityBlockingQueue_H_

@class IOSObjectArray;
@class JavaIoObjectInputStream;
@class JavaIoObjectOutputStream;
@class JavaUtilConcurrentLocksReentrantLock;
@class JavaUtilConcurrentTimeUnitEnum;
@class JavaUtilPriorityQueue;
@class SunMiscUnsafe;
@protocol JavaUtilCollection;
@protocol JavaUtilComparator;
@protocol JavaUtilConcurrentLocksCondition;

#include "J2ObjC_header.h"
#include "java/io/Serializable.h"
#include "java/util/AbstractQueue.h"
#include "java/util/Iterator.h"
#include "java/util/concurrent/BlockingQueue.h"

#define JavaUtilConcurrentPriorityBlockingQueue_DEFAULT_INITIAL_CAPACITY 11
#define JavaUtilConcurrentPriorityBlockingQueue_MAX_ARRAY_SIZE 2147483639
#define JavaUtilConcurrentPriorityBlockingQueue_serialVersionUID 5595510919245408276LL

@interface JavaUtilConcurrentPriorityBlockingQueue : JavaUtilAbstractQueue < JavaUtilConcurrentBlockingQueue, JavaIoSerializable > {
}

- (instancetype)init;

- (instancetype)initWithInt:(jint)initialCapacity;

- (instancetype)initWithInt:(jint)initialCapacity
     withJavaUtilComparator:(id<JavaUtilComparator>)comparator;

- (instancetype)initWithJavaUtilCollection:(id<JavaUtilCollection>)c;

- (jboolean)addWithId:(id)e;

- (jboolean)offerWithId:(id)e;

- (void)putWithId:(id)e;

- (jboolean)offerWithId:(id)e
               withLong:(jlong)timeout
withJavaUtilConcurrentTimeUnitEnum:(JavaUtilConcurrentTimeUnitEnum *)unit;

- (id)poll;

- (id)take;

- (id)pollWithLong:(jlong)timeout
withJavaUtilConcurrentTimeUnitEnum:(JavaUtilConcurrentTimeUnitEnum *)unit;

- (id)peek;

- (id<JavaUtilComparator>)comparator;

- (jint)size;

- (jint)remainingCapacity;

- (jboolean)removeWithId:(id)o;

- (void)removeEQWithId:(id)o;

- (jboolean)containsWithId:(id)o;

- (IOSObjectArray *)toArray;

- (NSString *)description;

- (jint)drainToWithJavaUtilCollection:(id<JavaUtilCollection>)c;

- (jint)drainToWithJavaUtilCollection:(id<JavaUtilCollection>)c
                              withInt:(jint)maxElements;

- (void)clear;

- (IOSObjectArray *)toArrayWithNSObjectArray:(IOSObjectArray *)a;

- (id<JavaUtilIterator>)iterator;


@end

FOUNDATION_EXPORT BOOL JavaUtilConcurrentPriorityBlockingQueue_initialized;
J2OBJC_STATIC_INIT(JavaUtilConcurrentPriorityBlockingQueue)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentPriorityBlockingQueue, serialVersionUID, jlong)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentPriorityBlockingQueue, DEFAULT_INITIAL_CAPACITY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentPriorityBlockingQueue, MAX_ARRAY_SIZE, jint)

FOUNDATION_EXPORT SunMiscUnsafe *JavaUtilConcurrentPriorityBlockingQueue_UNSAFE_;
J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentPriorityBlockingQueue, UNSAFE_, SunMiscUnsafe *)

FOUNDATION_EXPORT jlong JavaUtilConcurrentPriorityBlockingQueue_allocationSpinLockOffset_;
J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentPriorityBlockingQueue, allocationSpinLockOffset_, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilConcurrentPriorityBlockingQueue)

@interface JavaUtilConcurrentPriorityBlockingQueue_Itr : NSObject < JavaUtilIterator > {
 @public
  IOSObjectArray *array_;
  jint cursor_;
  jint lastRet_;
}

- (instancetype)initWithJavaUtilConcurrentPriorityBlockingQueue:(JavaUtilConcurrentPriorityBlockingQueue *)outer$
                                              withNSObjectArray:(IOSObjectArray *)array;

- (jboolean)hasNext;

- (id)next;

- (void)remove;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilConcurrentPriorityBlockingQueue_Itr)

J2OBJC_FIELD_SETTER(JavaUtilConcurrentPriorityBlockingQueue_Itr, array_, IOSObjectArray *)

CF_EXTERN_C_BEGIN
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilConcurrentPriorityBlockingQueue_Itr)

#endif // _JavaUtilConcurrentPriorityBlockingQueue_H_
