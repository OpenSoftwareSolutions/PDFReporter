//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/util/concurrent/Semaphore.java
//

#ifndef _JavaUtilConcurrentSemaphore_H_
#define _JavaUtilConcurrentSemaphore_H_

@class JavaUtilConcurrentSemaphore_Sync;
@class JavaUtilConcurrentTimeUnitEnum;
@protocol JavaUtilCollection;

#include "J2ObjC_header.h"
#include "java/io/Serializable.h"
#include "java/util/concurrent/locks/AbstractQueuedSynchronizer.h"

#define JavaUtilConcurrentSemaphore_serialVersionUID -3222578661600680210LL

@interface JavaUtilConcurrentSemaphore : NSObject < JavaIoSerializable > {
}

- (instancetype)initWithInt:(jint)permits;

- (instancetype)initWithInt:(jint)permits
                withBoolean:(jboolean)fair;

- (void)acquire;

- (void)acquireUninterruptibly;

- (jboolean)tryAcquire;

- (jboolean)tryAcquireWithLong:(jlong)timeout
withJavaUtilConcurrentTimeUnitEnum:(JavaUtilConcurrentTimeUnitEnum *)unit;

- (void)release__;

- (void)acquireWithInt:(jint)permits;

- (void)acquireUninterruptiblyWithInt:(jint)permits;

- (jboolean)tryAcquireWithInt:(jint)permits;

- (jboolean)tryAcquireWithInt:(jint)permits
                     withLong:(jlong)timeout
withJavaUtilConcurrentTimeUnitEnum:(JavaUtilConcurrentTimeUnitEnum *)unit;

- (void)release__WithInt:(jint)permits;

- (jint)availablePermits;

- (jint)drainPermits;

- (void)reducePermitsWithInt:(jint)reduction;

- (jboolean)isFair;

- (jboolean)hasQueuedThreads;

- (jint)getQueueLength;

- (id<JavaUtilCollection>)getQueuedThreads;

- (NSString *)description;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilConcurrentSemaphore)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentSemaphore, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilConcurrentSemaphore)

#define JavaUtilConcurrentSemaphore_Sync_serialVersionUID 1192457210091910933LL

@interface JavaUtilConcurrentSemaphore_Sync : JavaUtilConcurrentLocksAbstractQueuedSynchronizer {
}

- (instancetype)initWithInt:(jint)permits;

- (jint)getPermits;

- (jint)nonfairTryAcquireSharedWithInt:(jint)acquires;

- (jboolean)tryReleaseSharedWithInt:(jint)releases;

- (void)reducePermitsWithInt:(jint)reductions;

- (jint)drainPermits;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilConcurrentSemaphore_Sync)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentSemaphore_Sync, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilConcurrentSemaphore_Sync)

#define JavaUtilConcurrentSemaphore_NonfairSync_serialVersionUID -2694183684443567898LL

@interface JavaUtilConcurrentSemaphore_NonfairSync : JavaUtilConcurrentSemaphore_Sync {
}

- (instancetype)initWithInt:(jint)permits;

- (jint)tryAcquireSharedWithInt:(jint)acquires;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilConcurrentSemaphore_NonfairSync)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentSemaphore_NonfairSync, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilConcurrentSemaphore_NonfairSync)

#define JavaUtilConcurrentSemaphore_FairSync_serialVersionUID 2014338818796000944LL

@interface JavaUtilConcurrentSemaphore_FairSync : JavaUtilConcurrentSemaphore_Sync {
}

- (instancetype)initWithInt:(jint)permits;

- (jint)tryAcquireSharedWithInt:(jint)acquires;

@end

J2OBJC_EMPTY_STATIC_INIT(JavaUtilConcurrentSemaphore_FairSync)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(JavaUtilConcurrentSemaphore_FairSync, serialVersionUID, jlong)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilConcurrentSemaphore_FairSync)

#endif // _JavaUtilConcurrentSemaphore_H_
