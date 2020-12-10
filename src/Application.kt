package com.example02

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.features.*
import org.slf4j.event.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        route( "/"){
            get{
                call.respond(ToDo.getAll())
            }

            get("{id}"){
                val id = call.parameters["id"]
                call.respond(ToDo.get(id.toString()))
            }

            post("/create"){
                val item = call.receive<Item>()
                call.respond(ToDo.pullTask(item.id.toString(), item))
            }

            delete("{id}") {
                val id = call.parameters["id"]
                call.respond(ToDo.delete(id.toString()))
            }

            delete(){
                call.respond(ToDo.deleteAll())
            }
        }
    }
}
