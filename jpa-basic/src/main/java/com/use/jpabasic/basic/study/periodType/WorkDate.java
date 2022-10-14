package com.use.jpabasic.basic.study.periodType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

// 반드시 기본 생성자가 필요함!!!!
@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WorkDate {
    private LocalDate startDate;
    private LocalDate endDate;
}
