package dev.skyherobrine.backend.projects;

import dev.skyherobrine.backend.models.oracle.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class OrderDetailProject implements Serializable {
    private Product product;
    private Double quantity;
}
