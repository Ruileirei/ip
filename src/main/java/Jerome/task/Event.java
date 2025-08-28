package Jerome.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormat);
        this.to = LocalDateTime.parse(to, inputFormat);
    }

    public String getFromRaw() {
        return from.format(inputFormat);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public String getToRaw() {
        return to.format(inputFormat);
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatus() + "] " + description
                + "(from: " + from.format(displayFormat)
                + " to: " + to.format(displayFormat) + ")";
    }

}
