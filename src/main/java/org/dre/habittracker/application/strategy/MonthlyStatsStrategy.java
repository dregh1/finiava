package org.dre.habittracker.application.strategy;

import jakarta.enterprise.context.ApplicationScoped;
import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.model.Period;
import org.dre.habittracker.domain.port.in.PeriodStatsStrategy;
import org.dre.habittracker.domain.port.out.HabitRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class MonthlyStatsStrategy implements PeriodStatsStrategy {

    @Override
    public Period getPeriod() {
        return Period.MONTHLY;
    }

    @Override
    public List<Habit> getStats(HabitRepository repository) {
        LocalDate end   = LocalDate.now();
        LocalDate start = end.minusMonths(1);
        return repository.findBetween(start, end);
    }
}