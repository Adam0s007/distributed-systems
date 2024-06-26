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

public class Coffee implements java.lang.Cloneable,
                               java.io.Serializable
{
    public CoffeeStrength strength;

    public CoffeeType type;

    public int milkAmount;

    public Coffee()
    {
        this.strength = CoffeeStrength.Light;
        this.type = CoffeeType.Espresso;
    }

    public Coffee(CoffeeStrength strength, CoffeeType type, int milkAmount)
    {
        this.strength = strength;
        this.type = type;
        this.milkAmount = milkAmount;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Coffee r = null;
        if(rhs instanceof Coffee)
        {
            r = (Coffee)rhs;
        }

        if(r != null)
        {
            if(this.strength != r.strength)
            {
                if(this.strength == null || r.strength == null || !this.strength.equals(r.strength))
                {
                    return false;
                }
            }
            if(this.type != r.type)
            {
                if(this.type == null || r.type == null || !this.type.equals(r.type))
                {
                    return false;
                }
            }
            if(this.milkAmount != r.milkAmount)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::SmartHome::Coffee");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, strength);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, type);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, milkAmount);
        return h_;
    }

    public Coffee clone()
    {
        Coffee c = null;
        try
        {
            c = (Coffee)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        CoffeeStrength.ice_write(ostr, this.strength);
        CoffeeType.ice_write(ostr, this.type);
        ostr.writeInt(this.milkAmount);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.strength = CoffeeStrength.ice_read(istr);
        this.type = CoffeeType.ice_read(istr);
        this.milkAmount = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Coffee v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public Coffee ice_read(com.zeroc.Ice.InputStream istr)
    {
        Coffee v = new Coffee();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Coffee> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Coffee v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Coffee> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Coffee.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Coffee _nullMarshalValue = new Coffee();

    /** @hidden */
    public static final long serialVersionUID = -368562460L;
}
