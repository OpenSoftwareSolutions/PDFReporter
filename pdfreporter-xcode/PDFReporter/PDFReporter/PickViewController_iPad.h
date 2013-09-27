//
//  PickViewController_iPad.h
//  PDFReporter
//
//  Created by Fr3e on 7/21/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PickProtocol.h"

@interface PickViewController_iPad : UITableViewController {
    id<PickProtocol> pickResponder;
}

- (id)initWithPickResponder:(id<PickProtocol>)responder;

@end
