package com.adasoft.tis.domain;

import com.adasoft.tis.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "publications")
public class Publication extends BaseEntity<Long> {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String semester;
    @Column(nullable = false)
    private String fileUrl;
    @Column(nullable = false)
    private PublicationType type;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_adviser_id", nullable = false, updatable = false)
    private Adviser createdBy;

    public enum PublicationType {
        ANNOUNCEMENT,
        SPECIFICATION_SHEET
    }
}
