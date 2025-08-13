package dev.skyherobrine.backend.models.mongodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Document(collation = "ImportProducts")
@Getter @Setter
@NoArgsConstructor
public class ImportProduct {

    @Id
    private Long id;

    @Field(name = "product_id")
    private String productId;

    @Field(name = "supply_name")
    private String supplyName;

    private Double quantity;

    @Field(name = "created_at", targetType = FieldType.TIMESTAMP)
    private Timestamp createdAt;

    public ImportProduct(Long id, String productId, String supplyName, Double quantity) {
        this.id = id;
        this.productId = productId;
        this.supplyName = supplyName;
        this.quantity = quantity;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}
