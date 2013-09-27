//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-core/src/org/oss/pdfreporter/engine/util/JRSaver.java
//
//  Created by kendra on 9/27/13.
//

#include "java/io/BufferedWriter.h"
#include "java/io/File.h"
#include "java/io/FileWriter.h"
#include "java/io/IOException.h"
#include "java/io/OutputStream.h"
#include "org/oss/pdfreporter/engine/JRException.h"
#include "org/oss/pdfreporter/engine/util/JRSaver.h"

@implementation OrgOssPdfreporterEngineUtilJRSaver

+ (void)saveObjectWithId:(id)obj
            withNSString:(NSString *)fileName {
  [OrgOssPdfreporterEngineUtilJRSaver saveObjectWithId:obj withJavaIoFile:[[JavaIoFile alloc] initWithNSString:fileName]];
}

+ (void)saveObjectWithId:(id)obj
          withJavaIoFile:(JavaIoFile *)file {
}

+ (void)saveObjectWithId:(id)obj
  withJavaIoOutputStream:(JavaIoOutputStream *)os {
}

+ (void)saveClassSourceWithNSString:(NSString *)source
                     withJavaIoFile:(JavaIoFile *)file {
  JavaIoFileWriter *fwriter = nil;
  @try {
    fwriter = [[JavaIoFileWriter alloc] initWithJavaIoFile:file];
    JavaIoBufferedWriter *bufferedWriter = [[JavaIoBufferedWriter alloc] initWithJavaIoWriter:fwriter];
    [((JavaIoBufferedWriter *) nil_chk(bufferedWriter)) writeWithNSString:source];
    [((JavaIoBufferedWriter *) nil_chk(bufferedWriter)) flush];
    [((JavaIoFileWriter *) nil_chk(fwriter)) flush];
    [((JavaIoFileWriter *) nil_chk(fwriter)) close];
    [((JavaIoBufferedWriter *) nil_chk(bufferedWriter)) close];
  }
  @catch (JavaIoIOException *e) {
    @throw [[OrgOssPdfreporterEngineJRException alloc] initWithNSString:[NSString stringWithFormat:@"Error saving expressions class file : %@", file] withJavaLangThrowable:e];
  }
  @finally {
    if (fwriter != nil) {
      @try {
        [fwriter close];
      }
      @catch (JavaIoIOException *e) {
      }
    }
  }
}

- (id)init {
  return [super init];
}

@end