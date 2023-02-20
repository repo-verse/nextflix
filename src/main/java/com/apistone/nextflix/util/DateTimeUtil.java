package com.apistone.nextflix.util;

import com.apistone.nextflix.constant.StringConstant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.util
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/26/2023
 * Time: 10:02 AM
 * <p>
 * Use: Utility for Date time.
 */
public class DateTimeUtil {
    public static String now(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(StringConstant.PDF_FILE_FORMAT);
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
