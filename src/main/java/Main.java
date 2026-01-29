import java.time.Instant;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        DataRetriever dataRetriever = new DataRetriever();

        // 1. Création d'une commande
        Order order = new Order();
        order.setId(1);
        order.setReference("CMD-001");
        order.setCreationDatetime(Instant.now());
        order.setDishOrderList(new ArrayList<>());
        order.setPayment_status(PaymentStatusEnum.UNPAID);

        System.out.println("Commande créée :");
        System.out.println(order);
        System.out.println();

        // 2. Tentative de création de vente sur commande NON payée (ERREUR)
        try {
            dataRetriever.createSaleFrom(order);
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        System.out.println();

        // 3. Paiement de la commande
        order.setPayment_status(PaymentStatusEnum.PAID);
        System.out.println("Commande payée :");
        System.out.println(order);
        System.out.println();

        // 4. Création de la vente (OK)
        Sale sale = dataRetriever.createSaleFrom(order);

        System.out.println("Vente créée avec succès :");
        System.out.println("Vente ID        : " + sale.getId());
        System.out.println("Date de vente   : " + sale.getCreationDatetime());
        System.out.println("Commande liée   : " + sale.getOrder().getReference());
        System.out.println();

        // 5. Tentative de création d'une deuxième vente (ERREUR)
        try {
            dataRetriever.createSaleFrom(order);
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        System.out.println();

        // 6. Tentative de modification d'une commande payée (ERREUR)
        try {
            order.setDishOrderList(new ArrayList<>());
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }
    }
}
