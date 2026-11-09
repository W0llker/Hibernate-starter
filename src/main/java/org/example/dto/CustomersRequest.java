package org.example.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.CustomersInfo;

import javax.validation.Valid;


public record CustomersRequest(
        @NotNull
        String fullName,
        @Valid
        CustomersInfo customersInfo) {
}
