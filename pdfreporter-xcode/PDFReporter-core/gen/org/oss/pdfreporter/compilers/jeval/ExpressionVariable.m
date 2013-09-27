//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/compilers/jeval/ExpressionVariable.java
//
//  Created by kendra on 9/27/13.
//

#include "org/oss/pdfreporter/compilers/jeval/ExpressionVariable.h"
#include "org/oss/pdfreporter/compilers/jeval/IDataHolder.h"
#include "org/oss/pdfreporter/compilers/jeval/IExpressionChunk.h"
#include "org/oss/pdfreporter/engine/fill/JRFillVariable.h"

@implementation OrgOssPdfreporterCompilersJevalExpressionVariable

@synthesize data = data_;
@synthesize name = name_;

- (id)initWithOrgOssPdfreporterCompilersJevalIDataHolder:(id<OrgOssPdfreporterCompilersJevalIDataHolder>)data
                                            withNSString:(NSString *)name {
  if ((self = [super init])) {
    self.data = data;
    self.name = name;
  }
  return self;
}

- (id)getValue {
  return [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk([self getVariable])) getValue];
}

- (id)getOldValue {
  return [((OrgOssPdfreporterEngineFillJRFillVariable *) nil_chk([self getVariable])) getOldValue];
}

- (id)getVariableHolder {
  return [self getVariable];
}

- (OrgOssPdfreporterCompilersJevalIExpressionChunk_ExpresionTypeEnum *)getType {
  return [OrgOssPdfreporterCompilersJevalIExpressionChunk_ExpresionTypeEnum TYPE_VARIABLE];
}

- (NSString *)getName {
  return name_;
}

- (OrgOssPdfreporterEngineFillJRFillVariable *)getVariable {
  return [((id<OrgOssPdfreporterCompilersJevalIDataHolder>) nil_chk(data_)) getVariableWithNSString:[self getName]];
}

- (NSString *)description {
  return [NSString stringWithFormat:@"ExpressionVariable [name=%@]", name_];
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterCompilersJevalExpressionVariable *typedCopy = (OrgOssPdfreporterCompilersJevalExpressionVariable *) copy;
  typedCopy.data = data_;
  typedCopy.name = name_;
}

@end