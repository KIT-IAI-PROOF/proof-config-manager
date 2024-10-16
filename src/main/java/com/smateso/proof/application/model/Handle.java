package com.smateso.proof.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;

@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Handle {

    public static final String COLUMN_ID_NAME = "uuid";
    private String id;
    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private String uuid;

    @ManyToOne(cascade = ALL)
    @JsonBackReference
    @JoinColumn(name = "block_uuid")
    private Block block;

    @PrePersist
    protected void onCreate() {
        if (isNull(this.uuid)) {
            this.uuid = randomUUID().toString();
        }
    }

}
