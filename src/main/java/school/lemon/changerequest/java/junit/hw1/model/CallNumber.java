package school.lemon.changerequest.java.junit.hw1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Take look at <a href="https://en.wikipedia.org/wiki/MSISDN">MSISDN</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallNumber {
    private String SN;
    private String NDC;
    private String CC;
}
