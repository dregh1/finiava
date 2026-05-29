package org.dre.habittracker.domain.service;

import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.port.in.CreateHabitUseCase;
import org.dre.habittracker.domain.port.out.HabitRepository;

import java.time.LocalDate;

public class CreateHabitService implements CreateHabitUseCase {
    private final HabitRepository habitRepository;

    public CreateHabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public void execute(String description, LocalDate startDate, LocalDate endDate) {
        Habit habit = new Habit(
                description,
                startDate,
                endDate
        );
        habitRepository.save(habit);
    }
}
