package com.lostfind.lostfind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate; // Ensure this import is present
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private LostItemService lostItemService;

    @Autowired
    private FoundItemService foundItemService;

    @GetMapping("/report-lost")
    public String reportLostForm(@AuthenticationPrincipal User user, Model model) {
        // Add the authenticated user's username to the model
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        return "report-lost"; // Thymeleaf template for reporting lost items
    }

    @PostMapping("/report-lost")
    public String reportLostItem(@RequestParam String itemName, @RequestParam String description,
                                 @RequestParam String location, @RequestParam String dateLost,
                                 @AuthenticationPrincipal User user) {
        LocalDate lostDate = LocalDate.parse(dateLost);
        lostItemService.reportLostItem(itemName, description, location, lostDate, user);
        return "redirect:/"; // Redirect to homepage after reporting
    }

    @GetMapping("/report-found")
    public String reportFoundForm(@AuthenticationPrincipal User user, Model model) {
        // Add the authenticated user's username to the model
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        return "report-found"; // Thymeleaf template for reporting found items
    }

    @PostMapping("/report-found")
    public String reportFoundItem(@RequestParam String itemName, @RequestParam String description,
                                  @RequestParam String location, @RequestParam String dateFound,
                                  @AuthenticationPrincipal User user) {
        LocalDate foundDate = LocalDate.parse(dateFound);
        foundItemService.reportFoundItem(itemName, description, location, foundDate, user);
        return "redirect:/"; // Redirect to homepage after reporting
    }

    @GetMapping("/my-lost-items")
    public String viewMyLostItems(@AuthenticationPrincipal User user, Model model) {
        // Fetch lost items reported by the logged-in user
        List<LostItem> lostItems = lostItemService.getLostItemsByUser(user);
        model.addAttribute("lostItems", lostItems);
        return "my-lost-items"; // Thymeleaf template to display lost items
    }

    @GetMapping("/my-found-items")
    public String viewMyFoundItems(@AuthenticationPrincipal User user, Model model) {
        // Fetch found items reported by the logged-in user
        List<FoundItem> foundItems = foundItemService.getFoundItemsByUser(user);
        model.addAttribute("foundItems", foundItems);
        return "my-found-items"; // Thymeleaf template to display found items
    }
}