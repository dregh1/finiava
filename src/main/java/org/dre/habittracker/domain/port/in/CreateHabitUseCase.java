package org.dre.habittracker.domain.port.in;

import java.time.LocalDate;

public interface CreateHabitUseCase {
    void execute(String userId, String description, LocalDate startDate, LocalDate endDate);
}
