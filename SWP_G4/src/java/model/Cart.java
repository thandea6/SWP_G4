/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DiscountDAO;
import dal.ProductItemDAO;
import dal.ShopProductDAO;
import dal.VoucherDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author admin
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public int getQuantityById(int id) {
        Item item = getItemByID(id);
        return item != null ? item.getQuantity() : 0;
    }

    private Item getItemByID(int id) {
        for (Item i : items) {
            if (i.getProductItem().getProductItemId() == id) {
                return i;
            }
        }
        return null;
    }

//    public void addItem(Item t) {
//        Item existingItem = getItemByID(t.getProductItem().getProductItemId());
//        if (existingItem != null) {
//            existingItem.setQuantity(existingItem.getQuantity() + t.getQuantity());
//        } else {
//            items.add(t);
//        }
//    }
    public void addItem(Item t, int availableStock) {
        if (t == null || availableStock < 0) {
            throw new IllegalArgumentException("Invalid item or stock quantity.");
        }

        Item existingItem = getItemByID(t.getProductItem().getProductItemId());
        int newQuantity = t.getQuantity();

        if (existingItem != null) {
            newQuantity += existingItem.getQuantity();
        }

        if (newQuantity > availableStock) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        }

        if (existingItem != null) {
            existingItem.setQuantity(newQuantity);
        } else {
            System.out.println(t);
            System.out.println("hihi");
            items.add(t);
        }
    }

    public void removeItem(int id) {
        Item item = getItemByID(id);
        if (item != null) {
            items.remove(item);
        }
    }

    public void removeItemAll() {
        items.clear();
    }

    public int countItemAll() {
        return items.size();
    }

    public void removeAllItemsById(int id) {
        items.removeIf(item -> item.getProductItem().getProductItemId() == id);
    }

   public double getTotalMoney() {
        VoucherDAO vd = new VoucherDAO();
        double total = 0.0;

        for (Item i : items) {
            double itemPrice = (i.getPromotionalPrice() >= 0) ? i.getPromotionalPrice() : i.getProductItem().getShopProduct().getPrice();

            // Check if voucher code is applicable
            if (i.getCode() != null && !i.getCode().equals("0")) {
                Voucher v = vd.getVoucherByCode(i.getCode());
                if (v != null) {
                    // Apply voucher discount
                    double discount = v.getReducedAmount();
                    double money = i.getQuantity() * (itemPrice - discount);
                    if (money <=0){
                        money = 0;
                    }
                    total += money;
                } else {
                    // No valid voucher, use regular or promotional price
                    total += i.getQuantity() * itemPrice;
                }
            } else {
                // No voucher code, use regular or promotional price
                total += i.getQuantity() * itemPrice;
            }
        }
        if(total <= 0){
            return 1;
        }
        return total;
    }

    public double getTotalMoneyByCheckBox(int[] selectedIds) {
        double total = 0;
        Set<Integer> selectedIdSet = Arrays.stream(selectedIds).boxed().collect(Collectors.toSet());

        for (Item i : items) {
            if (selectedIdSet.contains(i.getProductItem().getProductItemId())) {
                total += i.getQuantity() * i.getProductItem().getShopProduct().getPrice();
            }
        }
        return total;
    }

    private ProductItem getProductByID(int id, List<ProductItem> list) {
        for (ProductItem i : list) {
            if (i.getProductItemId() == id) {
                return i;
            }
        }
        return null;
    }

