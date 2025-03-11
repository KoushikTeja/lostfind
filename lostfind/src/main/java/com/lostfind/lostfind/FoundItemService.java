package com.lostfind.lostfind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate; // Ensure this import is present
import java.util.List;

@Service
public class FoundItemService {

    @Autowired
    private FoundItemRepository foundItemRepository;

    public void reportFoundItem(String itemName, String description, String location, LocalDate dateFound, User user) {
        FoundItem foundItem = new FoundItem();
        foundItem.setItemName(itemName);
        foundItem.setDescription(description);
        foundItem.setLocation(location);
        foundItem.setDateFound(dateFound);
        foundItem.setUser(user);
        foundItemRepository.save(foundItem);
    }

    public List<FoundItem> getFoundItemsByUser(User user) {
        return foundItemRepository.findByUser(user);
    }
}