package csv.loaders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class RecipeDataMapper implements FieldSetMapper<RecipeData> {

	@Override
	public RecipeData mapFieldSet(FieldSet fieldSet) throws BindException {
		
		int i = 0;
		RecipeData data = new RecipeData();
		data.name = fieldSet.readString(i++);
		data.issue = fieldSet.readString(i++);
		String page = fieldSet.readString(i++);
		
		Pattern pattern = Pattern.compile("(\\d+)");
		Matcher pageMatcher = pattern.matcher(page);
		
		if (pageMatcher.find()) {
			data.page =  Integer.parseInt(pageMatcher.group());
		}
		
		data.category = fieldSet.readString(i++);
		data.comment = fieldSet.readString(i++);

		String nbStarsS = fieldSet.readString(i++);
		if (!nbStarsS.isEmpty()) {
			Matcher starMatcher = pattern.matcher(nbStarsS);
			if (starMatcher.find()) {
				data.nbStars = Integer.parseInt(starMatcher.group());
			}
		}		
		return data;
	}

}
