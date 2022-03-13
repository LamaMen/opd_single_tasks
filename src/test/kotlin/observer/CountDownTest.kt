package observer

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CountDownTest {
    @Test
    fun `can add subscriber to countdown`() {
        val countDown = CountDown()
        val subscriber = Subscriber("Ilia")

        countDown.subscribe(subscriber)

        val listenersField = CountDown::class.java.getDeclaredField("listeners")
        listenersField.isAccessible = true
        val listeners = listenersField.get(countDown) as List<*>

        assertEquals(1, listeners.size)
        assertTrue { listeners.contains(subscriber) }
    }

    @Test
    fun `can't add one subscriber two times to countdown`() {
        val countDown = CountDown()
        val subscriber = Subscriber("Ilia")

        countDown.subscribe(subscriber)
        assertThrows<IllegalArgumentException> { countDown.subscribe(subscriber) }
    }

    @Test
    fun `can remove subscriber from countdown`() {
        val countDown = CountDown()
        val subscriber = Subscriber("Ilia")

        val listenersField = CountDown::class.java.getDeclaredField("listeners")
        listenersField.isAccessible = true
        listenersField.set(countDown, mutableListOf(subscriber))

        countDown.unsubscribe(subscriber)

        val listeners = listenersField.get(countDown) as List<*>

        assertEquals(0, listeners.size)
        assertFalse { listeners.contains(subscriber) }
    }

    @Test
    fun `can't remove non-existent subscriber from countdown`() {
        val countDown = CountDown()
        val subscriber = Subscriber("Ilia")

        assertThrows<IllegalArgumentException> { countDown.unsubscribe(subscriber) }
    }

    @Test
    fun `countdown must notify all subscribers after delay`() {
        val countDown = CountDown()
        countDown.start()

        val subscriber1 = mockk<Subscriber>()
        val subscriber2 = mockk<Subscriber>()
        every { subscriber1.display(any()) } answers {}
        every { subscriber2.display(any()) } answers {}

        val message = "Hello world"
        countDown.subscribe(subscriber1)
        countDown.subscribe(subscriber2)

        countDown.notifyAll(1000, message)

        verify(inverse = true) { subscriber1.display(message) }
        verify(inverse = true) { subscriber2.display(message) }

        Thread.sleep(1000)

        verify { subscriber1.display(message) }
        verify { subscriber2.display(message) }
        countDown.close()
    }

    @Test
    fun `countdown must can notify multiple events`() {
        val countDown = CountDown()
        countDown.start()

        val subscriber = mockk<Subscriber>()
        every { subscriber.display(any()) } answers {}

        val message = "Hello world"
        countDown.subscribe(subscriber)

        countDown.notifyAll(1000, message)
        countDown.notifyAll(1000, message)

        verify(inverse = true) { subscriber.display(message) }

        Thread.sleep(1000)
        verify(exactly = 1) { subscriber.display(message) }

        Thread.sleep(1000)
        verify(exactly = 2) { subscriber.display(message) }
        countDown.close()
    }
}