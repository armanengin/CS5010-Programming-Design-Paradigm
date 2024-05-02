package problem1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ErrorMessageTest {

  @Test
  void getMessage_NO_CSV_FILE() {
    assertEquals("\nError: Missing argument --csv-file. Please specify the CSV file path using --csv-file path.\n",
        ErrorMessage.NO_CSV_FILE.getMessage());
  }

  @Test
  void getMessage_NO_OUTPUT_DIR() {
    assertEquals("\nError: Missing argument --output-dir. Please specify the output directory path using --output-dir path.\n",
        ErrorMessage.NO_OUTPUT_DIR.getMessage());
  }

  @Test
  void getMessage_EMAIL_BUT_NO_TEMPLATE() {
    assertEquals("\nError: Email operation initiated without an email template. Please provide an email template using --email-template path.\n",
        ErrorMessage.EMAIL_BUT_NO_TEMPLATE.getMessage());
  }

  @Test
  void getMessage_LETTER_BUT_NO_TEMPLATE() {
    assertEquals("\nError: Letter operation initiated without a letter template. Please provide a letter template using --letter-template path.\n",
        ErrorMessage.LETTER_BUT_NO_TEMPLATE.getMessage());
  }

  @Test
  void getMessage_EMAIL_TEMPLATE_BUT_NO_EMAIL() {
    assertEquals("\nError: An email template is provided without specifying the email operation. Please initiate the email operation using --email.\n",
        ErrorMessage.EMAIL_TEMPLATE_BUT_NO_EMAIL.getMessage());
  }

  @Test
  void getMessage_LETTER_TEMPLATE_BUT_NO_LETTER() {
    assertEquals("\nError: A letter template is provided without specifying the letter operation. Please initiate the letter operation using --letter.\n",
        ErrorMessage.LETTER_TEMPLATE_BUT_NO_LETTER.getMessage());
  }
}