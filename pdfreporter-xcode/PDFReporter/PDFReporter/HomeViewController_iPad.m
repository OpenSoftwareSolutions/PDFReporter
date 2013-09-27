//
//  ViewController.m
//  PDFReporter
//
//  Created by Fr3e on 7/20/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import "HomeViewController_iPad.h"
#import "PdfViewController_iPad.h"
#import "PickViewController_iPad.h"
#import <QuartzCore/QuartzCore.h>
#import "PickViewController_iPad.h"
#import "UpdateHelper.h"
#import "FileDownloader.h"

@interface HomeViewController_iPad ()

@end

@implementation HomeViewController_iPad

- (void)viewDidLoad
{
    [super viewDidLoad];
    testPosition = -1;
    pickViewController = [[PickViewController_iPad alloc] initWithPickResponder:self];
    UIView * pView = [pickViewController view];
    
    [pView setFrame:CGRectMake(0, 0, [[self popUpView] frame].size.width, [[self popUpView] frame].size.height-6)];
    [[self popUpView] addSubview:pView];
    
    [[_downloadView layer] setCornerRadius:9];
    [[pView layer] setCornerRadius:9];
    [[[self pickButton] layer] setCornerRadius:9];
    [[self pickButton] setClipsToBounds:YES];
    
    //[[self indicator] setHidden:NO];
    //[[UIApplication sharedApplication] beginIgnoringInteractionEvents];
    //[self performSelectorInBackground:@selector(initializeInParallel) withObject:nil];
    [UpdateHelper initializeReportsDirectory];
}

-(void)initializeInParallel
{
    [UpdateHelper initializeReportsDirectory];
    [self performSelectorOnMainThread:@selector(initializationDone) withObject:nil waitUntilDone:NO];
}

-(void)initializationDone
{
    [[self indicator] setHidden:YES];
    [[UIApplication sharedApplication] endIgnoringInteractionEvents];
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void) onPickedTest:(int)position
{
    [[self popUpView] setHidden:YES];
    [[self pickButton] setTitle:[[UpdateHelper getAllPLists] objectAtIndex:position] forState:UIControlStateNormal];
    testPosition = position;
    [self performSelectorInBackground:@selector(pickInParallel) withObject:nil];
    [[UIApplication sharedApplication] beginIgnoringInteractionEvents];
    [[self indicator] setHidden:NO];
}

-(void)pickInParallel
{
    @try {
        [UpdateHelper runPhase1ReportFromPListAtPosition:testPosition];
        [self performSelectorOnMainThread:@selector(finishPickInParallel) withObject:nil waitUntilDone:NO];
    }
    @catch (NSException *e)
    {
        [self performSelectorOnMainThread:@selector(finishPickWithException) withObject:nil waitUntilDone:NO];
    }
}

-(void)finishPickInParallel
{
    [[UIApplication sharedApplication] endIgnoringInteractionEvents];
    [[self indicator] setHidden:YES];
}

-(void)finishPickWithException
{
    [[self indicator] setHidden:YES];
    [[UIApplication sharedApplication] endIgnoringInteractionEvents];
    [[[UIAlertView alloc] initWithTitle:@"Exception" message:@"Can't load this report." delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
}

-(void)exportInParallel
{
    @try {
        //[UpdateHelper runReportFromPListAtPosition:testPosition];
        [UpdateHelper runPhase2ReportFromPListAtPosition:testPosition];
        [self performSelectorOnMainThread:@selector(finishSuccessfuly) withObject:nil waitUntilDone:NO];
    }
    @catch (NSException *e)
    {
        [self performSelectorOnMainThread:@selector(finishWithException) withObject:nil waitUntilDone:NO];
    }
}

-(void)finishWithException
{
    [[self indicator] setHidden:YES];
    [[UIApplication sharedApplication] endIgnoringInteractionEvents];
    [[[UIAlertView alloc] initWithTitle:@"Exception" message:@"Can't handle this report. Yet!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
}

-(void)finishSuccessfuly
{
    testPosition = -1;
    [[self pickButton] setTitle:@"[ No Report Selected ]" forState:UIControlStateNormal];
    [[self indicator] setHidden:YES];
    [[UIApplication sharedApplication] endIgnoringInteractionEvents];
    PdfViewController_iPad *viewController = [[PdfViewController_iPad alloc] initWithNibName:@"PdfViewController_iPad" bundle:nil];
    [viewController setModalTransitionStyle:UIModalTransitionStyleFlipHorizontal];
    [self presentViewController:viewController animated:YES completion:nil];
}

- (IBAction)generateClick:(id)sender
{
    [self showPopUp:NO];
    if(testPosition != -1) {
        [[self indicator] setHidden:NO];
        [[UIApplication sharedApplication] beginIgnoringInteractionEvents];
        [self performSelectorInBackground:@selector(exportInParallel) withObject:nil];
    }
}

- (void) showPopUp:(bool)show
{
    if([[self popUpView] isHidden] == !show) return;
    [[self popUpView] setHidden:!show];
    [[self popUpView] setAlpha:show?0:1];
    
    [UIView beginAnimations:nil context:nil];
        [UIView setAnimationDuration:0.2];
        [UIView setAnimationCurve:UIViewAnimationCurveEaseOut];
        [[self popUpView] setAlpha:show?1:0];
    [UIView commitAnimations];
}

- (IBAction)pickReportClick:(id)sender
{
    [self showPopUp:[[self popUpView] isHidden]];
}

- (IBAction)updateClick:(id)sender {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Reports Update" message:@"Do you want to download and update samples?" delegate:self cancelButtonTitle:@"No" otherButtonTitles:@"Yes", nil];
    [alert show];
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if(buttonIndex == 1)
    {
        NSString *documentdir = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
        NSString *zipFile = [documentdir stringByAppendingPathComponent:@"data.zip"];
        [FileDownloader downloadUrl:[UpdateHelper downloadUrl] toFile:zipFile withDelegate:self];
        [[UIApplication sharedApplication] beginIgnoringInteractionEvents];
        [_progressView setProgress:0];
        [_downloadView setHidden:NO];
        
        testPosition = -1;
        [[self pickButton] setTitle:@"[ No Report Selected ]" forState:UIControlStateNormal];
    }
}

-(void)downloader:(FileDownloader *)downloader onProgressUpdate:(float)progress
{
    [_progressView setProgress:progress];
}

-(void)downloader:(FileDownloader *)downloader finishedDownloadingUrl:(NSString *)url toFile:(NSString *)filePath withStatus:(int)status
{
    [[UIApplication sharedApplication] endIgnoringInteractionEvents];
    [_downloadView setHidden:YES];
    if(status == 200)
    {
        if (![UpdateHelper updateReportsWithZip:filePath])
        {
            [[[UIAlertView alloc] initWithTitle:@"Reports Update" message:@"Installing sample reports failed!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
        }
        [[pickViewController tableView] reloadData];
    }
    else
    {
        [[[UIAlertView alloc] initWithTitle:@"Reports Update" message:@"Download of sample reports failed!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
    }
}

-(void)downloader:(FileDownloader *)downloader failedDownloadingUrl:(NSString *)url toFile:(NSString *)filePath
{
    [[UIApplication sharedApplication] endIgnoringInteractionEvents];
    [_downloadView setHidden:YES];
    [[[UIAlertView alloc] initWithTitle:@"Reports Update" message:@"Download of sample reports failed!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil] show];
}

- (void)viewDidUnload {
    [self setDownloadView:nil];
    [self setProgressView:nil];
    [super viewDidUnload];
}
@end
