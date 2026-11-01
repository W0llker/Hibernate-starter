package org.example.entity;

import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customer")
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Customers customer;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @Column(name = "created_at")
    private LocalDate orderDate;
}
