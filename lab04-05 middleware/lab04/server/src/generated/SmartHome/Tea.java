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

public class Tea implements java.lang.Cloneable,
                            java.io.Serializable
{
    public TeaType type;

    public int amountOfLeaves;

    public Tea()
    {
        this.type = TeaType.Black;
    }

    public Tea(TeaType type, int amountOfLeaves)
    {
        this.type = type;
        this.amountOfLeaves = amountOfLeaves;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        Tea r = null;
        if(rhs instanceof Tea)
        {
            r = (Tea)rhs;
        }

        if(r != null)
        {
            if(this.type != r.type)
            {
                if(this.type == null || r.type == null || !this.type.equals(r.type))
                {
                    return false;
                }
            }
            if(this.amountOfLeaves != r.amountOfLeaves)
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
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::SmartHome::Tea");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, type);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, amountOfLeaves);
        return h_;
    }

    public Tea clone()
    {
        Tea c = null;
        try
        {
            c = (Tea)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        TeaType.ice_write(ostr, this.type);
        ostr.writeInt(this.amountOfLeaves);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.type = TeaType.ice_read(istr);
        this.amountOfLeaves = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, Tea v)
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

    static public Tea ice_read(com.zeroc.Ice.InputStream istr)
    {
        Tea v = new Tea();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<Tea> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, Tea v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<Tea> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(Tea.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final Tea _nullMarshalValue = new Tea();

    /** @hidden */
    public static final long serialVersionUID = -859855304L;
}
