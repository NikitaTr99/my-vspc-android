package com.ntsoftware.vspc.myvspc.ui.schedule.model;

import java.util.ArrayList;
import java.util.List;

public class SchWeek {

    List<SchDay> days;

    public SchWeek() {
    }

    public SchWeek(List<SchDay> days) {
        this.days = days;
    }

    public List<SchDay> getDays() {
        return days;
    }

    public void setDays(List<SchDay> days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "SchWeek{" +
                "days=" + days +
                '}';
    }

    public static class SchDay {

        private int day_of_week;

        private String name_day_of_week;

        List<LessonDetail> lessons;

        public SchDay() {
            lessons = new ArrayList<>();
        }

        public int getDay_of_week() {
            return day_of_week;
        }

        public void setDay_of_week(int day_of_week) {
            this.day_of_week = day_of_week;
        }

        public String getName_day_of_week() {
            return name_day_of_week;
        }

        public void setName_day_of_week(String name_day_of_week) {
            this.name_day_of_week = name_day_of_week;
        }

        public List<LessonDetail> getLessons() {
            return lessons;
        }

        public void setLessons(List<LessonDetail> lessons) {
            this.lessons = lessons;
        }

        @Override
        public String toString() {
            return "SchDay{" +
                    "day_of_week=" + day_of_week +
                    ", name_day_of_week='" + name_day_of_week + '\'' +
                    ", lessons=" + lessons +
                    '}';
        }
        public static class Builder {

            private final SchDay day;

            public Builder() {
                this.day = new SchDay();
            }

            public Builder DayOfWeek(int i) {
                day.day_of_week = i;
                return this;
            }

            public Builder NameDayOfWeek(String s) {
                day.name_day_of_week = s;
                return this;
            }

            public Builder AddLesson(LessonDetail l) {
                day.lessons.add(l);
                return this;
            }


            public Builder Lessons(List<LessonDetail> list) {
                day.lessons = list;
                return this;
            }

            public SchDay build() {
                return day;
            }
        }
    }


}
