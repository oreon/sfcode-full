/**
 * <copyright>
 * </copyright>
 *

 */
package org.xtext.example.entities.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.xtext.example.entities.EntitiesFactory;
import org.xtext.example.entities.EntitiesPackage;
import org.xtext.example.entities.Entity;
import org.xtext.example.entities.JAVAID;
import org.xtext.example.entities.Model;
import org.xtext.example.entities.PackagedType;
import org.xtext.example.entities.Property;
import org.xtext.example.entities.SimpleType;
import org.xtext.example.entities.Type;
import org.xtext.example.entities.TypeDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EntitiesFactoryImpl extends EFactoryImpl implements EntitiesFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static EntitiesFactory init()
  {
    try
    {
      EntitiesFactory theEntitiesFactory = (EntitiesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.xtext.org/example/Entities"); 
      if (theEntitiesFactory != null)
      {
        return theEntitiesFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new EntitiesFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntitiesFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case EntitiesPackage.MODEL: return createModel();
      case EntitiesPackage.TYPE_DEF: return createTypeDef();
      case EntitiesPackage.JAVAID: return createJAVAID();
      case EntitiesPackage.PACKAGE: return createPackage();
      case EntitiesPackage.PACKAGED_TYPE: return createPackagedType();
      case EntitiesPackage.TYPE: return createType();
      case EntitiesPackage.SIMPLE_TYPE: return createSimpleType();
      case EntitiesPackage.PROPERTY: return createProperty();
      case EntitiesPackage.ENTITY: return createEntity();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeDef createTypeDef()
  {
    TypeDefImpl typeDef = new TypeDefImpl();
    return typeDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JAVAID createJAVAID()
  {
    JAVAIDImpl javaid = new JAVAIDImpl();
    return javaid;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.xtext.example.entities.Package createPackage()
  {
    PackageImpl package_ = new PackageImpl();
    return package_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PackagedType createPackagedType()
  {
    PackagedTypeImpl packagedType = new PackagedTypeImpl();
    return packagedType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type createType()
  {
    TypeImpl type = new TypeImpl();
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleType createSimpleType()
  {
    SimpleTypeImpl simpleType = new SimpleTypeImpl();
    return simpleType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Property createProperty()
  {
    PropertyImpl property = new PropertyImpl();
    return property;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Entity createEntity()
  {
    EntityImpl entity = new EntityImpl();
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EntitiesPackage getEntitiesPackage()
  {
    return (EntitiesPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static EntitiesPackage getPackage()
  {
    return EntitiesPackage.eINSTANCE;
  }

} //EntitiesFactoryImpl
