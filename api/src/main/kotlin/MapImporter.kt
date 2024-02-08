import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun main() {
    val map = Map("/Slovenija_Ljubljana_Trnovo.osm")
    map.parse()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(CORS) {
            this.allowHeader(HttpHeaders.ContentType)
            this.allowHeader(HttpHeaders.Authorization)
            this.anyHost()
            this.allowMethod(HttpMethod.Post)
            this.allowMethod(HttpMethod.Put)
            this.allowMethod(HttpMethod.Get)
            this.allowMethod(HttpMethod.Delete)
        }

        routing {
            get("/lines") {
                call.respond(map.highwayLines)
            }
            get("/points") {
                call.respond(map.nodesCord)
            }
            get("/posts") {
                call.respond(map.postsNodes)
            }
        }
    }.start(wait = true)
}
