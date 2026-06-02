package org.dre.habittracker.application.strategy;

import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.model.Period;
import org.dre.habittracker.domain.port.in.PeriodStatsStrategy;
import org.dre.habittracker.domain.port.out.HabitRepository;

import java.util.List;

public class AllSatsStrategy implements PeriodStatsStrategy {
    @Override
    public Period getPeriod() {
        return Period.ALL;
    }

    @Override
    public List<Habit> getStats(HabitRepository repository) {
        return repository.findAll();
    }
}
