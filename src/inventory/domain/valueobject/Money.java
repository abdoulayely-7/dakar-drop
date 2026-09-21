package inventory.domain.valueobject;

import java.math.BigDecimal;

public record Money(BigDecimal amount, String currency) {

    public Money {

        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Valeurs obligatoires");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Montant invalide");
        }
    }

    public Money add(Money other) {

        checkCurrency(other);

        return new Money(
                this.amount.add(other.amount),
                this.currency);
    }

    public Money subtract(Money other) {

        checkCurrency(other);

        if (this.amount.compareTo(other.amount) < 0) {
            throw new IllegalArgumentException("Montant insuffisant");
        }

        return new Money(
                this.amount.subtract(other.amount),
                this.currency);
    }

    private void checkCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Devise différente");
        }
    }
}
