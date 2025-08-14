package dev.skyherobrine.backend.projects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProductReviewProject {
    private UserProject user;
    private Double rating;
    private String comment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
