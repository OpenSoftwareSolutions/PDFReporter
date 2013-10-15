//
//  PdfViewController_iPad.m
//  PDFReporter
//
//  Created by Fr3e on 7/20/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import "PdfViewController_iPad.h"


@interface PdfViewController_iPad ()

@end

@implementation PdfViewController_iPad

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    NSArray *arrayPaths =
    NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [[arrayPaths objectAtIndex:0] stringByAppendingPathComponent:@"output.pdf"];

    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL fileURLWithPath:path]];
    [[self webView] loadRequest:request];
    [_segControl setEnabled:[MFMailComposeViewController canSendMail] forSegmentAtIndex:1];
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleDefault animated:YES];
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)backClick:(id)sender {
     [self.presentingViewController dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)segmentValChanged:(id)sender {
    NSArray *arrayPaths =
    NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *path = [[arrayPaths objectAtIndex:0] stringByAppendingPathComponent:@"output.pdf"];
    
    if(((UISegmentedControl*)sender).selectedSegmentIndex == 0) {
        UIPrintInteractionController *pic = [UIPrintInteractionController sharedPrintController];
        pic.printingItem = [NSData dataWithContentsOfFile:path];
        [pic presentFromBarButtonItem:self.navigationItem.rightBarButtonItem animated:YES completionHandler:nil];
    }
    else {
        MFMailComposeViewController *mcvc = [[MFMailComposeViewController alloc] init];
        mcvc.mailComposeDelegate = self;
        [mcvc addAttachmentData:[NSData dataWithContentsOfFile:path] mimeType:@"application/pdf" fileName:@"Report.pdf"];
        [self presentViewController:mcvc animated:YES completion:nil];
    }
}

- (void)mailComposeController:(MFMailComposeViewController*)controller
          didFinishWithResult:(MFMailComposeResult)result
                        error:(NSError*)error;
{
    [self dismissViewControllerAnimated:YES completion:nil];
}
@end
