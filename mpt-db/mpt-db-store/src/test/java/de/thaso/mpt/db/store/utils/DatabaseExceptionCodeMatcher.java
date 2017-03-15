package de.thaso.mpt.db.store.utils;

import de.thaso.mpt.db.common.DatabaseError;
import de.thaso.mpt.db.common.DatabaseException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * DatabaseExceptionCodeMatcher
 *
 * @author thaler
 * @since 13.09.16
 */
public class DatabaseExceptionCodeMatcher extends TypeSafeMatcher<DatabaseException> {
    private DatabaseError errorCode;

    public DatabaseExceptionCodeMatcher(DatabaseError errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    protected boolean matchesSafely(final DatabaseException exception) {
        return exception.getDatabaseError() == errorCode;
    }

    public void describeTo(final Description description) {
        description.appendText("expects error code ")
                .appendValue(errorCode);
    }

    @Override
    protected void describeMismatchSafely(final DatabaseException exception,
                                          final Description mismatchDescription) {
        mismatchDescription.appendText("mpt ")
                .appendValue(exception.getDatabaseError());
    }
}
