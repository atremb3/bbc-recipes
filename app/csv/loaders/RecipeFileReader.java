package csv.loaders;

import java.io.File;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.core.io.Resource;

public class RecipeFileReader {

	private FlatFileItemReader<RecipeData> itemReader;
	private DefaultLineMapper<RecipeData> lineMapper;
	
	public RecipeFileReader(String filename) {
		Resource recipeFile = CSVFileFactory.create(filename);
		RecipeFileReaderFactory.create(recipeFile);
	}
	
	public RecipeFileReader(File file) {
		Resource recipeFile = CSVFileFactory.create(file);
		RecipeFileReaderFactory.create(recipeFile);
		itemReader = RecipeFileReaderFactory.create(recipeFile);
	}
	
   public RecipeFileReader(String directory, String fileName) {
        Resource file = CSVFileFactory.create(directory, fileName);
        itemReader = RecipeFileReaderFactory.create(file);
    }

    public void open() {
        itemReader.open(new ExecutionContext());
    }

    public void close() {
        itemReader.close();
    }

    public RecipeData read() throws Exception {
    	RecipeData data = itemReader.read();
        return data;
    }
}
