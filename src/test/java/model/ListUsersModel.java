package model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class ListUsersModel {
    private List<ListUsersData> listUsersData;
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private Support support;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ListUsersData {
        private String avatar;
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        private int id;
        @JsonProperty("last_name")
        private String lastName;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Support {
        private String text;
        private String url;
    }
}
