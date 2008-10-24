package org.maki.seam.model;

import java.util.Date;

import javax.persistence.PrePersist;

public class UserListener {

    @PrePersist
    public void setDates(User user) {
        Date now = new Date();
        if (user.getDateCreated() == null) {
            user.setDateCreated(now);
        }
        user.setDateUpdated(now);
    }
}
