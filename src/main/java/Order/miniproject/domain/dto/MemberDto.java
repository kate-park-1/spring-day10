package Order.miniproject.domain.dto;

import Order.miniproject.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
  String name;
  Address address;
}
