package org.dre.habittracker.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habit {
    private String id;
    private String userId;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<HabitCompletion> completions = new ArrayList<>();

    public Habit() {}

    public Habit(String id, String description, String userId,
                 LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Habit(String description,String userId,
                 LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Habit create(String userId, String description, LocalDate startDate, LocalDate endDate) {
        Habit habit = new Habit();
        habit.userId = userId;
        habit.description = description;
        habit.startDate = startDate;
        habit.endDate = endDate;
        return habit;
    }

    // Getters / Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public List<HabitCompletion> getCompletions() { return completions; }
    public void setCompletions(List<HabitCompletion> completions) {
        this.completions = completions;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", completions=" + completions +
                '}';
    }
}
