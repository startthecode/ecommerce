package org.authetication.ecommerce.entity.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.authetication.ecommerce.enums.Status;

@Entity
@Table(name = "status_table")
public class StatusEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        Long statusid;

        @Column(nullable = false,unique = true)
        @Enumerated(EnumType.STRING)
        private Status name;

    public StatusEntity() {
    }

    public StatusEntity(Status name) {
        this.name = name;
    }

    public Status getName() {
        return name;
    }

    public void setName(Status name) {
        this.name = name;
    }

    public Long getStatusid() {
        return statusid;
    }


}
