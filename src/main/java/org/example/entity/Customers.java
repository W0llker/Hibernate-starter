package org.example.entity;

import lombok.*;
import org.example.converter.RegisteredConverter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customers {
    @Id
    @GeneratedValue(generator = "customers_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customers_generator", sequenceName = "customers_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    //    @AttributeOverrides({
//            @AttributeOverride(name = "registeredAt", column = @Column(name = "registered_at")),
//            @AttributeOverride(name = "city", column = @Column(name = "city"))
//    })
    @Embedded
    private CustomersInfo customersInfo;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    @PrimaryKeyJoinColumn  -> альтернатива JoinColumn для РК
    @JoinColumn(name = "customer_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> orders;
}
