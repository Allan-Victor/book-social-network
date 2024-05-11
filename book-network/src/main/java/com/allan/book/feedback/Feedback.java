package com.allan.book.feedback;

import com.allan.book.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends BaseEntity {

    @Id
    @SequenceGenerator(name = "feedback_seq",
            sequenceName = "feedback_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "feedback_seq")
    private Integer feedbackId;
    private Double note;
    private String comment;

}
