package org.dre.habittracker.infrastructure.adapter.out.persistence.mapper;

import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.model.HabitCompletion;
import jakarta.enterprise.context.ApplicationScoped;
import org.dre.habittracker.infrastructure.adapter.out.persistence.document.HabitCompletionDocument;
import org.dre.habittracker.infrastructure.adapter.out.persistence.document.HabitDocument;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class HabitPersistenceMapper {
    // Document MongoDB → Modèle Domaine
    public Habit toDomain(HabitDocument document) {
        Habit habit = new Habit(
                document.id.toString(),
                document.description,
                document.startDate,
                document.endDate
        );

        List<HabitCompletion> completions = document.completions
                .stream()
                .map(c -> new HabitCompletion(c.date))
                .collect(Collectors.toList());

        habit.setCompletions(completions);
        return habit;
    }

    // Modèle Domaine → Document MongoDB
    public HabitDocument toDocument(Habit habit) {
        HabitDocument document = new HabitDocument();
        document.description = habit.getDescription();
        document.startDate   = habit.getStartDate();
        document.endDate     = habit.getEndDate();

        List<HabitCompletionDocument> completions = habit.getCompletions()
                .stream()
                .map(c -> new HabitCompletionDocument(c.getDate()))
                .collect(Collectors.toList());

        document.completions = completions;
        return document;
    }
}
