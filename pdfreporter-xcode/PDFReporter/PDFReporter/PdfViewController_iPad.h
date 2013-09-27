//
//  PdfViewController_iPad.h
//  PDFReporter
//
//  Created by Fr3e on 7/20/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MessageUI/MFMailComposeViewController.h>

@interface PdfViewController_iPad : UIViewController <MFMailComposeViewControllerDelegate>
@property (weak, nonatomic) IBOutlet UIWebView *webView;
- (IBAction)backClick:(id)sender;
- (IBAction)segmentValChanged:(id)sender;
@property (weak, nonatomic) IBOutlet UISegmentedControl *segControl;

@end
