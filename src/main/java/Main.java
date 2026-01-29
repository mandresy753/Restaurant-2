//public class Main {
//    public static void main(String[] args) {
//        DataRetriever dataRetriever = new DataRetriever();
//        Dish saladeVerte = dataRetriever.findDishById(1);
//        System.out.println(saladeVerte);
//
//        Dish poulet = dataRetriever.findDishById(2);
//        System.out.println(poulet);
//
//        Dish rizLegume = dataRetriever.findDishById(3);
//        rizLegume.setPrice(100.0);
//        Dish newRizLegume = dataRetriever.saveDish(rizLegume);
//        System.out.println(newRizLegume); // Should not throw exception
//
//
////        Dish rizLegumeAgain = dataRetriever.findDishById(3);
////        rizLegumeAgain.setPrice(null);
////        Dish savedNewRizLegume = dataRetriever.saveDish(rizLegume);
////        System.out.println(savedNewRizLegume); // Should throw exception
//
//        Ingredient laitue = dataRetriever.findIngredientById(1);
//        System.out.println(laitue);
//
//    }
//}



import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever retriever = new DataRetriever();

        try {
            // Créer les DishIngredient avec les IDs existants
            DishIngredient di1 = new DishIngredient();
            di1.setIngredient(retriever.findIngredientById(1)); // Laitue
            di1.setQuantity(0.2);

            DishIngredient di2 = new DishIngredient();
            di2.setIngredient(retriever.findIngredientById(2)); // Tomate
            di2.setQuantity(0.15);

            DishIngredient di3 = new DishIngredient();
            di3.setIngredient(retriever.findIngredientById(3)); // Poulet
            di3.setQuantity(1.0);

            DishIngredient di4 = new DishIngredient();
            di4.setIngredient(retriever.findIngredientById(4)); // Chocolat
            di4.setQuantity(0.3);

            DishIngredient di5 = new DishIngredient();
            di5.setIngredient(retriever.findIngredientById(5)); // Beurre
            di5.setQuantity(0.2);

            // Créer les plats en utilisant leurs IDs existants
            Dish salad = retriever.findDishById(1); // Salade fraîche
            Dish grilledChicken = retriever.findDishById(2); // Poulet grillé
            Dish chocolateCake = retriever.findDishById(4); // Gâteau au chocolat

            // Associer les ingrédients aux plats
            salad.setDishIngredients(List.of(di1, di2));
            grilledChicken.setDishIngredients(List.of(di3));
            chocolateCake.setDishIngredients(List.of(di4, di5));

            // Créer les DishOrder pour la commande
            DishOrder order1 = new DishOrder();
            order1.setDish(salad);
            order1.setQuantity(2); // 2 salades

            DishOrder order2 = new DishOrder();
            order2.setDish(grilledChicken);
            order2.setQuantity(1); // 1 poulet grillé

            DishOrder order3 = new DishOrder();
            order3.setDish(chocolateCake);
            order3.setQuantity(3); // 3 gâteaux au chocolat

            List<DishOrder> dishOrders = List.of(order1, order2, order3);

            // Créer l'objet Order
            Order order = new Order();
            order.setReference("ORD-20260129-001");
            order.setCreationDatetime(Instant.now());
            order.setDishOrderList(dishOrders);

            // Sauvegarder la commande
            Order savedOrder = retriever.saveOrder(order);

            System.out.println("Commande sauvegardée avec succès !");
            System.out.println("ID de la commande : " + savedOrder.getId());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors du test de saveOrder.");
        }
    }
}
