package ustredni_2019.q1;

import lombok.*;
import ustredni_2019.q1.boardtiles.BoardTile;
import ustredni_2019.q1.gson.templates.EndData;
import ustredni_2019.q1.gson.templates.StartData;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InputData {
    private int width;
    private int height;
    private StartData start;
    private EndData end;
    private BoardTile[][] boardPlan;
}
