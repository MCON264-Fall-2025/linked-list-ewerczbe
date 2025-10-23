package linkedlist;

import org.apache.commons.lang3.NotImplementedException;
import support.CycleInfo;
import support.LLNode;

public class LinkedListCycleAnalyzer<T> {
    public static <T> CycleInfo detectCycleInfo(LLNode<T> head) {
        if (head == null)
            return new CycleInfo(0, 0);
        LLNode<T> slow = head;
        LLNode<T> fast = head;

        // Step 1: Detect cycle using Floyd’s algorithm
        while (fast != null && fast.getLink() != null) {
            slow = slow.getLink();
            fast = fast.getLink().getLink();

            if (slow == fast) {
                // Step 2: Find cycle length
                int cycleLength = 1;
                LLNode<T> temp = slow.getLink();
                while (temp != slow) {
                    temp = temp.getLink();
                    cycleLength++;
                }

                // Step 3: Find entry index
                LLNode<T> ptr1 = head;
                LLNode<T> ptr2 = head;
                for (int i = 0; i < cycleLength; i++) {
                    ptr2 = ptr2.getLink();
                }

                int entryIndex = 0;
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.getLink();
                    ptr2 = ptr2.getLink();
                    entryIndex++;
                }

                return new CycleInfo(entryIndex, cycleLength);
            }
        }

        return new CycleInfo(-1, 0);
    }
}
