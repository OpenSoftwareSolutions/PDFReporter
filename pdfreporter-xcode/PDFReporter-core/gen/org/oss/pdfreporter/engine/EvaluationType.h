//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/EvaluationType.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineEvaluationType_H_
#define _OrgOssPdfreporterEngineEvaluationType_H_

#import "JreEmulation.h"
#include "java/lang/Enum.h"

typedef enum {
  OrgOssPdfreporterEngineEvaluationType_DEFAULT = 0,
  OrgOssPdfreporterEngineEvaluationType_OLD = 1,
  OrgOssPdfreporterEngineEvaluationType_ESTIMATED = 2,
} OrgOssPdfreporterEngineEvaluationType;

@interface OrgOssPdfreporterEngineEvaluationTypeEnum : JavaLangEnum < NSCopying > {
 @public
  char type_;
}
@property (nonatomic, assign) char type;

+ (OrgOssPdfreporterEngineEvaluationTypeEnum *)DEFAULT;
+ (OrgOssPdfreporterEngineEvaluationTypeEnum *)OLD;
+ (OrgOssPdfreporterEngineEvaluationTypeEnum *)ESTIMATED;
+ (IOSObjectArray *)values;
+ (OrgOssPdfreporterEngineEvaluationTypeEnum *)valueOfWithNSString:(NSString *)name;
- (id)copyWithZone:(NSZone *)zone;
- (id)initWithChar:(char)type
      withNSString:(NSString *)name
           withInt:(int)ordinal;
- (char)getType;
@end

#endif // _OrgOssPdfreporterEngineEvaluationType_H_