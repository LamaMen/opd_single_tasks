package observer

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals


class SubscriberTest {
    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @Test
    fun `subscriber must print message`() {
        val subscriber = Subscriber("Ilia")
        subscriber.display("Hello world")
        assertEquals("Ilia: Hello world", outputStreamCaptor.toString().trim())
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }
}