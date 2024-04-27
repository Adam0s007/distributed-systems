//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `smarthome1.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package SmartHome;

public class SugarCapacityException extends ResourceLimitException
{
    public SugarCapacityException()
    {
        super();
    }

    public SugarCapacityException(Throwable cause)
    {
        super(cause);
    }

    public SugarCapacityException(String message, String resource)
    {
        super(message, resource);
    }

    public SugarCapacityException(String message, String resource, Throwable cause)
    {
        super(message, resource, cause);
    }

    public String ice_id()
    {
        return "::SmartHome::SugarCapacityException";
    }

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::SmartHome::SugarCapacityException", -1, false);
        ostr_.endSlice();
        super._writeImpl(ostr_);
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        istr_.endSlice();
        super._readImpl(istr_);
    }

    /** @hidden */
    public static final long serialVersionUID = -1615941856L;
}
