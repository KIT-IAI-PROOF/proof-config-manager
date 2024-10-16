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
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    public static final String COLUMN_ID_NAME = "uuid";
    private String id;
    private String type;
    private String source;
    private String sourceHandle;
    private String target;
    private String targetHandle;
    private Boolean animated;
    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private String uuid;

    @ManyToOne(cascade = ALL)
    @JsonBackReference
    @JoinColumn(name = "workflow_uuid")
    private Workflow workflow;

    @PrePersist
    protected void onCreate() {
        if (isNull(this.uuid)) {
            this.uuid = randomUUID().toString();
        }
    }

}