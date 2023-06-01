package dev.clima.securityjwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        this.firstValue = projection.getFirstValue();
        this.secondValue = projection.getSecondValue();
        this.thirdValue = projection.getThirdValue();
        this.fourthValue = projection.getFourthValue();
    }

}
