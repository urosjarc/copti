package db

import com.urosjarc.dbmessiah.domain.table.Table
import com.urosjarc.dbmessiah.impl.postgresql.PgSchema
import domain.*

class MappingSchema: PgSchema(
    name = "mapping",
    tables = listOf(
        Table(primaryKey = Objekt::id, foreignKeys = listOf(Objekt::zeljevid to Zemljevid::class)),
        Table(primaryKey = Paket::id, foreignKeys = listOf(Paket::destinacija to Objekt::class)),
        Table(primaryKey = Postar::id),
        Table(primaryKey = Vektor::id, foreignKeys = listOf(Vektor::objekt to Objekt::class)),
        Table(primaryKey = Vozilo::id),
        Table(primaryKey = Zemljevid::id),
    )
)
