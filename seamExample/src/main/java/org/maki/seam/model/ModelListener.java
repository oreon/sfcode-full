package org.maki.seam.model;

import java.util.Date;

import javax.persistence.PrePersist;

import org.maki.seam.util.UserUtil;

public class ModelListener {

    @PrePersist
    public void setDatesAndUser(ModelBase modelBase) {
        User currentUser = UserUtil.getCurentUser();
        
        if (currentUser != null) {
            if (modelBase.getCreatedByUser() == null) {
                modelBase.setCreatedByUser(currentUser);
            }
            modelBase.setUpdatedByUser(currentUser);
            
            Date now = new Date();
            if (modelBase.getDateCreated() == null) {
                modelBase.setDateCreated(now);
            }
            modelBase.setDateUpdated(now);
            
        }
    }
}
