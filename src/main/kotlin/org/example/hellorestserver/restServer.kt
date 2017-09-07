package org.example.hellorestserver

import org.glassfish.grizzly.http.server.HttpServer
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory
import org.glassfish.jersey.jackson.JacksonFeature
import org.glassfish.jersey.server.ResourceConfig
import javax.ws.rs.ProcessingException
import javax.ws.rs.core.UriBuilder


fun main(args: Array<String>) {
    val HOST = "localhost"
    val PORT = 9000
    val baseUri = UriBuilder.fromUri("http://$HOST/").port(PORT).build()
    var server: HttpServer? = null

    try {
        server = GrizzlyHttpServerFactory.createHttpServer(baseUri, createConfiguration())
        println("REST Server Address: $HOST:$PORT")
    } catch (ex: ProcessingException) {
        println("Server Error: ${ex.message}")
        println("Exiting REST server...")
        server?.shutdown()
    }
}

private fun createConfiguration(): ResourceConfig {
    val config = ResourceConfig(HelloResource::class.java)

    // Optional but good practise to manually specify the JSON mapping implementation to use.
    config.packages("org.example.hellorestserver").register(JacksonFeature::class.java)
    return config
}