package services

import de.topobyte.osm4j.core.access.OsmIterator
import de.topobyte.osm4j.core.model.iface.EntityType
import de.topobyte.osm4j.core.model.iface.OsmNode
import de.topobyte.osm4j.core.model.iface.OsmRelation
import de.topobyte.osm4j.core.model.iface.OsmWay
import de.topobyte.osm4j.xml.dynsax.OsmXmlIterator
import java.io.InputStream

class Map(path: String) {
    val resource = this::class.java.getResource(path)!!

    val nodesCord = mutableListOf<List<Double>>()
    val highwayLines = mutableListOf<MutableList<List<Double>>>()
    val postsNodes = mutableListOf<List<Double>>()

    fun parse() {
        //Key, Number of occurences + unique values
        val input: InputStream = resource.openStream()
        val iterator: OsmIterator = OsmXmlIterator(input, false)

        val nodes = mutableMapOf<Long, OsmNode>()
        val highways = mutableMapOf<Long, OsmWay>()

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

                    val waysKeys = setOf(
                        "highway",
                        "footway",
                        "sidewalk",
                        "cycleway",
                        "busway",
                        "bicycle_road",
                        "bus_bay",
                        "oneway",
                        "oneway:bicycle",
                        "sac_scale",
                        "bridge"
                    )
                    val waysValues = setOf(
                        "bridge",
                        "bicycle",
                        "foot",
                        "hiking",
                    )

                    if (wayTags.keys.toSet().intersect(waysKeys).isNotEmpty() || wayTags.values.toSet().intersect(waysValues).isNotEmpty())
                        highways[way.id] = way
                }

                EntityType.Relation -> {
                    val rel = container.entity as OsmRelation
                }

                null -> continue
            }
        }

        /**
         * Highways
         */
        highways.forEach { wid, way ->
            highwayLines.add(mutableListOf())
            repeat(way.numberOfNodes) {
                val node = nodes[way.getNodeId(it)] ?: throw Exception("Could not get id")
                highwayLines.last().add(listOf(node.latitude, node.longitude))
                nodesCord.add(listOf(node.latitude, node.longitude))
            }
        }
    }
}
