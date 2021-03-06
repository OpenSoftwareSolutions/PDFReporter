//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: android/libcore/luni/src/main/java/java/util/Calendar.java
//

#ifndef _JavaUtilCalendar_H_
#define _JavaUtilCalendar_H_

@class IOSBooleanArray;
@class IOSIntArray;
@class IOSObjectArray;
@class JavaIoObjectInputStream;
@class JavaIoObjectOutputStream;
@class JavaUtilDate;
@class JavaUtilLocale;
@class JavaUtilTimeZone;
@protocol JavaUtilMap;

#include "J2ObjC_header.h"
#include "java/io/Serializable.h"
#include "java/lang/Comparable.h"

#define JavaUtilCalendar_ALL_STYLES 0
#define JavaUtilCalendar_AM 0
#define JavaUtilCalendar_AM_PM 9
#define JavaUtilCalendar_APRIL 3
#define JavaUtilCalendar_AUGUST 7
#define JavaUtilCalendar_DATE 5
#define JavaUtilCalendar_DAY_OF_MONTH 5
#define JavaUtilCalendar_DAY_OF_WEEK 7
#define JavaUtilCalendar_DAY_OF_WEEK_IN_MONTH 8
#define JavaUtilCalendar_DAY_OF_YEAR 6
#define JavaUtilCalendar_DECEMBER 11
#define JavaUtilCalendar_DST_OFFSET 16
#define JavaUtilCalendar_ERA 0
#define JavaUtilCalendar_FEBRUARY 1
#define JavaUtilCalendar_FIELD_COUNT 17
#define JavaUtilCalendar_FRIDAY 6
#define JavaUtilCalendar_HOUR 10
#define JavaUtilCalendar_HOUR_OF_DAY 11
#define JavaUtilCalendar_JANUARY 0
#define JavaUtilCalendar_JULY 6
#define JavaUtilCalendar_JUNE 5
#define JavaUtilCalendar_LONG 2
#define JavaUtilCalendar_MARCH 2
#define JavaUtilCalendar_MAY 4
#define JavaUtilCalendar_MILLISECOND 14
#define JavaUtilCalendar_MINUTE 12
#define JavaUtilCalendar_MONDAY 2
#define JavaUtilCalendar_MONTH 2
#define JavaUtilCalendar_NOVEMBER 10
#define JavaUtilCalendar_OCTOBER 9
#define JavaUtilCalendar_PM 1
#define JavaUtilCalendar_SATURDAY 7
#define JavaUtilCalendar_SECOND 13
#define JavaUtilCalendar_SEPTEMBER 8
#define JavaUtilCalendar_SHORT 1
#define JavaUtilCalendar_SUNDAY 1
#define JavaUtilCalendar_THURSDAY 5
#define JavaUtilCalendar_TUESDAY 3
#define JavaUtilCalendar_UNDECIMBER 12
#define JavaUtilCalendar_WEDNESDAY 4
#define JavaUtilCalendar_WEEK_OF_MONTH 4
#define JavaUtilCalendar_WEEK_OF_YEAR 3
#define JavaUtilCalendar_YEAR 1
#define JavaUtilCalendar_ZONE_OFFSET 15
#define JavaUtilCalendar_serialVersionUID -1807547505821590642LL

@interface JavaUtilCalendar : NSObject < JavaIoSerializable, NSCopying, JavaLangComparable > {
 @public
  jboolean areFieldsSet_;
  IOSIntArray *fields_;
  IOSBooleanArray *isSet__;
  jboolean isTimeSet_;
  jlong time_;
  jint lastTimeFieldSet_;
  jint lastDateFieldSet_;
}

- (instancetype)init;

- (instancetype)initWithJavaUtilTimeZone:(JavaUtilTimeZone *)timezone;

- (instancetype)initWithJavaUtilTimeZone:(JavaUtilTimeZone *)timezone
                      withJavaUtilLocale:(JavaUtilLocale *)locale;

- (void)addWithInt:(jint)field
           withInt:(jint)value;

- (jboolean)afterWithId:(id)calendar;

- (jboolean)beforeWithId:(id)calendar;

- (void)clear;

- (void)clearWithInt:(jint)field;

- (id)clone;

- (void)complete;

- (void)computeFields;

- (void)computeTime;

- (jboolean)isEqual:(id)object;

- (jint)getWithInt:(jint)field;

- (jint)getActualMaximumWithInt:(jint)field;

- (jint)getActualMinimumWithInt:(jint)field;

+ (IOSObjectArray *)getAvailableLocales;

- (jint)getFirstDayOfWeek;

- (jint)getGreatestMinimumWithInt:(jint)field;

+ (JavaUtilCalendar *)getInstance;

+ (JavaUtilCalendar *)getInstanceWithJavaUtilLocale:(JavaUtilLocale *)locale;

+ (JavaUtilCalendar *)getInstanceWithJavaUtilTimeZone:(JavaUtilTimeZone *)timezone;

+ (JavaUtilCalendar *)getInstanceWithJavaUtilTimeZone:(JavaUtilTimeZone *)timezone
                                   withJavaUtilLocale:(JavaUtilLocale *)locale;

