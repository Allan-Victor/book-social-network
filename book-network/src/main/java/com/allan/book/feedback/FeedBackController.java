package com.allan.book.feedback;

import com.allan.book.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
@RequiredArgsConstructor
@Tag(name = "FeedBack")
public class FeedBackController {

    private final FeedBackService service;

    @PostMapping
    public ResponseEntity<Integer> saveFeedback(
            @Valid @RequestBody FeedBackRequest request,
            Authentication connectedUSer
    ){
        return ResponseEntity.ok(service.save(request, connectedUSer));

    }

    @GetMapping("/book/{book-id}")
    public ResponseEntity<PageResponse<FeedBackResponse>> findAllFeedBacksByBook(
            @PathVariable("book-id") Integer bookId,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findAllFeedBacksByBook(bookId, page, size, connectedUser));

    }
}
