package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Skill {
    @JsonProperty("name")
    private String name;
    @JsonProperty("category_name")
    private String category_name;
}
