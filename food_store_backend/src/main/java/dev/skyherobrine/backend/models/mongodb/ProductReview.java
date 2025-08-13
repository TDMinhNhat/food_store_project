package dev.skyherobrine.backend.models.mongodb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Document(collection = "ProductReviews")
@Getter @Setter
@NoArgsConstructor
public class ProductReview {
    @Id
    private Long id;

    @Field(name = "product_id")
    private String productId;

    @Field(name = "user_id")
    private String userId;

    private Double rating;

    private String comment;

    private boolean status;

    @Field(name = "created_at", targetType = FieldType.TIMESTAMP)
    private Timestamp createdAt;

    @Field(name = "updated_at", targetType = FieldType.TIMESTAMP)
    private Timestamp updatedAt;

    public ProductReview(Long id, String productId, String userId, Double rating, String comment) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
        this.status = true;
        this.createdAt = this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
