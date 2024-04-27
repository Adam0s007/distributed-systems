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

public class PTZOperationException extends CameraOperationException
{
    public PTZOperationException()
    {
        super();
        this.action = "";
    }

    public PTZOperationException(Throwable cause)
    {
        super(cause);
        this.action = "";
    }

    public PTZOperationException(String message, String operation, String action)
    {
        super(message, operation);
        this.action = action;
    }

    public PTZOperationException(String message, String operation, String action, Throwable cause)
    {
        super(message, operation, cause);
        this.action = action;
    }

    public String ice_id()
    {
        return "::SmartHome::PTZOperationException";
    }

    public String action;

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::SmartHome::PTZOperationException", -1, false);
        ostr_.writeString(action);
        ostr_.endSlice();
        super._writeImpl(ostr_);
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        action = istr_.readString();
        istr_.endSlice();
        super._readImpl(istr_);
    }

    /** @hidden */
    public static final long serialVersionUID = -839282184L;
}
