package org.dre.habittracker.domain.port.out;

import org.dre.habittracker.domain.model.Habit;

import java.util.List;
import java.util.Optional;

public interface HabitRepository {
    void save(Habit habit);
    Optional<Habit> findById(String id);
    List<Habit> findAll();
}
