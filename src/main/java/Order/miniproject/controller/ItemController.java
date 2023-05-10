package Order.miniproject.controller;

import Order.miniproject.Service.ItemService;
import Order.miniproject.domain.Book;
import Order.miniproject.domain.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
  private final ItemService itemService;

  @GetMapping("/addItem")
  public String addItem(){
    return "items/addItem";
  }

  @PostMapping("/addItem")
  public String addItemProcess(@ModelAttribute BookDto bookDto){
    Book book = new Book();
    book.setName(bookDto.getName());
    book.setPrice(bookDto.getPrice());
    book.setStockQuantity(bookDto.getStockQuantity());
    book.setAuthor(bookDto.getAuthor());
    book.setIsbn(bookDto.getIsbn());
    itemService.saveItem(book);
    return "redirect:/items/itemList";
  }
}
