package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX;

import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.logic.commands.DeleteReaderCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteReaderCommand object.
 */
public class DeleteReaderCommandParser implements Parser<DeleteReaderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteReaderCommand
     * and returns a DeleteReaderCommand object for execution.
     *
     * @param args arguments given in the user input.
     * @return a DeleteReaderCommand object required for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteReaderCommand parse(String args) throws ParseException {
        if (!args.trim().matches("^[a-zA-Z]*$")) {
            try {
                Integer.parseInt(args.trim());
            } catch (NumberFormatException ne) {
                throw new ParseException(MESSAGE_INVALID_READER_DISPLAYED_INDEX);
            }
        }

        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteReaderCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReaderCommand.MESSAGE_USAGE), pe);

        }
    }

}
