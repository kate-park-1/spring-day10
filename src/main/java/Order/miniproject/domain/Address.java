package Order.miniproject.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
  private String city;
  private String street;
  @Column(length = 5)
  private String zipcode;

  protected Address() {}

  public Address(String city, String street, String zipcode) {
    this.city = city;
    this.street = street;
    this.zipcode = zipcode;
  }
}
