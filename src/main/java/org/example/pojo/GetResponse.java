package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponse {

    @JsonProperty("location")
    private Location location;

    @JsonProperty("accuracy")
    private int accuracy;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phone_number;

    @JsonProperty("address")
    private String address;

    @JsonProperty("types")
    private String types;

    @JsonProperty("website")
    private String website;

    @JsonProperty("language")
    private String language;
}
