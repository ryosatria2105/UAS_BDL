package util;

import model.Pegawai;

public class Session {

    private static Pegawai loggedUser;

    public static String currentUsername; // untuk Forgot Password
    public static int currentOTP;         // OTP yang sedang aktif

    public static void setLoggedUser(Pegawai p) {
        loggedUser = p;
    }

    public static Pegawai getLoggedUser() {
        return loggedUser;
    }

    public static void clear() {
        loggedUser = null;
        currentUsername = null;
        currentOTP = 0;
    }
}
