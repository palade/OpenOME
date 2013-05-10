/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cn.edu.pku.ss.matchmaker.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOModel implements org.apache.thrift.TBase<IOModel, IOModel._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("IOModel");

  private static final org.apache.thrift.protocol.TField URI_FIELD_DESC = new org.apache.thrift.protocol.TField("URI", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PARAMETER_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("parameterType", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PARAMETER_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("parameterValue", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new IOModelStandardSchemeFactory());
    schemes.put(TupleScheme.class, new IOModelTupleSchemeFactory());
  }

  public String URI; // required
  public String parameterType; // required
  public String parameterValue; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    URI((short)1, "URI"),
    PARAMETER_TYPE((short)2, "parameterType"),
    PARAMETER_VALUE((short)3, "parameterValue");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // URI
          return URI;
        case 2: // PARAMETER_TYPE
          return PARAMETER_TYPE;
        case 3: // PARAMETER_VALUE
          return PARAMETER_VALUE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private _Fields optionals[] = {_Fields.PARAMETER_VALUE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.URI, new org.apache.thrift.meta_data.FieldMetaData("URI", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARAMETER_TYPE, new org.apache.thrift.meta_data.FieldMetaData("parameterType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARAMETER_VALUE, new org.apache.thrift.meta_data.FieldMetaData("parameterValue", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(IOModel.class, metaDataMap);
  }

  public IOModel() {
  }

  public IOModel(
    String URI,
    String parameterType)
  {
    this();
    this.URI = URI;
    this.parameterType = parameterType;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public IOModel(IOModel other) {
    if (other.isSetURI()) {
      this.URI = other.URI;
    }
    if (other.isSetParameterType()) {
      this.parameterType = other.parameterType;
    }
    if (other.isSetParameterValue()) {
      this.parameterValue = other.parameterValue;
    }
  }

  public IOModel deepCopy() {
    return new IOModel(this);
  }

  @Override
  public void clear() {
    this.URI = null;
    this.parameterType = null;
    this.parameterValue = null;
  }

  public String getURI() {
    return this.URI;
  }

  public IOModel setURI(String URI) {
    this.URI = URI;
    return this;
  }

  public void unsetURI() {
    this.URI = null;
  }

  /** Returns true if field URI is set (has been assigned a value) and false otherwise */
  public boolean isSetURI() {
    return this.URI != null;
  }

  public void setURIIsSet(boolean value) {
    if (!value) {
      this.URI = null;
    }
  }

  public String getParameterType() {
    return this.parameterType;
  }

  public IOModel setParameterType(String parameterType) {
    this.parameterType = parameterType;
    return this;
  }

  public void unsetParameterType() {
    this.parameterType = null;
  }

  /** Returns true if field parameterType is set (has been assigned a value) and false otherwise */
  public boolean isSetParameterType() {
    return this.parameterType != null;
  }

  public void setParameterTypeIsSet(boolean value) {
    if (!value) {
      this.parameterType = null;
    }
  }

  public String getParameterValue() {
    return this.parameterValue;
  }

  public IOModel setParameterValue(String parameterValue) {
    this.parameterValue = parameterValue;
    return this;
  }

  public void unsetParameterValue() {
    this.parameterValue = null;
  }

  /** Returns true if field parameterValue is set (has been assigned a value) and false otherwise */
  public boolean isSetParameterValue() {
    return this.parameterValue != null;
  }

  public void setParameterValueIsSet(boolean value) {
    if (!value) {
      this.parameterValue = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case URI:
      if (value == null) {
        unsetURI();
      } else {
        setURI((String)value);
      }
      break;

    case PARAMETER_TYPE:
      if (value == null) {
        unsetParameterType();
      } else {
        setParameterType((String)value);
      }
      break;

    case PARAMETER_VALUE:
      if (value == null) {
        unsetParameterValue();
      } else {
        setParameterValue((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case URI:
      return getURI();

    case PARAMETER_TYPE:
      return getParameterType();

    case PARAMETER_VALUE:
      return getParameterValue();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case URI:
      return isSetURI();
    case PARAMETER_TYPE:
      return isSetParameterType();
    case PARAMETER_VALUE:
      return isSetParameterValue();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof IOModel)
      return this.equals((IOModel)that);
    return false;
  }

  public boolean equals(IOModel that) {
    if (that == null)
      return false;

    boolean this_present_URI = true && this.isSetURI();
    boolean that_present_URI = true && that.isSetURI();
    if (this_present_URI || that_present_URI) {
      if (!(this_present_URI && that_present_URI))
        return false;
      if (!this.URI.equals(that.URI))
        return false;
    }

    boolean this_present_parameterType = true && this.isSetParameterType();
    boolean that_present_parameterType = true && that.isSetParameterType();
    if (this_present_parameterType || that_present_parameterType) {
      if (!(this_present_parameterType && that_present_parameterType))
        return false;
      if (!this.parameterType.equals(that.parameterType))
        return false;
    }

    boolean this_present_parameterValue = true && this.isSetParameterValue();
    boolean that_present_parameterValue = true && that.isSetParameterValue();
    if (this_present_parameterValue || that_present_parameterValue) {
      if (!(this_present_parameterValue && that_present_parameterValue))
        return false;
      if (!this.parameterValue.equals(that.parameterValue))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(IOModel other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    IOModel typedOther = (IOModel)other;

    lastComparison = Boolean.valueOf(isSetURI()).compareTo(typedOther.isSetURI());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetURI()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.URI, typedOther.URI);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParameterType()).compareTo(typedOther.isSetParameterType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParameterType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parameterType, typedOther.parameterType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParameterValue()).compareTo(typedOther.isSetParameterValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParameterValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parameterValue, typedOther.parameterValue);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("IOModel(");
    boolean first = true;

    sb.append("URI:");
    if (this.URI == null) {
      sb.append("null");
    } else {
      sb.append(this.URI);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("parameterType:");
    if (this.parameterType == null) {
      sb.append("null");
    } else {
      sb.append(this.parameterType);
    }
    first = false;
    if (isSetParameterValue()) {
      if (!first) sb.append(", ");
      sb.append("parameterValue:");
      if (this.parameterValue == null) {
        sb.append("null");
      } else {
        sb.append(this.parameterValue);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (URI == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'URI' was not present! Struct: " + toString());
    }
    if (parameterType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'parameterType' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class IOModelStandardSchemeFactory implements SchemeFactory {
    public IOModelStandardScheme getScheme() {
      return new IOModelStandardScheme();
    }
  }

  private static class IOModelStandardScheme extends StandardScheme<IOModel> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, IOModel struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // URI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.URI = iprot.readString();
              struct.setURIIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PARAMETER_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.parameterType = iprot.readString();
              struct.setParameterTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PARAMETER_VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.parameterValue = iprot.readString();
              struct.setParameterValueIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, IOModel struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.URI != null) {
        oprot.writeFieldBegin(URI_FIELD_DESC);
        oprot.writeString(struct.URI);
        oprot.writeFieldEnd();
      }
      if (struct.parameterType != null) {
        oprot.writeFieldBegin(PARAMETER_TYPE_FIELD_DESC);
        oprot.writeString(struct.parameterType);
        oprot.writeFieldEnd();
      }
      if (struct.parameterValue != null) {
        if (struct.isSetParameterValue()) {
          oprot.writeFieldBegin(PARAMETER_VALUE_FIELD_DESC);
          oprot.writeString(struct.parameterValue);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class IOModelTupleSchemeFactory implements SchemeFactory {
    public IOModelTupleScheme getScheme() {
      return new IOModelTupleScheme();
    }
  }

  private static class IOModelTupleScheme extends TupleScheme<IOModel> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, IOModel struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.URI);
      oprot.writeString(struct.parameterType);
      BitSet optionals = new BitSet();
      if (struct.isSetParameterValue()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetParameterValue()) {
        oprot.writeString(struct.parameterValue);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, IOModel struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.URI = iprot.readString();
      struct.setURIIsSet(true);
      struct.parameterType = iprot.readString();
      struct.setParameterTypeIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.parameterValue = iprot.readString();
        struct.setParameterValueIsSet(true);
      }
    }
  }

}

