package com.allan.book.feedback;

import com.allan.book.book.Book;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FeedBackMapper {
    public FeedBack toFeedBack(FeedBackRequest request) {
        return FeedBack.builder()
                .note(request.note())
                .comment(request.comment())
                .book(Book.builder()
                        .bookId(request.bookId())
                        .archived(false) // Not required and has no impact :: just to satisfy lombok
                        .shareable(false)
                        .build())
                .build();
    }

    public FeedBackResponse toFeedBackResponse(FeedBack feedBack, Integer id) {
        return FeedBackResponse.builder()
                .note(feedBack.getNote())
                .comment(feedBack.getComment())
                .ownFeedBack(Objects.equals(feedBack.getCreatedBy(), id))
                .build();
    }
}
