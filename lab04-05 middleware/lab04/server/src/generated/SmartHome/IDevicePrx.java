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

public interface IDevicePrx extends com.zeroc.Ice.ObjectPrx
{
    default DeviceStatus getStatus()
        throws SmarthomeException
    {
        return getStatus(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default DeviceStatus getStatus(java.util.Map<String, String> context)
        throws SmarthomeException
    {
        try
        {
            return _iceI_getStatusAsync(context, true).waitForResponseOrUserEx();
        }
        catch(SmarthomeException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<DeviceStatus> getStatusAsync()
    {
        return _iceI_getStatusAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<DeviceStatus> getStatusAsync(java.util.Map<String, String> context)
    {
        return _iceI_getStatusAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<DeviceStatus> _iceI_getStatusAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<DeviceStatus> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getStatus", com.zeroc.Ice.OperationMode.Idempotent, sync, _iceE_getStatus);
        f.invoke(true, context, null, null, istr -> {
                     DeviceStatus ret;
                     ret = DeviceStatus.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_getStatus =
    {
        SmarthomeException.class
    };

    default DeviceStatus turnOn()
        throws DeviceOperationException
    {
        return turnOn(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default DeviceStatus turnOn(java.util.Map<String, String> context)
        throws DeviceOperationException
    {
        try
        {
            return _iceI_turnOnAsync(context, true).waitForResponseOrUserEx();
        }
        catch(DeviceOperationException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<DeviceStatus> turnOnAsync()
    {
        return _iceI_turnOnAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<DeviceStatus> turnOnAsync(java.util.Map<String, String> context)
    {
        return _iceI_turnOnAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<DeviceStatus> _iceI_turnOnAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<DeviceStatus> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "turnOn", null, sync, _iceE_turnOn);
        f.invoke(true, context, null, null, istr -> {
                     DeviceStatus ret;
                     ret = DeviceStatus.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_turnOn =
    {
        DeviceOperationException.class
    };

    default DeviceStatus turnOff()
        throws DeviceOperationException
    {
        return turnOff(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default DeviceStatus turnOff(java.util.Map<String, String> context)
        throws DeviceOperationException
    {
        try
        {
            return _iceI_turnOffAsync(context, true).waitForResponseOrUserEx();
        }
        catch(DeviceOperationException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<DeviceStatus> turnOffAsync()
    {
        return _iceI_turnOffAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<DeviceStatus> turnOffAsync(java.util.Map<String, String> context)
    {
        return _iceI_turnOffAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<DeviceStatus> _iceI_turnOffAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<DeviceStatus> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "turnOff", null, sync, _iceE_turnOff);
        f.invoke(true, context, null, null, istr -> {
                     DeviceStatus ret;
                     ret = DeviceStatus.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_turnOff =
    {
        DeviceOperationException.class
    };

    default String getDetails()
        throws SmarthomeException
    {
        return getDetails(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String getDetails(java.util.Map<String, String> context)
        throws SmarthomeException
    {
        try
        {
            return _iceI_getDetailsAsync(context, true).waitForResponseOrUserEx();
        }
        catch(SmarthomeException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getDetailsAsync()
    {
        return _iceI_getDetailsAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> getDetailsAsync(java.util.Map<String, String> context)
    {
        return _iceI_getDetailsAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_getDetailsAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getDetails", com.zeroc.Ice.OperationMode.Idempotent, sync, _iceE_getDetails);
        f.invoke(true, context, null, null, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_getDetails =
    {
        SmarthomeException.class
    };

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IDevicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), IDevicePrx.class, _IDevicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IDevicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), IDevicePrx.class, _IDevicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IDevicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), IDevicePrx.class, _IDevicePrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IDevicePrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), IDevicePrx.class, _IDevicePrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static IDevicePrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, IDevicePrx.class, _IDevicePrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static IDevicePrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, IDevicePrx.class, _IDevicePrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default IDevicePrx ice_context(java.util.Map<String, String> newContext)
    {
        return (IDevicePrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default IDevicePrx ice_adapterId(String newAdapterId)
    {
        return (IDevicePrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default IDevicePrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (IDevicePrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default IDevicePrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (IDevicePrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default IDevicePrx ice_invocationTimeout(int newTimeout)
    {
        return (IDevicePrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default IDevicePrx ice_connectionCached(boolean newCache)
    {
        return (IDevicePrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default IDevicePrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (IDevicePrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default IDevicePrx ice_secure(boolean b)
    {
        return (IDevicePrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default IDevicePrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (IDevicePrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default IDevicePrx ice_preferSecure(boolean b)
    {
        return (IDevicePrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default IDevicePrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (IDevicePrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default IDevicePrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (IDevicePrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default IDevicePrx ice_collocationOptimized(boolean b)
    {
        return (IDevicePrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default IDevicePrx ice_twoway()
    {
        return (IDevicePrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default IDevicePrx ice_oneway()
    {
        return (IDevicePrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default IDevicePrx ice_batchOneway()
    {
        return (IDevicePrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default IDevicePrx ice_datagram()
    {
        return (IDevicePrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default IDevicePrx ice_batchDatagram()
    {
        return (IDevicePrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default IDevicePrx ice_compress(boolean co)
    {
        return (IDevicePrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default IDevicePrx ice_timeout(int t)
    {
        return (IDevicePrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default IDevicePrx ice_connectionId(String connectionId)
    {
        return (IDevicePrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default IDevicePrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (IDevicePrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::SmartHome::IDevice";
    }
}
