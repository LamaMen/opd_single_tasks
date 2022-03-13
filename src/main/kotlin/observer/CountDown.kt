package observer

import java.util.concurrent.ConcurrentLinkedQueue

class CountDown : Thread() {
    private val eventQueue: ConcurrentLinkedQueue<CountDownEvent> = ConcurrentLinkedQueue()
    private val listeners = mutableListOf<Subscriber>()

    private data class CountDownEvent(val time: Long, val message: String)

    override fun run() {
        while (!currentThread().isInterrupted) {
            if (eventQueue.isNotEmpty()) {
                val nextEvent = eventQueue.remove()
                sleep(nextEvent.time)
                notifySubscribers(nextEvent.message)
            }
        }
    }

    fun subscribe(subscriber: Subscriber) {
        if (listeners.contains(subscriber)) {
            throw IllegalArgumentException("This subscriber already subscribed")
        }

        listeners.add(subscriber)
    }

    fun unsubscribe(subscriber: Subscriber) {
        if (!listeners.contains(subscriber)) {
            throw IllegalArgumentException("This subscriber not subscribed")
        }
        listeners.remove(subscriber)
    }

    fun notifyAll(time: Long, message: String) {
        eventQueue.add(CountDownEvent(time, message))
    }

    fun close() = interrupt()

    private fun notifySubscribers(message: String) {
        for (listener in listeners) {
            listener.display(message)
        }
    }
}
