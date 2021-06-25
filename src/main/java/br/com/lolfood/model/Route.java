package br.com.lolfood.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @NotNull
    @Range(min = 1)
    private Long id;

    private List<Long> orders;

    private boolean confirmed;
}
