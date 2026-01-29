import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Order {
    private Integer id;
    private String reference;
    private Instant creationDatetime;
    private List<DishOrder> dishOrderList;
    private PaymentStatusEnum payment_status;
    private Sale sale; // nouvelle propriété

    public PaymentStatusEnum getPayment_status() { return payment_status; }
    public void setPayment_status(PaymentStatusEnum payment_status) {
        if (this.payment_status == PaymentStatusEnum.PAID) {
            throw new IllegalStateException("Commande déjà payée : modification impossible");
        }
        this.payment_status = payment_status;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public Instant getCreationDatetime() { return creationDatetime; }
    public void setCreationDatetime(Instant creationDatetime) { this.creationDatetime = creationDatetime; }

    public List<DishOrder> getDishOrderList() { return dishOrderList; }
    public void setDishOrderList(List<DishOrder> dishOrderList) {
        if (this.payment_status == PaymentStatusEnum.PAID) {
            throw new IllegalStateException("Commande déjà payée : modification impossible");
        }
        this.dishOrderList = dishOrderList;
    }

    public Sale getSale() { return sale; }
    public void setSale(Sale sale) { this.sale = sale; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", creationDatetime=" + creationDatetime +
                ", dishOrderList=" + dishOrderList +
                ", payment_status=" + payment_status +
                ", sale=" + sale +
                '}';
    }

    Double getTotalAmountWithoutVat() { throw new RuntimeException("Not implemented"); }
    Double getTotalAmountWithVat() { throw new RuntimeException("Not implemented"); }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id)
                && Objects.equals(reference, order.reference)
                && Objects.equals(creationDatetime, order.creationDatetime)
                && Objects.equals(dishOrderList, order.dishOrderList)
                && payment_status == order.payment_status
                && Objects.equals(sale, order.sale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reference, creationDatetime, dishOrderList, payment_status, sale);
    }
}
