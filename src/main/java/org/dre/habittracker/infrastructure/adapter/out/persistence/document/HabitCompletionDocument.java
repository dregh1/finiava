package org.dre.habittracker.infrastructure.adapter.out.persistence.document;

import java.time.LocalDate;

public class HabitCompletionDocument {
    public LocalDate date;

    public HabitCompletionDocument() {}

    public HabitCompletionDocument(LocalDate date) {
        this.date = date;
    }
}
