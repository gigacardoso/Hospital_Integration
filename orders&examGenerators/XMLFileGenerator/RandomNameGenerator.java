
import java.io.*;
import java.util.*;

public class RandomNameGenerator {

    List<String> names;
    List<String> surnames;
    Random random;

    public RandomNameGenerator(String nameFilePath, Random random) {
        initNames(nameFilePath);
        initRandom(random);
    }
    
    public RandomNameGenerator(String nameFilePath, String surnameFilePath, Random random) {
        initNames(nameFilePath);
        initSurnames(surnameFilePath);
        initRandom(random);
    }

    private void initNames(String nameFilePath) {
        File nameFile = new File(nameFilePath);
        names = fileToList(nameFile);
    }

    private void initSurnames(String surnameFilePath) {
        File surnameFile = new File(surnameFilePath);
        surnames = fileToList(surnameFile);
    }

    private List<String> fileToList(File file) {
        List<String> list = new ArrayList<String>();

        FileReader fileReader = null;
        BufferedReader in = null;
        try {
            fileReader = new FileReader(file);
            in = new BufferedReader(fileReader);

            // fill array with file data
            String line;
            while ((line = in.readLine()) != null)
                list.add(line);

        } catch(IOException ioe) {
            throw new RuntimeException(ioe);
        } finally {
            if (in != null) {
                try {
                    in.close();
                    
                } catch(IOException ioe) {
                    // ignore
                }
            }
        }

        return list;
    }

    private void initRandom(Random random) {
        this.random = random;
    }

    /** Generate a random name */
    public String nextName() {
        int i = random.nextInt(names.size());
        int j = random.nextInt(surnames.size());

        return names.get(i) + " " + surnames.get(j);
    }

}