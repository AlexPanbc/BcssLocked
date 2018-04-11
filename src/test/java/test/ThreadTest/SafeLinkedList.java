package test.ThreadTest;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用LinkedList实现安全队列
 *
 */
public class SafeLinkedList<E> {


    private LinkedList<E> data = new LinkedList<>();
    private int totalSize;

    public SafeLinkedList(int totalSize) {
        this.totalSize = totalSize;

    }

    /**
     * 当队列已满时候，阻塞，直到取走数据为止
     * @param e
     */
    public void add(E e) {
        synchronized (data) {
            if (data.size() == totalSize) {
                try {
                    data.wait();
                } catch (InterruptedException e1) {
                    Thread.interrupted();
                    data.notifyAll();
                    System.out.println("队列已满啦！");
                }

            }
            data.add(e);
            data.notifyAll();

        }

    }

    /**
     * 当队列为空时候，阻塞等待
     * @return
     */
    public E get() {
        synchronized (data) {

            if (data.isEmpty()) {
                try {
                    data.wait();
                } catch (InterruptedException e) {
                    Thread.interrupted();
                    data.notifyAll();
                }
            }

            E e = data.getFirst();//获取队列头元素
            data.removeFirst();//移除对头元素
            data.notifyAll();
            return e;
        }
    }

    public synchronized int size() {
        return data.size();
    }
}
