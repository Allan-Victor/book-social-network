package com.allan.book.history;

import com.allan.book.common.BaseEntity;
import jakarta.persistence.Entity;
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

    // User relationship
    // Book relationship

    private boolean returned;
    private boolean returnApproved;
}

