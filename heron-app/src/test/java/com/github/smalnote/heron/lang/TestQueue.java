package com.github.smalnote.heron.lang;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("ALL")
public class TestQueue {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldSuccessWhenUseDequeAsStack() {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            queue.addLast(i);
        }
        Integer expected = 9;
        while (!queue.isEmpty()) {
            assertEquals(expected--, queue.pollLast());
        }
        // use ArrayDeque as stack/queue is faster
    }

    public void should() {
        /**
         * TransferQueue
         * TransferQueue(java7引入)继承了BlockingQueue（BlockingQueue又继承了Queue）并扩展了一些新方法。生产者会一直阻塞直到所添加到队列的元素被某一个消费者所消费（不仅仅是添加到队列里就完事）。
         *
         * LinkedTransferQueue
         * LinkedTransferQueue实际上是ConcurrentLinkedQueue、SynchronousQueue（公平模式）和LinkedBlockingQueue的超集。而且LinkedTransferQueue更好用，因为它不仅仅综合了这几个类的功能，同时也提供了更高效的实现。
         *
         * 对比SynchronousQueue
         * SynchronousQueue使用两个队列（一个用于正在等待的生产者、另一个用于正在等待的消费者）和一个用来保护两个队列的锁。而LinkedTransferQueue使用CAS操作实现一个非阻塞的方法，这是避免序列化处理任务的关键。
         *
         * 使用场景
         * 当我们不想生产者过度生产消息时，TransferQueue可能非常有用，可避免发生OutOfMemory错误。在这样的设计中，消费者的消费能力将决定生产者产生消息的速度。
         *
         * 作者：go4it
         * 链接：https://www.jianshu.com/p/b3e97770c551
         * 来源：简书
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
    }

    @Test
    public void shouldThrowExceptionAddToBlockingQueue() {
        thrown.expect(IllegalStateException.class);
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>(10);
        for (int i = 0; i < 11; i++) {
            blockingDeque.add(i);
        }
    }

    @Test
    public void shouldReturnFalseWhenAddToFullBlockingQueue() {
        BlockingQueue blockingQueue = new LinkedBlockingQueue(10);
        for (int i = 0; i < 11; i++) {
            boolean offered = blockingQueue.offer(i);
            assertEquals(offered, i < 10);
        }
    }

    @Test
    public void shouldBlockWhenAddToTransferQueue() throws InterruptedException {
        TransferQueue<Integer> transferQueue = new LinkedTransferQueue<>();
        for (int i = 0; i < 11; i++) {
            transferQueue.transfer(i);
        }
    }
}
