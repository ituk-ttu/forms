package ee.ituk.forms.server.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "responses")
public class ResponseModel implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id @Getter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private String email;
    @Getter @Setter private Boolean checkedDay1;
    @Getter @Setter private Boolean checkedDay2;
    @Getter @Setter private String packageDay1;
    @Getter @Setter private String packageDay2;

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
