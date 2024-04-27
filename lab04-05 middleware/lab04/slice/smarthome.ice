#ifndef SMART_HOME_DEVICES
#define SMART_HOME_DEVICES

module SmartHome {

    exception SmarthomeException {
            string message;
        };

    exception DeviceOperationException extends SmarthomeException {
        string operation;
    };


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

    struct DeviceInfo {
        string name;
        DeviceType type;
        int server;
    };

    ["java:type:java.util.ArrayList<DeviceInfo>"]
    sequence<DeviceInfo> DeviceList;

    interface ISmartHome {
        idempotent DeviceList getDevices();
        idempotent DeviceInfo getDeviceByName(string name);
        idempotent DeviceList getDevicesByServer(int server);
        idempotent DeviceInfo getDeviceByType(DeviceType type);
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
        idempotent string getDetails() throws SmarthomeException;
    };


    enum CameraMode { Night,Day,Auto};

    interface ICamera extends IDevice {
        idempotent CameraMode getCameraMode();
        idempotent bool setCameraMode(CameraMode mode) throws CameraOperationException;
    };

    interface IMotionDetectionCamera extends ICamera {
            idempotent bool enableMotionDetection() throws CameraMotionDetectionException;
            idempotent bool disableMotionDetection() throws CameraMotionDetectionException;
    };

    struct PtzPosition {
            int pan;
            int tilt;
            int zoom;
        };
    interface IPTZCamera extends ICamera {
            idempotent bool setPtz(PtzPosition ptzPosition)  throws PTZOperationException;
    };


   struct TVChannel {
           string name;
           string description;
       };
   ["java:type:java.util.ArrayList<TVChannel>"]
   sequence<TVChannel> TVChannelList;

    interface ITelevision extends IDevice {
            idempotent TVChannel getCurrentChannel() throws TelevisionOperationException;
            idempotent bool setChannel(int newChannel) throws InvalidChannelException;
            idempotent TVChannelList getChannelList();
    };

    struct SurroundEffect {
        string surroundSound;
        string pictureMode;
    };
     ["java:type:java.util.ArrayList<SurroundEffect>"]
    sequence<SurroundEffect> SurroundEffectList;

    interface IHomeCinemaTV extends ITelevision {
            idempotent SurroundEffectList getSurroundEffects();
            bool setSurroundEffect(SurroundEffect surroundEffect) throws TelevisionOperationException;
            idempotent SurroundEffect getCurrentSurroundEffect() throws SurroundEffectException;
            idempotent void disableSurroundSound();
        };

    interface IOutdoorTelevision extends ITelevision {
        idempotent bool setBrightness(int level) throws BrightnessAdjustmentException;
        idempotent bool waterproofMode(bool enable) throws TelevisionOperationException;
    };

    enum CoffeeStrength { Light, Medium, Strong };
    enum CoffeeType { Espresso, Americano, Latte, Cappuccino, Macchiato};

    struct Coffee {
        CoffeeStrength strength;
        CoffeeType type;
        int milkAmount;
    };
    ["java:type:java.util.ArrayList<Coffee>"]
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
        bool makeHotWater(int amount) throws WaterCapacityException;
        bool makeColdWater(int amount) throws WaterCapacityException;
        void addWater(int amount) throws WaterCapacityException;
        void addSugar(int amount)  throws SugarCapacityException;
    };

    interface ICoffeeMachine extends IDrinksMachine {
        bool makeCoffee(Coffee coffee) throws ResourceLimitException;
        void addMilk(int amount) throws MilkCapacityException;
        void addCoffeeBeans(int amount) throws CoffeeBeanCapacityException;
        idempotent CoffeeList getCoffeeList();
    };

    interface ITeaMachine extends IDrinksMachine {
        bool makeTea(Tea tea) throws ResourceLimitException;
        void addTeaLeavesOfType(TeaType type, int amount) throws TeaLeafCapacityException;
        idempotent TeaList getTeaList();
    };


};

#endif
