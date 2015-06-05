//
//  PdfListVc.m
//  PDFReporter-test
//
//  Created by Martin Krasnocka on 20/02/15.
//  Copyright (c) 2015 Open Software Solutions GmbH. All rights reserved.
//

#import "PdfListVc.h"
#import "ExportTest.h"
#import "PathHelper.h"

@interface PdfListVc ()
@property (nonatomic, strong) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *loader;
@end

@implementation PdfListVc
{
    NSMutableArray *_fileList;
    UIDocumentInteractionController *_documentInteractionController;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self reloadTableViewData];
}

- (IBAction)generateAction:(id)sender
{
    [self removeGeneratedFiles];
    [self reloadTableViewData];
    [_loader startAnimating];
    [UIApplication sharedApplication].idleTimerDisabled = YES;
    [[UIApplication sharedApplication] beginIgnoringInteractionEvents];
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.2 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [ExportTest runTests];
        [self reloadTableViewData];
        [_loader stopAnimating];
        [UIApplication sharedApplication].idleTimerDisabled = NO;
        [[UIApplication sharedApplication] endIgnoringInteractionEvents];
    });
    
}

- (void)removeGeneratedFiles
{
    NSString *documentsDir = [PathHelper documentsDirectory];
    NSArray * directoryContents = [[NSFileManager defaultManager] contentsOfDirectoryAtPath:documentsDir error:nil];
    [directoryContents enumerateObjectsUsingBlock:^(NSString *fileName, NSUInteger idx, BOOL *stop) {
        [[NSFileManager defaultManager] removeItemAtPath:[documentsDir stringByAppendingPathComponent:fileName] error:nil];
    }];
}

- (void)reloadTableViewData
{
    _fileList = [NSMutableArray array];
    NSArray * directoryContents = [[NSFileManager defaultManager] contentsOfDirectoryAtPath:[PathHelper documentsDirectory] error:nil];
    [directoryContents enumerateObjectsUsingBlock:^(NSString *fileName, NSUInteger idx, BOOL *stop) {
        if ([fileName hasSuffix:@".pdf"]) {
            [_fileList addObject:[fileName stringByReplacingOccurrencesOfString:@".pdf" withString:@""]];
        }
    }];
    [_tableView reloadData];
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return _fileList.count;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"pdfCell" forIndexPath:indexPath];
    
    // Configure the cell...
    cell.textLabel.text = _fileList[indexPath.row];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [self previewDocument:_fileList[indexPath.row]];
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}


#pragma mark - UIDocumentInteractionController

- (void)previewDocument:(NSString *)documentName
{
    NSURL *URL = [NSURL fileURLWithPath:[self absolutePathForDocumentWithName:documentName]];
    
    if (URL) {
        // Initialize Document Interaction Controller
        _documentInteractionController = [UIDocumentInteractionController interactionControllerWithURL:URL];
        
        // Configure Document Interaction Controller
        [_documentInteractionController setDelegate:self];
        
        // Preview PDF
        [_documentInteractionController presentPreviewAnimated:YES];
    }
}

- (UIViewController *) documentInteractionControllerViewControllerForPreview: (UIDocumentInteractionController *) controller
{
    return self;
}


#pragma mark - path helper

- (NSString *) absolutePathForDocumentWithName:(NSString *)name
{
    NSString *basePath = [PathHelper documentsDirectory];
    name = [name stringByAppendingString:@".pdf"];
    return [basePath stringByAppendingPathComponent:name];
}

@end
