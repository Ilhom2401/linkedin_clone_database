package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import database.BaseDatabase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements BaseDatabase {
    @JsonProperty("first_name")
    private String firstname;
    @JsonProperty("last_name")
    private String lastname;
    @JsonProperty("birth_date")
    private Date birthdate;
    @JsonProperty("about")
    private String about;
    @JsonProperty("email")
    private String email;
    @JsonProperty("region")
    private String region;
    @JsonProperty("education")
    private List<Education> educationList;
    @JsonProperty("experience")
    private List<Experience> experienceList;
    @JsonProperty("skill")
    private List<Skill> skillList;


}
