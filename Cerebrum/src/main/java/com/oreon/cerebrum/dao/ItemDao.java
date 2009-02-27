package com.oreon.cerebrum.dao;

import com.oreon.cerebrum.prescriptions.Item;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ItemDao extends GenericDAO<Item> {

}
