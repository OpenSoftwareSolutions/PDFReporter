//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: /Users/krasnocka/Downloads/j2objc-master/guava/sources/com/google/common/collect/NullsFirstOrdering.java
//

#include "J2ObjC_header.h"

#if !ComGoogleCommonCollectNullsFirstOrdering_RESTRICT
#define ComGoogleCommonCollectNullsFirstOrdering_INCLUDE_ALL 1
#endif
#undef ComGoogleCommonCollectNullsFirstOrdering_RESTRICT
#if !defined (_ComGoogleCommonCollectNullsFirstOrdering_) && (ComGoogleCommonCollectNullsFirstOrdering_INCLUDE_ALL || ComGoogleCommonCollectNullsFirstOrdering_INCLUDE)
#define _ComGoogleCommonCollectNullsFirstOrdering_

#define ComGoogleCommonCollectOrdering_RESTRICT 1
#define ComGoogleCommonCollectOrdering_INCLUDE 1
#include "com/google/common/collect/Ordering.h"

#define JavaIoSerializable_RESTRICT 1
#define JavaIoSerializable_INCLUDE 1
#include "java/io/Serializable.h"


#define ComGoogleCommonCollectNullsFirstOrdering_serialVersionUID 0LL

@interface ComGoogleCommonCollectNullsFirstOrdering : ComGoogleCommonCollectOrdering < JavaIoSerializable > {
 @public
  ComGoogleCommonCollectOrdering *ordering_;
}

- (instancetype)initWithComGoogleCommonCollectOrdering:(ComGoogleCommonCollectOrdering *)ordering;

- (jint)compareWithId:(id)left
               withId:(id)right;

- (ComGoogleCommonCollectOrdering *)reverse;

- (ComGoogleCommonCollectOrdering *)nullsFirst;

- (ComGoogleCommonCollectOrdering *)nullsLast;

- (jboolean)isEqual:(id)object;

- (NSUInteger)hash;

- (NSString *)description;

@end

J2OBJC_EMPTY_STATIC_INIT(ComGoogleCommonCollectNullsFirstOrdering)

J2OBJC_FIELD_SETTER(ComGoogleCommonCollectNullsFirstOrdering, ordering_, ComGoogleCommonCollectOrdering *)

CF_EXTERN_C_BEGIN

J2OBJC_STATIC_FIELD_GETTER(ComGoogleCommonCollectNullsFirstOrdering, serialVersionUID, jlong)
CF_EXTERN_C_END
#endif

J2OBJC_TYPE_LITERAL_HEADER(ComGoogleCommonCollectNullsFirstOrdering)
