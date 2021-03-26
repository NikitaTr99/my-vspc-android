package com.ntsoftware.vspc.myvspc.screens.schedule.model;

public class ScheduleLesson {

    private String name;

    private String type;

    private String teacher;

    private String audience;

    private String start;

    private String end;

    private String _break;

    public ScheduleLesson(String name, String type, String teacher, String audience, String start, String end, String _break) {
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.audience = audience;
        this.start = start;
        this.end = end;
        this._break = _break;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String get_break() {
        return _break;
    }

    public void set_break(String _break) {
        this._break = _break;
    }

    @Override
    public String toString() {
        return "ScheduleLesson{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", teacher='" + teacher + '\'' +
                ", audience='" + audience + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", _break='" + _break + '\'' +
                '}';
    }
}
