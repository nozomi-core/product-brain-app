package app.productbrain.data.provider

import androidx.room.RoomDatabase
import androidx.room.immediateTransaction
import androidx.room.useWriterConnection
import app.productbrain.data.lang.DataResult

class TransactionProvider(
    val database: RoomDatabase
) {
    suspend fun <T> withTransaction(block: suspend () -> T): DataResult<T> {
        return database.useWriterConnection { connection ->
            connection.immediateTransaction {
                DataResult.tryResult(block)
            }
        }
    }
}