import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AddressRandomGenerator {

    List<String> addresses;
    Random random;

    public AddressRandomGenerator(String addressFilePath, Random random) {
        initNames(addressFilePath);
        initRandom(random);
    }

	private void initRandom(Random random) {
        this.random = random;
	}

	private void initNames(String addressFilePath) {
        File addressFile = new File(addressFilePath);
        addresses = fileToList(addressFile);
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
	
    /** Generate a random address */
    public String nextAddress() {
        int i = random.nextInt(addresses.size());
        return addresses.get(i);
    }
}
