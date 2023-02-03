package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;

import tigerlily.tasks.Deadline;
import tigerlily.tasks.TaskList;

import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private final String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Deadline, adds it to the TaskList, and displays confirmation message of addition.
     *
     * @param taskList the TaskList the new Deadline is added to
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used during this session
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] s1 = input.substring(9).split("/by");
        Deadline newDeadline = new Deadline(s1[0].trim(), LocalDate.parse(s1[1].trim(),
                Deadline.getDeadlineFormatter()));
        taskList.addTask(newDeadline);
        ui.showAddedMessage(newDeadline, taskList);
    }
}