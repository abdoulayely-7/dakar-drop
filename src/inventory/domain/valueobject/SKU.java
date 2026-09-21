package inventory.domain.valueobject;

public record SKU(String value) {

    public SKU {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SKU obligatoire");
        }

        if (!value.matches("[A-Z0-9\\-]{4,30}")) {
            throw new IllegalArgumentException("SKU invalide");
        }
    }

}
