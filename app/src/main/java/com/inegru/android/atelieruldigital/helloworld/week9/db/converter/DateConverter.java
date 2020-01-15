package com.inegru.android.atelieruldigital.helloworld.week9.db.converter;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

/**
 * .
 */
public class DateConverter {

    private DateConverter() {
    }

    @TypeConverter
    @Nullable
    public static Date toDate(@Nullable Long date) {
        return date != null ? new Date(date) : null;
    }

    @TypeConverter
    @Nullable
    public static Long toTimeStamp(@Nullable Date date) {
        return date != null ? date.getTime() : null;
    }
}
