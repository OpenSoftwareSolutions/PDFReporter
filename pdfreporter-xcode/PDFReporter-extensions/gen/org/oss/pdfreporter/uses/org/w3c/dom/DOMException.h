//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: pdfreporter-extensions/src/org/oss/pdfreporter/uses/org/w3c/dom/DOMException.java
//
//  Created by kendra on 9/27/13.
//

#ifndef _OrgOssPdfreporterUsesOrgW3cDomDOMException_H_
#define _OrgOssPdfreporterUsesOrgW3cDomDOMException_H_

#import "JreEmulation.h"
#include "java/lang/RuntimeException.h"

#define OrgOssPdfreporterUsesOrgW3cDomDOMException_DOMSTRING_SIZE_ERR 2
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_HIERARCHY_REQUEST_ERR 3
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_INDEX_SIZE_ERR 1
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_INUSE_ATTRIBUTE_ERR 10
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_INVALID_ACCESS_ERR 15
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_INVALID_CHARACTER_ERR 5
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_INVALID_MODIFICATION_ERR 13
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_INVALID_STATE_ERR 11
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_NAMESPACE_ERR 14
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_NOT_FOUND_ERR 8
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_NOT_SUPPORTED_ERR 9
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_NO_DATA_ALLOWED_ERR 6
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_NO_MODIFICATION_ALLOWED_ERR 7
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_SYNTAX_ERR 12
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_TYPE_MISMATCH_ERR 17
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_VALIDATION_ERR 16
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_WRONG_DOCUMENT_ERR 4
#define OrgOssPdfreporterUsesOrgW3cDomDOMException_serialVersionUID 6627732366795969916

@interface OrgOssPdfreporterUsesOrgW3cDomDOMException : JavaLangRuntimeException {
 @public
  short int code_;
}

@property (nonatomic, assign) short int code;

+ (short int)INDEX_SIZE_ERR;
+ (short int)DOMSTRING_SIZE_ERR;
+ (short int)HIERARCHY_REQUEST_ERR;
+ (short int)WRONG_DOCUMENT_ERR;
+ (short int)INVALID_CHARACTER_ERR;
+ (short int)NO_DATA_ALLOWED_ERR;
+ (short int)NO_MODIFICATION_ALLOWED_ERR;
+ (short int)NOT_FOUND_ERR;
+ (short int)NOT_SUPPORTED_ERR;
+ (short int)INUSE_ATTRIBUTE_ERR;
+ (short int)INVALID_STATE_ERR;
+ (short int)SYNTAX_ERR;
+ (short int)INVALID_MODIFICATION_ERR;
+ (short int)NAMESPACE_ERR;
+ (short int)INVALID_ACCESS_ERR;
+ (short int)VALIDATION_ERR;
+ (short int)TYPE_MISMATCH_ERR;
+ (long long int)serialVersionUID;
- (id)initWithShortInt:(short int)code
          withNSString:(NSString *)message;
@end

#endif // _OrgOssPdfreporterUsesOrgW3cDomDOMException_H_