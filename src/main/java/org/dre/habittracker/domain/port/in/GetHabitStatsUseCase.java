package org.dre.habittracker.domain.port.in;

import org.dre.habittracker.domain.model.Habit;

import java.util.List;

public interface GetHabitStatsUseCase {
    List<Habit> getStats(String period); // "WEEK", "MONTH", "YEAR"
}
