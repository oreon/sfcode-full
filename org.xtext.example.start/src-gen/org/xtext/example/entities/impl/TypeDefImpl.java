/**
 * <copyright>
 * </copyright>
 *

 */
package org.xtext.example.entities.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.xtext.example.entities.EntitiesPackage;
import org.xtext.example.entities.JAVAID;
import org.xtext.example.entities.TypeDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.xtext.example.entities.impl.TypeDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.xtext.example.entities.impl.TypeDefImpl#getMappedType <em>Mapped Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDefImpl extends MinimalEObjectImpl.Container implements TypeDef
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getMappedType() <em>Mapped Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMappedType()
   * @generated
   * @ordered
   */
  protected JAVAID mappedType;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TypeDefImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return EntitiesPackage.Literals.TYPE_DEF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntitiesPackage.TYPE_DEF__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JAVAID getMappedType()
  {
    return mappedType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMappedType(JAVAID newMappedType, NotificationChain msgs)
  {
    JAVAID oldMappedType = mappedType;
    mappedType = newMappedType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EntitiesPackage.TYPE_DEF__MAPPED_TYPE, oldMappedType, newMappedType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMappedType(JAVAID newMappedType)
  {
    if (newMappedType != mappedType)
    {
      NotificationChain msgs = null;
      if (mappedType != null)
        msgs = ((InternalEObject)mappedType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EntitiesPackage.TYPE_DEF__MAPPED_TYPE, null, msgs);
      if (newMappedType != null)
        msgs = ((InternalEObject)newMappedType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EntitiesPackage.TYPE_DEF__MAPPED_TYPE, null, msgs);
      msgs = basicSetMappedType(newMappedType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntitiesPackage.TYPE_DEF__MAPPED_TYPE, newMappedType, newMappedType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EntitiesPackage.TYPE_DEF__MAPPED_TYPE:
        return basicSetMappedType(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case EntitiesPackage.TYPE_DEF__NAME:
        return getName();
      case EntitiesPackage.TYPE_DEF__MAPPED_TYPE:
        return getMappedType();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EntitiesPackage.TYPE_DEF__NAME:
        setName((String)newValue);
        return;
      case EntitiesPackage.TYPE_DEF__MAPPED_TYPE:
        setMappedType((JAVAID)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case EntitiesPackage.TYPE_DEF__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EntitiesPackage.TYPE_DEF__MAPPED_TYPE:
        setMappedType((JAVAID)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case EntitiesPackage.TYPE_DEF__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EntitiesPackage.TYPE_DEF__MAPPED_TYPE:
        return mappedType != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //TypeDefImpl
