package com.renanwillian.rest;


import com.renanwillian.service.ProducerService;
import com.renanwillian.service.dto.ProducerDTO;
import com.renanwillian.util.RestUtils;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/producer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Produtor")
public class ProducerEndpoint {

    @Inject
    ProducerService producerService;

    @GET
    public Response list() {
        return RestUtils.okOrNoContent(producerService.list());
    }

    @GET
    @Path("/{producerId:[0-9]+}")
    public Response find(@PathParam Long producerId) {
        return Response.ok(producerService.find(producerId)).build();
    }

    @POST
    public Response persist(@Valid ProducerDTO producer) {
        return Response.ok(producerService.persist(producer)).build();
    }

    @PUT
    @Path("/{producerId:[0-9]+}")
    public Response persist(@PathParam Long producerId, @Valid ProducerDTO producer) {
        producer.setProducerId(producerId);
        return Response.ok(producerService.persist(producer)).build();
    }

    @DELETE
    @Path("/{producerId:[0-9]+}")
    public Response delete(@PathParam Long producerId) {
        try {
            producerService.delete(producerId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/award-interval")
    @Operation(description = "Intervalo de prêmios")
    public Response getProducerAwardInterval() {
        return Response.ok(producerService.getProducerAwardInterval()).build();
    }
}
