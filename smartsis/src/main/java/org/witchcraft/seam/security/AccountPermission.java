package org.witchcraft.seam.security;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.jboss.seam.annotations.security.permission.PermissionAction;
import org.jboss.seam.annotations.security.permission.PermissionDiscriminator;
import org.jboss.seam.annotations.security.permission.PermissionRole;
import org.jboss.seam.annotations.security.permission.PermissionTarget;
import org.jboss.seam.annotations.security.permission.PermissionUser;

@Entity
public class AccountPermission implements Serializable
{          
   @Id @GeneratedValue public Integer permissionId;
   @PermissionUser @PermissionRole public String recipient;
   @PermissionTarget public String target;
   @PermissionAction public String action;
   @PermissionDiscriminator public String discriminator;
}