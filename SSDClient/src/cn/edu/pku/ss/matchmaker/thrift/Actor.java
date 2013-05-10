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

public class Actor implements org.apache.thrift.TBase<Actor, Actor._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Actor");

  private static final org.apache.thrift.protocol.TField URI_FIELD_DESC = new org.apache.thrift.protocol.TField("URI", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TITLE_FIELD_DESC = new org.apache.thrift.protocol.TField("title", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PHONE_FIELD_DESC = new org.apache.thrift.protocol.TField("phone", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField FAX_FIELD_DESC = new org.apache.thrift.protocol.TField("fax", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField EMAIL_FIELD_DESC = new org.apache.thrift.protocol.TField("email", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField PHYSICAL_ADDRESS_FIELD_DESC = new org.apache.thrift.protocol.TField("physicalAddress", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField WEB_URI_FIELD_DESC = new org.apache.thrift.protocol.TField("webURI", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ActorStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ActorTupleSchemeFactory());
  }

  public String URI; // required
  public String name; // optional
  public String title; // optional
  public String phone; // optional
  public String fax; // optional
  public String email; // optional
  public String physicalAddress; // optional
  public String webURI; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    URI((short)1, "URI"),
    NAME((short)2, "name"),
    TITLE((short)3, "title"),
    PHONE((short)4, "phone"),
    FAX((short)5, "fax"),
    EMAIL((short)6, "email"),
    PHYSICAL_ADDRESS((short)7, "physicalAddress"),
    WEB_URI((short)8, "webURI");

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
        case 2: // NAME
          return NAME;
        case 3: // TITLE
          return TITLE;
        case 4: // PHONE
          return PHONE;
        case 5: // FAX
          return FAX;
        case 6: // EMAIL
          return EMAIL;
        case 7: // PHYSICAL_ADDRESS
          return PHYSICAL_ADDRESS;
        case 8: // WEB_URI
          return WEB_URI;
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
  private _Fields optionals[] = {_Fields.NAME,_Fields.TITLE,_Fields.PHONE,_Fields.FAX,_Fields.EMAIL,_Fields.PHYSICAL_ADDRESS,_Fields.WEB_URI};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.URI, new org.apache.thrift.meta_data.FieldMetaData("URI", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TITLE, new org.apache.thrift.meta_data.FieldMetaData("title", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PHONE, new org.apache.thrift.meta_data.FieldMetaData("phone", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.FAX, new org.apache.thrift.meta_data.FieldMetaData("fax", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EMAIL, new org.apache.thrift.meta_data.FieldMetaData("email", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PHYSICAL_ADDRESS, new org.apache.thrift.meta_data.FieldMetaData("physicalAddress", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.WEB_URI, new org.apache.thrift.meta_data.FieldMetaData("webURI", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Actor.class, metaDataMap);
  }

  public Actor() {
  }

  public Actor(
    String URI)
  {
    this();
    this.URI = URI;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Actor(Actor other) {
    if (other.isSetURI()) {
      this.URI = other.URI;
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetTitle()) {
      this.title = other.title;
    }
    if (other.isSetPhone()) {
      this.phone = other.phone;
    }
    if (other.isSetFax()) {
      this.fax = other.fax;
    }
    if (other.isSetEmail()) {
      this.email = other.email;
    }
    if (other.isSetPhysicalAddress()) {
      this.physicalAddress = other.physicalAddress;
    }
    if (other.isSetWebURI()) {
      this.webURI = other.webURI;
    }
  }

  public Actor deepCopy() {
    return new Actor(this);
  }

  @Override
  public void clear() {
    this.URI = null;
    this.name = null;
    this.title = null;
    this.phone = null;
    this.fax = null;
    this.email = null;
    this.physicalAddress = null;
    this.webURI = null;
  }

  public String getURI() {
    return this.URI;
  }

  public Actor setURI(String URI) {
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

  public String getName() {
    return this.name;
  }

  public Actor setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getTitle() {
    return this.title;
  }

  public Actor setTitle(String title) {
    this.title = title;
    return this;
  }

  public void unsetTitle() {
    this.title = null;
  }

  /** Returns true if field title is set (has been assigned a value) and false otherwise */
  public boolean isSetTitle() {
    return this.title != null;
  }

  public void setTitleIsSet(boolean value) {
    if (!value) {
      this.title = null;
    }
  }

  public String getPhone() {
    return this.phone;
  }

  public Actor setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public void unsetPhone() {
    this.phone = null;
  }

  /** Returns true if field phone is set (has been assigned a value) and false otherwise */
  public boolean isSetPhone() {
    return this.phone != null;
  }

  public void setPhoneIsSet(boolean value) {
    if (!value) {
      this.phone = null;
    }
  }

  public String getFax() {
    return this.fax;
  }

  public Actor setFax(String fax) {
    this.fax = fax;
    return this;
  }

  public void unsetFax() {
    this.fax = null;
  }

  /** Returns true if field fax is set (has been assigned a value) and false otherwise */
  public boolean isSetFax() {
    return this.fax != null;
  }

  public void setFaxIsSet(boolean value) {
    if (!value) {
      this.fax = null;
    }
  }

  public String getEmail() {
    return this.email;
  }

  public Actor setEmail(String email) {
    this.email = email;
    return this;
  }

  public void unsetEmail() {
    this.email = null;
  }

  /** Returns true if field email is set (has been assigned a value) and false otherwise */
  public boolean isSetEmail() {
    return this.email != null;
  }

  public void setEmailIsSet(boolean value) {
    if (!value) {
      this.email = null;
    }
  }

  public String getPhysicalAddress() {
    return this.physicalAddress;
  }

  public Actor setPhysicalAddress(String physicalAddress) {
    this.physicalAddress = physicalAddress;
    return this;
  }

  public void unsetPhysicalAddress() {
    this.physicalAddress = null;
  }

  /** Returns true if field physicalAddress is set (has been assigned a value) and false otherwise */
  public boolean isSetPhysicalAddress() {
    return this.physicalAddress != null;
  }

  public void setPhysicalAddressIsSet(boolean value) {
    if (!value) {
      this.physicalAddress = null;
    }
  }

  public String getWebURI() {
    return this.webURI;
  }

  public Actor setWebURI(String webURI) {
    this.webURI = webURI;
    return this;
  }

  public void unsetWebURI() {
    this.webURI = null;
  }

  /** Returns true if field webURI is set (has been assigned a value) and false otherwise */
  public boolean isSetWebURI() {
    return this.webURI != null;
  }

  public void setWebURIIsSet(boolean value) {
    if (!value) {
      this.webURI = null;
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

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case TITLE:
      if (value == null) {
        unsetTitle();
      } else {
        setTitle((String)value);
      }
      break;

    case PHONE:
      if (value == null) {
        unsetPhone();
      } else {
        setPhone((String)value);
      }
      break;

    case FAX:
      if (value == null) {
        unsetFax();
      } else {
        setFax((String)value);
      }
      break;

    case EMAIL:
      if (value == null) {
        unsetEmail();
      } else {
        setEmail((String)value);
      }
      break;

    case PHYSICAL_ADDRESS:
      if (value == null) {
        unsetPhysicalAddress();
      } else {
        setPhysicalAddress((String)value);
      }
      break;

    case WEB_URI:
      if (value == null) {
        unsetWebURI();
      } else {
        setWebURI((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case URI:
      return getURI();

    case NAME:
      return getName();

    case TITLE:
      return getTitle();

    case PHONE:
      return getPhone();

    case FAX:
      return getFax();

    case EMAIL:
      return getEmail();

    case PHYSICAL_ADDRESS:
      return getPhysicalAddress();

    case WEB_URI:
      return getWebURI();

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
    case NAME:
      return isSetName();
    case TITLE:
      return isSetTitle();
    case PHONE:
      return isSetPhone();
    case FAX:
      return isSetFax();
    case EMAIL:
      return isSetEmail();
    case PHYSICAL_ADDRESS:
      return isSetPhysicalAddress();
    case WEB_URI:
      return isSetWebURI();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Actor)
      return this.equals((Actor)that);
    return false;
  }

  public boolean equals(Actor that) {
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

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_title = true && this.isSetTitle();
    boolean that_present_title = true && that.isSetTitle();
    if (this_present_title || that_present_title) {
      if (!(this_present_title && that_present_title))
        return false;
      if (!this.title.equals(that.title))
        return false;
    }

    boolean this_present_phone = true && this.isSetPhone();
    boolean that_present_phone = true && that.isSetPhone();
    if (this_present_phone || that_present_phone) {
      if (!(this_present_phone && that_present_phone))
        return false;
      if (!this.phone.equals(that.phone))
        return false;
    }

    boolean this_present_fax = true && this.isSetFax();
    boolean that_present_fax = true && that.isSetFax();
    if (this_present_fax || that_present_fax) {
      if (!(this_present_fax && that_present_fax))
        return false;
      if (!this.fax.equals(that.fax))
        return false;
    }

    boolean this_present_email = true && this.isSetEmail();
    boolean that_present_email = true && that.isSetEmail();
    if (this_present_email || that_present_email) {
      if (!(this_present_email && that_present_email))
        return false;
      if (!this.email.equals(that.email))
        return false;
    }

    boolean this_present_physicalAddress = true && this.isSetPhysicalAddress();
    boolean that_present_physicalAddress = true && that.isSetPhysicalAddress();
    if (this_present_physicalAddress || that_present_physicalAddress) {
      if (!(this_present_physicalAddress && that_present_physicalAddress))
        return false;
      if (!this.physicalAddress.equals(that.physicalAddress))
        return false;
    }

    boolean this_present_webURI = true && this.isSetWebURI();
    boolean that_present_webURI = true && that.isSetWebURI();
    if (this_present_webURI || that_present_webURI) {
      if (!(this_present_webURI && that_present_webURI))
        return false;
      if (!this.webURI.equals(that.webURI))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(Actor other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    Actor typedOther = (Actor)other;

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
    lastComparison = Boolean.valueOf(isSetName()).compareTo(typedOther.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, typedOther.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTitle()).compareTo(typedOther.isSetTitle());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTitle()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.title, typedOther.title);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPhone()).compareTo(typedOther.isSetPhone());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPhone()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.phone, typedOther.phone);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFax()).compareTo(typedOther.isSetFax());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFax()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.fax, typedOther.fax);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEmail()).compareTo(typedOther.isSetEmail());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEmail()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.email, typedOther.email);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPhysicalAddress()).compareTo(typedOther.isSetPhysicalAddress());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPhysicalAddress()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.physicalAddress, typedOther.physicalAddress);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWebURI()).compareTo(typedOther.isSetWebURI());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWebURI()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.webURI, typedOther.webURI);
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
    StringBuilder sb = new StringBuilder("Actor(");
    boolean first = true;

    sb.append("URI:");
    if (this.URI == null) {
      sb.append("null");
    } else {
      sb.append(this.URI);
    }
    first = false;
    if (isSetName()) {
      if (!first) sb.append(", ");
      sb.append("name:");
      if (this.name == null) {
        sb.append("null");
      } else {
        sb.append(this.name);
      }
      first = false;
    }
    if (isSetTitle()) {
      if (!first) sb.append(", ");
      sb.append("title:");
      if (this.title == null) {
        sb.append("null");
      } else {
        sb.append(this.title);
      }
      first = false;
    }
    if (isSetPhone()) {
      if (!first) sb.append(", ");
      sb.append("phone:");
      if (this.phone == null) {
        sb.append("null");
      } else {
        sb.append(this.phone);
      }
      first = false;
    }
    if (isSetFax()) {
      if (!first) sb.append(", ");
      sb.append("fax:");
      if (this.fax == null) {
        sb.append("null");
      } else {
        sb.append(this.fax);
      }
      first = false;
    }
    if (isSetEmail()) {
      if (!first) sb.append(", ");
      sb.append("email:");
      if (this.email == null) {
        sb.append("null");
      } else {
        sb.append(this.email);
      }
      first = false;
    }
    if (isSetPhysicalAddress()) {
      if (!first) sb.append(", ");
      sb.append("physicalAddress:");
      if (this.physicalAddress == null) {
        sb.append("null");
      } else {
        sb.append(this.physicalAddress);
      }
      first = false;
    }
    if (isSetWebURI()) {
      if (!first) sb.append(", ");
      sb.append("webURI:");
      if (this.webURI == null) {
        sb.append("null");
      } else {
        sb.append(this.webURI);
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

  private static class ActorStandardSchemeFactory implements SchemeFactory {
    public ActorStandardScheme getScheme() {
      return new ActorStandardScheme();
    }
  }

  private static class ActorStandardScheme extends StandardScheme<Actor> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Actor struct) throws org.apache.thrift.TException {
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
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TITLE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.title = iprot.readString();
              struct.setTitleIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PHONE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.phone = iprot.readString();
              struct.setPhoneIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // FAX
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.fax = iprot.readString();
              struct.setFaxIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // EMAIL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.email = iprot.readString();
              struct.setEmailIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // PHYSICAL_ADDRESS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.physicalAddress = iprot.readString();
              struct.setPhysicalAddressIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // WEB_URI
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.webURI = iprot.readString();
              struct.setWebURIIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Actor struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.URI != null) {
        oprot.writeFieldBegin(URI_FIELD_DESC);
        oprot.writeString(struct.URI);
        oprot.writeFieldEnd();
      }
      if (struct.name != null) {
        if (struct.isSetName()) {
          oprot.writeFieldBegin(NAME_FIELD_DESC);
          oprot.writeString(struct.name);
          oprot.writeFieldEnd();
        }
      }
      if (struct.title != null) {
        if (struct.isSetTitle()) {
          oprot.writeFieldBegin(TITLE_FIELD_DESC);
          oprot.writeString(struct.title);
          oprot.writeFieldEnd();
        }
      }
      if (struct.phone != null) {
        if (struct.isSetPhone()) {
          oprot.writeFieldBegin(PHONE_FIELD_DESC);
          oprot.writeString(struct.phone);
          oprot.writeFieldEnd();
        }
      }
      if (struct.fax != null) {
        if (struct.isSetFax()) {
          oprot.writeFieldBegin(FAX_FIELD_DESC);
          oprot.writeString(struct.fax);
          oprot.writeFieldEnd();
        }
      }
      if (struct.email != null) {
        if (struct.isSetEmail()) {
          oprot.writeFieldBegin(EMAIL_FIELD_DESC);
          oprot.writeString(struct.email);
          oprot.writeFieldEnd();
        }
      }
      if (struct.physicalAddress != null) {
        if (struct.isSetPhysicalAddress()) {
          oprot.writeFieldBegin(PHYSICAL_ADDRESS_FIELD_DESC);
          oprot.writeString(struct.physicalAddress);
          oprot.writeFieldEnd();
        }
      }
      if (struct.webURI != null) {
        if (struct.isSetWebURI()) {
          oprot.writeFieldBegin(WEB_URI_FIELD_DESC);
          oprot.writeString(struct.webURI);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ActorTupleSchemeFactory implements SchemeFactory {
    public ActorTupleScheme getScheme() {
      return new ActorTupleScheme();
    }
  }

  private static class ActorTupleScheme extends TupleScheme<Actor> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Actor struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.URI);
      BitSet optionals = new BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetTitle()) {
        optionals.set(1);
      }
      if (struct.isSetPhone()) {
        optionals.set(2);
      }
      if (struct.isSetFax()) {
        optionals.set(3);
      }
      if (struct.isSetEmail()) {
        optionals.set(4);
      }
      if (struct.isSetPhysicalAddress()) {
        optionals.set(5);
      }
      if (struct.isSetWebURI()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetTitle()) {
        oprot.writeString(struct.title);
      }
      if (struct.isSetPhone()) {
        oprot.writeString(struct.phone);
      }
      if (struct.isSetFax()) {
        oprot.writeString(struct.fax);
      }
      if (struct.isSetEmail()) {
        oprot.writeString(struct.email);
      }
      if (struct.isSetPhysicalAddress()) {
        oprot.writeString(struct.physicalAddress);
      }
      if (struct.isSetWebURI()) {
        oprot.writeString(struct.webURI);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Actor struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.URI = iprot.readString();
      struct.setURIIsSet(true);
      BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.title = iprot.readString();
        struct.setTitleIsSet(true);
      }
      if (incoming.get(2)) {
        struct.phone = iprot.readString();
        struct.setPhoneIsSet(true);
      }
      if (incoming.get(3)) {
        struct.fax = iprot.readString();
        struct.setFaxIsSet(true);
      }
      if (incoming.get(4)) {
        struct.email = iprot.readString();
        struct.setEmailIsSet(true);
      }
      if (incoming.get(5)) {
        struct.physicalAddress = iprot.readString();
        struct.setPhysicalAddressIsSet(true);
      }
      if (incoming.get(6)) {
        struct.webURI = iprot.readString();
        struct.setWebURIIsSet(true);
      }
    }
  }

}

