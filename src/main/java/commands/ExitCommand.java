package commands;

import data.TaskList;
import ui.TextUi;

public class ExitCommand extends Command {
    public static final String COMMAND_TEXT = "bye";

    /**
     * Returns exiting message
     *
     * @param tasks
     * @param ui
     * @return response message
     */
    @Override
    public String execute(TaskList tasks, TextUi ui) {
        return ui.getFormattedMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if given command is an ExitCommand
     *
     * @param command
     * @return if command is exit boolean
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
