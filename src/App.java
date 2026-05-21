import java.math.BigDecimal;
import java.util.List;

import inventory.domain.entity.Product;
import inventory.domain.valueobject.Money;
import inventory.domain.valueobject.SKU;

public class App {

    public static void main(String[] args) {

        Product product = new Product(
                new SKU("TSHIRT-NIKE-001"),
                "T-Shirt Nike",
                "T-shirt coton",
                new Money(new BigDecimal("5000"), "XOF"),
                10);

        System.out.println("Stock initial: " + product.stock());
        System.out.println("Low stock ? " + product.isLowStock());

        product.ajouterStock(5);
        System.out.println("Après ajout: " + product.stock());

        product.retirerStock(3);
        System.out.println("Après retrait: " + product.stock());

        System.out.println("Low stock ? " + product.isLowStock());

        try {
            product.retirerStock(100);
        } catch (Exception e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }

        Product p1 = new Product(
                new SKU("CAP-001"),
                "Casquette",
                "Adidas cap",
                new Money(new BigDecimal("3000"), "XOF"),
                3);

        Product p2 = new Product(
                new SKU("SHOES-001"),
                "Chaussure",
                "Running shoes",
                new Money(new BigDecimal("15000"), "XOF"),
                2);

        Product p3 = new Product(
                new SKU("WATCH-001"),
                "Montre",
                "Smart watch",
                new Money(new BigDecimal("20000"), "XOF"),
                20);

        List<Product> lowStockProducts = List.of(p1, p2, p3)
                .stream()
                .filter(Product::isLowStock)
                .toList();

        System.out.println("=== PRODUITS EN ALERTE ===");
        System.out.println("Nombre: " + lowStockProducts.size());
    }

}
