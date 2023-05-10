package Order.miniproject.Service;

import Order.miniproject.domain.Item;
import Order.miniproject.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
  private final ItemRepository itemRepository;

  @Transactional
  public Long saveItem(Item item) {
    itemRepository.save(item);
    return item.getId();
  }

  public List<Item> findAllItems() {
    return itemRepository.findAll();
  }

  public Item findItem(Long id) {
    return itemRepository.findById(id);
  }
}
