package com.sample.plugins

import com.sample.model.BaseModel
import com.sample.model.ResponseModel
import com.sample.service.BaseService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.configureRouting() {
    val baseService: BaseService = get()
    routing {
        post("/createUser") {
            try {
                val user = call.receive<BaseModel>()
                println(user.name)
                val userData = baseService.baseOperation(user)
                val responseMessage = ResponseModel(
                    true,
                    201,
                    "User Created Successfully",
                    userData
                )
                call.respond(HttpStatusCode.Created, responseMessage)
            } catch (e: Exception) {
                val responseMessage = ResponseModel<Nothing>(
                    false,
                    500,
                    "Failed to save user: ${e.message}",
                )
                call.respond(HttpStatusCode.InternalServerError, responseMessage)
            }
        }
    }
}
