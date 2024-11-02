package tester;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;


public class TestArrayDequeEC {
    @Test
    public void test_removeFirst() {
        ArrayDequeSolution<Integer> L1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> L2 = new StudentArrayDeque<>();
        int N = 500;
        for (int i = 0; i <= N; i++) {
            int random = StdRandom.uniform(0, 3);
            if (random == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L1.addFirst(randVal);
                L2.addFirst(randVal);
                System.out.println("addFirst(" + randVal + ")");
            } else if (random == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L1.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }else if (L2.isEmpty() == false && random == 2) {
                int actual = L2.removeFirst();
                int expected = L1.removeFirst();
                assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                        + " not equal to " + expected + "!", expected, actual);
            }
        }
    }

    @Test
    public void test_removeLast() {
        ArrayDequeSolution<Integer> L1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> L2 = new StudentArrayDeque<>();
        int N = 500;
        for (int i = 0; i <= N; i++) {
            int random = StdRandom.uniform(0, 3);
            if (random == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L1.addFirst(randVal);
                L2.addFirst(randVal);
                System.out.println("addFirst(" + randVal + ")");
            } else if (random == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L1.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (L2.isEmpty()) {
                continue;
            } else if (L2.isEmpty() == false && random == 2) {
                //removeLast
                int actual = L2.removeLast();
                int expected = L1.removeLast();
                assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                        + " not equal to " + expected + "!", expected, actual);
            }
        }
    }
}