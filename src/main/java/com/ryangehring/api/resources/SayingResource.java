package com.ryangehring.api.resources;


import com.ryangehring.api.core.Saying;
import com.ryangehring.api.database.SayingDAO;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam ;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;


@Path("/sayings")
@Produces(MediaType.APPLICATION_JSON)
public class SayingResource {


    SayingDAO sayingDAO;

    public SayingResource(SayingDAO sayingDAO) {
        this.sayingDAO = sayingDAO;
    }

    @GET
    public List<Saying> getAll(){
        return sayingDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public Saying get(@PathParam("id") Integer id){
        return sayingDAO.find(id);
    }


}