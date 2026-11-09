package org.example.entity;

import lombok.*;
import org.example.converter.RegisteredConverter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@FetchProfile(name = "withOrders", fetchOverrides = {
        @FetchProfile.FetchOverride(entity = Customers.class, association = "orders", mode = FetchMode.JOIN)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
@NamedEntityGraph(name = "customers_with_order",
        attributeNodes = {
                @NamedAttributeNode("orders")
        })
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //    @PrimaryKeyJoinColumn  -> альтернатива JoinColumn для РК
    @JoinColumn(name = "customer_id")
    @BatchSize(size = 5)
//        @Fetch(FetchMode.SELECT)
    //    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Order> orders;
}
