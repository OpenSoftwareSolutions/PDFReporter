//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/JRQueryChunkHandler.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterEngineUtilJRQueryChunkHandler_H_
#define _OrgOssPdfreporterEngineUtilJRQueryChunkHandler_H_

@class IOSObjectArray;

#import "JreEmulation.h"

@protocol OrgOssPdfreporterEngineUtilJRQueryChunkHandler < NSObject, JavaObject >
- (void)handleTextChunkWithNSString:(NSString *)text;
- (void)handleParameterChunkWithNSString:(NSString *)text;
- (void)handleParameterClauseChunkWithNSString:(NSString *)text;
- (void)handleClauseChunkWithNSStringArray:(IOSObjectArray *)tokens;
@end

#endif // _OrgOssPdfreporterEngineUtilJRQueryChunkHandler_H_