package org.oop.lostfound.config;

public class Session
{
    private static int id;
    private static String username;
    private static String role;

    public static void setId(int id) { Session.id = id; }
    public static int getId() { return id; }

    public static void setUsername(String username) { Session.username = username; }
    public static String getUsername() { return username; }

    public static void setRole(String role) { Session.role = role; }
    public static String getRole() { return role; }

    // Hapus data saat logout
    public static void clear()
    {
        id = 0;
        username = null;
        role = null;
    }
}