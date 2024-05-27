package com.allan.book.feedback;

import com.allan.book.book.Book;
import com.allan.book.book.BookRepository;
import com.allan.book.common.PageResponse;
import com.allan.book.exception.OperationNotPermittedException;
import com.allan.book.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final BookRepository bookRepository;
    private final FeedBackMapper feedBackMapper;
    private final FeedBackRepository feedBackRepository;
    public Integer save(FeedBackRequest request, Authentication connectedUser) {
        // Check if book is available
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(()-> new EntityNotFoundException("No book found with the ID " +request.bookId()));

        // Check archived and shareable status of book
        if(book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give feedback for an archived or not shareable book");
        }
        // User user = (User) connectedUser.getPrincipal();
        if (Objects.equals(book.getCreatedBy(), connectedUser.getName())){     // check if owner is the one trying to borrow
            // throw an exception
            throw  new OperationNotPermittedException("You cannot give a feedback to your own book");
        }
        FeedBack feedBack = feedBackMapper.toFeedBack(request);
        return feedBackRepository.save(feedBack).getFeedBackId();
    }

    public PageResponse<FeedBackResponse> findAllFeedBacksByBook(Integer bookId, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = (User) connectedUser.getPrincipal();
        Page<FeedBack> feedBacks = feedBackRepository.findAllByBookId(bookId, pageable);
        List<FeedBackResponse> feedBackResponses = feedBacks.stream()
                .map(f -> feedBackMapper.toFeedBackResponse(f, user.getId()))
                .toList();
        return new PageResponse<>(
                feedBackResponses,
                feedBacks.getNumber(),
                feedBacks.getSize(),
                feedBacks.getTotalElements(),
                feedBacks.getTotalPages(),
                feedBacks.isFirst(),
                feedBacks.isLast()
        );

    }
}
