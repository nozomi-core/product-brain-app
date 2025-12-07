package app.productbrain.data.provider

import androidx.room.immediateTransaction
import androidx.room.useWriterConnection
import app.productbrain.data.database.AppDatabase
import app.productbrain.common.Maybe

class TransactionProvider(
    val database: AppDatabase
) {
    suspend fun <T> withTransaction(block: suspend () -> T): Maybe<T> {
        return database.useWriterConnection { connection ->
            connection.immediateTransaction {
                Maybe.tryResult(block)
            }
        }
    }
}