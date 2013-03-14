package csv.loaders;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.Resource;

public class RecipeFileReaderFactory {

	public static final String[] FIELD_TOKENS = new String[]{
		"name",
		"issue",
		"page",
		"category",
		"comment",
		"nbStars"
	};
	
    public static FlatFileItemReader<RecipeData> create(Resource recipeFile) {
        FlatFileItemReader<RecipeData> recipeDataReader = new FlatFileItemReader<RecipeData>();
        recipeDataReader.setLinesToSkip(0);
        recipeDataReader.setResource(recipeFile);
        recipeDataReader.setLineMapper(getLineMapper());
        return recipeDataReader;
    }
    
    private static LineMapper getLineMapper() {
        DefaultLineMapper lineMapper = new DefaultLineMapper();
        lineMapper.setLineTokenizer( getTokenizer() );
        lineMapper.setFieldSetMapper(new RecipeDataMapper());
        return lineMapper;
    }
    
    private static LineTokenizer getTokenizer() {
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(FIELD_TOKENS);
        return tokenizer;
    }
}
