package seedu.smartlib.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartlib.testutil.Assert.assertThrows;

import java.net.URL;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.fxml.FXML;
import seedu.smartlib.MainApp;
import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.model.book.Book;

public class BookCardTest {

    private static final String MISSING_FILE_PATH = "UiPartTest/missingFile.fxml";
    private static final String INVALID_FILE_PATH = "UiPartTest/invalidFile.fxml";
    private static final String VALID_FILE_PATH = "UiPartTest/validFile.fxml";
    private static final String VALID_FILE_WITH_FX_ROOT_PATH = "UiPartTest/validFileWithFxRoot.fxml";
    private static final TestFxmlObject VALID_FILE_ROOT = new TestFxmlObject("Hello World!");

    @TempDir
    public Path testFolder;

    @Test
    public void constructor_nullBook_throwsExceptionInInitializerError() {
        assertThrows(ExceptionInInitializerError.class, () -> new BookCard(null, 1));
    }
/*
    @Test
    public void constructor_missingFileUrl_throwsAssertionError() throws Exception {
        URL missingFileUrl = new URL(testFolder.toUri().toURL(), MISSING_FILE_PATH);
        assertThrows(AssertionError.class, () -> new TestUiPart<Object>(missingFileUrl));
        assertThrows(AssertionError.class, () -> new TestUiPart<Object>(missingFileUrl, new Object()));
    }

    @Test
    public void constructor_invalidFileUrl_throwsAssertionError() {
        URL invalidFileUrl = getTestFileUrl(INVALID_FILE_PATH);
        assertThrows(AssertionError.class, () -> new TestUiPart<Object>(invalidFileUrl));
        assertThrows(AssertionError.class, () -> new TestUiPart<Object>(invalidFileUrl, new Object()));
    }

    @Test
    public void constructor_validFileUrl_loadsFile() {
        URL validFileUrl = getTestFileUrl(VALID_FILE_PATH);
        assertEquals(VALID_FILE_ROOT, new TestUiPart<TestFxmlObject>(validFileUrl).getRoot());
    }

    @Test
    public void constructor_validFileWithFxRootUrl_loadsFile() {
        URL validFileUrl = getTestFileUrl(VALID_FILE_WITH_FX_ROOT_PATH);
        TestFxmlObject root = new TestFxmlObject();
        assertEquals(VALID_FILE_ROOT, new TestUiPart<TestFxmlObject>(validFileUrl, root).getRoot());
    }

    @Test
    public void constructor_nullFileName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TestUiPart<Object>((String) null));
        assertThrows(NullPointerException.class, () -> new TestUiPart<Object>((String) null, new Object()));
    }

    @Test
    public void constructor_missingFileName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TestUiPart<Object>(MISSING_FILE_PATH));
        assertThrows(NullPointerException.class, () -> new TestUiPart<Object>(MISSING_FILE_PATH, new Object()));
    }

    @Test
    public void constructor_invalidFileName_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new TestUiPart<Object>(INVALID_FILE_PATH));
        assertThrows(AssertionError.class, () -> new TestUiPart<Object>(INVALID_FILE_PATH, new Object()));
    }

    private URL getTestFileUrl(String testFilePath) {
        String testFilePathInView = "/view/" + testFilePath;
        URL testFileUrl = MainApp.class.getResource(testFilePathInView);
        assertNotNull(testFileUrl, testFilePathInView + " does not exist.");
        return testFileUrl;
    }
    */
    /*
    @Test
    public void equals() {
        final Index fifthReaderIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertTrue(fifthReaderIndex.equals(Index.fromOneBased(5)));
        assertTrue(fifthReaderIndex.equals(Index.fromZeroBased(4)));

        // same object -> returns true
        assertTrue(fifthReaderIndex.equals(fifthReaderIndex));

        // null -> returns false
        assertFalse(fifthReaderIndex.equals(null));

        // different types -> returns false
        assertFalse(fifthReaderIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(fifthReaderIndex.equals(Index.fromOneBased(1)));
    }
     */

}
