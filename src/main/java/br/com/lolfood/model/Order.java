package br.com.lolfood.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @NotNull @Range(min = 1)
    private Long id;
    @NotNull @Range(min = 1)
    private Long restaurant;
    @NotNull @Range(min = 1)
    private Long client;

    private String pickup;

    private String delivered;

    private OrderStatus status;
}
