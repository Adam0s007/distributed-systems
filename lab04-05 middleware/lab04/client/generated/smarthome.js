//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `smarthome.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

/* eslint-disable */
/* jshint ignore: start */

(function(module, require, exports)
{
    const Ice = require("ice").Ice;
    const _ModuleRegistry = Ice._ModuleRegistry;
    const Slice = Ice.Slice;

    let SmartHome = _ModuleRegistry.module("SmartHome");

    SmartHome.SmarthomeException = class extends Ice.UserException
    {
        constructor(message = "", _cause = "")
        {
            super(_cause);
            this.message = message;
        }

        static get _parent()
        {
            return Ice.UserException;
        }

        static get _id()
        {
            return "::SmartHome::SmarthomeException";
        }

        _mostDerivedType()
        {
            return SmartHome.SmarthomeException;
        }

        _writeMemberImpl(ostr)
        {
            ostr.writeString(this.message);
        }

        _readMemberImpl(istr)
        {
            this.message = istr.readString();
        }
    };

    SmartHome.DeviceOperationException = class extends SmartHome.SmarthomeException
    {
        constructor(message, operation = "", _cause = "")
        {
            super(message, _cause);
            this.operation = operation;
        }

        static get _parent()
        {
            return SmartHome.SmarthomeException;
        }

        static get _id()
        {
            return "::SmartHome::DeviceOperationException";
        }

        _mostDerivedType()
        {
            return SmartHome.DeviceOperationException;
        }

        _writeMemberImpl(ostr)
        {
            ostr.writeString(this.operation);
        }

        _readMemberImpl(istr)
        {
            this.operation = istr.readString();
        }
    };

    SmartHome.NotEnabledException = class extends SmartHome.DeviceOperationException
    {
        constructor(message, operation, _cause = "")
        {
            super(message, operation, _cause);
        }

        static get _parent()
        {
            return SmartHome.DeviceOperationException;
        }

        static get _id()
        {
            return "::SmartHome::NotEnabledException";
        }

        _mostDerivedType()
        {
            return SmartHome.NotEnabledException;
        }
    };

    SmartHome.ResourceLimitException = class extends SmartHome.SmarthomeException
    {
        constructor(message, resource = "", _cause = "")
        {
            super(message, _cause);
            this.resource = resource;
        }

        static get _parent()
        {
            return SmartHome.SmarthomeException;
        }

        static get _id()
        {
            return "::SmartHome::ResourceLimitException";
        }

        _mostDerivedType()
        {
            return SmartHome.ResourceLimitException;
        }

        _writeMemberImpl(ostr)
        {
            ostr.writeString(this.resource);
        }

        _readMemberImpl(istr)
        {
            this.resource = istr.readString();
        }
    };

    SmartHome.CameraOperationException = class extends SmartHome.DeviceOperationException
    {
        constructor(message, operation, _cause = "")
        {
            super(message, operation, _cause);
        }

        static get _parent()
        {
            return SmartHome.DeviceOperationException;
        }

        static get _id()
        {
            return "::SmartHome::CameraOperationException";
        }

        _mostDerivedType()
        {
            return SmartHome.CameraOperationException;
        }
    };

    SmartHome.TelevisionOperationException = class extends SmartHome.DeviceOperationException
    {
        constructor(message, operation, _cause = "")
        {
            super(message, operation, _cause);
        }

        static get _parent()
        {
            return SmartHome.DeviceOperationException;
        }

        static get _id()
        {
            return "::SmartHome::TelevisionOperationException";
        }

        _mostDerivedType()
        {
            return SmartHome.TelevisionOperationException;
        }
    };

    SmartHome.CameraMotionDetectionException = class extends SmartHome.CameraOperationException
    {
        constructor(message, operation, _cause = "")
        {
            super(message, operation, _cause);
        }

        static get _parent()
        {
            return SmartHome.CameraOperationException;
        }

        static get _id()
        {
            return "::SmartHome::CameraMotionDetectionException";
        }

        _mostDerivedType()
        {
            return SmartHome.CameraMotionDetectionException;
        }
    };

    SmartHome.PTZOperationException = class extends SmartHome.CameraOperationException
    {
        constructor(message, operation, action = "", _cause = "")
        {
            super(message, operation, _cause);
            this.action = action;
        }

        static get _parent()
        {
            return SmartHome.CameraOperationException;
        }

        static get _id()
        {
            return "::SmartHome::PTZOperationException";
        }

        _mostDerivedType()
        {
            return SmartHome.PTZOperationException;
        }

        _writeMemberImpl(ostr)
        {
            ostr.writeString(this.action);
        }

        _readMemberImpl(istr)
        {
            this.action = istr.readString();
        }
    };

    SmartHome.InvalidChannelException = class extends SmartHome.DeviceOperationException
    {
        constructor(message, operation, _cause = "")
        {
            super(message, operation, _cause);
        }

        static get _parent()
        {
            return SmartHome.DeviceOperationException;
        }

        static get _id()
        {
            return "::SmartHome::InvalidChannelException";
        }

        _mostDerivedType()
        {
            return SmartHome.InvalidChannelException;
        }
    };

    SmartHome.BrightnessAdjustmentException = class extends SmartHome.TelevisionOperationException
    {
        constructor(message, operation, _cause = "")
        {
            super(message, operation, _cause);
        }

        static get _parent()
        {
            return SmartHome.TelevisionOperationException;
        }

        static get _id()
        {
            return "::SmartHome::BrightnessAdjustmentException";
        }

        _mostDerivedType()
        {
            return SmartHome.BrightnessAdjustmentException;
        }
    };

    SmartHome.SurroundEffectException = class extends SmartHome.TelevisionOperationException
    {
        constructor(message, operation, _cause = "")
        {
            super(message, operation, _cause);
        }

        static get _parent()
        {
            return SmartHome.TelevisionOperationException;
        }

        static get _id()
        {
            return "::SmartHome::SurroundEffectException";
        }

        _mostDerivedType()
        {
            return SmartHome.SurroundEffectException;
        }
    };

    SmartHome.WaterCapacityException = class extends SmartHome.ResourceLimitException
    {
        constructor(message, resource, _cause = "")
        {
            super(message, resource, _cause);
        }

        static get _parent()
        {
            return SmartHome.ResourceLimitException;
        }

        static get _id()
        {
            return "::SmartHome::WaterCapacityException";
        }

        _mostDerivedType()
        {
            return SmartHome.WaterCapacityException;
        }
    };

    SmartHome.MilkCapacityException = class extends SmartHome.ResourceLimitException
    {
        constructor(message, resource, _cause = "")
        {
            super(message, resource, _cause);
        }

        static get _parent()
        {
            return SmartHome.ResourceLimitException;
        }

        static get _id()
        {
            return "::SmartHome::MilkCapacityException";
        }

        _mostDerivedType()
        {
            return SmartHome.MilkCapacityException;
        }
    };

    SmartHome.SugarCapacityException = class extends SmartHome.ResourceLimitException
    {
        constructor(message, resource, _cause = "")
        {
            super(message, resource, _cause);
        }

        static get _parent()
        {
            return SmartHome.ResourceLimitException;
        }

        static get _id()
        {
            return "::SmartHome::SugarCapacityException";
        }

        _mostDerivedType()
        {
            return SmartHome.SugarCapacityException;
        }
    };

    SmartHome.CoffeeBeanCapacityException = class extends SmartHome.ResourceLimitException
    {
        constructor(message, resource, _cause = "")
        {
            super(message, resource, _cause);
        }

        static get _parent()
        {
            return SmartHome.ResourceLimitException;
        }

        static get _id()
        {
            return "::SmartHome::CoffeeBeanCapacityException";
        }

        _mostDerivedType()
        {
            return SmartHome.CoffeeBeanCapacityException;
        }
    };

    SmartHome.TeaLeafCapacityException = class extends SmartHome.ResourceLimitException
    {
        constructor(message, resource, _cause = "")
        {
            super(message, resource, _cause);
        }

        static get _parent()
        {
            return SmartHome.ResourceLimitException;
        }

        static get _id()
        {
            return "::SmartHome::TeaLeafCapacityException";
        }

        _mostDerivedType()
        {
            return SmartHome.TeaLeafCapacityException;
        }
    };

    SmartHome.DeviceType = Slice.defineEnum([
        ['Camera', 0], ['MotionDetectionCamera', 1], ['PTZCamera', 2], ['Television', 3], ['HomeCinemaTV', 4],
        ['OutdoorTelevision', 5], ['CoffeeMachine', 6], ['TeaMachine', 7], ['DrinksMachine', 8]]);

    SmartHome.DeviceInfo = class
    {
        constructor(name = "", type = SmartHome.DeviceType.Camera, server = 0)
        {
            this.name = name;
            this.type = type;
            this.server = server;
        }

        _write(ostr)
        {
            ostr.writeString(this.name);
            SmartHome.DeviceType._write(ostr, this.type);
            ostr.writeInt(this.server);
        }

        _read(istr)
        {
            this.name = istr.readString();
            this.type = SmartHome.DeviceType._read(istr);
            this.server = istr.readInt();
        }

        static get minWireSize()
        {
            return  6;
        }
    };

    Slice.defineStruct(SmartHome.DeviceInfo, true, true);

    Slice.defineSequence(SmartHome, "DeviceListHelper", "SmartHome.DeviceInfo", false);

    const iceC_SmartHome_ISmartHome_ids = [
        "::Ice::Object",
        "::SmartHome::ISmartHome"
    ];

    SmartHome.ISmartHome = class extends Ice.Object
    {
    };

    SmartHome.ISmartHomePrx = class extends Ice.ObjectPrx
    {
    };

    Slice.defineOperations(SmartHome.ISmartHome, SmartHome.ISmartHomePrx, iceC_SmartHome_ISmartHome_ids, 1,
    {
        "getDevices": [, 2, 2, , ["SmartHome.DeviceListHelper"], , , , , ],
        "getDeviceByName": [, 2, 2, , [SmartHome.DeviceInfo], [[7]], , , , ],
        "getDevicesByServer": [, 2, 2, , ["SmartHome.DeviceListHelper"], [[3]], , , , ],
        "getDeviceByType": [, 2, 2, , [SmartHome.DeviceInfo], [[SmartHome.DeviceType._helper]], , , , ]
    });

    SmartHome.DeviceStatus = Slice.defineEnum([
        ['Starting', 0], ['Disabled', 1], ['Enabled', 2], ['ShuttingDown', 3]]);

    const iceC_SmartHome_IDevice_ids = [
        "::Ice::Object",
        "::SmartHome::IDevice"
    ];

    SmartHome.IDevice = class extends Ice.Object
    {
    };

    SmartHome.IDevicePrx = class extends Ice.ObjectPrx
    {
    };

    Slice.defineOperations(SmartHome.IDevice, SmartHome.IDevicePrx, iceC_SmartHome_IDevice_ids, 1,
    {
        "getStatus": [, 2, 2, , [SmartHome.DeviceStatus._helper], , ,
        [
            SmartHome.SmarthomeException
        ], , ],
        "turnOn": [, , , , [SmartHome.DeviceStatus._helper], , ,
        [
            SmartHome.DeviceOperationException
        ], , ],
        "turnOff": [, , , , [SmartHome.DeviceStatus._helper], , ,
        [
            SmartHome.DeviceOperationException
        ], , ],
        "getDetails": [, 2, 2, , [7], , ,
        [
            SmartHome.NotEnabledException
        ], , ],
        "isTurnedOn": [, 2, 2, , , , ,
        [
            SmartHome.NotEnabledException
        ], , ]
    });

    SmartHome.CameraMode = Slice.defineEnum([
        ['Night', 0], ['Day', 1], ['Auto', 2]]);

    const iceC_SmartHome_ICamera_ids = [
        "::Ice::Object",
        "::SmartHome::ICamera",
        "::SmartHome::IDevice"
    ];

    SmartHome.ICamera = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.IDevice
            ];
        }
    };

    SmartHome.ICameraPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.IDevicePrx];
        }
    };

    Slice.defineOperations(SmartHome.ICamera, SmartHome.ICameraPrx, iceC_SmartHome_ICamera_ids, 1,
    {
        "getCameraMode": [, 2, 2, , [SmartHome.CameraMode._helper], , ,
        [
            SmartHome.NotEnabledException
        ], , ],
        "setCameraMode": [, 2, 2, , [1], [[SmartHome.CameraMode._helper]], ,
        [
            SmartHome.CameraOperationException,
            SmartHome.NotEnabledException
        ], , ]
    });

    const iceC_SmartHome_IMotionDetectionCamera_ids = [
        "::Ice::Object",
        "::SmartHome::ICamera",
        "::SmartHome::IDevice",
        "::SmartHome::IMotionDetectionCamera"
    ];

    SmartHome.IMotionDetectionCamera = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.ICamera
            ];
        }
    };

    SmartHome.IMotionDetectionCameraPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.ICameraPrx];
        }
    };

    Slice.defineOperations(SmartHome.IMotionDetectionCamera, SmartHome.IMotionDetectionCameraPrx, iceC_SmartHome_IMotionDetectionCamera_ids, 3,
    {
        "enableMotionDetection": [, 2, 2, , [1], , ,
        [
            SmartHome.CameraMotionDetectionException,
            SmartHome.NotEnabledException
        ], , ],
        "disableMotionDetection": [, 2, 2, , [1], , ,
        [
            SmartHome.CameraMotionDetectionException,
            SmartHome.NotEnabledException
        ], , ]
    });

    SmartHome.PtzPosition = class
    {
        constructor(pan = 0, tilt = 0, zoom = 0)
        {
            this.pan = pan;
            this.tilt = tilt;
            this.zoom = zoom;
        }

        _write(ostr)
        {
            ostr.writeInt(this.pan);
            ostr.writeInt(this.tilt);
            ostr.writeInt(this.zoom);
        }

        _read(istr)
        {
            this.pan = istr.readInt();
            this.tilt = istr.readInt();
            this.zoom = istr.readInt();
        }

        static get minWireSize()
        {
            return  12;
        }
    };

    Slice.defineStruct(SmartHome.PtzPosition, true, false);

    const iceC_SmartHome_IPTZCamera_ids = [
        "::Ice::Object",
        "::SmartHome::ICamera",
        "::SmartHome::IDevice",
        "::SmartHome::IPTZCamera"
    ];

    SmartHome.IPTZCamera = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.ICamera
            ];
        }
    };

    SmartHome.IPTZCameraPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.ICameraPrx];
        }
    };

    Slice.defineOperations(SmartHome.IPTZCamera, SmartHome.IPTZCameraPrx, iceC_SmartHome_IPTZCamera_ids, 3,
    {
        "setPtz": [, 2, 2, , [1], [[SmartHome.PtzPosition]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.PTZOperationException
        ], , ],
        "getPtz": [, 2, 2, , [SmartHome.PtzPosition], , ,
        [
            SmartHome.NotEnabledException
        ], , ]
    });

    SmartHome.TVChannel = class
    {
        constructor(name = "", description = "")
        {
            this.name = name;
            this.description = description;
        }

        _write(ostr)
        {
            ostr.writeString(this.name);
            ostr.writeString(this.description);
        }

        _read(istr)
        {
            this.name = istr.readString();
            this.description = istr.readString();
        }

        static get minWireSize()
        {
            return  2;
        }
    };

    Slice.defineStruct(SmartHome.TVChannel, true, true);

    Slice.defineSequence(SmartHome, "TVChannelListHelper", "SmartHome.TVChannel", false);

    const iceC_SmartHome_ITelevision_ids = [
        "::Ice::Object",
        "::SmartHome::IDevice",
        "::SmartHome::ITelevision"
    ];

    SmartHome.ITelevision = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.IDevice
            ];
        }
    };

    SmartHome.ITelevisionPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.IDevicePrx];
        }
    };

    Slice.defineOperations(SmartHome.ITelevision, SmartHome.ITelevisionPrx, iceC_SmartHome_ITelevision_ids, 2,
    {
        "getCurrentChannel": [, 2, 2, , [SmartHome.TVChannel], , ,
        [
            SmartHome.NotEnabledException,
            SmartHome.TelevisionOperationException
        ], , ],
        "setChannel": [, 2, 2, , [1], [[3]], ,
        [
            SmartHome.InvalidChannelException,
            SmartHome.NotEnabledException
        ], , ],
        "getChannelList": [, 2, 2, , ["SmartHome.TVChannelListHelper"], , ,
        [
            SmartHome.NotEnabledException
        ], , ]
    });

    SmartHome.SurroundEffect = class
    {
        constructor(surroundSound = "", pictureMode = "")
        {
            this.surroundSound = surroundSound;
            this.pictureMode = pictureMode;
        }

        _write(ostr)
        {
            ostr.writeString(this.surroundSound);
            ostr.writeString(this.pictureMode);
        }

        _read(istr)
        {
            this.surroundSound = istr.readString();
            this.pictureMode = istr.readString();
        }

        static get minWireSize()
        {
            return  2;
        }
    };

    Slice.defineStruct(SmartHome.SurroundEffect, true, true);

    Slice.defineSequence(SmartHome, "SurroundEffectListHelper", "SmartHome.SurroundEffect", false);

    const iceC_SmartHome_IHomeCinemaTV_ids = [
        "::Ice::Object",
        "::SmartHome::IDevice",
        "::SmartHome::IHomeCinemaTV",
        "::SmartHome::ITelevision"
    ];

    SmartHome.IHomeCinemaTV = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.ITelevision
            ];
        }
    };

    SmartHome.IHomeCinemaTVPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.ITelevisionPrx];
        }
    };

    Slice.defineOperations(SmartHome.IHomeCinemaTV, SmartHome.IHomeCinemaTVPrx, iceC_SmartHome_IHomeCinemaTV_ids, 2,
    {
        "getEffects": [, 2, 2, , ["SmartHome.SurroundEffectListHelper"], , ,
        [
            SmartHome.NotEnabledException
        ], , ],
        "setEffect": [, , , , [1], [[SmartHome.SurroundEffect]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.TelevisionOperationException
        ], , ],
        "getCurrentEffect": [, 2, 2, , [SmartHome.SurroundEffect], , ,
        [
            SmartHome.NotEnabledException,
            SmartHome.SurroundEffectException
        ], , ],
        "disableSound": [, 2, 2, , , , ,
        [
            SmartHome.NotEnabledException
        ], , ]
    });

    const iceC_SmartHome_IOutdoorTelevision_ids = [
        "::Ice::Object",
        "::SmartHome::IDevice",
        "::SmartHome::IOutdoorTelevision",
        "::SmartHome::ITelevision"
    ];

    SmartHome.IOutdoorTelevision = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.ITelevision
            ];
        }
    };

    SmartHome.IOutdoorTelevisionPrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.ITelevisionPrx];
        }
    };

    Slice.defineOperations(SmartHome.IOutdoorTelevision, SmartHome.IOutdoorTelevisionPrx, iceC_SmartHome_IOutdoorTelevision_ids, 2,
    {
        "setBrightness": [, 2, 2, , [1], [[3]], ,
        [
            SmartHome.BrightnessAdjustmentException,
            SmartHome.NotEnabledException
        ], , ],
        "waterproofMode": [, 2, 2, , [1], [[1]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.TelevisionOperationException
        ], , ]
    });

    SmartHome.CoffeeStrength = Slice.defineEnum([
        ['Light', 0], ['Medium', 1], ['Strong', 2]]);

    SmartHome.CoffeeType = Slice.defineEnum([
        ['Espresso', 0], ['Americano', 1], ['Latte', 2], ['Cappuccino', 3], ['Macchiato', 4]]);

    SmartHome.Coffee = class
    {
        constructor(strength = SmartHome.CoffeeStrength.Light, type = SmartHome.CoffeeType.Espresso, milkAmount = 0)
        {
            this.strength = strength;
            this.type = type;
            this.milkAmount = milkAmount;
        }

        _write(ostr)
        {
            SmartHome.CoffeeStrength._write(ostr, this.strength);
            SmartHome.CoffeeType._write(ostr, this.type);
            ostr.writeInt(this.milkAmount);
        }

        _read(istr)
        {
            this.strength = SmartHome.CoffeeStrength._read(istr);
            this.type = SmartHome.CoffeeType._read(istr);
            this.milkAmount = istr.readInt();
        }

        static get minWireSize()
        {
            return  6;
        }
    };

    Slice.defineStruct(SmartHome.Coffee, true, true);

    Slice.defineSequence(SmartHome, "CoffeeListHelper", "SmartHome.Coffee", false);

    SmartHome.TeaType = Slice.defineEnum([
        ['Black', 0], ['Green', 1], ['Herbal', 2], ['Oolong', 3]]);

    SmartHome.Tea = class
    {
        constructor(type = SmartHome.TeaType.Black, amountOfLeaves = 0)
        {
            this.type = type;
            this.amountOfLeaves = amountOfLeaves;
        }

        _write(ostr)
        {
            SmartHome.TeaType._write(ostr, this.type);
            ostr.writeInt(this.amountOfLeaves);
        }

        _read(istr)
        {
            this.type = SmartHome.TeaType._read(istr);
            this.amountOfLeaves = istr.readInt();
        }

        static get minWireSize()
        {
            return  5;
        }
    };

    Slice.defineStruct(SmartHome.Tea, true, true);

    SmartHome.MachineTeaInfo = class
    {
        constructor(type = SmartHome.TeaType.Black, maxAmountOfLeaves = 0, currentAmountOfLeaves = 0)
        {
            this.type = type;
            this.maxAmountOfLeaves = maxAmountOfLeaves;
            this.currentAmountOfLeaves = currentAmountOfLeaves;
        }

        _write(ostr)
        {
            SmartHome.TeaType._write(ostr, this.type);
            ostr.writeInt(this.maxAmountOfLeaves);
            ostr.writeInt(this.currentAmountOfLeaves);
        }

        _read(istr)
        {
            this.type = SmartHome.TeaType._read(istr);
            this.maxAmountOfLeaves = istr.readInt();
            this.currentAmountOfLeaves = istr.readInt();
        }

        static get minWireSize()
        {
            return  9;
        }
    };

    Slice.defineStruct(SmartHome.MachineTeaInfo, true, true);

    Slice.defineSequence(SmartHome, "TeaListHelper", "SmartHome.MachineTeaInfo", false);

    const iceC_SmartHome_IDrinksMachine_ids = [
        "::Ice::Object",
        "::SmartHome::IDevice",
        "::SmartHome::IDrinksMachine"
    ];

    SmartHome.IDrinksMachine = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.IDevice
            ];
        }
    };

    SmartHome.IDrinksMachinePrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.IDevicePrx];
        }
    };

    Slice.defineOperations(SmartHome.IDrinksMachine, SmartHome.IDrinksMachinePrx, iceC_SmartHome_IDrinksMachine_ids, 2,
    {
        "makeHotWater": [, , , , [1], [[3]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.WaterCapacityException
        ], , ],
        "makeColdWater": [, , , , [1], [[3]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.WaterCapacityException
        ], , ],
        "addWater": [, , , , , [[3]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.WaterCapacityException
        ], , ],
        "addSugar": [, , , , , [[3]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.SugarCapacityException
        ], , ]
    });

    const iceC_SmartHome_ICoffeeMachine_ids = [
        "::Ice::Object",
        "::SmartHome::ICoffeeMachine",
        "::SmartHome::IDevice",
        "::SmartHome::IDrinksMachine"
    ];

    SmartHome.ICoffeeMachine = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.IDrinksMachine
            ];
        }
    };

    SmartHome.ICoffeeMachinePrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.IDrinksMachinePrx];
        }
    };

    Slice.defineOperations(SmartHome.ICoffeeMachine, SmartHome.ICoffeeMachinePrx, iceC_SmartHome_ICoffeeMachine_ids, 1,
    {
        "makeCoffee": [, , , , [1], [[SmartHome.Coffee]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.ResourceLimitException
        ], , ],
        "addMilk": [, , , , , [[3]], ,
        [
            SmartHome.MilkCapacityException,
            SmartHome.NotEnabledException
        ], , ],
        "addCoffeeBeans": [, , , , , [[3]], ,
        [
            SmartHome.CoffeeBeanCapacityException,
            SmartHome.NotEnabledException
        ], , ],
        "getCoffeeHistory": [, 2, 2, , ["SmartHome.CoffeeListHelper"], , ,
        [
            SmartHome.NotEnabledException
        ], , ]
    });

    const iceC_SmartHome_ITeaMachine_ids = [
        "::Ice::Object",
        "::SmartHome::IDevice",
        "::SmartHome::IDrinksMachine",
        "::SmartHome::ITeaMachine"
    ];

    SmartHome.ITeaMachine = class extends Ice.Object
    {
        static get _iceImplements()
        {
            return [
                SmartHome.IDrinksMachine
            ];
        }
    };

    SmartHome.ITeaMachinePrx = class extends Ice.ObjectPrx
    {
        static get _implements()
        {
            return [
                SmartHome.IDrinksMachinePrx];
        }
    };

    Slice.defineOperations(SmartHome.ITeaMachine, SmartHome.ITeaMachinePrx, iceC_SmartHome_ITeaMachine_ids, 3,
    {
        "makeTea": [, , , , [1], [[SmartHome.Tea]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.ResourceLimitException
        ], , ],
        "addTeaLeavesOfType": [, , , , , [[SmartHome.TeaType._helper], [3]], ,
        [
            SmartHome.NotEnabledException,
            SmartHome.TeaLeafCapacityException
        ], , ],
        "getTeaList": [, 2, 2, , ["SmartHome.TeaListHelper"], , ,
        [
            SmartHome.NotEnabledException
        ], , ]
    });
    exports.SmartHome = SmartHome;
}
(typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? module : undefined,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? require :
 (typeof WorkerGlobalScope !== "undefined" && self instanceof WorkerGlobalScope) ? self.Ice._require : window.Ice._require,
 typeof(global) !== "undefined" && typeof(global.process) !== "undefined" ? exports :
 (typeof WorkerGlobalScope !== "undefined" && self instanceof WorkerGlobalScope) ? self : window));
