package services

import de.topobyte.osm4j.core.access.OsmIterator
import de.topobyte.osm4j.core.model.iface.EntityType
import de.topobyte.osm4j.core.model.iface.OsmNode
import de.topobyte.osm4j.core.model.iface.OsmRelation
import de.topobyte.osm4j.core.model.iface.OsmWay
import de.topobyte.osm4j.xml.dynsax.OsmXmlIterator
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
import java.io.File
import java.io.InputStream


class Map(path: String) {
    val resource = this::class.java.getResource(path)!!

    val linesCord = mutableListOf<MutableList<List<Double>>>()
    val nodesCord = mutableListOf<List<Double>>()

    fun parse() {
        //Key, Number of occurences + unique values
        val nodeTags = mutableMapOf<String, Pair<Int, MutableSet<String>>>()
        val wayTags = mutableMapOf<String, Pair<Int, MutableSet<String>>>()

        val input: InputStream = resource.openStream()
        val iterator: OsmIterator = OsmXmlIterator(input, false)

        val nodes = mutableMapOf<Long, OsmNode>()
        val ways = mutableMapOf<Long, OsmWay>()

        for (container in iterator) {
            when (container.type) {
                EntityType.Node -> {
                    val node = container.entity as OsmNode

                    val nodeTags = mutableMapOf<String, String>()
                    repeat(node.numberOfTags) {
                        val tag = node.getTag(it) ?: throw Exception("Could not get tag $it from node: $node")
                        nodeTags[tag.key] = tag.value
                    }

                    nodes[node.id] = node
                }

                EntityType.Way -> {
                    val way = container.entity as OsmWay

                    val wayTags = mutableMapOf<String, String>()
                    repeat(way.numberOfTags) {
                        val tag = way.getTag(it) ?: throw Exception("Could not get tag $it from way: $way")
                        wayTags[tag.key] = tag.value
                    }

                    wayTags["highway"] ?: continue
                    ways[way.id] = way
                }

                EntityType.Relation -> {
                    val rel = container.entity as OsmRelation
                    println(rel)
                }

                null -> continue
            }
        }

        ways.forEach { wid, way ->
            linesCord.add(mutableListOf())
            repeat(way.numberOfNodes) {
                val node = nodes[way.getNodeId(it)] ?: throw Exception("Could not get id")
                linesCord.last().add(listOf(node.latitude, node.longitude))
                nodesCord.add(listOf(node.latitude, node.longitude))
            }
        }
    }
}

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
                call.respond(map.linesCord)
            }
            get("/points") {
                call.respond(map.nodesCord)
            }
        }
    }.start(wait = true)
}
