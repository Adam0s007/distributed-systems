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

public interface IHomeCinemaTVPrx extends ITelevisionPrx
{
    default java.util.List<SurroundEffect> getSurroundEffects()
    {
        return getSurroundEffects(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default java.util.List<SurroundEffect> getSurroundEffects(java.util.Map<String, String> context)
    {
        return _iceI_getSurroundEffectsAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.util.List<SurroundEffect>> getSurroundEffectsAsync()
    {
        return _iceI_getSurroundEffectsAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.util.List<SurroundEffect>> getSurroundEffectsAsync(java.util.Map<String, String> context)
    {
        return _iceI_getSurroundEffectsAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.util.List<SurroundEffect>> _iceI_getSurroundEffectsAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.util.List<SurroundEffect>> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getSurroundEffects", com.zeroc.Ice.OperationMode.Idempotent, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     java.util.List<SurroundEffect> ret;
                     ret = SurroundEffectListHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    default boolean setSurroundEffect(SurroundEffect surroundEffect)
        throws TelevisionOperationException
    {
        return setSurroundEffect(surroundEffect, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean setSurroundEffect(SurroundEffect surroundEffect, java.util.Map<String, String> context)
        throws TelevisionOperationException
    {
        try
        {
            return _iceI_setSurroundEffectAsync(surroundEffect, context, true).waitForResponseOrUserEx();
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

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> setSurroundEffectAsync(SurroundEffect surroundEffect)
    {
        return _iceI_setSurroundEffectAsync(surroundEffect, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.Boolean> setSurroundEffectAsync(SurroundEffect surroundEffect, java.util.Map<String, String> context)
    {
        return _iceI_setSurroundEffectAsync(surroundEffect, context, false);
    }

    /**
     * @hidden
     * @param iceP_surroundEffect -
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> _iceI_setSurroundEffectAsync(SurroundEffect iceP_surroundEffect, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "setSurroundEffect", null, sync, _iceE_setSurroundEffect);
        f.invoke(true, context, null, ostr -> {
                     SurroundEffect.ice_write(ostr, iceP_surroundEffect);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_setSurroundEffect =
    {
        TelevisionOperationException.class
    };

    default SurroundEffect getCurrentSurroundEffect()
        throws SurroundEffectException
    {
        return getCurrentSurroundEffect(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default SurroundEffect getCurrentSurroundEffect(java.util.Map<String, String> context)
        throws SurroundEffectException
    {
        try
        {
            return _iceI_getCurrentSurroundEffectAsync(context, true).waitForResponseOrUserEx();
        }
        catch(SurroundEffectException ex)
        {
            throw ex;
        }
        catch(com.zeroc.Ice.UserException ex)
        {
            throw new com.zeroc.Ice.UnknownUserException(ex.ice_id(), ex);
        }
    }

    default java.util.concurrent.CompletableFuture<SurroundEffect> getCurrentSurroundEffectAsync()
    {
        return _iceI_getCurrentSurroundEffectAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<SurroundEffect> getCurrentSurroundEffectAsync(java.util.Map<String, String> context)
    {
        return _iceI_getCurrentSurroundEffectAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<SurroundEffect> _iceI_getCurrentSurroundEffectAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<SurroundEffect> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "getCurrentSurroundEffect", com.zeroc.Ice.OperationMode.Idempotent, sync, _iceE_getCurrentSurroundEffect);
        f.invoke(true, context, null, null, istr -> {
                     SurroundEffect ret;
                     ret = SurroundEffect.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    /** @hidden */
    static final Class<?>[] _iceE_getCurrentSurroundEffect =
    {
        SurroundEffectException.class
    };

    default void disableSurroundSound()
    {
        disableSurroundSound(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void disableSurroundSound(java.util.Map<String, String> context)
    {
        _iceI_disableSurroundSoundAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> disableSurroundSoundAsync()
    {
        return _iceI_disableSurroundSoundAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> disableSurroundSoundAsync(java.util.Map<String, String> context)
    {
        return _iceI_disableSurroundSoundAsync(context, false);
    }

    /**
     * @hidden
     * @param context -
     * @param sync -
     * @return -
     **/
    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_disableSurroundSoundAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "disableSurroundSound", com.zeroc.Ice.OperationMode.Idempotent, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IHomeCinemaTVPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), IHomeCinemaTVPrx.class, _IHomeCinemaTVPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IHomeCinemaTVPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), IHomeCinemaTVPrx.class, _IHomeCinemaTVPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IHomeCinemaTVPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), IHomeCinemaTVPrx.class, _IHomeCinemaTVPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static IHomeCinemaTVPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), IHomeCinemaTVPrx.class, _IHomeCinemaTVPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static IHomeCinemaTVPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, IHomeCinemaTVPrx.class, _IHomeCinemaTVPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static IHomeCinemaTVPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, IHomeCinemaTVPrx.class, _IHomeCinemaTVPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default IHomeCinemaTVPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (IHomeCinemaTVPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default IHomeCinemaTVPrx ice_adapterId(String newAdapterId)
    {
        return (IHomeCinemaTVPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default IHomeCinemaTVPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (IHomeCinemaTVPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default IHomeCinemaTVPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (IHomeCinemaTVPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default IHomeCinemaTVPrx ice_invocationTimeout(int newTimeout)
    {
        return (IHomeCinemaTVPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default IHomeCinemaTVPrx ice_connectionCached(boolean newCache)
    {
        return (IHomeCinemaTVPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default IHomeCinemaTVPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (IHomeCinemaTVPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default IHomeCinemaTVPrx ice_secure(boolean b)
    {
        return (IHomeCinemaTVPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default IHomeCinemaTVPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (IHomeCinemaTVPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default IHomeCinemaTVPrx ice_preferSecure(boolean b)
    {
        return (IHomeCinemaTVPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default IHomeCinemaTVPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (IHomeCinemaTVPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default IHomeCinemaTVPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (IHomeCinemaTVPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default IHomeCinemaTVPrx ice_collocationOptimized(boolean b)
    {
        return (IHomeCinemaTVPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default IHomeCinemaTVPrx ice_twoway()
    {
        return (IHomeCinemaTVPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default IHomeCinemaTVPrx ice_oneway()
    {
        return (IHomeCinemaTVPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default IHomeCinemaTVPrx ice_batchOneway()
    {
        return (IHomeCinemaTVPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default IHomeCinemaTVPrx ice_datagram()
    {
        return (IHomeCinemaTVPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default IHomeCinemaTVPrx ice_batchDatagram()
    {
        return (IHomeCinemaTVPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default IHomeCinemaTVPrx ice_compress(boolean co)
    {
        return (IHomeCinemaTVPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default IHomeCinemaTVPrx ice_timeout(int t)
    {
        return (IHomeCinemaTVPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default IHomeCinemaTVPrx ice_connectionId(String connectionId)
    {
        return (IHomeCinemaTVPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    @Override
    default IHomeCinemaTVPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return (IHomeCinemaTVPrx)_ice_fixed(connection);
    }

    static String ice_staticId()
    {
        return "::SmartHome::IHomeCinemaTV";
    }
}