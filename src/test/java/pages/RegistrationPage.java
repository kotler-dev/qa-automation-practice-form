package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationPage {

    public RegistrationPage openPage(String page) {
        Selenide.open(page);
        return this;
    }

    public RegistrationPage removeAds() {
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
        return this;
    }

    private final SelenideElement firstNameInput = $("#firstName");

    public RegistrationPage fillStudentFirstNameField(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    private final SelenideElement lastNameInput = $("#lastName");

    public RegistrationPage fillStudentLastNameField(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    private final SelenideElement userEmailInput = $("#userEmail");

    public RegistrationPage fillStudentUserEmailField(String email) {
        userEmailInput.setValue(email);
        return this;
    }

    private final SelenideElement genderWrapperInput = $("#genterWrapper");

    public RegistrationPage fillStudentGenderField(String gender) {
        genderWrapperInput.$(byText(gender)).click();
        return this;
    }

    private final SelenideElement userMobileNumberInput = $("#userNumber");

    public RegistrationPage fillStudentMobileNumberField(String mobileNumber) {
        userMobileNumberInput.setValue(mobileNumber);
        return this;
    }

    private final SelenideElement monthSelectDataPicker = $(".react-datepicker__month-select");

    private void fillMonthSelectDataPicker(String month) {
        monthSelectDataPicker.selectOptionByValue(month);
    }

    private final SelenideElement yearSelectDataPicker = $(".react-datepicker__year-select");

    private void fillYearSelectDataPicker(String year) {
        yearSelectDataPicker.selectOptionByValue(year);
    }

    private final ElementsCollection daySelectDataPicker = $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)");

    private void fillDaySelectDataPicker(String day) {
        daySelectDataPicker.findBy(text(day)).click();
    }

    public RegistrationPage fillDateOfBirth(Map<String, String> dateOfBirth) {
        $("#dateOfBirthInput").click();
        fillMonthSelectDataPicker(dateOfBirth.get("month"));
        fillYearSelectDataPicker(dateOfBirth.get("year"));
        fillDaySelectDataPicker(dateOfBirth.get("day"));
        return this;
    }

    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement subjectsWrapper = $("#subjectsWrapper");

    public RegistrationPage fillSubjects(String subject) {
        if (subject == null) {
            throw new RuntimeException("Subject is null");
        }
        subjectsInput.setValue(String.valueOf(subject.charAt(0))).scrollTo();
        subjectsWrapper.$(byText(subject)).click();
        return this;
    }

    private final SelenideElement hobbiesWrapper = $("#hobbiesWrapper");

    public RegistrationPage fillHobbies(Pair<Integer, String> hobby) {
        hobbiesWrapper.$(".custom-control", hobby.getLeft()).click();
        return this;
    }

    private final SelenideElement uploadPicture = $("#uploadPicture");

    public RegistrationPage fillUploadPictureInput(String file) {
        uploadPicture.uploadFromClasspath(file);
        return this;
    }

    private final SelenideElement currentAddress = $("#currentAddress");

    public RegistrationPage fillCurrentAddress(String currentAddress) {
        this.currentAddress.setValue(currentAddress);
        return this;
    }

    private final SelenideElement stateInput = $("#react-select-3-input");
    private final SelenideElement stateWrapper = $("#stateCity-wrapper");
    private final SelenideElement cityInput = $("#react-select-4-input");
    private final SelenideElement cityWrapper = $("#stateCity-wrapper");

    public RegistrationPage fillStateAndCity(Pair<String, String> stateAndCity) {
        String state = stateAndCity.getLeft();
        String city = stateAndCity.getRight();
        stateInput.setValue(String.valueOf(state.charAt(0)));
        stateWrapper.$(byText(state)).click();
        cityInput.setValue(String.valueOf(city.charAt(0)));
        cityWrapper.$(byText(city)).click();
        return this;
    }

    public void submitButton() {
        $("#submit").scrollTo().click();
    }
}
