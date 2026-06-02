package org.dre.habittracker.domain.port.in;

import org.dre.habittracker.domain.model.Habit;

import java.time.LocalDate;
import java.util.List;

public interface GetHabitStatsByDateRangeUseCase {
    List<Habit> getStatsByDateRange(LocalDate dateStart, LocalDate dateEnd);
}