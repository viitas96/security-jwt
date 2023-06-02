package dev.clima.securityjwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ResponsesInfoDTO {

    private Boolean answered;
    private Integer firstValue;
    private Integer secondValue;
    private Integer thirdValue;
    private Integer fourthValue;

    public ResponsesInfoDTO(boolean answered, ResponseProjection projection) {
        this.answered = answered;
        this.firstValue = Optional.ofNullable(projection.getFirstValue()).orElse(0);
        this.secondValue = Optional.ofNullable(projection.getSecondValue()).orElse(0);
        this.thirdValue = Optional.ofNullable(projection.getThirdValue()).orElse(0);
        this.fourthValue = Optional.ofNullable(projection.getFourthValue()).orElse(0);
    }

}
