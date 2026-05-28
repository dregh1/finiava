package org.dre.habittracker.infrastructure.adapter.in.web.dto;

import java.time.LocalDate;

public class CreateHabitRequest {

    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
}