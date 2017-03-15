package de.thaso.mpt.db.it.utils;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * CauseMatcher
 *
 * @author thaler
 * @since 14.09.16
 */
public class SecondCauseMatcher extends TypeSafeMatcher<Throwable> {

    private final Class<? extends Throwable> type;
    private final String[] expectedMessages;

    public SecondCauseMatcher(Class<? extends Throwable> type, String... expectedMessages) {
        this.type = type;
        this.expectedMessages = expectedMessages;
    }

    @Override
    protected boolean matchesSafely(Throwable item) {
        Throwable throwable = item.getCause();
        if(type.isAssignableFrom(throwable.getClass())) {
            for(String message : expectedMessages) {
                if (throwable.getMessage().contains(message)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("expects type ")
                .appendValue(type)
                .appendText(" and a message ")
                .appendValue(expectedMessages);
    }
}
