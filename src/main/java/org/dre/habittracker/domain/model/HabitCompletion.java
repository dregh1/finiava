package org.dre.habittracker.domain.model;

import java.time.LocalDate;

public class HabitCompletion {
    private LocalDate date;

    public HabitCompletion() {}

    public HabitCompletion(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
