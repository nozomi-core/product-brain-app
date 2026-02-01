package app.productbrain.data.provider

import androidx.room.immediateTransaction
import androidx.room.useWriterConnection
import app.productbrain.data.database.AppDatabase
import app.productbrain.common.tryMaybe

class TransactionProvider(
    private val database: AppDatabase
) {
    suspend fun <T> tryTransaction(block: suspend () -> T) = tryMaybe {
        database.useWriterConnection { connection ->
            connection.immediateTransaction {
                block()
            }
        }
    }
}