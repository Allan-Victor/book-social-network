package com.allan.book.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
@Builder
public class Token {

    @Id
    @SequenceGenerator(name = "token_seq",
    sequenceName = "token_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "token_seq")
    private Integer id;

    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}
