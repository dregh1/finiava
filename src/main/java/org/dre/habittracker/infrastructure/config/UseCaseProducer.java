package org.dre.habittracker.infrastructure.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.dre.habittracker.domain.port.in.*;
import org.dre.habittracker.domain.port.out.HabitRepository;
import org.dre.habittracker.domain.service.CompleteHabitService;
import org.dre.habittracker.domain.service.CreateHabitService;
import org.dre.habittracker.application.service.HabitStatsService;

@ApplicationScoped
public class UseCaseProducer {

    @Inject
    HabitRepository habitRepository;

    private HabitStatsService habitStatsService;

    @Inject
    @Any
    Instance<PeriodStatsStrategy> strategyInstances;

    @PostConstruct
    void init() {
        this.habitStatsService = new HabitStatsService(habitRepository, strategyInstances);
    }

    @Produces
    @ApplicationScoped
    public CreateHabitUseCase createHabitUseCase() {
        return new CreateHabitService(habitRepository);
    }

    @Produces
    @ApplicationScoped
    public CompleteHabitUseCase completeHabitUseCase() {
        return new CompleteHabitService(habitRepository);
    }

    @Produces
    @ApplicationScoped
    public GetHabitStatsUseCase getHabitStatsUseCase() {
        return habitStatsService;
    }

    @Produces
    @ApplicationScoped
    public GetHabitStatsByDateRangeUseCase getHabitStatsByDateRangeUseCase() {
        return habitStatsService;
    }
}
