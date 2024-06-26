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

public interface ITelevisionPrx extends IDevicePrx
{
    default TVChannel getCurrentChannel()
        throws NotEnabledException,
               TelevisionOperationException
    {
        return getCurrentChannel(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default TVChannel getCurrentChannel(java.util.Map<String, String> context)
        throws NotEnabledException,
               TelevisionOperationException
    {
        try
        {
            return _iceI_getCurrentChannelAsync(context, true).waitForResponseOrUserEx();
        }
        catch(NotEnabledException ex)
        {
            throw ex;
        }
        catch(TelevisionOperationException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<TVChannel> getCurrentChannelAsync()
    {
        return _iceI_getCurrentChannelAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<TVChannel> getCurrentChannelAsync(java.util.Map<String, String> context)
    {
        return _iceI_getCurrentChannelAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<TVChannel> _iceI_getCurrentChannelAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<TVChannel> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getCurrentChannel", com.zeroc.Ice.OperationMode.Idempotent, sync, _iceE_getCurrentChannel);
        f.invoke(true, context, null, null, istr -> {
                     TVChannel ret;
                     ret = TVChannel.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_getCurrentChannel =
    {
        NotEnabledException.class,
        TelevisionOperationException.class
    };

    default boolean setChannel(int newChannel)
        throws InvalidChannelException,
               NotEnabledException
    {
        return setChannel(newChannel, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean setChannel(int newChannel, java.util.Map<String, String> context)
        throws InvalidChannelException,
               NotEnabledException
    {
        try
        {
            return _iceI_setChannelAsync(newChannel, context, true).waitForResponseOrUserEx();
        }
        catch(InvalidChannelException ex)
        {
            throw ex;
        }
        catch(NotEnabledException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> setChannelAsync(int newChannel)
    {
        return _iceI_setChannelAsync(newChannel, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> setChannelAsync(int newChannel, java.util.Map<String, String> context)
    {
        return _iceI_setChannelAsync(newChannel, context, false);
    }

    /**
     * @hidden
     * @param iceP_newChannel -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_setChannelAsync(int iceP_newChannel, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "setChannel", com.zeroc.Ice.OperationMode.Idempotent, sync, _iceE_setChannel);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeInt(iceP_newChannel);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_setChannel =
    {
        InvalidChannelException.class,
        NotEnabledException.class
    };

    default java.util.List<TVChannel> getChannelList()
        throws NotEnabledException
    {
        return getChannelList(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default java.util.List<TVChannel> getChannelList(java.util.Map<String, String> context)
        throws NotEnabledException
    {
        try
        {
            return _iceI_getChannelListAsync(context, true).waitForResponseOrUserEx();
        }
        catch(NotEnabledException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<java.util.List<TVChannel>> getChannelListAsync()
    {
        return _iceI_getChannelListAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.util.List<TVChannel>> getChannelListAsync(java.util.Map<String, String> context)
    {
        return _iceI_getChannelListAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.util.List<TVChannel>> _iceI_getChannelListAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.util.List<TVChannel>> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getChannelList", com.zeroc.Ice.OperationMode.Idempotent, sync, _iceE_getChannelList);
        f.invoke(true, context, null, null, istr -> {
                     java.util.List<TVChannel> ret;
                     ret = TVChannelListHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_getChannelList =
    {
        NotEnabledException.class
    };

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ITelevisionPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), ITelevisionPrx.class, _ITelevisionPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ITelevisionPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), ITelevisionPrx.class, _ITelevisionPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ITelevisionPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), ITelevisionPrx.class, _ITelevisionPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ITelevisionPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), ITelevisionPrx.class, _ITelevisionPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static ITelevisionPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, ITelevisionPrx.class, _ITelevisionPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static ITelevisionPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, ITelevisionPrx.class, _ITelevisionPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default ITelevisionPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (ITelevisionPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default ITelevisionPrx ice_adapterId(String newAdapterId)
    {
        return (ITelevisionPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default ITelevisionPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (ITelevisionPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default ITelevisionPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (ITelevisionPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default ITelevisionPrx ice_invocationTimeout(int newTimeout)
    {
        return (ITelevisionPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default ITelevisionPrx ice_connectionCached(boolean newCache)
    {
        return (ITelevisionPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default ITelevisionPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (ITelevisionPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ITelevisionPrx ice_secure(boolean b)
    {
        return (ITelevisionPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default ITelevisionPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (ITelevisionPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ITelevisionPrx ice_preferSecure(boolean b)
    {
        return (ITelevisionPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default ITelevisionPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (ITelevisionPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default ITelevisionPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (ITelevisionPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default ITelevisionPrx ice_collocationOptimized(boolean b)
    {
        return (ITelevisionPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default ITelevisionPrx ice_twoway()
    {
        return (ITelevisionPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default ITelevisionPrx ice_oneway()
    {
        return (ITelevisionPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default ITelevisionPrx ice_batchOneway()
    {
        return (ITelevisionPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default ITelevisionPrx ice_datagram()
    {
        return (ITelevisionPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default ITelevisionPrx ice_batchDatagram()
    {
        return (ITelevisionPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default ITelevisionPrx ice_compress(boolean co)
    {
        return (ITelevisionPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default ITelevisionPrx ice_timeout(int t)
    {
        return (ITelevisionPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default ITelevisionPrx ice_connectionId(String connectionId)
    {
        return (ITelevisionPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default ITelevisionPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (ITelevisionPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::SmartHome::ITelevision";
    }
}
