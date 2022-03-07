package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Experience {
    @JsonProperty("name")
    private String name;
    @JsonProperty("region")
    private String region;
    @JsonProperty("description")
    private String description;
    @JsonProperty("linkedin")
    private String linkedIn;
    @JsonProperty("start_date")
    private Date startDate;
    @JsonProperty("end_date")
    private Date endDate;
}
