package com.batdemir.android.todolist.application.android.Tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ToolStorage {

    // Primary physical SD-CARD (not emulated)
    private static final String RAW_EXTERNAL_STORAGE = System.getenv("EXTERNAL_STORAGE");

    // All Secondary SD-CARDs (all exclude primary) separated by File.pathSeparator, i.e: ":", ";"
    private static final String RAW_SECONDARY_STORAGES = System.getenv("SECONDARY_STORAGE");

    // Primary emulated SD-CARD
    private static final String RAW_EMULATED_STORAGE_TARGET = System.getenv("EMULATED_STORAGE_TARGET");

    // PhysicalPaths based on phone model
    @SuppressLint("SdCardPath")
    @SuppressWarnings("SpellCheckingInspection")
    private static final String[] KNOWN_PHYSICAL_PATHS = new String[]{
            "/storage/sdcard0",
            "/storage/sdcard1",                 //Motorola Xoom
            "/storage/extsdcard",               //Samsung SGS3
            "/storage/sdcard0/external_sdcard", //User request
            "/mnt/extsdcard",
            "/mnt/sdcard/external_sd",          //Samsung galaxy family
            "/mnt/sdcard/ext_sd",
            "/mnt/external_sd",
            "/mnt/media_rw/sdcard1",            //4.4.2 on CyanogenMod S3
            "/removable/microsd",               //Asus transformer prime
            "/mnt/emmc",
            "/storage/external_SD",             //LG
            "/storage/ext_sd",                  //HTC One Max
            "/storage/removable/sdcard1",       //Sony Xperia Z1
            "/data/sdext",
            "/data/sdext2",
            "/data/sdext3",
            "/data/sdext4",
            "/sdcard1",                         //Sony Xperia Z
            "/sdcard2",                         //HTC One M8s
            "/storage/microsd"                  //ASUS ZenFone 2
    };

    /**
     * Returns all available SD-Cards in the system (include emulated)
     * <p/>
     * Warning: Hack! Based on Android source code of version 4.3 (API 18)
     * Because there is no standard way to get it.
     *
     * @return paths to all available SD-Cards in the system (include emulated)
     */
    public static String[] getStorageDirectories(Context context) {
        // Final set of paths
        final Set<String> finalAvailableDirectoriesSet = new HashSet<>();
        if (TextUtils.isEmpty(RAW_EMULATED_STORAGE_TARGET)) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Solution of empty raw emulated storage for android version > marshmallow
                // because the RAW_EXTERNAL_STORAGE become something i.e: "/Storage/A5F9-15F4"
                File[] files = context.getExternalFilesDirs(null);
                for (File file : files) {
                    if (file != null) {
                        String applicationSpecificAbsolutePath = file.getAbsolutePath();
                        String emulatedRootPath = applicationSpecificAbsolutePath.substring(
                                0, applicationSpecificAbsolutePath.indexOf("Android/data")
                        );
                        finalAvailableDirectoriesSet.add(emulatedRootPath);
                    }
                }
            } else {
                if (TextUtils.isEmpty(RAW_EXTERNAL_STORAGE)) {
                    finalAvailableDirectoriesSet.addAll(getAvailablePhysicalPaths());
                } else {
                    // Device has physical external storage; use plain paths.
                    finalAvailableDirectoriesSet.add(RAW_EXTERNAL_STORAGE);
                }
            }
        } else {
            // Device has emulated storage; external storage paths should have id in the last segment
            String rawStorageId = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                final String[] folders = File.separator.split(path);
                final String lastSegment = folders[folders.length - 1];
                if (!TextUtils.isEmpty(lastSegment) && TextUtils.isDigitsOnly(lastSegment)) {
                    rawStorageId = lastSegment;
                }
            }
            // i.e: "/storage/emulated/storageId" where storageId is 0, 1, 2, ...
            if (TextUtils.isEmpty(rawStorageId)) {
                finalAvailableDirectoriesSet.add(RAW_EMULATED_STORAGE_TARGET);
            } else {
                finalAvailableDirectoriesSet.add(RAW_EMULATED_STORAGE_TARGET + File.separator + rawStorageId);
            }
        }
        // Add all secondary storages
        if (!TextUtils.isEmpty(RAW_SECONDARY_STORAGES)) {
            // All Secondary SD-CARDs split into array
            final String[] rawSecondaryStorages = RAW_SECONDARY_STORAGES.split(File.pathSeparator);
            Collections.addAll(finalAvailableDirectoriesSet, rawSecondaryStorages);
        }
        return finalAvailableDirectoriesSet.toArray(new String[finalAvailableDirectoriesSet.size()]);
    }

    public static String[] getSDCardDirectory(Context context) {
        // Final set of paths
        final Set<String> finalAvailableDirectoriesSet = new HashSet<>();
        if (TextUtils.isEmpty(RAW_EMULATED_STORAGE_TARGET)) {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Solution of empty raw emulated storage for android version > marshmallow
                // because the RAW_EXTERNAL_STORAGE become something i.e: "/Storage/A5F9-15F4"
                File[] files = context.getExternalFilesDirs(null);
                for (File file : files) {
                    if (file != null) {
                        String applicationSpecificAbsolutePath = file.getAbsolutePath();
                        String emulatedRootPath = applicationSpecificAbsolutePath.substring(
                                0, applicationSpecificAbsolutePath.indexOf("Android/data")
                        );
                        if (!emulatedRootPath.contains("emulated")) {
                            finalAvailableDirectoriesSet.add(emulatedRootPath);
                        }
                    }
                }
            } else {
                if (TextUtils.isEmpty(RAW_EXTERNAL_STORAGE)) {
                    finalAvailableDirectoriesSet.addAll(getAvailablePhysicalPaths());
                } else {
                    // Device has physical external storage; use plain paths.
                    finalAvailableDirectoriesSet.add(RAW_EXTERNAL_STORAGE);
                }
            }
        } else {
            // Device has emulated storage; external storage paths should have id in the last segment
            String rawStorageId = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                final String[] folders = File.separator.split(path);
                final String lastSegment = folders[folders.length - 1];
                if (!TextUtils.isEmpty(lastSegment) && TextUtils.isDigitsOnly(lastSegment)) {
                    rawStorageId = lastSegment;
                }
            }
            // i.e: "/storage/emulated/storageId" where storageId is 0, 1, 2, ...
            if (TextUtils.isEmpty(rawStorageId)) {
                finalAvailableDirectoriesSet.add(RAW_EMULATED_STORAGE_TARGET);
            } else {
                finalAvailableDirectoriesSet.add(RAW_EMULATED_STORAGE_TARGET + File.separator + rawStorageId);
            }
        }
        // Add all secondary storages
//        if (!TextUtils.isEmpty(RAW_SECONDARY_STORAGES)) {
//            // All Secondary SD-CARDs split into array
//            final String[] rawSecondaryStorages = RAW_SECONDARY_STORAGES.split(File.pathSeparator);
//            Collections.addAll(finalAvailableDirectoriesSet, rawSecondaryStorages);
//        }
        return finalAvailableDirectoriesSet.toArray(new String[finalAvailableDirectoriesSet.size()]);
    }

    /**
     * Filter available physical paths from known physical paths
     *
     * @return List of available physical paths from current device
     */
    private static List<String> getAvailablePhysicalPaths() {
        List<String> availablePhysicalPaths = new ArrayList<>();
        for (String physicalPath : KNOWN_PHYSICAL_PATHS) {
            File file = new File(physicalPath);
            if (file.exists()) {
                availablePhysicalPaths.add(physicalPath);
            }
        }
        return availablePhysicalPaths;
    }
}
