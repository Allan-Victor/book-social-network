package com.allan.book.history;

import com.allan.book.book.Book;
import com.allan.book.common.BaseEntity;
import com.allan.book.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTransactionHistory extends BaseEntity {

    @Id
    @SequenceGenerator(name = "trans_seq",
            sequenceName = "trans_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "trans_seq")
    private Integer transactionId;

    // User relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Book relationship
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private boolean returned;
    private boolean returnApproved;
}

