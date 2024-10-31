package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing test1 = new AListNoResizing();
        BuggyAList test2 = new BuggyAList();
        test1.addLast(4); test2.addLast(4);
        test1.addLast(5); test2.addLast(5);
        test1.addLast(6); test2.addLast(6);
        assertEquals(test1.size(), test2.size());
        assertEquals(test1.removeLast(), test2.removeLast());
        assertEquals(test1.removeLast(), test2.removeLast());
        assertEquals(test1.removeLast(), test2.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L1 = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L1.addLast(randVal);
                L2.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                assertEquals(L1.size(), L2.size());
            }else if(L1.size() == 0){
                continue;
            } else if (operationNumber == 2){
                assertEquals(L1.getLast(), L2.getLast());
            }else if(operationNumber == 3){
                assertEquals(L1.removeLast(), L2.removeLast());
            }
        }
    }
}
