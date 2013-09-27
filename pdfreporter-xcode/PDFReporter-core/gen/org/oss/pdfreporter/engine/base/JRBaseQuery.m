//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/base/JRBaseQuery.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/lang/CloneNotSupportedException.h"
#include "org/oss/pdfreporter/engine/JRQuery.h"
#include "org/oss/pdfreporter/engine/JRQueryChunk.h"
#include "org/oss/pdfreporter/engine/JRRuntimeException.h"
#include "org/oss/pdfreporter/engine/base/JRBaseObjectFactory.h"
#include "org/oss/pdfreporter/engine/base/JRBaseQuery.h"
#include "org/oss/pdfreporter/engine/base/JRBaseQueryChunk.h"
#include "org/oss/pdfreporter/engine/query/QueryExecuterFactory.h"
#include "org/oss/pdfreporter/engine/util/JRCloneUtils.h"
#include "org/oss/pdfreporter/engine/util/JRQueryParser.h"

@implementation OrgOssPdfreporterEngineBaseJRBaseQuery

@synthesize chunks = chunks_;
@synthesize language = language_;

- (id)init {
  if ((self = [super init])) {
    language_ = [OrgOssPdfreporterEngineQueryQueryExecuterFactory QUERY_LANGUAGE_SQL];
  }
  return self;
}

- (id)initWithOrgOssPdfreporterEngineJRQuery:(id<OrgOssPdfreporterEngineJRQuery>)query
withOrgOssPdfreporterEngineBaseJRBaseObjectFactory:(OrgOssPdfreporterEngineBaseJRBaseObjectFactory *)factory {
  if ((self = [super init])) {
    language_ = [OrgOssPdfreporterEngineQueryQueryExecuterFactory QUERY_LANGUAGE_SQL];
    [((OrgOssPdfreporterEngineBaseJRBaseObjectFactory *) nil_chk(factory)) putWithId:query withId:self];
    IOSObjectArray *jrChunks = [((id<OrgOssPdfreporterEngineJRQuery>) nil_chk(query)) getChunks];
    if (jrChunks != nil && (int) [((IOSObjectArray *) nil_chk(jrChunks)) count] > 0) {
      chunks_ = [IOSObjectArray arrayWithLength:(int) [((IOSObjectArray *) nil_chk(jrChunks)) count] type:[IOSClass classWithProtocol:@protocol(OrgOssPdfreporterEngineJRQueryChunk)]];
      for (int i = 0; i < (int) [((IOSObjectArray *) nil_chk(chunks_)) count]; i++) {
        (void) [((IOSObjectArray *) nil_chk(chunks_)) replaceObjectAtIndex:i withObject:[((OrgOssPdfreporterEngineBaseJRBaseObjectFactory *) nil_chk(factory)) getQueryChunkWithOrgOssPdfreporterEngineJRQueryChunk:[((IOSObjectArray *) nil_chk(jrChunks)) objectAtIndex:i]]];
      }
    }
    language_ = [((id<OrgOssPdfreporterEngineJRQuery>) nil_chk(query)) getLanguage];
  }
  return self;
}

- (IOSObjectArray *)getChunks {
  return self.chunks;
}

- (NSString *)getText {
  return [((OrgOssPdfreporterEngineUtilJRQueryParser *) nil_chk([OrgOssPdfreporterEngineUtilJRQueryParser instance])) asTextWithOrgOssPdfreporterEngineJRQueryChunkArray:[self getChunks]];
}

- (NSString *)getLanguage {
  return language_;
}

- (id)clone {
  OrgOssPdfreporterEngineBaseJRBaseQuery *clone = nil;
  @try {
    clone = (OrgOssPdfreporterEngineBaseJRBaseQuery *) [super clone];
  }
  @catch (JavaLangCloneNotSupportedException *e) {
    @throw [[OrgOssPdfreporterEngineJRRuntimeException alloc] initWithJavaLangThrowable:e];
  }
  ((OrgOssPdfreporterEngineBaseJRBaseQuery *) nil_chk(clone)).chunks = [OrgOssPdfreporterEngineUtilJRCloneUtils cloneArrayWithOrgOssPdfreporterEngineJRCloneableArray:chunks_];
  return clone;
}

- (id)copyWithZone:(NSZone *)zone {
  return [self clone];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterEngineBaseJRBaseQuery *typedCopy = (OrgOssPdfreporterEngineBaseJRBaseQuery *) copy;
  typedCopy.chunks = chunks_;
  typedCopy.language = language_;
}

@end