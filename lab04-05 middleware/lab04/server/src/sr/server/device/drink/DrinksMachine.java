package server.device.drink;

import SmartHome.*;
import com.zeroc.Ice.Current;
import server.device.Device;

public class DrinksMachine extends Device implements IDrinksMachine {

    private int sugarCapacity;
    private int waterCapacity;
    private final int MAX_WATER_CAPACITY = 1000;
    private final int MAX_SUGAR_CAPACITY = 100;

    public DrinksMachine(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.sugarCapacity = 0;
        this.waterCapacity = 0;
    }
    @Override
    public boolean makeHotWater(int amount, Current current) throws WaterCapacityException {
        System.out.println("Method DrinksMachine.makeHotWater with args " + amount + " called by: " + current.id.name + ", category: " + current.id.category);

        if(amount > waterCapacity) {
            throw new WaterCapacityException("Not enough water in the tank", "makeHotWater");
        }
        try {
            Thread.sleep(1200);
            this.waterCapacity -= amount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Hot water is ready");
        return true;

    }

    @Override
    public boolean makeColdWater(int amount, Current current) throws WaterCapacityException {
        System.out.println("Method DrinksMachine.makeColdWater with args " + amount + " called by: " + current.id.name + ", category: " + current.id.category);

        if(amount > waterCapacity) {
            throw new WaterCapacityException("Not enough water in the tank", "makeColdWater");
        }
        try {
            Thread.sleep(1200);
            this.waterCapacity -= amount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Cold water is ready");
        return true;
    }

    @Override
    public void addWater(int amount, Current current) throws WaterCapacityException {
        System.out.println("Method DrinksMachine.addWater with args " + amount + " called by: " + current.id.name + ", category: " + current.id.category);

        if(amount + waterCapacity > this.MAX_WATER_CAPACITY) {
            throw new WaterCapacityException("Water tank is full", "addWater");
        }
        try {
            Thread.sleep(1200);
            this.waterCapacity += amount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Water added");
    }

    @Override
    public void addSugar(int amount, Current current) throws SugarCapacityException {
        System.out.println("Method DrinksMachine.addSugar with args " + amount + " called by: " + current.id.name + ", category: " + current.id.category);

        if(amount + sugarCapacity > this.MAX_SUGAR_CAPACITY) {
            throw new SugarCapacityException("Sugar tank is full", "addSugar");
        }
        try {
            Thread.sleep(1200);
            this.sugarCapacity += amount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Sugar added");
    }

    @Override
    public String getDetails(Current current) throws SmarthomeException {

        return super.getDetails(current)+ "water capacity: " + this.waterCapacity + "/" + this.MAX_WATER_CAPACITY + "\n sugar capacity: " + this.sugarCapacity + "/" + this.MAX_SUGAR_CAPACITY + "\n";
    }


}
