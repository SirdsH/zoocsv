package com.company;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Action {
    private LocalDateTime start;
    private LocalDateTime end;
    private String summary;
    private String description;

    public Action(LocalDateTime start, LocalDateTime end, String summary, String description) {
        this.start = start;
        this.end = end;
        this.summary = summary;
        this.description = description;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }
}
