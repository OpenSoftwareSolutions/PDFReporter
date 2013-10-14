#import "IOSRealEstateTestProvider.h"

@implementation IOSRealestateTestProvider

- (NSString *)inputPathWithNSString:(NSString *)input
{
    return [[[NSBundle mainBundle] bundlePath] stringByAppendingPathComponent:input];
}

- (NSString *)outputPathWithNSString:(NSString *)input
{
    return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
}

- (NSString *)databasePath {
    return [self inputPathWithNSString:@"realestate.db"];
}
@end
