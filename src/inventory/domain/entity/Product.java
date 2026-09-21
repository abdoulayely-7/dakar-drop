package inventory.domain.entity;

import java.util.UUID;

import inventory.domain.valueobject.Money;
import inventory.domain.valueobject.SKU;
import inventory.domain.exception.InsufficientStockException;

public class Product {

    private final UUID id;
    private final SKU sku;
    private String name;
    private String description;
    private Money price;
    private int stock;

    private static final int LOW_STOCK = 5;

    public Product(SKU sku, String name, String description, Money price, int stock) {

        if (sku == null || price == null) {
            throw new IllegalArgumentException("SKU et prix obligatoires");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Stock initial invalide");
        }

        this.id = UUID.randomUUID();
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public boolean isLowStock() {
        return this.stock < LOW_STOCK;
    }

    public void ajouterStock(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantité invalide");
        }

        this.stock += quantity;
    }

    public void retirerStock(int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantité invalide");
        }

        if (this.stock < quantity) {
            throw new InsufficientStockException(
                    "Stock insuffisant pour SKU: " + sku.value());
        }

        this.stock -= quantity;
    }

    public boolean estEnAlerteStock() {
        return this.stock < 5;
    }

    public UUID id() {
        return id;
    }

    public SKU sku() {
        return sku;
    }

    public String name() {
        return name;
    }

    public Money price() {
        return price;
    }

    public int stock() {
        return stock;
    }
}
