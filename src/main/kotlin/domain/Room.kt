package domain

/**
 * Problem fact (nekaj kar se ne bo spreminjalo),
 * neka stavba ima fiksno kolicino prostorov kjer se lahko izvajajo predavanja
 */
class Room(val name: String) {
    override fun toString(): String = this.name
}
