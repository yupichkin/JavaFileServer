package server;

import java.io.*;

public class SerializationUtils {
    public static final String SP = File.separator;
    public static final String pathToFolder = Constants.defaultFolderForData;

    public static void serializeFileIdInfo(FileStorage fileStorage) throws IOException {
        String filename = pathToFolder + SP + "idFiles.data";
        try {
            serialize(fileStorage, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeFileIdInfo() throws IOException {
        String filename = pathToFolder + SP + "idFiles.data";
        File file = new File(filename);
        FileStorage fileStorage = null;
        if(file.createNewFile()) { //returns false if file already was created
            fileStorage = FileStorage.getInstance();
        } else {
            try {
                FileStorage.deserialize((FileStorage) deserialize(filename));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    private static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
