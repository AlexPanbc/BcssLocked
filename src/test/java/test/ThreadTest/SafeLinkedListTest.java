package test.ThreadTest;


import org.junit.Test;

/**
 * 多线程条件下测试LinkedList存取数据是否安全
 */
public class SafeLinkedListTest {

    private SafeLinkedList<String> data = new SafeLinkedList<>(10);

    @Test
    public void putGetTest() throws InterruptedException {

        //add test
        Thread t1 = new Thread(new OperAddThread());
        Thread t2 = new Thread(new OperAddThread());
        Thread t3 = new Thread(new OperAddThread());

        Thread getThread = new Thread(new OperGetThread());
        getThread.setName("getThread");

        t1.start();
        t2.start();
        t3.start();
        getThread.start();

        t1.join();
        t2.join();
        t3.join();
        getThread.join();

        System.out.println(data.size());

    }


    class OperAddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                String addData=Thread.currentThread().getName()+"|"+String.valueOf(i);
                data.add(addData);
                System.out.println("add size=" + data.size());
                System.out.println(Thread.currentThread().getName() + " addData=" + addData);
            }
        }
    }

    class OperGetThread implements Runnable {
        @Override
        public void run() {
            int i=11;
            while (i>=0) {
                i--;
                String value = data.get();
                System.out.println("get size=" + data.size());
                System.out.println("get string--" + " ;value=" + value);
            }
        }
    }
}
