package ee.ituk.forms.server.database;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "responses")
@Data
public class ResponseModel implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String email;
    private Boolean checkedDay1;
    private Boolean checkedDay2;
    private String packageDay1;
    private String packageDay2;

    protected ResponseModel() {}

    public ResponseModel(String name, String email, Boolean checked_d1, Boolean checked_d2, String package_day1, String package_day2) {
        this.name = name;
        this.email = email;
        this.checkedDay1 = checked_d1;
        this.checkedDay2 = checked_d2;

        if (this.checkedDay1) {
            this.packageDay1 = package_day1;
        } else {
            this.packageDay1 = "";
        }

        if (this.checkedDay2) {
            this.packageDay2 = package_day2;
        } else {
            this.packageDay2 = "";
        }
    }
}
