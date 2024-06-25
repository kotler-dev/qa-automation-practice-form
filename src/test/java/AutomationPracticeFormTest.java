import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }

    @Test
    void fillStudentRegistrationFormTest() {
        open("/automation-practice-form");

        // Advertisement
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // Student data
        $("#firstName").setValue("Morty");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("morty@smith.qa");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1010101010");

        // Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("11");
        $(".react-datepicker__year-select").selectOptionByValue("1999");
        $(".react-datepicker__day--003").click();

        // Subjects
        $("#subjectsInput").setValue("C").scrollTo();
        $(byText("Computer Science")).click();

        // Hobbies
        // $("#hobbiesWrapper").$(".custom-control", 1).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();

        // Picture
        $("#uploadPicture").uploadFromClasspath("toolsqalogo.png");

        // Current Address
        $("#currentAddress").setValue("Earth");

        // State and City
        $("#react-select-3-input").setValue("H");
        $(byText("Haryana")).click();
        $("#react-select-4-input").setValue("K");
        $(byText("Karnal")).click();

        // Submit
        $("#submit").scrollTo().click();

        // Validate
        // $(".table-responsive").getText();
        var table = $(".table-responsive");
        table.shouldHave(text("Student Name Morty Smith"));
        table.shouldHave(text("Student Email morty@smith.qa"));
        table.shouldHave(text("Gender Male"));
        table.shouldHave(text("Mobile 1010101010"));
        table.shouldHave(text("Date of Birth 03 December,1999"));
        table.shouldHave(text("Subjects Computer Science"));
        table.shouldHave(text("Hobbies Reading"));
        table.shouldHave(text("Picture toolsqalogo.png"));
        table.shouldHave(text("Address Earth"));
        table.shouldHave(text("State and City Haryana Karnal"));
    }
}
