package ustredni_2019.q1.gson.templates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JsonData {
    private int SIRKA;
    private int VYSKA;
    private StartData START;
    private EndData CIL;
    private String[] PLAN;
}
