package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.converter.RegisteredConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customers {
    @Id
    private long id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String city;
    @Convert(converter = RegisteredConverter.class)
    @Column(name = "registered_at")
    private Registered registeredAt;
    @Enumerated(EnumType.STRING)
    private Role role;
}
