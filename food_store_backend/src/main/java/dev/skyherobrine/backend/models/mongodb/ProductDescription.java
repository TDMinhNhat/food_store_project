package dev.skyherobrine.backend.models.mongodb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Map;

@Document(collation = "ProductDescriptions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductDescription {

    @Id
    @Field(name = "product_id")
    private String productId;

    private List<Map<String,String>> descriptions;
}
