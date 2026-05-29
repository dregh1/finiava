package org.dre.habittracker.infrastructure.adapter.in.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.habittracker.domain.port.in.CompleteHabitUseCase;
import org.dre.habittracker.domain.port.in.CreateHabitUseCase;
import org.dre.habittracker.domain.port.in.GetHabitStatsUseCase;
import org.dre.habittracker.infrastructure.adapter.in.web.dto.CompleteHabitRequest;
import org.dre.habittracker.infrastructure.adapter.in.web.dto.CreateHabitRequest;
import org.dre.habittracker.infrastructure.adapter.in.web.dto.HabitResponse;
import org.dre.habittracker.infrastructure.adapter.in.web.mapper.HabitWebMapper;

import java.util.List;
import java.util.stream.Collectors;
@Path("/habits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HabitResource {

    @Inject
    CreateHabitUseCase createHabitUseCase;

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

    // Récupérer toutes les habitudes
    @GET
    public List<HabitResponse> getAll() {
        return getHabitStatsUseCase.getStats("ALL")
                .stream()
                .map(habitWebMapper::toResponse)
                .collect(Collectors.toList());
    }
}