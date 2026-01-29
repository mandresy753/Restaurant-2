import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        DataRetriever dataRetriever = new DataRetriever();

        // -------------------------------
        // 1. Création d'une commande UNPAID
        // -------------------------------
        Order order = new Order();
        order.setReference("CMD-001");
        order.setCreationDatetime(Instant.now());
        order.setDishOrderList(new ArrayList<>()); // liste vide pour l'exemple
        order.setPayment_status(PaymentStatusEnum.UNPAID);

        // Sauvegarde dans la base
        order = dataRetriever.saveOrder(order);

        System.out.println("Commande créée (UNPAID) :");
        System.out.println(order);
        System.out.println();

        // -------------------------------
        // 2. Tentative de création de vente sur commande NON payée
        // -------------------------------
        try {
            dataRetriever.createSaleFrom(order);
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue (vente impossible sur commande UNPAID) : " + e.getMessage());
        }

        System.out.println();

        // -------------------------------
        // 3. Paiement de la commande
        // -------------------------------
        order.setPayment_status(PaymentStatusEnum.PAID);
        order = dataRetriever.saveOrder(order); // Mettre à jour le paiement dans la BDD

        System.out.println("Commande payée (PAID) :");
        System.out.println(order);
        System.out.println();

        // -------------------------------
        // 4. Création de la vente
        // -------------------------------
        Sale sale = dataRetriever.createSaleFrom(order);

        System.out.println("Vente créée avec succès :");
        System.out.println("Vente ID        : " + sale.getId());
        System.out.println("Date de vente   : " + sale.getCreationDatetime());
        System.out.println("Commande liée   : " + sale.getOrder().getReference());
        System.out.println();

        // -------------------------------
        // 5. Tentative de création d'une deuxième vente
        // -------------------------------
        try {
            dataRetriever.createSaleFrom(order);
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue (double vente impossible) : " + e.getMessage());
        }

        System.out.println();

        // -------------------------------
        // 6. Tentative de modification des plats après paiement
        // -------------------------------
        try {
            order.setDishOrderList(new ArrayList<>()); // Devrait lever exception
        } catch (IllegalStateException e) {
            System.out.println("Erreur attendue (modification impossible sur commande PAID) : " + e.getMessage());
        }

        System.out.println();

        // -------------------------------
        // 7. Affichage final de la commande avec sa vente
        // -------------------------------
        System.out.println("État final de la commande :");
        System.out.println(order);
    }
}
