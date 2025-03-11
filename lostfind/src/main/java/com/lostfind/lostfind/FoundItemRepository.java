package com.lostfind.lostfind;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
    List<FoundItem> findByUser(User user); // Find items reported by a specific user
}