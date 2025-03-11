package com.lostfind.lostfind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate; // Ensure this import is present
import java.util.List;

@Service
public class LostItemService {

    @Autowired
    private LostItemRepository lostItemRepository;

    public void reportLostItem(String itemName, String description, String location, LocalDate dateLost, User user) {
        LostItem lostItem = new LostItem();
        lostItem.setItemName(itemName);
        lostItem.setDescription(description);
        lostItem.setLocation(location);
        lostItem.setDateLost(dateLost);
        lostItem.setUser(user);
        lostItemRepository.save(lostItem);
    }

    public List<LostItem> getLostItemsByUser(User user) {
        return lostItemRepository.findByUser(user);
    }
}