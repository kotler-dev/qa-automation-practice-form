import components.ModalTableWidget;
import components.RegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.UserFaker;

public class AutomationPracticeFormWithPageObjectTest extends TestBase {


    @Test
    @DisplayName("Заполнение формы регистрации студента только с обязательными полями")
    void fillRequiredFieldsInRegistrationFormSuccessTest() {
        UserFaker userFaker = new UserFaker();
        userFaker
                .setFirstName(null)
                .setLastName(null)
                .setGender(null)
                .setMobileNumber(null);

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage
                .setUrl("https://demoqa.com")
                .openPage("/automation-practice-form")
                .fillStudentFirstNameField(userFaker.getFirstName())
                .fillStudentLastNameField(userFaker.getLastName())
                .fillStudentGenderField(userFaker.getGender())
                .fillStudentMobileNumberField(userFaker.getMobileNumber())
                .submitButton();

        ModalTableWidget modalTableWidget = new ModalTableWidget();
        modalTableWidget
                .student(userFaker)
                .checkField("Name")
                .checkField("Gender")
                .checkField("Mobile");
    }

    @Test
    @DisplayName("Заполнение формы регистрации студента некорректными полями")
    void fillRequiredFieldsInRegistrationFormFailureTest() {
        UserFaker userFaker = new UserFaker();
        userFaker
                .setFirstName(null)
                .setLastName(null)
                .setGender(null)
                .setMobileNumber(null);

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage
                .setUrl("https://demoqa.com")
                .openPage("/automation-practice-form")
                .fillStudentFirstNameField(userFaker.getFirstName())
                .fillStudentLastNameField(userFaker.getLastName())
                .fillStudentGenderField(userFaker.getGender())
                .fillStudentMobileNumberField(userFaker.getMobileNumber())
                .submitButton();

        ModalTableWidget modalTableWidget = new ModalTableWidget();
        modalTableWidget
                .student(userFaker)
                .checkField("Name")
                .checkInvalidField("Email")
                .checkField("Gender")
                .checkField("Mobile")
                .checkInvalidField("Date");
    }

    @Test
    @DisplayName("Заполнение всех полей формы регистрации студента")
    void fillAllFieldsInRegistrationFormTest() {
        UserFaker userFaker = new UserFaker();
        userFaker
                .setFirstName(null)
                .setLastName(null)
                .setEmail(null)
                .setGender(null)
                .setMobileNumber(null)
                .setDateOfBirth("1 March 1991")
                .setSubjects()
                .setHobbies()
                .setPicturePath()
                .setCurrentAddress()
                .setStateAndCity();

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage
                .setUrl("https://demoqa.com")
                .openPage("/automation-practice-form")
                .fillStudentFirstNameField(userFaker.getFirstName())
                .fillStudentLastNameField(userFaker.getLastName())
                .fillStudentUserEmailField(userFaker.getEmail())
                .fillStudentGenderField(userFaker.getGender())
                .fillStudentMobileNumberField(userFaker.getMobileNumber())
                .fillDateOfBirth(userFaker.getDateOfBirth())
                .fillSubjects(userFaker.getSubjects())
                .fillHobbies(userFaker.getHobbies())
                .fillUploadPictureInput(userFaker.getPicturePath())
                .fillCurrentAddress(userFaker.getCurrentAddress())
                .fillStateAndCity(userFaker.getStateAndCity())
                .submitButton();

        ModalTableWidget modalTableWidget = new ModalTableWidget();
        modalTableWidget
                .student(userFaker)
                .checkField("Name")
                .checkField("Email")
                .checkField("Gender")
                .checkField("Mobile")
                .checkField("Date of Birth")
                .checkField("Subjects")
                .checkField("Hobbies")
                .checkField("Picture")
                .checkField("Current Address")
                .checkField("State and City");
    }
}
