package org.dre.habittracker.domain.port.in;

import java.time.LocalDate;

public interface CompleteHabitUseCase {
    void execute(String habitId, LocalDate date);
}
