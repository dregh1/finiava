package org.dre.habittracker.infrastructure.adapter.in.rest;


import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.dre.habittracker.application.dto.AuthResponse;
import org.dre.habittracker.application.dto.LoginRequest;
import org.dre.habittracker.application.dto.RegisterRequest;
import org.dre.habittracker.domain.model.AuthResult;
import org.dre.habittracker.domain.model.Token;
import org.dre.habittracker.domain.port.in.LoginUseCase;
import org.dre.habittracker.domain.port.in.RegisterUseCase;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    @Inject
    RegisterUseCase registerUseCase;

    @Inject
    LoginUseCase loginUseCase;

    @POST
    @Path("/register")
    public Response register(@Valid RegisterRequest request) {
        try {
            AuthResult result = registerUseCase.register(
                    request.getName(),
                    request.getEmail(),
                    request.getPassword()
            );

            AuthResponse response = AuthResponse.of(
                    result.getToken().getAccessToken(),
                    result.getToken().getExpiresIn(),
                    AuthResponse.UserInfo.of(
                            result.getUser().getId(),
                            result.getUser().getName(),
                            result.getUser().getEmail()
                    )
            );

            return Response.status(Response.Status.CREATED)
                    .entity(response)
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest request) {
        try {
            AuthResult result = loginUseCase.login(
                    request.getEmail(),
                    request.getPassword()
            );

            AuthResponse response = AuthResponse.of(
                    result.getToken().getAccessToken(),
                    result.getToken().getExpiresIn(),
                    AuthResponse.UserInfo.of(
                            result.getUser().getId(),
                            result.getUser().getName(),
                            result.getUser().getEmail()
                    )
            );

            return Response.ok(response).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        }
    }

    public static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
