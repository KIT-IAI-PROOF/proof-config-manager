package com.smateso.proof.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static java.util.Objects.isNull;
import static java.util.UUID.randomUUID;
import static lombok.ToString.Exclude;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BLOCK")
public class Block {

    public static final String COLUMN_ID_NAME = "uuid";
    private String id;
    private String label;
    private String description;
    private String color;
    private String type;

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private String uuid;

    @Embedded
    private Position position;

    @JsonManagedReference
    @Exclude
    @OneToMany(fetch = EAGER, mappedBy = "block", cascade = ALL)
    private List<Handle> sourceHandles;

    @JsonManagedReference
    @Exclude
    @OneToMany(fetch = EAGER, mappedBy = "block", cascade = ALL)
    private List<Handle> targetHandles;

    @JsonBackReference
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "workflow_uuid")
    private Workflow workflow;

    @PrePersist
    protected void onCreate() {
        if (isNull(this.uuid)) {
            this.uuid = randomUUID().toString();
        }
    }

}
