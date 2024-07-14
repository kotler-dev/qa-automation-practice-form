import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import pages.components.TextBoxTableWidget;
import utils.UserFaker;

public class TextBoxFormWithPageObjectTest extends TestBase {


    @Test
    @DisplayName("Заполнение формы регистрации Text Box")
    void fillRequiredFieldsInRegistrationFormSuccessTest() {
        UserFaker userFaker = new UserFaker();
        userFaker
                .setFullName(null)
                .setEmail(null)
                .setCurrentAddress()
                .setPermanentAddress();

        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage
                .setUrl("https://demoqa.com")
                .openPage("/text-box")
                .fillFullNameField(userFaker.getFullName())
                .fillStudentUserEmailField(userFaker.getEmail())
                .fillCurrentAddress(userFaker.getCurrentAddress())
                .fillPermanentAddress(userFaker.getPermanentAddress())
                .submitButton();

        TextBoxTableWidget tableWidget = new TextBoxTableWidget();
        tableWidget
                .student(userFaker)
                .checkField("Name")
                .checkField("Email")
                .checkField("Current Address")
                .checkField("Permanent Address");
    }
}
