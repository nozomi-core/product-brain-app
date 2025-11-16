package app.productbrain.data.repository.login

import app.productbrain.data.lang.Forest
import app.productbrain.data.provider.ClockProvider
import app.productbrain.data.provider.TransactionProvider

class LoginRepository(
    private val clock: ClockProvider,
    private val transaction: TransactionProvider,
    private val dao: LoginDao
) {
    suspend fun insertLogin() = transaction.withTransaction {
        val time = clock.now().utcTimeMillis
        val model = LoginEntryEntity(
            name = "example",
            instant =  time
        )
        Forest.d("Welcome to the insert login: $time")
        dao.insert(model)
        model
    }
}