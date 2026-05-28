package org.dre.habittracker.domain.port.in;

import java.time.LocalDate;

public interface CreateHabitService {
    void execute(String description, LocalDate startDate, LocalDate endDate);
}
