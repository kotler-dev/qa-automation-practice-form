package pages.components;

import com.codeborne.selenide.SelenideElement;
import utils.UserFaker;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxTableWidget {

    public TextBoxTableWidget() {
    }

    private final Map<String, String> tableRow = new HashMap<>();

    public TextBoxTableWidget student(UserFaker user) {
        tableRow.put("Name", "Name:" + (user.getFirstName() + " " + user.getLastName()));
        tableRow.put("Email", "Email:" + user.getEmail());
        tableRow.put("Current Address", "Current Address :" + user.getCurrentAddress());
        tableRow.put("Permanent Address", "Permananet Address :" + (user.getStateAndCity() != null ? user.getStateAndCity().getLeft() + " " + user.getStateAndCity().getRight() : ""));
        return this;
    }

    public TextBoxTableWidget checkField(String field) {
        SelenideElement output = $("#output");
        output.shouldHave(text(tableRow.get(field)));
        return this;
    }

    public TextBoxTableWidget checkInvalidField(String field) {
        String s = tableRow.get(field) != null ? tableRow.get(field) : "invalid date";
        SelenideElement output = $("#output");
        output.shouldNotHave(text(s));
        return this;
    }
}