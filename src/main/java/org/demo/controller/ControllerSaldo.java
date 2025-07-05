package org.demo.controller;


import org.demo.model.MasjidBalance;
import org.demo.repository.RepositorySaldo;
import org.demo.service.MasjidBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/masjid-balances")
public class ControllerSaldo {

    private final MasjidBalanceService masjidBalanceService;

    @Autowired
    public ControllerSaldo(MasjidBalanceService masjidBalanceService) {
        this.masjidBalanceService = masjidBalanceService;
    }

    @GetMapping
    public ResponseEntity<List<MasjidBalance>> getAllMasjidBalances() {
        List<MasjidBalance> balances = masjidBalanceService.getAllBalances();
        return ResponseEntity.ok(balances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasjidBalance> getMasjidBalanceById(@PathVariable Long id) {
        return masjidBalanceService.getBalanceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<MasjidBalance> getMasjidBalanceByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date); // Assuming date format YYYY-MM-DD
            return masjidBalanceService.getBalanceByDate(localDate)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<MasjidBalance> createMasjidBalance(@RequestBody MasjidBalance masjidBalance) {
        MasjidBalance createdBalance = masjidBalanceService.createMasjidBalance(masjidBalance);
        return new ResponseEntity<>(createdBalance, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasjidBalance> updateMasjidBalance(@PathVariable Long id, @RequestBody MasjidBalance masjidBalance) {
        try {
            MasjidBalance updatedBalance = masjidBalanceService.updateMasjidBalance(id, masjidBalance);
            return ResponseEntity.ok(updatedBalance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasjidBalance(@PathVariable Long id) {
        try {
            masjidBalanceService.deleteMasjidBalance(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}/calculate-eom-balance")
    public ResponseEntity<MasjidBalance> calculateEndOfMonthBalance(@PathVariable Long id) {
        try {
            MasjidBalance updatedBalance = masjidBalanceService.calculateAndSetEndOfMonthBalance(id);
            return ResponseEntity.ok(updatedBalance);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/current-summary")
    public ResponseEntity<Map<String, BigDecimal>> getCurrentSummary() {
        BigDecimal currentCashBalance = masjidBalanceService.getCurrentOverallCashBalance();
        // Assuming current month for monthly income/expenditure for this summary
        LocalDate currentMonth = LocalDate.now().withDayOfMonth(1); // First day of the current month
        BigDecimal monthlyIncome = masjidBalanceService.getMonthlyIncome(currentMonth);
        BigDecimal monthlyExpenditure = masjidBalanceService.getMonthlyExpenditure(currentMonth);

        Map<String, BigDecimal> summary = Map.of(
                "currentCashBalance", currentCashBalance,
                "monthlyIncome", monthlyIncome,
                "monthlyExpenditure", monthlyExpenditure
        );
        return ResponseEntity.ok(summary);
    }
}
