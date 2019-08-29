package com.batdemir.android.todolist.application.android.GlobalVar;

import com.batdemir.android.todolist.application.android.Entity.ServiceModels.UserModel;

public abstract class GlobalVariable {

    public static UserModel userModel;

    public enum DateFormat{
        CALENDER_DATE_FORMAT("EEE MMM dd hh:mm:ss 'GMT'Z yyyy"),
        DEFAULT_DATE_FORMAT("yyyy-MM-dd'T'HH:mm:ss"),
        NORMAL_DATE_FORMAT("yyyy-MM-dd HH:mm:ss"),
        SMALL_DATE_FORMAT("yyyy-MM-dd"),
        SHOW_DATE_FORMAT("dd/MM/yyyy"),
        SHOW_FULL_FORMAT("dd/MM/yyyy HH:mm"),
        DEFAULT_TIME_FORMAT("HH:mm:ss"),
        SMALL_TIME_FORMAT("HH:mm");
        String str;

        DateFormat(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    public enum DecimalFormat{
        DEFAULT_DECIMAL_FORMAT("#,###.##");
        String str;

        DecimalFormat(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
}
