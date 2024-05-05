#ifndef SMART_HOME_DEVICES
#define SMART_HOME_DEVICES

module SmartHome {

    exception SmarthomeException {
            string message;
        };

    exception DeviceOperationException extends SmarthomeException {
        string operation;
    };

    exception NotEnabledException extends DeviceOperationException {};

    exception ResourceLimitException extends SmarthomeException {
        string resource;
    };

    exception CameraOperationException extends DeviceOperationException {};

    exception TelevisionOperationException extends DeviceOperationException {};

    exception CameraMotionDetectionException extends CameraOperationException {};

    exception PTZOperationException extends CameraOperationException {
        string action; // Specific action (pan, tilt, zoom) that failed
    };


    exception InvalidChannelException extends DeviceOperationException {};

    exception BrightnessAdjustmentException extends TelevisionOperationException {};

    exception SurroundEffectException extends TelevisionOperationException {};

    exception WaterCapacityException extends ResourceLimitException {};

    exception MilkCapacityException extends ResourceLimitException {};

    exception SugarCapacityException extends ResourceLimitException {};

    exception CoffeeBeanCapacityException extends ResourceLimitException {};

    exception TeaLeafCapacityException extends ResourceLimitException {};

    enum DeviceType {
    Camera,
    MotionDetectionCamera,
    PTZCamera,
    Television,
    HomeCinemaTV,
    OutdoorTelevision,
    CoffeeMachine,
    TeaMachine,
    DrinksMachine
    };

    struct DeviceDetails {
        string name;
        DeviceType type;
        int server;
    };

    ["java:type:java.util.ArrayList<DeviceDetails>"]
    sequence<DeviceDetails> DeviceList;

    interface ISmartHome {
        idempotent DeviceList getDevices();
        idempotent DeviceDetails getDeviceByName(string name);
        idempotent DeviceList getDevicesByServer(int server);
        idempotent DeviceDetails getDeviceByType(DeviceType type);
    };



    enum DeviceStatus {
    Starting,
    Disabled,
    Enabled,
    ShuttingDown
    };

    interface IDevice  {
        idempotent DeviceStatus getStatus() throws SmarthomeException;
        DeviceStatus turnOn() throws DeviceOperationException;
        DeviceStatus turnOff() throws DeviceOperationException;
        idempotent string getDetails() throws NotEnabledException;
        idempotent void isTurnedOn() throws NotEnabledException;
    };


    enum CameraMode { Night,Day,Auto};

    interface ICamera extends IDevice {
        idempotent CameraMode getCameraMode() throws NotEnabledException;
        idempotent bool setCameraMode(CameraMode mode) throws NotEnabledException,CameraOperationException;
    };

    interface IMotionDetectionCamera extends ICamera {
            idempotent bool enableMotionDetection() throws NotEnabledException,CameraMotionDetectionException;
            idempotent bool disableMotionDetection() throws NotEnabledException,CameraMotionDetectionException;
    };

    struct PtzPosition {
            int pan;
            int tilt;
            int zoom;
        };
    interface IPTZCamera extends ICamera {
            idempotent bool setPtz(PtzPosition ptzPosition)  throws NotEnabledException, PTZOperationException;
            idempotent PtzPosition getPtz() throws NotEnabledException;
    };


   struct TVChannel {
           string name;
           string description;
       };
   ["java:type:java.util.ArrayList<TVChannel>"]
   sequence<TVChannel> TVChannelList;

    interface ITelevision extends IDevice {
            idempotent TVChannel getCurrentChannel() throws NotEnabledException,TelevisionOperationException;
            idempotent bool setChannel(int newChannel) throws NotEnabledException,InvalidChannelException;
            idempotent TVChannelList getChannelList() throws NotEnabledException;
    };

    struct SurroundEffect {
        string surroundSound;
        string pictureMode;
    };
     ["java:type:java.util.ArrayList<SurroundEffect>"]
    sequence<SurroundEffect> SurroundEffectList;

    interface IHomeCinemaTV extends ITelevision {
            idempotent SurroundEffectList getEffects() throws NotEnabledException;
            bool setEffect(SurroundEffect surroundEffect) throws NotEnabledException,TelevisionOperationException;
            idempotent SurroundEffect getCurrentEffect() throws NotEnabledException,SurroundEffectException;
            idempotent void disableSound() throws NotEnabledException;
        };

    interface IOutdoorTelevision extends ITelevision {
        idempotent bool setBrightness(int level) throws NotEnabledException, BrightnessAdjustmentException;
        idempotent bool waterproofMode(bool enable) throws NotEnabledException, TelevisionOperationException;
    };

    enum CoffeeStrength { Light, Medium, Strong };
    enum CoffeeType { Espresso, Americano, Latte, Cappuccino, Macchiato};

    struct Coffee {
        CoffeeStrength strength;
        CoffeeType type;
        int milkAmount;
    };
    ["java:type:java.util.LinkedList<Coffee>"]
    sequence <Coffee> CoffeeList;

    enum TeaType { Black, Green, Herbal, Oolong };

    struct Tea {
        TeaType type;
        int amountOfLeaves;
    };
    struct MachineTeaInfo{
     TeaType type;
     int maxAmountOfLeaves;
     int currentAmountOfLeaves;
    };

    ["java:type:java.util.ArrayList<MachineTeaInfo>"]
    sequence <MachineTeaInfo> TeaList;

    interface IDrinksMachine extends IDevice {
        bool makeHotWater(int amount) throws NotEnabledException,WaterCapacityException;
        bool makeColdWater(int amount) throws NotEnabledException,WaterCapacityException;
        void addWater(int amount) throws NotEnabledException,WaterCapacityException;
        void addSugar(int amount)  throws NotEnabledException,SugarCapacityException;
    };

    interface ICoffeeMachine extends IDrinksMachine {
        bool makeCoffee(Coffee coffee) throws NotEnabledException,ResourceLimitException;
        void addMilk(int amount) throws NotEnabledException,MilkCapacityException;
        void addCoffeeBeans(int amount) throws NotEnabledException, CoffeeBeanCapacityException;
        idempotent CoffeeList getCoffeeHistory() throws NotEnabledException;
    };

    interface ITeaMachine extends IDrinksMachine {
        bool makeTea(Tea tea) throws NotEnabledException,ResourceLimitException;
        void addTeaLeavesOfType(TeaType type, int amount) throws NotEnabledException,TeaLeafCapacityException;
        idempotent TeaList getTeaList() throws NotEnabledException;
    };


};

#endif
