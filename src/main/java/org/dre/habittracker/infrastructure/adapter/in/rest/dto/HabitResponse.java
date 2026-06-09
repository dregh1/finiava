package org.dre.habittracker.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;
import java.util.List;

public class HabitResponse {

    public String id;
    public String description;
    public LocalDate startDate;
    public LocalDate endDate;
    public List<LocalDate> completions;
}