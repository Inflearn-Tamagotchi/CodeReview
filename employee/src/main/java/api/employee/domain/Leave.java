package api.employee.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
public class Leave {
    private int remainingDays;

    // ==== 생성자 ==== //
    public Leave(LocalDate workStartDate) {
        if (workStartDate.getYear() == LocalDate.now().getYear()) {
            this.remainingDays = 11;
        } else {
            this.remainingDays = 15;
        }
    }

    // ==== 편의 메서드 ==== //
    public void increaseRemainingDays() {
        this.remainingDays++;
    }

    public void decreaseRemainingDays() {
        if (this.remainingDays <= 0) {
            throw new IllegalArgumentException("연차의 잔여일수가 부족합니다.");
        }
        this.remainingDays--;
    }

}