package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    @Query("""
            select a from Address a
            where a.houseNumber = ?1 and a.street = ?2 and a.ward = ?3 and a.city = ?4 and a.country = ?5""")
    Optional<Address> findByHouseNumberAndStreetAndWardAndCityAndCountry(String houseNumber, String street, String ward, String city, String country);
}
