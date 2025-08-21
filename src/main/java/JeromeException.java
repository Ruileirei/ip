public class JeromeException {
    //Task used is wrong/invalid
    public static class InvalidTaskTypeException extends Exception {
        public InvalidTaskTypeException() {
            super("Invalid or Wrong task");
        }
    }

    //task does not have the required fields
    public static class EmptyTaskException extends Exception {
        public final String type;
        public EmptyTaskException(String type) {
            super("Task is incomplete");
            this.type = type;
        }
    }

    //task fields are not correct
    public static class WrongfulArgumentException extends Exception {
        public final String type;
        public final String from;
        public final String to;
        public final String by;

        public WrongfulArgumentException(String type, String from, String to, String by) {
            super();
            this.type = type;
            this.from = from;
            this.to = to;
            this.by = by;
        }

        @Override
        public String getMessage() {
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
    public static class InvalidTaskDeclarationException extends Exception {
        public final String type;

        public InvalidTaskDeclarationException(String type) {
            super("Task fields are not filled in correctly");
            this.type = type;
        }
    }
}
