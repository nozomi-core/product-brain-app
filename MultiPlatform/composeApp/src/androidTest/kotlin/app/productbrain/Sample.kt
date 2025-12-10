import app.productbrain.common.Maybe
import app.productbrain.common.unwrap

class TestKoinAndroid {
    //TODO: Test android koin dependancies

    suspend fun doSomething() {

        Maybe.of("12.90")
            .then(::processAge)
            .unwrap { it ->
                ""
                1
            }.then { result ->

            }

    }

    suspend fun processAge(age: String): Maybe<Int> = Maybe.tryMaybe {
        age.toInt()
    }

    suspend fun append(age: Maybe<Int>): Maybe<AppendString> {
        return age.then { value ->
            AppendString("$value-append")
        }
    }
}


data class AppendString(val popcorn: String)
