package dev.skyherobrine.backend.projects;

import dev.skyherobrine.backend.enums.ProductStatus;
import dev.skyherobrine.backend.models.oracle.ProductImage;
import dev.skyherobrine.backend.models.oracle.ProductPrice;
import dev.skyherobrine.backend.models.oracle.ProductType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProductProject {
    private String productId;
    private String productName;
    private String manufacturer;
    private String originCountry;
    private Double quantity;
    private String unit;
    private ProductType productType;
    private ProductStatus status;
    private ProductPrice price;
    private List<ProductImage> images;
    private List<ProductReviewProject> reviews;
    private Map<String,String> descriptions;
}
