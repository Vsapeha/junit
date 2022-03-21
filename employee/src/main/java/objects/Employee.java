package objects;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import utils.RandomDataGenerator;

@Data
@Builder
public class Employee {

    @Builder.Default
    private String firstName = RandomDataGenerator.getName();

    @Builder.Default
    private String lastName = RandomDataGenerator.getName();

    @Builder.Default
    private String nativeName = RandomDataGenerator.getName();

    @Builder.Default
    private String login = RandomDataGenerator.getName();

    @Builder.Default
    private String personalHr = "Herasimenia Maryna";

    @Builder.Default
    private String currency = "USD";

    @Builder.Default
    private String whereabout = "UA, Vinnytsia";

    @Builder.Default
    private String overtimeHoursPerWeek = "0";

    @Builder.Default
    private String startDate = "11/01/2021";

    @Builder.Default
    private String department = "Exadel";
//    private String department = "--Development Department 1";

    @Builder.Default
    private String position = "QA Engineer";

    @Builder.Default
    private String office = "---UA V Exadel";

    @Builder.Default
    private String employmentType = "Employee (monthly rate)";

    @Builder.Default
    private String normalHoursPerWeek = "40";

    public String getFullName() {
        return lastName + " " + firstName;
    }
}
