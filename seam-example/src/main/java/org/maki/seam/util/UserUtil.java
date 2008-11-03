package org.maki.seam.util;

import org.maki.seam.model.User;


public class UserUtil {

    private static ThreadLocal<User> currentUser = new ThreadLocal<User>();
    private static ThreadLocal<String> ipAddress = new ThreadLocal<String>();
    
    public static void setCurrentUserAndIp(User user, String ip) {
        currentUser.set(user);
        ipAddress.set(ip);
    }
    
    public static User getCurentUser() { return currentUser.get(); }
    public static String getIpAddress() { return ipAddress.get(); }
}
