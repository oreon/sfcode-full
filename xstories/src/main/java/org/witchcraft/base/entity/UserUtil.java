package org.witchcraft.base.entity;

import org.wcdemo.xstories.TeamMember;


public class UserUtil {

    private static ThreadLocal<TeamMember> currentUser = new ThreadLocal<TeamMember>();
    private static ThreadLocal<String> ipAddress = new ThreadLocal<String>();
    
    public static void setCurrentUserAndIp(TeamMember user, String ip) {
        currentUser.set(user);
        ipAddress.set(ip);
    }
    
    public static TeamMember getCurentUser() { return currentUser.get(); }
    public static String getIpAddress() { return ipAddress.get(); }
}
