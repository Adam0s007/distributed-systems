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

/**
 * Helper class for marshaling/unmarshaling CoffeeList.
 **/
public final class CoffeeListHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, java.util.List<Coffee> v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.size());
            for(Coffee elem : v)
            {
                Coffee.ice_write(ostr, elem);
            }
        }
    }

    public static java.util.List<Coffee> read(com.zeroc.Ice.InputStream istr)
    {
        final java.util.List<Coffee> v;
        v = new java.util.LinkedList<Coffee>();
        final int len0 = istr.readAndCheckSeqSize(6);
        for(int i0 = 0; i0 < len0; i0++)
        {
            Coffee elem;
            elem = Coffee.ice_read(istr);
            v.add(elem);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<java.util.List<Coffee>> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.List<Coffee> v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            CoffeeListHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<java.util.List<Coffee>> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            java.util.List<Coffee> v;
            v = CoffeeListHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}