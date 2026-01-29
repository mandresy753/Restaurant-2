import java.time.Instant;

public class Sale {

    private Integer id;
    private Instant creationDatetime;
    private Order order;

    // Constructeur vide
    public Sale() {
        this.creationDatetime = Instant.now();
    }

    // Constructeur avec arguments (optionnel)
    public Sale(Integer id, Order order) {
        this.id = id;
        this.order = order;
        this.creationDatetime = Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Instant creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
