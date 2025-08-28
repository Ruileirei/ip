public class JeromeException extends Exception {
    public JeromeException(String message) {
        super(message);
    }
    //Task used is wrong/invalid
    public static class InvalidTaskTypeException extends JeromeException {
        public InvalidTaskTypeException() {
            super("Oh deary me... I'm sorry, but I don't know what task this is :-(");
        }
    }

    //task does not have the required fields
    public static class EmptyTaskException extends JeromeException {
        public final String type;

        public EmptyTaskException(String type) {
            super("My bad g, the description of "
                    + type + " cannot be empty!");
            this.type = type;
        }
    }

    //task fields are not correct
    public static class WrongfulArgumentException extends JeromeException {
        public final String type;
        public final String from;
        public final String to;
        public final String by;

        public WrongfulArgumentException(String type, String from, String to, String by) {
            super(buildMessage(type, from, to, by));
            this.type = type;
            this.from = from;
            this.to = to;
            this.by = by;
        }

        private static String buildMessage(String type, String from, String to, String by) {
            StringBuilder sb = new StringBuilder("Aww, " + type + " does not take in the argument(s) ");
            boolean bool = false;


            if (by != null) {
                sb.append("'by'");
                bool = true;
            }
            if (from != null) {
                if (bool) sb.append(", ");
                sb.append("'from'");
                bool = true;
            }
            if (to != null) {
                if (bool) sb.append(", ");
                sb.append("'to'");
            }
            return sb.toString();
        }
    }

    //tasks fields are invalid
    public static class InvalidTaskDeclarationException extends JeromeException {
        public final String type;

        public InvalidTaskDeclarationException(String type) {
            super("Im soooo Sorry... " + type + " is missing fields...");
            this.type = type;
        }
    }
}
