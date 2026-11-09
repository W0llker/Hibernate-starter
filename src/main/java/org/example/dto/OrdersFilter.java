package org.example.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrdersFilter {
    String fullName;
}