//    public Cart(String txt, List<ProductItem> list1) {
//        ProductItemDAO d = new ProductItemDAO();
//        items = new ArrayList<>();
//        if (txt != null && !txt.isEmpty()) {
//            String[] s = txt.split("/");
//            for (String i : s) {
//                String[] n = i.split(":");
//                try {
//                    int id = Integer.parseInt(n[0]);
//                    int quantity = Integer.parseInt(n[1]);
//                    ProductItem p = getProductByID(id, list1);
//                    if (p != null) {
//                        //ProductItem productItem, Size sizeId, String colorValue, int quantity, double price
//                        int stock,totalquantity;
//                        stock = d.getQuantitybyProductItemId(id);
//                        totalquantity = getTotalQuantity();
//                        if (totalquantity > stock) {
//                            return;
//                        } else {
//                            Item t = new Item(p, p.getSizeValue(), p.getColorValue(), quantity, p.getShopProduct().getPrice());
//                            addItem(t);
//                        }
//                    }
//                } catch (NumberFormatException e) {
//                    System.out.println("Error parsing cart data: " + e.getMessage());
//                }
//            }
//        }
//    }
    public Cart(String txt, List<ProductItem> list1) {
        ProductItemDAO productItemDAO = new ProductItemDAO();
        items = new ArrayList<>();
        System.out.println(txt);
        if (txt != null && !txt.isEmpty()) {
            String[] entries = txt.split("/");

            for (String entry : entries) {
                try {
                    String[] parts = entry.split(":");
                    int id = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);
                    String code = parts[2];

                    ProductItem productItem = getProductByID(id, list1);
                    if (productItem == null) {
                        System.out.println("Product item not found for ID: " + id);
                        continue;
                    }

                    int stock = productItemDAO.getQuantitybyProductItemId(id);
                    if (quantity > stock) {
                        System.out.println("Requested quantity exceeds available stock for item ID: " + id);
                        continue;
                    }
                    DiscountDAO discountDAO = new DiscountDAO();
                    Discount discount = discountDAO.getActiveDiscountByProductId(id);
                    double promotionalPrice = (discount != null) ? discount.getPromotionalPrice() : -1;
                    System.out.println("Promotional Price: " + promotionalPrice);

                    Item item = new Item(
                            productItem,
                            productItem.getShop().getShopName(),
                            productItem.getSizeValue(),
                            productItem.getColorValue(),
                            quantity,
                            productItem.getShopProduct().getPrice(),
                            code,
                            promotionalPrice
                    );

                    System.out.println("Item Added: " + item.getPromotionalPrice());
                    addItem(item, stock);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing cart data: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error processing entry: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Unexpected error: " + e.getMessage());
                }
            }
        }
    }

    public String updateCartAfterPurchase(String cartContent, int[] purchasedIds) {
        // Chuyá»ƒn Ä‘á»•i ná»™i dung giá»� hÃ ng tá»« chuá»—i sang danh sÃ¡ch
        List<String> cartItems = new ArrayList<>(Arrays.asList(cartContent.split("/")));
        System.out.println("Cart before removal: " + cartItems);

        // Loáº¡i bá»� cÃ¡c sáº£n pháº©m Ä‘Ã£ mua khá»�i giá»� hÃ ng
        for (int id : purchasedIds) {
            cartItems.removeIf(item -> {
                String[] parts = item.split(":");
                return parts.length > 0 && Integer.parseInt(parts[0]) == id;
            });
        }

        System.out.println("Cart after removal: " + cartItems);
        // Chuyá»ƒn Ä‘á»•i láº¡i danh sÃ¡ch giá»� hÃ ng thÃ nh chuá»—i
        return String.join("/", cartItems);
    }

    public String updateCartAfterPurchase(String cartContent, String[] purchasedItems) {
        // Convert the cart content from a string to a list
        List<String> cartItems = new ArrayList<>(Arrays.asList(cartContent.split("/")));
        System.out.println("Cart before removal: " + cartItems);

        // Iterate over each purchased item prefix and remove items that start with that prefix
        for (String purchasedItem : purchasedItems) {
            String prefix = purchasedItem.split(":")[0];  // Extract the prefix before ":"
            cartItems.removeIf(item -> item.startsWith(prefix + ":"));
        }

        System.out.println("Cart after removal: " + cartItems);

        // Convert the list of cart items back to a string
        return String.join("/", cartItems);
    }

    public String saveSelectedCartItems(String cartContent, int[] selectedIds) {
        // Convert the cart content from a string to a list
        List<String> cartItems = new ArrayList<>(Arrays.asList(cartContent.split("/")));
        System.out.println("Cart before selection: " + cartItems);

        // Create a list to hold the selected items
        List<String> selectedItems = new ArrayList<>();

        // Add the selected items to the new list without removing them from the original cart
        for (int id : selectedIds) {
            for (String item : cartItems) {
                String[] parts = item.split(":");
                if (parts.length > 0 && Integer.parseInt(parts[0]) == id) {
                    selectedItems.add(item);
                }
            }
        }

        System.out.println("Selected items: " + selectedItems);
        // Convert the list of selected items back to a string
        return String.join("/", selectedItems);
    }

    @Override
    public String toString() {
        return "Cart{" + "items=" + items + '}';
    }

    ;

    public static void main(String[] args) {
        ProductItemDAO ShopproductDao = new ProductItemDAO();
        List<ProductItem> list1 = ShopproductDao.getAllProductItem();
        Cart cart = new Cart("43:1:0", list1);
        System.out.println(cart.items.get(0).getPromotionalPrice());
        System.out.println(cart.getTotalMoney());
    }
}
