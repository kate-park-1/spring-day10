package Order.miniproject.Service;

import Order.miniproject.domain.*;
import Order.miniproject.domain.dto.MemberDto;
import Order.miniproject.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
  @Autowired
  OrderService orderService;
  @Autowired
  MemberService memberService;
  @Autowired
  ItemService itemService;
  @Autowired
  OrderRepository orderRepository;

  @BeforeEach
  void setup() {

  }

  @Test
  void 정상주문생성테스트() {
    // 멤버, 책 , 주문아이템, 주문등록
    Long memberId = createMember();
    Long itemId = createItem();
    // when
    Long orderId = orderService.saveOrder(memberId, itemId, 10);
    // then
    Order order = orderRepository.findById(orderId);
    assertThat(90).isEqualTo(itemService.findItem(itemId).getStockQuantity());
    assertThat(OrderStatus.ORDER).isEqualTo(order.getOrderStatus());
    assertThat(1).isEqualTo(order.getOrderItems().size());
  }

  @Test
  void 재고수량초과주문테스트() {
    // 멤버, 책 , 주문아이템, 주문등록
    Long memberId = createMember();
    Long itemId = createItem();
    Item item = itemService.findItem(itemId);
    //when,then
    try {
      Long orderId = orderService.saveOrder(memberId, itemId, 110);
    } catch (Exception e) {
      assertThat("not enough stock to decrease").isEqualTo(e.getMessage());
    }
  }

  @Test
  void 주문취소테스트() {
    // 멤버, 책 , 주문아이템, 주문등록 , 주문취소
    Long memberId = createMember();
    Long itemId = createItem();
    Item item = itemService.findItem(itemId);
    Long orderId = orderService.saveOrder(memberId, itemId, 10);
    Order order = orderRepository.findById(orderId);
    //when
    orderService.cancelOrder(orderId);
    //then
    assertThat(100).isEqualTo(item.getStockQuantity());
    assertThat(OrderStatus.CANCEL).isEqualTo(order.getOrderStatus());
  }

  Long createMember(){
    MemberDto memberDto = new MemberDto();
    memberDto.setName("test");
    memberDto.setAddress(new Address("Seoul", "doksan", "11111"));
    Long joinId = memberService.join(memberDto);
    return joinId;
  }

  Long createItem(){
    Book book = new Book();
    book.setName("test");
    book.setPrice(1000);
    book.setStockQuantity(100);
    Long itemId = itemService.saveItem(book);
    return itemId;
  }

}