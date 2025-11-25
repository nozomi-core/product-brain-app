import app.productbrain.data.lang.Maybe
import kotlinx.coroutines.runBlocking

class TestKoinAndroid {
    //TODO: Test android koin dependancies

    suspend fun doSomething() {

        val age = Maybe.of("12.90")
            .pipe(::processAge)
            .pipe(::append)
            .onSuccess { appened ->

            }
    }

    suspend fun processAge(age: Maybe<String>): Maybe<Int> {
        return age.then { value ->
            value.toInt()
        }
    }

    suspend fun append(age: Maybe<Int>): Maybe<AppendString> {
        return age.then { value ->
            AppendString("$value-append")
        }
    }
}


data class AppendString(val popcorn: String)
