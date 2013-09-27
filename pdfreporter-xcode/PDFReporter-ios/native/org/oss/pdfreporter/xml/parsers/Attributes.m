//
//  Attributes.m
//  JasperReportiOS
//
//  Created by Martin Krasnočka on 5/8/13.
//  Copyright (c) 2013 Digireport. All rights reserved.
//

#import "Attributes.h"

@interface Attributes ()
@property (nonatomic, strong) NSMutableDictionary *attributeDict;
@end

@implementation Attributes

-(id)initWithDictionary:(NSDictionary *)dict
{
    self = [super init];
    if (self) {
        _attributeDict = [dict mutableCopy];
    }
    return self;
}

-(NSMutableDictionary *)attributeDict
{
    if (!_attributeDict) {
        _attributeDict = [[NSMutableDictionary alloc] init];
    }
    return _attributeDict;
}

- (int)getLength
{
    return [self.attributeDict.allKeys count];
}
- (NSString *)getURIWithInt:(int)index
{
    return nil;
}
- (NSString *)getLocalNameWithInt:(int)index
{
#warning TODO: return empty string if namespace processing is not being performed
    if (index >= [[self.attributeDict allKeys] count]) {
        return nil;
    }
    return [[self.attributeDict allKeys] objectAtIndex:index];
}
- (NSString *)getQNameWithInt:(int)index
{
    return nil;
}
- (NSString *)getTypeWithInt:(int)index
{
    return nil;
}
- (NSString *)getValueWithInt:(int)index
{
    if (index >= [[self.attributeDict allValues] count]) {
        return nil;
    }
    return [[self.attributeDict allValues] objectAtIndex:index];
}
- (int)getIndexWithNSString:(NSString *)uri
               withNSString:(NSString *)localName
{
    return 0;
}
- (int)getIndexWithNSString:(NSString *)qName
{
    return 0;
}
- (NSString *)getTypeWithNSString:(NSString *)uri
                     withNSString:(NSString *)localName
{
    return nil;
}
- (NSString *)getTypeWithNSString:(NSString *)qName
{
    return nil;
}
- (NSString *)getValueWithNSString:(NSString *)uri
                      withNSString:(NSString *)localName
{
    return nil;
}
- (NSString *)getValueWithNSString:(NSString *)qName
{
    return [self.attributeDict objectForKey:qName];
}

-(NSString *)description
{
    return [NSString stringWithFormat:@"%@", self.attributeDict];
}

@end
