import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void studentCanRegister() {
        String firstName = "Sam";
        String lastName = "Gold";
        String gender = "Male";

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue("sam.gold@gmail.com");
        $(byText(gender)).click();
        $("#userNumber").setValue("1234567891");

        //select date in datepicker
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $$(".react-datepicker__day--001").first().click();

        //select subjects
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("English").pressEnter();

        $(byText("Sports")).click();
        $(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#currentAddress").setValue("Student address: street...");

        //select state and city
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        //assert
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(byText(firstName + " " + lastName)).shouldBe(visible);
        $(byText(gender)).shouldBe(visible);
        $(".modal-content").shouldHave(text("sam.gold@gmail.com"),
                text("1234567891"), text("01 June,2000"), text("Maths, English"),
                text("Sports, Music"), text("Student address: street..."), text("NCR Delhi"), text("image.jpg"));
    }
}
