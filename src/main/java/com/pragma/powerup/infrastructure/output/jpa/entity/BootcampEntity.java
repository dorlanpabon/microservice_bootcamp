package com.pragma.powerup.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Table("bootcamps")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BootcampEntity {
    @Id
    private Long id;  // Se espera que la base de datos genere el ID de forma automática

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("capacity_count")
    private Integer capacityCount;
}
