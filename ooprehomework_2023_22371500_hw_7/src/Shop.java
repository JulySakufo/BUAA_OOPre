public class Shop {
    private static long bottleCapacitySum;
    private static long bottleSum;
    private static long bottlePrice;
    private static long equipmentStarSum;
    private static long equipmentSum;
    private static long equipmentPrice;
    private static long foodEnergySum;
    private static long foodSum;
    private static long foodPrice;
    
    public Shop() {
        bottleCapacitySum = 0;
        bottleSum = 0;
        equipmentStarSum = 0;
        equipmentSum = 0;
        foodEnergySum = 0;
        foodSum = 0;
        bottlePrice = 0;
        equipmentPrice = 0;
        foodPrice = 0;
    }
    
    public static void bottleRecord(int capacity, long price) {
        bottleCapacitySum = bottleCapacitySum + capacity;
        bottleSum = bottleSum + 1;
        bottlePrice = bottlePrice + price;
    }
    
    public static void equipmentRecord(int star, long price) {
        equipmentStarSum = equipmentStarSum + star;
        equipmentSum = equipmentSum + 1;
        equipmentPrice = equipmentPrice + price;
    }
    
    public static void foodRecord(int energy, long price) {
        foodEnergySum = foodEnergySum + energy;
        foodSum = foodSum + 1;
        foodPrice = foodPrice + price;
    }
    
    public static long createBottleCapacity() {
        return bottleCapacitySum / bottleSum;
    }
    
    public static long createBottlePrice() {
        return bottlePrice / bottleSum;
    }
    
    public static long createEquipmentStar() {
        return equipmentStarSum / equipmentSum;
    }
    
    public static long createEquipmentPrice() {
        return equipmentPrice / equipmentSum;
    }
    
    public static long createFoodEnergy() {
        return foodEnergySum / foodSum;
    }
    
    public static long createFoodPrice() {
        return foodPrice / foodSum;
    }
}
