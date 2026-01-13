package com.twilight.eCommercePlatform.entities;
import com.twilight.eCommercePlatform.dto.entity.AddressDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String landMark;

    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)
    private OrderEntity order;
    @OneToOne(mappedBy = "address",fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Address (AddressDTO dto){
        if ( dto == null ) {
            throw new NullPointerException("DTO can not be empty");
        }
        this.setStreet( dto.getStreet() );
        this.setCity( dto.getCity() );
        this.setState( dto.getState() );
        this.setCountry( dto.getCountry() );
        this.setPostalCode( dto.getPostalCode() );
        this.setLandMark( dto.getLandMark() );
    }
    public static void updateAddress(Address address, AddressDTO addressDTO){
        if(addressDTO.getCity()!=null)
            address.setCity(addressDTO.getCity());
        if(addressDTO.getCountry()!=null)
            address.setCountry(addressDTO.getCountry());
        if (addressDTO.getLandMark() != null)
            address.setLandMark(address.getLandMark());
        if(addressDTO.getPostalCode() != null)
            address.setPostalCode(addressDTO.getPostalCode());
        if (addressDTO.getStreet()!= null)
            address.setStreet(addressDTO.getStreet());
        if(addressDTO.getState() != null )
            address.setState(addressDTO.getState());
    }
}
