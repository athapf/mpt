package de.thaso.mpt.app.rf;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SimpleJavaTestLibrary
 *
 * @author thaler
 * @since 2017-06-27
 */
public class SimpleKeywordLibrary {

    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    private Queue<String> testQueue;

    public void createAnEmptyQueue() {
        testQueue = new LinkedList<String>();
    }

    public void addAnElement(final String element) {
        testQueue.add(element);
    }

    public void pullElement() {
        testQueue.poll();
    }

    public void theFirstElementShouldBe(final String result) throws Exception {
        final String lastElement = testQueue.peek();
        if (!result.equals(lastElement)) {
            throw new Exception("Wrong element: " + lastElement);
        }
    }
}
