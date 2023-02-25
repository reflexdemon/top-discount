package io.vpv.topdiscount.service;

import io.vpv.topdiscount.dao.ItemDao;
import io.vpv.topdiscount.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Item save(final Item item) {
        return itemDao.save(item);
    }

    public void deleteAll() {
        itemDao.deleteAll();
    }

    public Item getItemById(final Integer id) {
        return itemDao.findById(id).orElseThrow();
    }
}
