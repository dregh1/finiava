package org.dre.habittracker.infrastructure.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.dre.habittracker.domain.port.in.CompleteHabitUseCase;
import org.dre.habittracker.domain.port.in.CreateHabitUseCase;
import org.dre.habittracker.domain.port.in.GetHabitStatsUseCase;
import org.dre.habittracker.domain.port.out.HabitRepository;
import org.dre.habittracker.domain.service.CompleteHabitService;
import org.dre.habittracker.domain.service.CreateHabitService;
import org.dre.habittracker.domain.service.HabitStatsService;

@ApplicationScoped
public class UseCaseProducer {

    @Inject
    HabitRepository habitRepository;

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
        return new HabitStatsService(habitRepository);
    }
}
