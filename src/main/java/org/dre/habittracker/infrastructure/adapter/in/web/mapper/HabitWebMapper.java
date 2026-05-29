package org.dre.habittracker.infrastructure.adapter.in.web.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.model.HabitCompletion;
import org.dre.habittracker.infrastructure.adapter.in.web.dto.HabitResponse;

import java.util.stream.Collectors;

@ApplicationScoped
public class HabitWebMapper {

    public HabitResponse toResponse(Habit habit) {
        HabitResponse response = new HabitResponse();
        response.id          = habit.getId();
        response.description = habit.getDescription();
        response.startDate   = habit.getStartDate();
        response.endDate     = habit.getEndDate();
        response.completions = habit.getCompletions()
                .stream()
                .map(HabitCompletion::getDate)
                .collect(Collectors.toList());
        return response;
    }
}
