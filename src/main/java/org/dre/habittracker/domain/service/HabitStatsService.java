package org.dre.habittracker.domain.service;

import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.port.in.GetHabitStatsUseCase;
import org.dre.habittracker.domain.port.out.HabitRepository;

import java.util.List;

public class HabitStatsService implements GetHabitStatsUseCase {
    private final HabitRepository habitRepository;

    public HabitStatsService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public List<Habit> getStats(String period) {
        return habitRepository.findAll();
    }
}
