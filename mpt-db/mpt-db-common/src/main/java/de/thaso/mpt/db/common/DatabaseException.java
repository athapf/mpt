package de.thaso.mpt.db.common;

/**
 * ApplicationException
 *
 * @author thaler
 * @since 12.09.16
 */
public class DatabaseException extends RuntimeException {

    private DatabaseError databaseError;

    public DatabaseException(final DatabaseError databaseError, final String message) {
        super(message);
        this.databaseError = databaseError;
    }

    public DatabaseError getDatabaseError() {
        return databaseError;
    }
}
