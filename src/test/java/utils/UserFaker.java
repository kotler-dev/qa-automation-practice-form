package utils;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.tuple.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserFaker {

    private final Faker faker = new Faker(new Locale("en-EN"), new Random());

    public UserFaker() {
    }

    enum Gender {
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        private final String value;

        Gender(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public UserFaker setFirstName(String firstName) {
        this.firstName = firstName == null ? faker.name().firstName() : firstName;
        return this;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public UserFaker setLastName(String lastName) {
        this.lastName = lastName == null ? faker.name().lastName() : lastName;
        return this;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public UserFaker setEmail(String email) {
        this.email = email == null ? faker.internet().emailAddress() : email;
        return this;
    }

    private String gender;

    public String getGender() {
        return gender;
    }

    public UserFaker setGender(String gender) {
        if (gender == null | Objects.equals(gender, "")) {
            String[] genders = {Gender.MALE.getValue(), Gender.FEMALE.getValue(), Gender.OTHER.getValue()};
            this.gender = faker.options().option(genders);
        } else {
            this.gender = gender;
        }
        return this;
    }

    private String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public UserFaker setMobileNumber(String mobileNumber) {
        if (mobileNumber == null) {
            this.mobileNumber = faker.number().digits(10);
        } else {
            this.mobileNumber = mobileNumber;
        }
        return this;
    }

    private Map<String, String> dateOfBirth;

    public Map<String, String> getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Устанавливает дату рождения в формате "ДД ММ ГГГГ".
     */
    public UserFaker setDateOfBirth(String dateOfBirth) {
        Map<String, String> dob = new HashMap<>();
        if (dateOfBirth == null) {
            throw new RuntimeException("dateOfBirth is null");
        } else {
            Date date;
            try {
                date = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(dateOfBirth);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
            String month = String.valueOf(cal.get(Calendar.MONTH));
            String year = String.valueOf(cal.get(Calendar.YEAR));
            dob.put("day", day);
            dob.put("month", month);
            dob.put("year", year);

            if (day.length() == 1) {
                day = "0" + day;
            }
            String datePrint = day + " " +
                    cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + "," +
                    year;
            dob.put("print", datePrint);

            this.dateOfBirth = dob;
        }
        return this;
    }

    public String dateOfBirthPrint() {
        if (dateOfBirth != null) {
            return dateOfBirth.get("print");
        } else {
            return "";
        }
    }

    private String subject;

    public String getSubjects() {
        return subject;
    }

    public UserFaker setSubjects() {
        String[] subjects = {
                "Arts", "History", "English", "Chemistry",
                "Computer Science", "Commerce", "Economics", "Maths",
                "Social Studies", "Accounting", "Physics", "Biology",
                "Hindi", "Civics"
        };
        this.subject = faker.options().option(subjects);
        return this;
    }

    private Pair<Integer, String> hobbies;

    public Pair<Integer, String> getHobbies() {
        if (hobbies == null) {
            return hobbies = Pair.of(0, "");
        }
        return hobbies;
    }

    public UserFaker setHobbies() {
        String[] s = {"Sports", "Reading", "Music"};
        String hobby = faker.options().option(s);
        this.hobbies = Pair.of(Arrays.asList(s).indexOf(hobby), hobby);
        return this;
    }

    private String picturePath;

    public String getPicturePath() {
        return picturePath;
    }

    public UserFaker setPicturePath() {
        String[] files = {"toolsqalogo.png"};
        this.picturePath = faker.options().option(files);
        return this;
    }

    private String currentAddress;

    public String getCurrentAddress() {
        return currentAddress;
    }

    public UserFaker setCurrentAddress() {
        String[] subjects = {
                "Arts", "History", "English", "Chemistry",
                "Computer Science", "Commerce", "Economics", "Maths",
                "Social Studies", "Accounting", "Physics", "Biology",
                "Hindi", "Civics"
        };
        this.currentAddress = faker.options().option(subjects);
        return this;
    }

    private Pair<String, String> stateAndCity;

    public Pair<String, String> getStateAndCity() {
        return stateAndCity;
    }

    public UserFaker setStateAndCity() {
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        String state = faker.options().option(states);

        Map<String, String[]> cities = new HashMap<>();
        cities.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        cities.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        cities.put("Haryana", new String[]{"Karnal", "Panipat"});
        cities.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
        this.stateAndCity = Pair.of(state, faker.options().option(cities.get(state)));
        return this;
    }
}
