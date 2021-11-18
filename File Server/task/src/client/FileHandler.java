package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileHandler {
    private final String fileFolder;

    public FileHandler(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    public boolean saveFile(String filename, byte[] data) { //returns true if file created, and false if already was created
        File file = new File(fileFolder + File.separator + filename);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(data);
        } catch (Exception e) {
            return false; //file is not created
        }
        return true;
    }

    public byte[] getFileByName(String filename) {
        File file = new File(fileFolder + File.separator + filename);
        byte[] fileAsByteArray = new byte[(int) file.length()];
        try {
            FileInputStream fis = new FileInputStream(file);
            if (fis.read(fileAsByteArray, 0, fileAsByteArray.length) == 0) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return fileAsByteArray;
    }
}
