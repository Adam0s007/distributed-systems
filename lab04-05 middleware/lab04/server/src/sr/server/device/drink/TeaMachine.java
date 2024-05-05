package server.device.drink;

import SmartHome.*;
import com.zeroc.Ice.Current;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeaMachine extends DrinksMachine implements ITeaMachine {

    private Map<TeaType, MachineTeaInfo> teaInventory;

    public TeaMachine(DeviceInfo deviceInfo) {
        super(deviceInfo);
        this.teaInventory = new HashMap<>();

        teaInventory.put(TeaType.Black, new MachineTeaInfo(TeaType.Black, 200, 0));
        teaInventory.put(TeaType.Green, new MachineTeaInfo(TeaType.Green, 150, 0));
        teaInventory.put(TeaType.Herbal, new MachineTeaInfo(TeaType.Herbal, 100, 0));
        teaInventory.put(TeaType.Oolong, new MachineTeaInfo(TeaType.Oolong, 180, 0));
    }

    @Override
    public boolean makeTea(Tea tea, Current current) throws ResourceLimitException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method TeaMachine.makeTea with args " + tea.type + ", " + tea.amountOfLeaves + ", current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);

        MachineTeaInfo teaInfo = teaInventory.get(tea.type);
        if (teaInfo == null || tea.amountOfLeaves > teaInfo.currentAmountOfLeaves || tea.amountOfLeaves <= 0) {
            throw new ResourceLimitException("Insufficient tea leaves to make the requested tea", "makeTea");
        }
        super.makeHotWater(100, current);
        try {
            Thread.sleep(300);
            teaInfo.currentAmountOfLeaves -= tea.amountOfLeaves;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Tea is ready");
        return true;
    }

    @Override
    public void addTeaLeavesOfType(TeaType type, int amount, Current current) throws TeaLeafCapacityException,NotEnabledException {
        this.isTurnedOn(current);
        System.out.println("Method TeaMachine.addTeaLeavesOfType with args " + type + ", " + amount + ", current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);

        MachineTeaInfo teaInfo = teaInventory.get(type);
        if (teaInfo == null || amount + teaInfo.currentAmountOfLeaves > teaInfo.maxAmountOfLeaves) {
            throw new TeaLeafCapacityException("Tea leaves tank is full or type is incorrect", "addTeaLeavesOfType");
        }
        if(amount < 0) {
            throw new TeaLeafCapacityException("Amount of tea leaves must be positive", "addTeaLeavesOfType");
        }

        try {
            Thread.sleep(300);
            teaInfo.currentAmountOfLeaves += amount;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Tea leaves added");
    }

    @Override
    public List<MachineTeaInfo> getTeaList(Current current) throws NotEnabledException{
        this.isTurnedOn(current);
        System.out.println("Method TeaMachine.getTeaList with no args, current.id.name: " + current.id.name + ", current.id.category: " + current.id.category);
        return new ArrayList<>(teaInventory.values());
    }

    @Override
    public String getDetails(Current current) throws NotEnabledException {
        String mess = super.getDetails(current);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<TeaType, MachineTeaInfo> entry : teaInventory.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" : ");
            stringBuilder.append(entry.getValue().currentAmountOfLeaves + "/" + entry.getValue().maxAmountOfLeaves);
            stringBuilder.append("\n");
        }
        return mess + "\n{TeaMachine: \n" + stringBuilder.toString() + "}\n";
    }

    @Override
    public void isTurnedOn(Current current) throws NotEnabledException {
        super.isTurnedOn(current);
    }
}
