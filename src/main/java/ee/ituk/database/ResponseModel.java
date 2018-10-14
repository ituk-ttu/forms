package ee.ituk.database;

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
    @Getter @Setter private Boolean checked_d1;
    @Getter @Setter private Boolean checked_d2;
    @Getter @Setter private String package_day1;
    @Getter @Setter private String package_day2;

    protected ResponseModel() {}

    public ResponseModel(String name, String email, Boolean checked_d1, Boolean checked_d2, String package_day1, String package_day2) {
        this.name = name;
        this.email = email;
        this.checked_d1 = checked_d1;
        this.checked_d2 = checked_d2;
        this.package_day1 = package_day1;
        this.package_day2 = package_day2;
    }
}
