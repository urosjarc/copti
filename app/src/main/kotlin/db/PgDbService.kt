package db

import PgService
import com.urosjarc.dbmessiah.Serializer
import com.urosjarc.dbmessiah.Service
import com.zaxxer.hikari.HikariConfig
import services.DbService

class PgDbService(
    val username: String,
    val password: String,
    val jdbcUrl: String
): DbService {

    val config = HikariConfig().apply {
        this.username = username
        this.password = password
        this.jdbcUrl = jdbcUrl
    }
    val service = PgService(conf = config, ser = Serializer(

    ))
}
