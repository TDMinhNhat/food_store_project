package dev.skyherobrine.backend.dtos;

import dev.skyherobrine.backend.models.oracle.Address;

import java.io.Serializable;

/**
 * DTO for {@link dev.skyherobrine.backend.models.oracle.Address}
 */
public record AddressDto(String houseNumber, String street, String ward, String city,
                         String country) implements Serializable {

    public Address toObject() {
        return new Address(houseNumber, street, ward, city, country);
    }
}