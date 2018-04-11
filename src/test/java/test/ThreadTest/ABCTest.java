package test.ThreadTest;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2017/9/14
 * \* Time: 下午5:10
 * \* Description:
 * \
 */
public class ABCTest {
    Object lockA=new Object();
    Object lockB=new Object();

    boolean b_A=true;
    boolean b_B=false;
    boolean b_C=false;


    @Test
    public void testprint() throws InterruptedException {

        Thread t_A=new Thread(new PrintClass("A"));
        Thread t_B=new Thread(new PrintClass("B"));
        Thread t_C=new Thread(new PrintClass("C"));



        t_A.start();
        t_B.start();
        t_A.join();
        t_B.join();




    }

    class PrintClass implements Runnable{
        String name;
        PrintClass(String name){
            this.name=name;
        }

        @Override
        public void run() {



        }
    }
}
