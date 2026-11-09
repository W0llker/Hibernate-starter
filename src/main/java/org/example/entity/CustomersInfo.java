package org.example.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.example.converter.RegisteredConverter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Email
    private String email;
    @NotNull
    private String city;
    @Convert(converter = RegisteredConverter.class)
    @Column(name = "registered_at")
    private Registered registeredAt;
}
