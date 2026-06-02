package org.dre.habittracker.domain.port.in;

import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.model.Period;
import org.dre.habittracker.domain.port.out.HabitRepository;

import java.util.List;

public interface  PeriodStatsStrategy {
    Period getPeriod();
    List<Habit> getStats(HabitRepository repository);
}
