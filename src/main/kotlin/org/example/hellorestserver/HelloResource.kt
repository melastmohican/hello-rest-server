package org.example.hellorestserver

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/hello")
class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getMessage(@QueryParam("name") name: String): Response {
        println("Processing request...")
        return Response.ok(mapOf("msg" to "Hello $name! :)"), MediaType.APPLICATION_JSON).build()
    }
}