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

@Document(collation = "ImportProducts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
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

}
