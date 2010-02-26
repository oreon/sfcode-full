/**
 * <copyright>
 * </copyright>
 *

 */
package org.xtext.example.entities;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.entities.TypeDef#getName <em>Name</em>}</li>
 *   <li>{@link org.xtext.example.entities.TypeDef#getMappedType <em>Mapped Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.entities.EntitiesPackage#getTypeDef()
 * @model
 * @generated
 */
public interface TypeDef extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.xtext.example.entities.EntitiesPackage#getTypeDef_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.xtext.example.entities.TypeDef#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Mapped Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapped Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapped Type</em>' containment reference.
   * @see #setMappedType(JAVAID)
   * @see org.xtext.example.entities.EntitiesPackage#getTypeDef_MappedType()
   * @model containment="true"
   * @generated
   */
  JAVAID getMappedType();

  /**
   * Sets the value of the '{@link org.xtext.example.entities.TypeDef#getMappedType <em>Mapped Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mapped Type</em>' containment reference.
   * @see #getMappedType()
   * @generated
   */
  void setMappedType(JAVAID value);

} // TypeDef
