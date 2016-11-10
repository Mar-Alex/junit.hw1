package school.lemon.changerequest.java.junit.hw1.task1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents some abstract Contact with call number, name and surname
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Long id;
    private CallNumber callNumber;
    private String name;
    private String surname;

    public Contact(Contact contact) {
        this.id = contact.id;
        this.callNumber = contact.callNumber;
        this.name = contact.name;
        this.surname = contact.surname;
    }
}
