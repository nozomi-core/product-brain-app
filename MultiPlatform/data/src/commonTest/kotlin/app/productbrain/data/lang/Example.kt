package app.productbrain.data.lang

import app.productbrain.common.UlidFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class UlidFactoryTest {

    //01KAZV59DH0W6QCY9GPP67YBVA : Mine
    //01KAZV5XKSXQMYD0F61DZHP68G

    @Test
    fun doThis() {
        val ulid = UlidFactory.create()
        assertEquals("1", ulid.value)
    }
}