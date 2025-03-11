package com.lostfind.lostfind;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    List<LostItem> findByUser(User user); // Find items reported by a specific user
}