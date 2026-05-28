package org.dre.habittracker.domain.service;

import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.model.HabitCompletion;
import org.dre.habittracker.domain.port.in.CompleteHabitUseCase;
import org.dre.habittracker.domain.port.out.HabitRepository;

import java.time.LocalDate;

public class CompleteHabitService implements CompleteHabitUseCase {
    private final HabitRepository habitRepository;

    public CompleteHabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public void execute(String habitId, LocalDate date) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habitude non trouvée"));

        boolean alreadyDone = habit.getCompletions()
                .stream()
                .anyMatch(c -> c.getDate().equals(date));

        if (alreadyDone) {
            throw new RuntimeException("Habitude déjà complétée ce jour");
        }

        habit.getCompletions().add(new HabitCompletion(date));
        habitRepository.save(habit);
    }
}
