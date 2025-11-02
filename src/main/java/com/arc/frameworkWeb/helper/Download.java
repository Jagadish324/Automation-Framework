package com.arc.frameworkWeb.helper;

import com.arc.frameworkWeb.utility.CONSTANT;
import com.arc.helper.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Download {
    private static final Logger log = LoggerFactory.getLogger(Download.class);
    /**
     * Verifies whether all expected items (files or folders) have been successfully downloaded
     * to a specified folder.
     *
     * This method waits for the download to complete in the specified folder and then compares
     * the files in the folder with the expected items. It returns {@code true} if all expected
     * items are present, otherwise {@code false}.
     *
     * @param folderName The name of the folder where the files are expected to be downloaded.
     * @param fileName   A comma-separated string of file names to verify in the download folder.
     * @return {@code true} if all expected items are found in the folder after the download
     *         completes; {@code
     */

    public static boolean verifyAllItemDownloadedSuccessfully(String folderName,String fileName) {
        String downloadPath = (SystemInfo.getOsName().contains("win")) ? System.getProperty("user.dir") + "\\" + folderName + "\\" :
                System.getProperty("user.dir") + "/" + folderName + "/";
        if(waitToCompleteDownload(CONSTANT.EXPLICIT_WAIT,downloadPath)) {
            List<String> itemToVerify = CommonHelper.getListFromCommaSeparatedString(fileName);
            File dir = new File(downloadPath);
            File[] dir_contents = dir.listFiles();
            List<String> fileNames = new ArrayList<>();
            for (File file : dir_contents) {
                fileNames.add(file.getName());
            }
            System.out.println("Files/Folders present in " + folderName + " folder:");
            System.out.println(fileNames);
            System.out.println(itemToVerify);
            return fileNames.containsAll(itemToVerify);
        }
        else{
            return false;
        }
    }

    /**
     * Waits for a file download to complete in the specified directory within the given timeout period.
     *
     * This method monitors the specified directory to detect and confirm the completion of a file download.
     * It first checks if the download has started using the `isDownloadStarted` method. Once the download starts,
     * it continuously checks for the presence of temporary or partial download files (e.g., files ending with `.crdownload`).
     * The method returns `true` if the download completes successfully within the timeout period, otherwise `false`.
     *
     * @param timeoutSeconds The maximum duration (in seconds) to wait for the download to complete.
     * @param path           The directory path where the file download is expected to occur.
     * @return {@code true} if the download completes within the timeout period; {@code false} otherwise.
     */
    public static boolean waitToCompleteDownload(int timeoutSeconds,String path) {
        File dir = new File(path);
        boolean downloadCompleted = false;
        long startTime = System.currentTimeMillis();
        long timeoutMillis = timeoutSeconds * 1000L;

        if(isDownloadStarted(path)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (!downloadCompleted) {
                if (System.currentTimeMillis() - startTime > timeoutMillis) {
                    log.info("Timeout !! waited for " + timeoutSeconds + " seconds");
                    break;
                }
                File[] files = dir.listFiles((dir1, name) -> name.endsWith(".crdownload"));
                if (files == null || files.length == 0) {
                    downloadCompleted = true;
                    log.info("Download completed.");
                } else {
                    log.info("downloading.....");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return downloadCompleted;
    }
    /**
     * Checks whether a file download has started in the specified directory.
     *
     * This method continuously monitors the directory at the given `path` to
     * check if any files exist, indicating that the download has started.
     * It waits for a maximum duration defined by `CONSTANT.EXPLICIT_WAIT`.
     *
     * @param path The directory path where the file download is expected to occur.
     * @return {@code true} if a file is detected within the timeout period, indicating
     *         that the download has started; {@code false} otherwise.
     */
    public static boolean isDownloadStarted(String path) {
        File dir = new File(path);
        boolean downloadStarted = false;
        long startTime = System.currentTimeMillis();
        long timeoutMillis = CONSTANT.EXPLICIT_WAIT * 1000L;

        while (!downloadStarted) {

            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                downloadStarted = true;
            } else {
                if (System.currentTimeMillis() - startTime > timeoutMillis) {
                    log.info("Timeout !! waited for " + CONSTANT.EXPLICIT_WAIT + " seconds to start download");
                    break;
                }
                log.info("Waiting to start download...");
                try {
                    Thread.sleep(500); // Sleep for 500 milliseconds before checking again
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(downloadStarted)
            log.info("Download Started.");
        return downloadStarted;
    }
}
