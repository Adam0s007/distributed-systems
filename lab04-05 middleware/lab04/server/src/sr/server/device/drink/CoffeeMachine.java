package server.device.drink;

import SmartHome.*;
import com.zeroc.Ice.Current;

import java.util.*;

public class CoffeeMachine extends DrinksMachine implements ICoffeeMachine {

    private int coffeeBeansCapacity;
    private int milkCapacity;
    private final int MAX_COFFEE_BEANS_CAPACITY = 500;
    private final int MAX_MILK_CAPACITY = 1000;

    private List<Coffee> coffeeList;
    private Map<CoffeeType,Integer> coffeeMapper;
    private Map<CoffeeStrength, Double> strengthModifiers;


    public CoffeeMachine(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.coffeeBeansCapacity = 0;
        this.milkCapacity = 0;
        this.coffeeList = new LinkedList<>();
        this.coffeeMapper = new HashMap<>();
        this.coffeeMapper.put(CoffeeType.Latte, 20);
        this.coffeeMapper.put(CoffeeType.Cappuccino, 40);
        this.coffeeMapper.put(CoffeeType.Espresso, 80);
        this.coffeeMapper.put(CoffeeType.Americano, 60);

        this.strengthModifiers = new HashMap<>();
        this.strengthModifiers.put(CoffeeStrength.Light, -0.2);  // 20% less beans
        this.strengthModifiers.put(CoffeeStrength.Medium, 0.0);  // No change
        this.strengthModifiers.put(CoffeeStrength.Strong, 0.2);  // 20% more beans

    }

    private int coffeeMapper(CoffeeType type, CoffeeStrength strength){
        int baseBeanCount = this.coffeeMapper.getOrDefault(type, 20);
        double modifier = strengthModifiers.getOrDefault(strength,0.0);
        return (int) (baseBeanCount * (1 + modifier));
    }


    @Override
    public boolean makeCoffee(Coffee coffee, Current current) throws ResourceLimitException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method CoffeeMachine.makeCoffee with args " + coffee.milkAmount+"," +coffee.strength + "," + coffee.type + ", current.id.name: " + current.id.name + ",  current.id.category: " + current.id.category);

        CoffeeType type = coffee.type;
        CoffeeStrength strength = coffee.strength;
        if(coffee.milkAmount > milkCapacity){
            throw new ResourceLimitException("Not enough milk in the tank", "makeCoffee");
        }
        if(coffee.milkAmount < 0){
            throw new ResourceLimitException("Amount of milk must be positive", "makeCoffee");
        }
        int coffeeBeansAmount = coffeeMapper(type, strength);
        if(coffeeBeansAmount > coffeeBeansCapacity){
            throw new ResourceLimitException("Not enough coffee beans in the tank", "makeCoffee");
        }

        super.makeHotWater(100, current);
        try {
            Thread.sleep(2000);
            this.milkCapacity -= coffee.milkAmount;
            this.coffeeBeansCapacity -= coffeeBeansAmount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Coffee is ready");
        return true;

    }

    @Override
    public void addMilk(int amount, Current current) throws MilkCapacityException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method CoffeeMachine.addMilk with args " + amount + ", current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        if(amount + milkCapacity > this.MAX_MILK_CAPACITY){
            throw new MilkCapacityException("Milk tank is full", "addMilk");
        }
        try {
            Thread.sleep(1200);
            this.milkCapacity += amount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Milk added");
    }

    @Override
    public void addCoffeeBeans(int amount, Current current) throws CoffeeBeanCapacityException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method CoffeeMachine.addCoffeeBeans with args " + amount + ", current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        if(amount + coffeeBeansCapacity > this.MAX_COFFEE_BEANS_CAPACITY){
            throw new CoffeeBeanCapacityException("Coffee beans tank is full", "addCoffeeBeans");
        }
        try {
            Thread.sleep(1200);
            this.coffeeBeansCapacity += amount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Coffee beans added");

    }

    @Override
    public List<Coffee> getCoffeeList(Current current) throws NotEnabledException{
        this.isTurnedOn(current);
        System.out.println("Method CoffeeMachine.getCoffeeList with not args, current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        return coffeeList;
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException  {
        String mess = super.getDetails(current);
        return mess + "\n{CoffeeMachine: \n" +
                "Coffee beans: " + this.coffeeBeansCapacity + "/" + this.MAX_COFFEE_BEANS_CAPACITY + "\n" +
                "Milk: " + this.milkCapacity + "/" + this.MAX_MILK_CAPACITY + "}\n";
    }

    @Override
    public void isTurnedOn(Current current) throws NotEnabledException {
        super.isTurnedOn(current);
    }

    @Override
    public DeviceStatus turnOff(Current current) throws DeviceOperationException {
        if(this.coffeeList.size() >= 7){
            this.coffeeList = this.coffeeList.subList(0,7);
        }
        return super.turnOff(current);
    }
}
