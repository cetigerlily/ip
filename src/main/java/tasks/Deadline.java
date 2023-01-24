package tasks;
/**
 * Deadlines are tasks which need to be done by a certain date/time
 */
public class Deadline extends Task {
    private String when;
    public Deadline(String description, String when) {
        super(description);
        this.when = when;
    }

    @Override
    public String getDataString() {
        return "D | " + super.getDataString() + " | " + when;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + when + ")";
    }
}