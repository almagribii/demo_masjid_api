package org.demo.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@NoArgsConstructor
@Getter
@Setter

public class MasjidBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private LocalDate date;
    private BigDecimal currentCashBalance;
    private BigDecimal monthlyIncome;
    private BigDecimal monthlyExpenditure;
    private BigDecimal beginningOfMonthBalance;
    private BigDecimal endOfMonthBalance;

    public MasjidBalance(LocalDate date, BigDecimal currentCashBalance, BigDecimal monthlyIncome,
                         BigDecimal monthlyExpenditure, BigDecimal beginningOfMonthBalance,
                         BigDecimal endOfMonthBalance) {
        this.date = date;
        this.currentCashBalance = currentCashBalance;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenditure = monthlyExpenditure;
        this.beginningOfMonthBalance = beginningOfMonthBalance;
        this.endOfMonthBalance = endOfMonthBalance;
    }

    public String toString() {
        return "MasjidBalance{" +
                "id" + id +
                ", date=" + date +
                ", currentCashBalance=" + currentCashBalance +
                ", monthlyIncome=" + monthlyIncome +
                ", monthlyExpenditure=" + monthlyExpenditure +
                ", beginningOfMonthBalance=" + beginningOfMonthBalance +
                ", endOfMonthBalance=" + endOfMonthBalance +
                '}';
    }
}