- (jint)getLeastMaximumWithInt:(jint)field;

- (jint)getMaximumWithInt:(jint)field;

- (jint)getMinimalDaysInFirstWeek;

- (jint)getMinimumWithInt:(jint)field;

- (JavaUtilDate *)getTime;

- (jlong)getTimeInMillis;

- (JavaUtilTimeZone *)getTimeZone;

- (NSUInteger)hash;

- (jint)internalGetWithInt:(jint)field;

- (jboolean)isLenient;

- (jboolean)isSetWithInt:(jint)field;

- (void)rollWithInt:(jint)field
            withInt:(jint)value;

- (void)rollWithInt:(jint)field
        withBoolean:(jboolean)increment;

- (void)setWithInt:(jint)field
           withInt:(jint)value;

- (void)setWithInt:(jint)year
           withInt:(jint)month
           withInt:(jint)day;

- (void)setWithInt:(jint)year
           withInt:(jint)month
           withInt:(jint)day
           withInt:(jint)hourOfDay
           withInt:(jint)minute;

- (void)setWithInt:(jint)year
           withInt:(jint)month
           withInt:(jint)day
           withInt:(jint)hourOfDay
           withInt:(jint)minute
           withInt:(jint)second;

- (void)setFirstDayOfWeekWithInt:(jint)value;

- (void)setLenientWithBoolean:(jboolean)value;

- (void)setMinimalDaysInFirstWeekWithInt:(jint)value;

- (void)setTimeWithJavaUtilDate:(JavaUtilDate *)date;

- (void)setTimeInMillisWithLong:(jlong)milliseconds;

- (void)setTimeZoneWithJavaUtilTimeZone:(JavaUtilTimeZone *)timezone;

- (NSString *)description;

- (jint)compareToWithId:(JavaUtilCalendar *)anotherCalendar;

- (NSString *)getDisplayNameWithInt:(jint)field
                            withInt:(jint)style
                 withJavaUtilLocale:(JavaUtilLocale *)locale;

- (id<JavaUtilMap>)getDisplayNamesWithInt:(jint)field
                                  withInt:(jint)style
                       withJavaUtilLocale:(JavaUtilLocale *)locale;

@end

FOUNDATION_EXPORT BOOL JavaUtilCalendar_initialized;
J2OBJC_STATIC_INIT(JavaUtilCalendar)

J2OBJC_FIELD_SETTER(JavaUtilCalendar, fields_, IOSIntArray *)
J2OBJC_FIELD_SETTER(JavaUtilCalendar, isSet__, IOSBooleanArray *)

CF_EXTERN_C_BEGIN

FOUNDATION_EXPORT IOSObjectArray *JavaUtilCalendar_getAvailableLocales();

FOUNDATION_EXPORT JavaUtilCalendar *JavaUtilCalendar_getInstance();

FOUNDATION_EXPORT JavaUtilCalendar *JavaUtilCalendar_getInstanceWithJavaUtilLocale_(JavaUtilLocale *locale);

FOUNDATION_EXPORT JavaUtilCalendar *JavaUtilCalendar_getInstanceWithJavaUtilTimeZone_(JavaUtilTimeZone *timezone);

FOUNDATION_EXPORT JavaUtilCalendar *JavaUtilCalendar_getInstanceWithJavaUtilTimeZone_withJavaUtilLocale_(JavaUtilTimeZone *timezone, JavaUtilLocale *locale);

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, serialVersionUID, jlong)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, JANUARY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, FEBRUARY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, MARCH, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, APRIL, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, MAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, JUNE, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, JULY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, AUGUST, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, SEPTEMBER, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, OCTOBER, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, NOVEMBER, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, DECEMBER, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, UNDECIMBER, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, SUNDAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, MONDAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, TUESDAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, WEDNESDAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, THURSDAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, FRIDAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, SATURDAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, ERA, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, YEAR, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, MONTH, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, WEEK_OF_YEAR, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, WEEK_OF_MONTH, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, DATE, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, DAY_OF_MONTH, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, DAY_OF_YEAR, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, DAY_OF_WEEK, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, DAY_OF_WEEK_IN_MONTH, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, AM_PM, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, HOUR, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, HOUR_OF_DAY, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, MINUTE, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, SECOND, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, MILLISECOND, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, ZONE_OFFSET, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, DST_OFFSET, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, FIELD_COUNT, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, AM, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, PM, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, ALL_STYLES, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, SHORT, jint)

J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, LONG, jint)

FOUNDATION_EXPORT IOSObjectArray *JavaUtilCalendar_FIELD_NAMES_;
J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, FIELD_NAMES_, IOSObjectArray *)

FOUNDATION_EXPORT IOSObjectArray *JavaUtilCalendar_serialPersistentFields_;
J2OBJC_STATIC_FIELD_GETTER(JavaUtilCalendar, serialPersistentFields_, IOSObjectArray *)
CF_EXTERN_C_END

J2OBJC_TYPE_LITERAL_HEADER(JavaUtilCalendar)

#endif // _JavaUtilCalendar_H_
