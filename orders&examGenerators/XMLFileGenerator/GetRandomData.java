import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GetRandomData {

	List<String> data;
	Random random;

	public GetRandomData(String dataFilePath, Random random) {
		initData(dataFilePath);
		initRandom(random);
	}

	private void initRandom(Random random) {
		this.random = random;
	}

	private void initData(String dataFilePath) {
		File dataFile = new File(dataFilePath);
		data = fileToList(dataFile);
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
	
    /** Generate a random data */
    public String nextName() {
        int i = random.nextInt(data.size());
        return data.get(i);
    }

}
