package components;

import com.codeborne.selenide.SelenideElement;
import utils.UserFaker;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ModalTableWidget {

    public ModalTableWidget() {
    }

    private final Map<String, String> tableRow = new HashMap<>();

    public ModalTableWidget student(UserFaker user) {
        tableRow.put("Name", "Student Name " + (user.getFirstName() + " " + user.getLastName()));
        tableRow.put("Email", "Student Email " + user.getEmail());
        tableRow.put("Gender", "Gender " + (user.getGender() != null ? user.getGender() : ""));
        tableRow.put("Mobile", "Mobile " + user.getMobileNumber());
        tableRow.put("Date of Birth", "Date of Birth " + user.dateOfBirthPrint());
        tableRow.put("Subjects", "Subjects " + user.getSubjects());
        tableRow.put("Hobbies", "Hobbies " + (user.getHobbies() != null ? user.getHobbies().getRight() : ""));
        tableRow.put("Picture", "Picture " + user.getPicturePath());
        tableRow.put("Current Address", "Address " + user.getCurrentAddress());
        tableRow.put("State and City", "State and City " + (user.getStateAndCity() != null ? user.getStateAndCity().getLeft() + " " + user.getStateAndCity().getRight() : ""));
        return this;
    }

    public ModalTableWidget checkField(String field) {
        SelenideElement table = $(".table-responsive");
        table.shouldHave(text(tableRow.get(field)));
        return this;
    }

    public ModalTableWidget checkInvalidField(String field) {
        String s = tableRow.get(field) != null ? tableRow.get(field) : "invalid date";
        SelenideElement table = $(".table-responsive");
        table.shouldNotHave(text(s));
        return this;
    }
}