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

public class ReturnPoint implements org.apache.thrift.TBase<ReturnPoint, ReturnPoint._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReturnPoint");

  private static final org.apache.thrift.protocol.TField TOTAL_POINT_FIELD_DESC = new org.apache.thrift.protocol.TField("totalPoint", org.apache.thrift.protocol.TType.DOUBLE, (short)1);
  private static final org.apache.thrift.protocol.TField CURRENT_POINT_FIELD_DESC = new org.apache.thrift.protocol.TField("currentPoint", org.apache.thrift.protocol.TType.DOUBLE, (short)2);
  private static final org.apache.thrift.protocol.TField TIMES_FIELD_DESC = new org.apache.thrift.protocol.TField("times", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ReturnPointStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ReturnPointTupleSchemeFactory());
  }

  public double totalPoint; // optional
  public double currentPoint; // optional
  public int times; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    TOTAL_POINT((short)1, "totalPoint"),
    CURRENT_POINT((short)2, "currentPoint"),
    TIMES((short)3, "times");

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
        case 1: // TOTAL_POINT
          return TOTAL_POINT;
        case 2: // CURRENT_POINT
          return CURRENT_POINT;
        case 3: // TIMES
          return TIMES;
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
  private static final int __TOTALPOINT_ISSET_ID = 0;
  private static final int __CURRENTPOINT_ISSET_ID = 1;
  private static final int __TIMES_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.TOTAL_POINT,_Fields.CURRENT_POINT,_Fields.TIMES};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TOTAL_POINT, new org.apache.thrift.meta_data.FieldMetaData("totalPoint", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.CURRENT_POINT, new org.apache.thrift.meta_data.FieldMetaData("currentPoint", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.TIMES, new org.apache.thrift.meta_data.FieldMetaData("times", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReturnPoint.class, metaDataMap);
  }

  public ReturnPoint() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReturnPoint(ReturnPoint other) {
    __isset_bitfield = other.__isset_bitfield;
    this.totalPoint = other.totalPoint;
    this.currentPoint = other.currentPoint;
    this.times = other.times;
  }

  public ReturnPoint deepCopy() {
    return new ReturnPoint(this);
  }

  @Override
  public void clear() {
    setTotalPointIsSet(false);
    this.totalPoint = 0.0;
    setCurrentPointIsSet(false);
    this.currentPoint = 0.0;
    setTimesIsSet(false);
    this.times = 0;
  }

  public double getTotalPoint() {
    return this.totalPoint;
  }

  public ReturnPoint setTotalPoint(double totalPoint) {
    this.totalPoint = totalPoint;
    setTotalPointIsSet(true);
    return this;
  }

  public void unsetTotalPoint() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTALPOINT_ISSET_ID);
  }

  /** Returns true if field totalPoint is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalPoint() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTALPOINT_ISSET_ID);
  }

  public void setTotalPointIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTALPOINT_ISSET_ID, value);
  }

  public double getCurrentPoint() {
    return this.currentPoint;
  }

  public ReturnPoint setCurrentPoint(double currentPoint) {
    this.currentPoint = currentPoint;
    setCurrentPointIsSet(true);
    return this;
  }

  public void unsetCurrentPoint() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CURRENTPOINT_ISSET_ID);
  }

  /** Returns true if field currentPoint is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentPoint() {
    return EncodingUtils.testBit(__isset_bitfield, __CURRENTPOINT_ISSET_ID);
  }

  public void setCurrentPointIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CURRENTPOINT_ISSET_ID, value);
  }

  public int getTimes() {
    return this.times;
  }

  public ReturnPoint setTimes(int times) {
    this.times = times;
    setTimesIsSet(true);
    return this;
  }

  public void unsetTimes() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIMES_ISSET_ID);
  }

  /** Returns true if field times is set (has been assigned a value) and false otherwise */
  public boolean isSetTimes() {
    return EncodingUtils.testBit(__isset_bitfield, __TIMES_ISSET_ID);
  }

  public void setTimesIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIMES_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TOTAL_POINT:
      if (value == null) {
        unsetTotalPoint();
      } else {
        setTotalPoint((Double)value);
      }
      break;

    case CURRENT_POINT:
      if (value == null) {
        unsetCurrentPoint();
      } else {
        setCurrentPoint((Double)value);
      }
      break;

    case TIMES:
      if (value == null) {
        unsetTimes();
      } else {
        setTimes((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TOTAL_POINT:
      return Double.valueOf(getTotalPoint());

    case CURRENT_POINT:
      return Double.valueOf(getCurrentPoint());

    case TIMES:
      return Integer.valueOf(getTimes());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TOTAL_POINT:
      return isSetTotalPoint();
    case CURRENT_POINT:
      return isSetCurrentPoint();
    case TIMES:
      return isSetTimes();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ReturnPoint)
      return this.equals((ReturnPoint)that);
    return false;
  }

  public boolean equals(ReturnPoint that) {
    if (that == null)
      return false;

    boolean this_present_totalPoint = true && this.isSetTotalPoint();
    boolean that_present_totalPoint = true && that.isSetTotalPoint();
    if (this_present_totalPoint || that_present_totalPoint) {
      if (!(this_present_totalPoint && that_present_totalPoint))
        return false;
      if (this.totalPoint != that.totalPoint)
        return false;
    }

    boolean this_present_currentPoint = true && this.isSetCurrentPoint();
    boolean that_present_currentPoint = true && that.isSetCurrentPoint();
    if (this_present_currentPoint || that_present_currentPoint) {
      if (!(this_present_currentPoint && that_present_currentPoint))
        return false;
      if (this.currentPoint != that.currentPoint)
        return false;
    }

    boolean this_present_times = true && this.isSetTimes();
    boolean that_present_times = true && that.isSetTimes();
    if (this_present_times || that_present_times) {
      if (!(this_present_times && that_present_times))
        return false;
      if (this.times != that.times)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(ReturnPoint other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ReturnPoint typedOther = (ReturnPoint)other;

    lastComparison = Boolean.valueOf(isSetTotalPoint()).compareTo(typedOther.isSetTotalPoint());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalPoint()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalPoint, typedOther.totalPoint);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCurrentPoint()).compareTo(typedOther.isSetCurrentPoint());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentPoint()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentPoint, typedOther.currentPoint);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTimes()).compareTo(typedOther.isSetTimes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.times, typedOther.times);
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
    StringBuilder sb = new StringBuilder("ReturnPoint(");
    boolean first = true;

    if (isSetTotalPoint()) {
      sb.append("totalPoint:");
      sb.append(this.totalPoint);
      first = false;
    }
    if (isSetCurrentPoint()) {
      if (!first) sb.append(", ");
      sb.append("currentPoint:");
      sb.append(this.currentPoint);
      first = false;
    }
    if (isSetTimes()) {
      if (!first) sb.append(", ");
      sb.append("times:");
      sb.append(this.times);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ReturnPointStandardSchemeFactory implements SchemeFactory {
    public ReturnPointStandardScheme getScheme() {
      return new ReturnPointStandardScheme();
    }
  }

  private static class ReturnPointStandardScheme extends StandardScheme<ReturnPoint> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReturnPoint struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TOTAL_POINT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.totalPoint = iprot.readDouble();
              struct.setTotalPointIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CURRENT_POINT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.currentPoint = iprot.readDouble();
              struct.setCurrentPointIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TIMES
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.times = iprot.readI32();
              struct.setTimesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReturnPoint struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetTotalPoint()) {
        oprot.writeFieldBegin(TOTAL_POINT_FIELD_DESC);
        oprot.writeDouble(struct.totalPoint);
        oprot.writeFieldEnd();
      }
      if (struct.isSetCurrentPoint()) {
        oprot.writeFieldBegin(CURRENT_POINT_FIELD_DESC);
        oprot.writeDouble(struct.currentPoint);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTimes()) {
        oprot.writeFieldBegin(TIMES_FIELD_DESC);
        oprot.writeI32(struct.times);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ReturnPointTupleSchemeFactory implements SchemeFactory {
    public ReturnPointTupleScheme getScheme() {
      return new ReturnPointTupleScheme();
    }
  }

  private static class ReturnPointTupleScheme extends TupleScheme<ReturnPoint> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReturnPoint struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetTotalPoint()) {
        optionals.set(0);
      }
      if (struct.isSetCurrentPoint()) {
        optionals.set(1);
      }
      if (struct.isSetTimes()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetTotalPoint()) {
        oprot.writeDouble(struct.totalPoint);
      }
      if (struct.isSetCurrentPoint()) {
        oprot.writeDouble(struct.currentPoint);
      }
      if (struct.isSetTimes()) {
        oprot.writeI32(struct.times);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReturnPoint struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.totalPoint = iprot.readDouble();
        struct.setTotalPointIsSet(true);
      }
      if (incoming.get(1)) {
        struct.currentPoint = iprot.readDouble();
        struct.setCurrentPointIsSet(true);
      }
      if (incoming.get(2)) {
        struct.times = iprot.readI32();
        struct.setTimesIsSet(true);
      }
    }
  }

}
