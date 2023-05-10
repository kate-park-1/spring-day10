package Order.miniproject.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
  private String name;
  private int price;
  private int stockQuantity;
  private String author;
  private String isbn;
  // 생성자 추가
}
