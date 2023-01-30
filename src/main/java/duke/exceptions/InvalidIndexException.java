package duke.exceptions;

public class InvalidIndexException extends DukeExceptions {
    private static final String MESSAGE = "⚠ oops...there's no task with this number\ntry asking for 'list' to check task " +
            "numbers\n";
    public InvalidIndexException() {
        super(MESSAGE);
    }
}