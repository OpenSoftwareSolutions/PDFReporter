//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/commons/arrays/Array2DImpl.java
//
//  Created by kendra on 9/27/13.
//

#include "IOSClass.h"
#include "IOSObjectArray.h"
#include "java/lang/ArrayIndexOutOfBoundsException.h"
#include "org/oss/pdfreporter/commons/arrays/Array2DImpl.h"

@implementation OrgOssPdfreporterCommonsArraysArray2DImpl

@synthesize iMax = iMax_;
@synthesize jMax = jMax_;
@synthesize array = array_;

- (id)initWithInt:(int)iMax
          withInt:(int)jMax {
  if ((self = [super init])) {
    self.iMax = iMax;
    self.jMax = jMax;
    self.array = [IOSObjectArray arrayWithLength:iMax * jMax type:[IOSClass classWithClass:[NSObject class]]];
  }
  return self;
}

- (id)getWithInt:(int)i
         withInt:(int)j {
  [self assertRangeWithInt:i withInt:j];
  return (id) [((IOSObjectArray *) nil_chk(array_)) objectAtIndex:i * iMax_ + j];
}

- (void)setWithInt:(int)i
           withInt:(int)j
            withId:(id)element {
  [self assertRangeWithInt:i withInt:j];
  (void) [((IOSObjectArray *) nil_chk(array_)) replaceObjectAtIndex:i * iMax_ + j withObject:element];
}

- (int)getLengthI {
  return iMax_;
}

- (int)getLengthJ {
  return jMax_;
}

- (void)assertRangeWithInt:(int)i
                   withInt:(int)j {
  if (i > iMax_ || j > jMax_ || i < 0 || j < 0) {
    @throw [[JavaLangArrayIndexOutOfBoundsException alloc] initWithNSString:[NSString stringWithFormat:@"assert failed: 0 <= i < %d and 0 <= j < %d", iMax_, jMax_]];
  }
}

- (void)copyAllPropertiesTo:(id)copy {
  [super copyAllPropertiesTo:copy];
  OrgOssPdfreporterCommonsArraysArray2DImpl *typedCopy = (OrgOssPdfreporterCommonsArraysArray2DImpl *) copy;
  typedCopy.iMax = iMax_;
  typedCopy.jMax = jMax_;
  typedCopy.array = array_;
}

@end