//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/JRGenericElementType.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineJRGenericElementType_H_
#define _OrgOssPdfreporterEngineJRGenericElementType_H_

#import "JreEmulation.h"
#include "java/io/Serializable.h"

#define OrgOssPdfreporterEngineJRGenericElementType_serialVersionUID 10200

@interface OrgOssPdfreporterEngineJRGenericElementType : NSObject < JavaIoSerializable > {
 @public
  NSString *namespace__;
  NSString *name_;
}

@property (nonatomic, copy) NSString *namespace_;
@property (nonatomic, copy) NSString *name;

- (id)initWithNSString:(NSString *)namespace_
          withNSString:(NSString *)name;
- (NSString *)getNamespace;
- (NSString *)getName;
- (NSUInteger)hash;
- (BOOL)isEqual:(id)o;
- (NSString *)description;
@end

#endif // _OrgOssPdfreporterEngineJRGenericElementType_H_