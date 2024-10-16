package com.smateso.proof.application.model;

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
@Table(name = "WORKFLOW")
public class Workflow {

    public static final String COLUMN_ID_NAME = "uuid";
    private String id;
    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    private String uuid;

    @Exclude
    @JsonManagedReference
    @OneToMany(fetch = EAGER, mappedBy = "workflow", cascade = ALL)
    private List<Block> blocks;

    @Exclude
    @JsonManagedReference
    @OneToMany(fetch = EAGER, mappedBy = "workflow", cascade = ALL)
    private List<Edge> edges;

    @PrePersist
    protected void onCreate() {
        if (isNull(this.uuid)) {
            this.uuid = randomUUID().toString();
        }
    }

}
