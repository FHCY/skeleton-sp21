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
        int randVal = StdRandom.uniform(0, 100);
        String msg = "";
        for (int i = 0; i <= N; i++) {
            int random = StdRandom.uniform(0, 3);
            if (random == 0) {
                // addFirst
                L1.addFirst(randVal);
                L2.addFirst(randVal);
                System.out.println("addFirst(" + randVal + ")");
            } else if (random == 1) {
                // addLast
                L1.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            }else if (L2.size() != 0 && random == 2) {
                if(randVal <50){
                    msg +="removeFirst()\n";
                    assertEquals(msg, L1.removeFirst(), L2.removeFirst());
                } else {
                    msg +="removeLast()\n";
                    assertEquals(msg, L1.removeLast(), L2.removeLast());
                }
            }
        }
    }
}