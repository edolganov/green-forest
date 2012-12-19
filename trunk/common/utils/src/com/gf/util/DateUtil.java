/*
 * Copyright 2012 Evgeny Dolganov (evgenij.dolganov@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gf.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	
    public static long ONE_SECOND = 1000L;
    public static long ONE_MINUTE = 60L * ONE_SECOND;
    public static long ONE_HOUR = 60L * ONE_MINUTE;
    public static long ONE_DAY = 24L * ONE_HOUR;

    public static Date dateStart(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date dateEnd(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date monthStart(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStart(date));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date monthEnd(Date date) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateEnd(date));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date previousMonthStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthStart(date));
        if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        } else {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        }
        return calendar.getTime();
    }

    public static Date previousMonthEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthStart(date));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        return dateEnd(calendar.getTime());
    }

    public static Date weekStart(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateStart(date));
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }


    public static long dateDiffInDays(Date dateEnd, Date dateStart) {
        return Math.abs((dateEnd.getTime() - dateStart.getTime()) / ONE_DAY);
    }
    

	public static Date addMonths(Date date, int monthsCount) {
		
        if (date == null) {
            return null;
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setLenient(false);
        calendar.add(Calendar.MONTH, monthsCount);
        return calendar.getTime();
	}

    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
        return calendar.getTime();
    }

    public static Date decreaseDays(Date date, int days) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
        return calendar.getTime();
    }

    public static Date nextDateFromToday() {
        return nextDate(new Date());
    }

    public static Date nextWeekFromToday() {
        return addDays(new Date(), 7);
    }
    
    public static Date nextTwoWeeksFromToday() {
        return addDays(new Date(), 14);
    }

    public static Date nextDate(Date date) {
        return addDays(date, 1);
    }
    
    public static int dayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date shiftDate(Date date, TimeZone timeZone, String pattern) {
        if ((timeZone == null) || (date == null)) return date;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String s = sdf.format(date);
        sdf.setTimeZone(timeZone);
        try {
            return sdf.parse(s);
        } catch (ParseException e) {
            return date;
        }
    }

    public static Double getTimeInMilliseconds(int scale){
        return new BigDecimal(System.nanoTime()).divide(new BigDecimal(1000000)).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static Float getDeltaTimeInMilliseconds(double startTime, double finishTime, int scale){
        return new BigDecimal(finishTime).subtract(new BigDecimal(startTime)).setScale(scale, RoundingMode.HALF_UP).floatValue();
    }

}

