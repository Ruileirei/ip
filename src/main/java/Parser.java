public class Parser {
    public static String getTaskType(String input) {
        return input.split(" ", 2)[0];
    }

    public static String getDescription(String input) {
        try {
            String split = input.split(" ", 2)[1];
            return split;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static String[] splitBySlash(String input) {
        return input.split("/", 2);
    }

    public static int getSize(String input) throws NumberFormatException {
        String[] split = input.split(" ");
        return Integer.parseInt(split[1]) - 1;
    }

    public static String[] extractFromTo(String input) throws JeromeException.InvalidTaskDeclarationException {
        int fromIndex = input.indexOf("from ");
        int toIndex = input.indexOf("/to ");
        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            throw new JeromeException.InvalidTaskDeclarationException("event");
        }
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 4).trim();
        return new String[]{from, to};
    }

    public static String extractBy(String input) {
        return input.substring(3).trim();
    }

    public static Command parse(String input) throws JeromeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("mark")) {
            int index = getSize(input);
            return new MarkCommand(index, false);
        }

        if (input.startsWith("unmark")) {
            int index = getSize(input);
            return new MarkCommand(index,true);
        }

        if (input.startsWith("delete")) {
            int index = getSize(input);
            return new DeleteCommand(index);
        }

        String[] formattedInput = splitBySlash(input);
        String taskType = getTaskType(formattedInput[0]);
        String desc = getDescription(formattedInput[0]);

        if (!taskType.equals("todo") && !taskType.equals("event") && !taskType.equals("deadline")) {
            throw new JeromeException.InvalidTaskTypeException();
        }

        if (desc == null) {
            throw new JeromeException.EmptyTaskException(taskType);
        }

        switch (taskType) {
            case "todo":
                if (input.contains("/from") || input.contains("/to") || input.contains("/by")) {
                    throw new JeromeException.WrongfulArgumentException("todo",
                            input.contains("/from") ? "from" : null,
                            input.contains("/to") ? "to" : null,
                            input.contains("/by") ? "by" : null);
                }
                return new AddCommand(new Todo(desc));

            case "deadline":
                if (formattedInput.length == 1 || !input.contains("/by")) {
                    throw new JeromeException.InvalidTaskDeclarationException("deadline");
                }
                if (input.contains("/from") || input.contains("/to")) {
                    throw new JeromeException.WrongfulArgumentException("deadline",
                            input.contains("/from") ? "from" : null,
                            input.contains("/to") ? "to" : null,
                            null);
                }
                try {
                    String by = extractBy(formattedInput[1]);
                    return new AddCommand(new Deadline(desc, by));
                } catch (Exception e) {
                    throw new JeromeException.InvalidTaskDeclarationException("deadline");
                }

            case "event":
                if (formattedInput.length == 1 || !input.contains("/from") || !input.contains("/to")) {
                    throw new JeromeException.InvalidTaskDeclarationException("event");
                }
                if (input.contains("/by")) {
                    throw new JeromeException.WrongfulArgumentException("event", null, null, "by");
                }
                try {
                    String[] fromTo = extractFromTo(formattedInput[1]);
                    return new AddCommand(new Event(desc, fromTo[0], fromTo[1]));
                } catch (Exception e) {
                    throw new JeromeException.InvalidTaskDeclarationException("event");
                }

            default:
                throw new JeromeException.InvalidTaskTypeException();
        }
    }
}
