//
//  PickViewController_iPad.m
//  PDFReporter
//
//  Created by Fr3e on 7/21/13.
//  Copyright (c) 2013 Fr3e. All rights reserved.
//

#import "PickViewController_iPad.h"
#import "UpdateHelper.h"

@interface PickViewController_iPad ()

@end

@implementation PickViewController_iPad

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (id)initWithPickResponder:(id<PickProtocol>)responder
{
    self = [super initWithNibName:@"PickViewController_iPad" bundle:nil];
    if (self) {
        pickResponder = responder;
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    // Uncomment the following line to preserve selection between presentations.
    //self.clearsSelectionOnViewWillAppear = NO;
    //[self.tableView setSeparatorColor:[UIColor colorWithRed:0.4196 green:0.4509 blue:0.4627 alpha:1]];
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [[UpdateHelper getAllPLists] count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier];
        [[cell textLabel] setTextColor:[UIColor blackColor]];
        [[cell textLabel] setFont:[UIFont fontWithName:@"Helvetica-Light" size:20]];
        [cell setSelectionStyle:UITableViewCellSelectionStyleNone];
    }
    [[cell textLabel] setText:[[UpdateHelper getAllPLists] objectAtIndex:indexPath.row]];
    
    return cell;
}


#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [pickResponder onPickedTest:indexPath.row];
    [tableView deselectRowAtIndexPath:indexPath animated:NO];
}

@end
