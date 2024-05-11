package com.allan.book.book;

import com.allan.book.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends BaseEntity {

    @Id
    @SequenceGenerator(name = "book_seq",
            sequenceName = "book_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "book_seq")
    private Integer bookId;
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

}
