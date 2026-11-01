package org.example.entity;

import lombok.*;
import org.example.converter.RegisteredConverter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class CustomersInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 8982995080610033048L;
    private String email;
    private String city;
    @Convert(converter = RegisteredConverter.class)
    @Column(name = "registered_at")
    private Registered registeredAt;
    @Transient
    @Generated(GenerationTime.INSERT)
    @ColumnDefault("150")
    @Basic(optional = false, fetch = FetchType.EAGER)
    private String nullParam;

}
