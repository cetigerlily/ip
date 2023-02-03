package duke;

import duke.exceptions.InvalidFilePathException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import duke.exceptions.DukeExceptions;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public enum CommandType {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, FIND}
    public Command handleCommand(String input) throws DukeExceptions {
        try {
            CommandType command = CommandType.valueOf(input.split(" ")[0].toUpperCase());
            switch (command) {
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand();
                case TODO:
                    return new ToDoCommand(input);
                case DEADLINE:
                    return new DeadlineCommand(input);
                case EVENT:
                    return new EventCommand(input);
                case MARK:
                    return new MarkCommand(input);
                case UNMARK:
                    return new UnmarkCommand(input);
                case DELETE:
                    return new DeleteCommand(input);
            case FIND:
                return new FindCommand(input);
                default:
                    throw new UnknownCommandException();
            }
        } catch(IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }
    public Task translateFileToTaskList(String input) throws DukeExceptions {
        String[] splitInput = input.split(" \\| ");
        String taskType = splitInput[0];
        String taskStatus = splitInput[1];
        String taskDescription = splitInput[2];
        Task newTask;

        switch (taskType) {
            case "T":
                newTask = new ToDo(taskDescription);
                break;
            case "E":
                String[] eventTiming = splitInput[3].split("to");
                newTask = new Event(taskDescription, LocalDateTime.parse(eventTiming[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse(eventTiming[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                break;
            case "D":
                newTask = new Deadline(taskDescription, LocalDate.parse(splitInput[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                break;
            default:
                throw new InvalidFilePathException();
        }

        if(taskStatus.equals("1")) {
            newTask.markDone();
        } else {
            newTask.unmarkDone();
        }
        return newTask;
    }
}