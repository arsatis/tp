package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.smartlib.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.smartlib.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.smartlib.logic.commands.CommandTestUtil.TAG_DESC_MEMBERSHIP;
import static seedu.smartlib.logic.commands.CommandTestUtil.TAG_DESC_VIP;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_MEMBERSHIP;
import static seedu.smartlib.logic.commands.CommandTestUtil.VALID_TAG_VIP;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.smartlib.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.smartlib.testutil.TypicalModels.AMY;
import static seedu.smartlib.testutil.TypicalModels.BOB;

import org.junit.jupiter.api.Test;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.AddReaderCommand;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.tag.Tag;
import seedu.smartlib.testutil.ReaderBuilder;

public class AddReaderCommandParserTest {

    private AddReaderCommandParser parser = new AddReaderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Reader expectedReader = new ReaderBuilder(BOB).withTags(VALID_TAG_MEMBERSHIP).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_MEMBERSHIP, new AddReaderCommand(expectedReader));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_MEMBERSHIP, new AddReaderCommand(expectedReader));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_MEMBERSHIP, new AddReaderCommand(expectedReader));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_MEMBERSHIP, new AddReaderCommand(expectedReader));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + TAG_DESC_MEMBERSHIP, new AddReaderCommand(expectedReader));

        // multiple tags - all accepted
        Reader expectedReaderMultipleTags = new ReaderBuilder(BOB).withTags(VALID_TAG_MEMBERSHIP, VALID_TAG_VIP)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_VIP + TAG_DESC_MEMBERSHIP, new AddReaderCommand(expectedReaderMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Reader expectedReader = new ReaderBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddReaderCommand(expectedReader));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReaderCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_VIP + TAG_DESC_MEMBERSHIP, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_VIP + TAG_DESC_MEMBERSHIP, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_VIP + TAG_DESC_MEMBERSHIP, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TAG_DESC_VIP + TAG_DESC_MEMBERSHIP, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_MEMBERSHIP, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_VIP + TAG_DESC_MEMBERSHIP,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReaderCommand.MESSAGE_USAGE));
    }

}
