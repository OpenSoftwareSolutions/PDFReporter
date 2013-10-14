#import "IOSExportTestProvider.h"

@implementation IOSExportTestProvider

- (NSString *)inputPathWithNSString:(NSString *)input
{
    return [[[NSBundle mainBundle] bundlePath] stringByAppendingPathComponent:input];
}

- (NSString *)outputPathWithNSString:(NSString *)input
{
    return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
}

- (NSString *)databasePath {
    return [self inputPathWithNSString:@"database.db"];
}
@end
