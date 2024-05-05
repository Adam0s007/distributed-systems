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

package SmartHome;

public interface ICoffeeMachine extends IDrinksMachine
{
    boolean makeCoffee(Coffee coffee, com.zeroc.Ice.Current current)
        throws NotEnabledException,
               ResourceLimitException;

    void addMilk(int amount, com.zeroc.Ice.Current current)
        throws MilkCapacityException,
               NotEnabledException;

    void addCoffeeBeans(int amount, com.zeroc.Ice.Current current)
        throws CoffeeBeanCapacityException,
               NotEnabledException;

    java.util.List<Coffee> getCoffeeHistory(com.zeroc.Ice.Current current)
        throws NotEnabledException;

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::SmartHome::ICoffeeMachine",
        "::SmartHome::IDevice",
        "::SmartHome::IDrinksMachine"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::SmartHome::ICoffeeMachine";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_makeCoffee(ICoffeeMachine obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        Coffee iceP_coffee;
        iceP_coffee = Coffee.ice_read(istr);
        inS.endReadParams();
        boolean ret = obj.makeCoffee(iceP_coffee, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        ostr.writeBool(ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_addMilk(ICoffeeMachine obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_amount;
        iceP_amount = istr.readInt();
        inS.endReadParams();
        obj.addMilk(iceP_amount, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_addCoffeeBeans(ICoffeeMachine obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_amount;
        iceP_amount = istr.readInt();
        inS.endReadParams();
        obj.addCoffeeBeans(iceP_amount, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_getCoffeeHistory(ICoffeeMachine obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(com.zeroc.Ice.OperationMode.Idempotent, current.mode);
        inS.readEmptyParams();
        java.util.List<Coffee> ret = obj.getCoffeeHistory(current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        CoffeeListHelper.write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "addCoffeeBeans",
        "addMilk",
        "addSugar",
        "addWater",
        "getCoffeeHistory",
        "getDetails",
        "getStatus",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "isTurnedOn",
        "makeCoffee",
        "makeColdWater",
        "makeHotWater",
        "turnOff",
        "turnOn"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return _iceD_addCoffeeBeans(this, in, current);
            }
            case 1:
            {
                return _iceD_addMilk(this, in, current);
            }
            case 2:
            {
                return IDrinksMachine._iceD_addSugar(this, in, current);
            }
            case 3:
            {
                return IDrinksMachine._iceD_addWater(this, in, current);
            }
            case 4:
            {
                return _iceD_getCoffeeHistory(this, in, current);
            }
            case 5:
            {
                return IDevice._iceD_getDetails(this, in, current);
            }
            case 6:
            {
                return IDevice._iceD_getStatus(this, in, current);
            }
            case 7:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 8:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 9:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 10:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 11:
            {
                return IDevice._iceD_isTurnedOn(this, in, current);
            }
            case 12:
            {
                return _iceD_makeCoffee(this, in, current);
            }
            case 13:
            {
                return IDrinksMachine._iceD_makeColdWater(this, in, current);
            }
            case 14:
            {
                return IDrinksMachine._iceD_makeHotWater(this, in, current);
            }
            case 15:
            {
                return IDevice._iceD_turnOff(this, in, current);
            }
            case 16:
            {
                return IDevice._iceD_turnOn(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}