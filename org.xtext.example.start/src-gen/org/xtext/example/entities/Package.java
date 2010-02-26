/**
 * <copyright>
 * </copyright>
 *

 */
package org.xtext.example.entities;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.xtext.example.entities.Package#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.xtext.example.entities.EntitiesPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends PackagedType
{
  /**
   * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.entities.PackagedType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Properties</em>' containment reference list.
   * @see org.xtext.example.entities.EntitiesPackage#getPackage_Properties()
   * @model containment="true"
   * @generated
   */
  EList<PackagedType> getProperties();

} // Package
