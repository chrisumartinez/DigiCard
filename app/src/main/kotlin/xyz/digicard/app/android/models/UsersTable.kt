package xyz.digicard.app.android.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.jayrave.falkon.dao.Dao
import com.jayrave.falkon.dao.DaoImpl
import com.jayrave.falkon.engine.DefaultEngine
import com.jayrave.falkon.engine.Type
import com.jayrave.falkon.engine.android.sqlite.AndroidSqliteEngineCore
import com.jayrave.falkon.mapper.*
import com.jayrave.falkon.mapper.lib.SimpleIdExtractFromHelper
import com.jayrave.falkon.sqlBuilders.sqlite.*
import xyz.digicard.app.android.App
import java.util.*

class UsersTable private constructor(context: Context) :
        BaseEnhancedTable<User, UUID, Dao<User, UUID>>(
                name = "users",
                configuration = tableConfig(context),
                createTableSqlBuilder = SqliteCreateTableSqlBuilder()) {

    override val dao: Dao<User, UUID> = DaoImpl(
            table = this,
            insertSqlBuilder = SqliteInsertSqlBuilder(),
            updateSqlBuilder = SqliteUpdateSqlBuilder(),
            deleteSqlBuilder = SqliteDeleteSqlBuilder(),
            insertOrReplaceSqlBuilder = SqliteInsertOrReplaceSqlBuilder(),
            querySqlBuilder = SqliteQuerySqlBuilder()
    )

    private val id = col(User::id, isId = true, isNonNull = true)
    private val firstName = col(User::firstName, isNonNull = true)
    private val lastName = col(User::lastName, isNonNull = true)
    private val email = col(User::email, isNonNull = true)
    private val phone = col(User::phone)
    private val photoUrl = col(User::photoUrl)
    private val linkedInUrl = col(User::linkedInUrl)


    private val simpleIdExtractor = SimpleIdExtractFromHelper(id)
    override fun <C> extractFrom(id: UUID, column: Column<User, C>): C {
        return simpleIdExtractor.extractFrom(id, column)
    }


    override fun create(value: Table.Value<User>): User {
        return User(
                id = value of id,
                firstName = value of firstName,
                lastName = value of lastName,
                email = value of email,
                phone = value of phone,
                photoUrl = value of photoUrl,
                linkedInUrl = value of linkedInUrl
        )
    }



    companion object {

        val instance by lazy {
            UsersTable(App.instance)
        }

        private fun tableConfig(context: Context): TableConfiguration {
            val config = TableConfigurationImpl(
                    typeTranslator = SqliteTypeTranslator(),
                    engine = DefaultEngine(AndroidSqliteEngineCore(sqliteOpenHelper(context)))
            )

            // To handle primitives (their boxed versions) & `String`s
            config.registerDefaultConverters()

            // To handle `UUID`s
            config.registerForNonNullValues(
                    clazz = UUID::class.java,
                    converter = object : Converter<UUID> {
                        override val dbType: Type = Type.STRING
                        override fun from(dataProducer: DataProducer): UUID {
                            return UUID.fromString(dataProducer.getString())
                        }

                        override fun to(value: UUID, dataConsumer: DataConsumer) {
                            dataConsumer.put(value.toString())
                        }
                    }
            )

            return config
        }

        private fun sqliteOpenHelper(context: Context): SQLiteOpenHelper {
            return object : SQLiteOpenHelper(context, "digicard", null, 1) {
                override fun onCreate(db: SQLiteDatabase) {
                    db.execSQL("CREATE TABLE users (id TEXT NOT NULL, first_name TEXT NOT NULL, last_name TEXT NOT NULL, email TEXT NOT NULL, phone TEXT, photo_url TEXT, linked_in_url TEXT, PRIMARY KEY (id))")
                }

                override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
                    throw UnsupportedOperationException("not implemented")
                }
            }
        }
    }
}