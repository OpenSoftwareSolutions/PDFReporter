//
//  ViewController.h
//  PDFReporter
//
//  Created by Fr3e on 7/20/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PickProtocol.h"
#import "FileDownloaderProtocol.h"

@class PickViewController_iPad;

@interface HomeViewController_iPhone : UIViewController <PickProtocol, UIAlertViewDelegate, FileDownloaderProtocol>
{
    int testPosition;
    PickViewController_iPad *pickViewController;
}
@property (weak, nonatomic) IBOutlet UIButton *pickButton;
@property (weak, nonatomic) IBOutlet UIView *popUpView;
@property (weak, nonatomic) IBOutlet UIProgressView *progressView;
@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *indicator;
@property (weak, nonatomic) IBOutlet UIView *downloadView;

- (IBAction)pickButtonClick:(id)sender;
- (IBAction)generateButtonClick:(id)sender;
- (IBAction)updateClick:(id)sender;

@end
