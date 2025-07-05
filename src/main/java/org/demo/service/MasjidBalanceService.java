package org.demo.service;

import org.springframework.transaction.annotation.Transactional;
import org.demo.model.MasjidBalance;
import org.demo.repository.RepositorySaldo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MasjidBalanceService {

    private final RepositorySaldo masjidBalanceRepository;

    @Autowired
    public MasjidBalanceService(RepositorySaldo masjidBalanceRepository) {
        this.masjidBalanceRepository = masjidBalanceRepository;
    }

    @Transactional(readOnly = true)
    public List<MasjidBalance> getAllBalances() {
        return masjidBalanceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<MasjidBalance> getBalanceById(Long id) {
        return masjidBalanceRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<MasjidBalance> getBalanceByDate(LocalDate date) {
        return masjidBalanceRepository.findByDate(date);
    }

    @Transactional
    public MasjidBalance createMasjidBalance(MasjidBalance masjidBalance) {
        // You might want to add validation here before saving
        return masjidBalanceRepository.save(masjidBalance);
    }

    @Transactional
    public MasjidBalance updateMasjidBalance(Long id, MasjidBalance updatedMasjidBalance) {
        return masjidBalanceRepository.findById(id).map(existingBalance -> {
            existingBalance.setDate(updatedMasjidBalance.getDate());
            existingBalance.setCurrentCashBalance(updatedMasjidBalance.getCurrentCashBalance());
            existingBalance.setMonthlyIncome(updatedMasjidBalance.getMonthlyIncome());
            existingBalance.setMonthlyExpenditure(updatedMasjidBalance.getMonthlyExpenditure());
            existingBalance.setBeginningOfMonthBalance(updatedMasjidBalance.getBeginningOfMonthBalance());
            existingBalance.setEndOfMonthBalance(updatedMasjidBalance.getEndOfMonthBalance());
            return masjidBalanceRepository.save(existingBalance);
        }).orElseThrow(() -> new RuntimeException("Masjid Balance with ID " + id + " not found"));
    }

    @Transactional
    public void deleteMasjidBalance(Long id) {
        masjidBalanceRepository.deleteById(id);
    }

    /**
     * Calculates and updates the end of month balance based on beginning balance, income, and expenditure.
     * This is an example of business logic that would reside in the service layer.
     *
     * @param balanceId The ID of the balance record to update.
     * @return The updated MasjidBalance object.
     */
    @Transactional
    public MasjidBalance calculateAndSetEndOfMonthBalance(Long balanceId) {
        return masjidBalanceRepository.findById(balanceId).map(balance -> {
            BigDecimal beginning = Optional.ofNullable(balance.getBeginningOfMonthBalance()).orElse(BigDecimal.ZERO);
            BigDecimal income = Optional.ofNullable(balance.getMonthlyIncome()).orElse(BigDecimal.ZERO);
            BigDecimal expenditure = Optional.ofNullable(balance.getMonthlyExpenditure()).orElse(BigDecimal.ZERO);

            BigDecimal calculatedEndOfMonthBalance = beginning.add(income).subtract(expenditure);
            balance.setEndOfMonthBalance(calculatedEndOfMonthBalance);
            return masjidBalanceRepository.save(balance);
        }).orElseThrow(() -> new RuntimeException("Masjid Balance with ID " + balanceId + " not found"));
    }

    /**
     * This method could be used to get the current overall cash balance,
     * perhaps by querying the latest entry or a specifically designated "current" entry.
     * For simplicity, let's assume we'll just return the current_cash_balance from the latest record.
     * In a real application, you might have a more complex way to derive this,
     * perhaps a single record for "current cash" that is updated frequently.
     */
    @Transactional(readOnly = true)
    public BigDecimal getCurrentOverallCashBalance() {
        // This is a simplified approach. In a real application, you'd have
        // a more robust way to determine the "current" balance,
        // e.g., a specific record dedicated to the overall current cash,
        // or a query that sums all active assets.
        // For now, let's assume we fetch the latest record and return its currentCashBalance.
        List<MasjidBalance> allBalances = masjidBalanceRepository.findAll();
        return allBalances.stream()
                .max((b1, b2) -> b1.getDate().compareTo(b2.getDate()))
                .map(MasjidBalance::getCurrentCashBalance)
                .orElse(BigDecimal.ZERO); // Or throw an exception if no balance exists
    }

    /**
     * This method could be used to get the current monthly income,
     * perhaps by querying the latest entry or summing up incomes for the current month.
     */
    @Transactional(readOnly = true)
    public BigDecimal getMonthlyIncome(LocalDate month) {
        // Example: Find a balance entry for the given month and return its monthly income
        return masjidBalanceRepository.findByDate(month)
                .map(MasjidBalance::getMonthlyIncome)
                .orElse(BigDecimal.ZERO);
    }

    /**
     * This method could be used to get the current monthly expenditure.
     */
    @Transactional(readOnly = true)
    public BigDecimal getMonthlyExpenditure(LocalDate month) {
        // Example: Find a balance entry for the given month and return its monthly expenditure
        return masjidBalanceRepository.findByDate(month)
                .map(MasjidBalance::getMonthlyExpenditure)
                .orElse(BigDecimal.ZERO);
    }
}
