package org.dre.habittracker.application.service;

import jakarta.enterprise.inject.Instance;
import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.model.Period;
import org.dre.habittracker.domain.port.in.GetHabitStatsByDateRangeUseCase;
import org.dre.habittracker.domain.port.in.GetHabitStatsUseCase;
import org.dre.habittracker.domain.port.in.PeriodStatsStrategy;
import org.dre.habittracker.domain.port.out.HabitRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HabitStatsService implements GetHabitStatsUseCase, GetHabitStatsByDateRangeUseCase {

    private final HabitRepository habitRepository;
    private final Map<Period, PeriodStatsStrategy> strategies;

    public HabitStatsService(
            HabitRepository habitRepository,
            Instance<PeriodStatsStrategy> strategyInstances
    ) {
        this.habitRepository = habitRepository;
        this.strategies = StreamSupport
                .stream(strategyInstances.spliterator(), false)
                .collect(Collectors.toMap(
                        PeriodStatsStrategy::getPeriod,
                        Function.identity()
                ));
    }

    //(Dependency Inversion)
    @Override
    public List<Habit> getStats(String periodValue) {
        Period period = Period.from(periodValue);

        PeriodStatsStrategy strategy = strategies.get(period);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Aucune stratégie trouvée pour la période : " + period
            );
        }

        return strategy.getStats(habitRepository);
    }

    public List<Habit> getStatsByDateRange(LocalDate dateStart, LocalDate dateEnd) {
        if (dateStart.isAfter(dateEnd)) {
            throw new IllegalArgumentException(
                    "dateStart (" + dateStart + ") ne peut pas être après dateEnd (" + dateEnd + ")"
            );
        }
        List<Habit> habits = habitRepository.findBetween(dateStart, dateEnd);

        return habits.stream()
                .sorted(Comparator.comparing(Habit::getStartDate))
                .collect(Collectors.toList());
    }

}
