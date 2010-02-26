/**
 * <copyright>
 * </copyright>
 *

 */
package org.xtext.example.entities;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.xtext.example.entities.EntitiesFactory
 * @model kind="package"
 * @generated
 */
public interface EntitiesPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "entities";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.xtext.org/example/Entities";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "entities";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EntitiesPackage eINSTANCE = org.xtext.example.entities.impl.EntitiesPackageImpl.init();

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.ModelImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__ELEMENTS = 0;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.TypeDefImpl <em>Type Def</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.TypeDefImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getTypeDef()
   * @generated
   */
  int TYPE_DEF = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__NAME = 0;

  /**
   * The feature id for the '<em><b>Mapped Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF__MAPPED_TYPE = 1;

  /**
   * The number of structural features of the '<em>Type Def</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_DEF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.JAVAIDImpl <em>JAVAID</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.JAVAIDImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getJAVAID()
   * @generated
   */
  int JAVAID = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVAID__NAME = 0;

  /**
   * The number of structural features of the '<em>JAVAID</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int JAVAID_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.PackagedTypeImpl <em>Packaged Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.PackagedTypeImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getPackagedType()
   * @generated
   */
  int PACKAGED_TYPE = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGED_TYPE__NAME = 0;

  /**
   * The number of structural features of the '<em>Packaged Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGED_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.PackageImpl <em>Package</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.PackageImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getPackage()
   * @generated
   */
  int PACKAGE = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE__NAME = PACKAGED_TYPE__NAME;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE__PROPERTIES = PACKAGED_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Package</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_FEATURE_COUNT = PACKAGED_TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.TypeImpl <em>Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.TypeImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getType()
   * @generated
   */
  int TYPE = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE__NAME = PACKAGED_TYPE__NAME;

  /**
   * The number of structural features of the '<em>Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_FEATURE_COUNT = PACKAGED_TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.SimpleTypeImpl <em>Simple Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.SimpleTypeImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getSimpleType()
   * @generated
   */
  int SIMPLE_TYPE = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TYPE__NAME = TYPE__NAME;

  /**
   * The number of structural features of the '<em>Simple Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.PropertyImpl <em>Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.PropertyImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getProperty()
   * @generated
   */
  int PROPERTY = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__NAME = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__TYPE = 1;

  /**
   * The feature id for the '<em><b>Many</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY__MANY = 2;

  /**
   * The number of structural features of the '<em>Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.xtext.example.entities.impl.EntityImpl <em>Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.xtext.example.entities.impl.EntityImpl
   * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getEntity()
   * @generated
   */
  int ENTITY = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__NAME = TYPE__NAME;

  /**
   * The feature id for the '<em><b>Extends</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__EXTENDS = TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Properties</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__PROPERTIES = TYPE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__PACKAGE = TYPE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_FEATURE_COUNT = TYPE_FEATURE_COUNT + 3;


  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see org.xtext.example.entities.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.entities.Model#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.xtext.example.entities.Model#getElements()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Elements();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.TypeDef <em>Type Def</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Def</em>'.
   * @see org.xtext.example.entities.TypeDef
   * @generated
   */
  EClass getTypeDef();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.entities.TypeDef#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.entities.TypeDef#getName()
   * @see #getTypeDef()
   * @generated
   */
  EAttribute getTypeDef_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.xtext.example.entities.TypeDef#getMappedType <em>Mapped Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mapped Type</em>'.
   * @see org.xtext.example.entities.TypeDef#getMappedType()
   * @see #getTypeDef()
   * @generated
   */
  EReference getTypeDef_MappedType();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.JAVAID <em>JAVAID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>JAVAID</em>'.
   * @see org.xtext.example.entities.JAVAID
   * @generated
   */
  EClass getJAVAID();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.entities.JAVAID#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.entities.JAVAID#getName()
   * @see #getJAVAID()
   * @generated
   */
  EAttribute getJAVAID_Name();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.Package <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Package</em>'.
   * @see org.xtext.example.entities.Package
   * @generated
   */
  EClass getPackage();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.entities.Package#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Properties</em>'.
   * @see org.xtext.example.entities.Package#getProperties()
   * @see #getPackage()
   * @generated
   */
  EReference getPackage_Properties();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.PackagedType <em>Packaged Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Packaged Type</em>'.
   * @see org.xtext.example.entities.PackagedType
   * @generated
   */
  EClass getPackagedType();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.entities.PackagedType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.entities.PackagedType#getName()
   * @see #getPackagedType()
   * @generated
   */
  EAttribute getPackagedType_Name();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type</em>'.
   * @see org.xtext.example.entities.Type
   * @generated
   */
  EClass getType();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.SimpleType <em>Simple Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Type</em>'.
   * @see org.xtext.example.entities.SimpleType
   * @generated
   */
  EClass getSimpleType();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.Property <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property</em>'.
   * @see org.xtext.example.entities.Property
   * @generated
   */
  EClass getProperty();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.entities.Property#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.xtext.example.entities.Property#getName()
   * @see #getProperty()
   * @generated
   */
  EAttribute getProperty_Name();

  /**
   * Returns the meta object for the reference '{@link org.xtext.example.entities.Property#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.xtext.example.entities.Property#getType()
   * @see #getProperty()
   * @generated
   */
  EReference getProperty_Type();

  /**
   * Returns the meta object for the attribute '{@link org.xtext.example.entities.Property#isMany <em>Many</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Many</em>'.
   * @see org.xtext.example.entities.Property#isMany()
   * @see #getProperty()
   * @generated
   */
  EAttribute getProperty_Many();

  /**
   * Returns the meta object for class '{@link org.xtext.example.entities.Entity <em>Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Entity</em>'.
   * @see org.xtext.example.entities.Entity
   * @generated
   */
  EClass getEntity();

  /**
   * Returns the meta object for the reference '{@link org.xtext.example.entities.Entity#getExtends <em>Extends</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extends</em>'.
   * @see org.xtext.example.entities.Entity#getExtends()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_Extends();

  /**
   * Returns the meta object for the containment reference list '{@link org.xtext.example.entities.Entity#getProperties <em>Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Properties</em>'.
   * @see org.xtext.example.entities.Entity#getProperties()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_Properties();

  /**
   * Returns the meta object for the reference '{@link org.xtext.example.entities.Entity#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Package</em>'.
   * @see org.xtext.example.entities.Entity#getPackage()
   * @see #getEntity()
   * @generated
   */
  EReference getEntity_Package();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EntitiesFactory getEntitiesFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.ModelImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__ELEMENTS = eINSTANCE.getModel_Elements();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.TypeDefImpl <em>Type Def</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.TypeDefImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getTypeDef()
     * @generated
     */
    EClass TYPE_DEF = eINSTANCE.getTypeDef();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TYPE_DEF__NAME = eINSTANCE.getTypeDef_Name();

    /**
     * The meta object literal for the '<em><b>Mapped Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TYPE_DEF__MAPPED_TYPE = eINSTANCE.getTypeDef_MappedType();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.JAVAIDImpl <em>JAVAID</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.JAVAIDImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getJAVAID()
     * @generated
     */
    EClass JAVAID = eINSTANCE.getJAVAID();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute JAVAID__NAME = eINSTANCE.getJAVAID_Name();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.PackageImpl <em>Package</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.PackageImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getPackage()
     * @generated
     */
    EClass PACKAGE = eINSTANCE.getPackage();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PACKAGE__PROPERTIES = eINSTANCE.getPackage_Properties();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.PackagedTypeImpl <em>Packaged Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.PackagedTypeImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getPackagedType()
     * @generated
     */
    EClass PACKAGED_TYPE = eINSTANCE.getPackagedType();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PACKAGED_TYPE__NAME = eINSTANCE.getPackagedType_Name();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.TypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.TypeImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getType()
     * @generated
     */
    EClass TYPE = eINSTANCE.getType();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.SimpleTypeImpl <em>Simple Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.SimpleTypeImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getSimpleType()
     * @generated
     */
    EClass SIMPLE_TYPE = eINSTANCE.getSimpleType();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.PropertyImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getProperty()
     * @generated
     */
    EClass PROPERTY = eINSTANCE.getProperty();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY__TYPE = eINSTANCE.getProperty_Type();

    /**
     * The meta object literal for the '<em><b>Many</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY__MANY = eINSTANCE.getProperty_Many();

    /**
     * The meta object literal for the '{@link org.xtext.example.entities.impl.EntityImpl <em>Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.xtext.example.entities.impl.EntityImpl
     * @see org.xtext.example.entities.impl.EntitiesPackageImpl#getEntity()
     * @generated
     */
    EClass ENTITY = eINSTANCE.getEntity();

    /**
     * The meta object literal for the '<em><b>Extends</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__EXTENDS = eINSTANCE.getEntity_Extends();

    /**
     * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__PROPERTIES = eINSTANCE.getEntity_Properties();

    /**
     * The meta object literal for the '<em><b>Package</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__PACKAGE = eINSTANCE.getEntity_Package();

  }

} //EntitiesPackage
