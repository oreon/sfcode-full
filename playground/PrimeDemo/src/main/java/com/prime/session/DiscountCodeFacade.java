/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prime.session;

import com.prime.domain.DiscountCode;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author goutham
 */
@Stateless
public class DiscountCodeFacade extends AbstractFacade<DiscountCode> {
    @PersistenceContext(unitName = "primeDemoPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscountCodeFacade() {
        super(DiscountCode.class);
    }

}
