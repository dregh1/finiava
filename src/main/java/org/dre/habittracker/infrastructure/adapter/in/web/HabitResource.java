package org.dre.habittracker.infrastructure.adapter.in.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.habittracker.domain.model.Habit;
import org.dre.habittracker.domain.port.in.CompleteHabitUseCase;
import org.dre.habittracker.domain.port.in.CreateHabitUseCase;
import org.dre.habittracker.domain.port.in.GetHabitStatsByDateRangeUseCase;
import org.dre.habittracker.domain.port.in.GetHabitStatsUseCase;
import org.dre.habittracker.domain.port.out.HabitRepository;
import org.dre.habittracker.infrastructure.adapter.in.web.dto.CompleteHabitRequest;
import org.dre.habittracker.infrastructure.adapter.in.web.dto.CreateHabitRequest;
import org.dre.habittracker.infrastructure.adapter.in.web.dto.HabitResponse;
import org.dre.habittracker.infrastructure.adapter.in.web.mapper.HabitWebMapper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
@Path("/habits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HabitResource {

    @Inject
    CreateHabitUseCase createHabitUseCase;

    @Inject
    HabitRepository habitRepository;

    @Inject
    GetHabitStatsByDateRangeUseCase getHabitStatsByDateRangeUseCase;

    @Inject
    CompleteHabitUseCase completeHabitUseCase;

    @Inject
    GetHabitStatsUseCase getHabitStatsUseCase;

    @Inject
    HabitWebMapper habitWebMapper;

    // Créer une habitude
    @POST
    public Response create(CreateHabitRequest request) {
        createHabitUseCase.execute(
                request.description,
                request.startDate,
                request.endDate
        );
        return Response.status(Response.Status.CREATED).build();
    }

    // Compléter une habitude
    @POST
    @Path("/{id}/complete")
    public Response complete(@PathParam("id") String id,
                             CompleteHabitRequest request) {
        completeHabitUseCase.execute(id, request.date);
        return Response.ok().build();
    }

    @GET
    @Path("/stats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStats(@QueryParam("period") String period) {
        try {
            List<Habit> stats = getHabitStatsUseCase.getStats(period);
            return Response.ok(
                    stats.stream()
                        .map(habitWebMapper::toResponse)
                        .collect(Collectors.toList())
            ).build();
        } catch (IllegalArgumentException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // Récupérer toutes les habitudes
    @GET
    public List<HabitResponse> getAll() {
            return habitRepository.findAll()
                    .stream()
                    .map(habitWebMapper::toResponse)
                    .toList();
//        return getHabitStatsUseCase.getStats("ALL")
//                .stream()
//                .map(habitWebMapper::toResponse)
//                .collect(Collectors.toList());
    }

    @GET
    @Path("/stats/range")
    public Response getStatsBetween(
            @QueryParam("start") String dateStart,
            @QueryParam("end") String dateEnd) {

        try {
            LocalDate start = LocalDate.parse(dateStart); // format : 2024-01-01
            LocalDate end   = LocalDate.parse(dateEnd);

            List<Habit> stats = getHabitStatsByDateRangeUseCase
                    .getStatsByDateRange(start, end);

            return Response.ok(
                    stats.
                    stream()
                    .map(habitWebMapper::toResponse)
                    .collect(Collectors.toList())
            ).build();

        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Format de date invalide. Utilisez : yyyy-MM-dd").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        }
    }
}