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
public class Restaurant {

    @NotNull @Range(min = 1)
    private Long id;
    @NotNull
    private Double lat;
    @NotNull
    private Double lon;
}
