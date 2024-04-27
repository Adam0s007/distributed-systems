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

public class CameraOperationException extends DeviceOperationException
{
    public CameraOperationException()
    {
        super();
    }

    public CameraOperationException(Throwable cause)
    {
        super(cause);
    }

    public CameraOperationException(String message, String operation)
    {
        super(message, operation);
    }

    public CameraOperationException(String message, String operation, Throwable cause)
    {
        super(message, operation, cause);
    }

    public String ice_id()
    {
        return "::SmartHome::CameraOperationException";
    }

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::SmartHome::CameraOperationException", -1, false);
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
    public static final long serialVersionUID = 1819258282L;
}
