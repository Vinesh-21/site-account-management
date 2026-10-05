package com.energyplatform.site_account_management.Utils;

public class CommonMethods {


    private CommonMethods() {

    }

    public static String secondsToTime(Long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}
