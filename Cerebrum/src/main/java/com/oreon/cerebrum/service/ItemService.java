package com.oreon.cerebrum.service;

import com.oreon.cerebrum.prescriptions.Item;
import com.oreon.cerebrum.dao.ItemDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Item
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface ItemService extends ItemDao, BaseService<Item> {

}
