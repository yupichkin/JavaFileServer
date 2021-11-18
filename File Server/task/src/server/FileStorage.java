package server;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//class for storing file and creating and saving their IDs
public class FileStorage implements Serializable {
    private static final long serialVersionUID = 1L;

    private static FileStorage fileStorage;
    private Map<Integer, String> idAndFilenames;
    private int idCounter;
    private String storageFolder;

    public static void deserialize(FileStorage deserializedInstance) {
        fileStorage = deserializedInstance;
    }

    public static FileStorage getInstance(){
        if(fileStorage == null) {
            fileStorage = new FileStorage();
        }
        return fileStorage;
    }

    private FileStorage() {
        this.idAndFilenames = new HashMap<>();
        this.idCounter = 0;
        storageFolder = Constants.defaultFolderForData;
    }

    public boolean deleteFileById(Integer id) {
        String filename = idAndFilenames.get(id);
        if(filename == null) { //no existing file
            return false;
        }
        idAndFilenames.remove(id);
        return deleteFile(filename);
    }

    public boolean deleteFileByName(String filename) { //returns false when deletions fails
        idAndFilenames.values().remove(filename);
        return deleteFile(filename);
    }

    private boolean deleteFile(String filename) {
        File file = new File(storageFolder + File.separator + filename);
        return file.delete();
    }

    public int saveFile(String filename, byte[] data) { //returns id of file, returns 0 if there are creating fails;
        File file = new File(storageFolder + File.separator + filename);
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(data);
        } catch (Exception e) {
            return 0;
        }
        idAndFilenames.put(++idCounter, filename);
        return idCounter;
    }

    public byte[] getFileById(Integer id) {//returns null if there is no existing file
        String filename = idAndFilenames.get(id);
        if(filename == null) { //no existing file
            return null;
        }
        return getFileByName(filename);
    }

    public byte[] getFileByName(String filename) {
        File file = new File(storageFolder + File.separator + filename);
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