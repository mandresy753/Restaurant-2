import java.time.Instant;

public class Sale {

    private final Integer id;
    private final Instant creationDatetime;
    private final Order order;

    // Constructeur avec tous les champs obligatoires
    public Sale(Integer id, Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Une vente doit être associée à une commande");
        }
        this.id = id;
        this.order = order;
        this.creationDatetime = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public Instant getCreationDatetime() {
        return creationDatetime;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Sale{id=" + id + ", creationDatetime=" + creationDatetime + ", orderRef=" + order.getReference() + "}";
    }
}
