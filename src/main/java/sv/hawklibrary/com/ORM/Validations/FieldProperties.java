package sv.hawklibrary.com.ORM.Validations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import sv.hawklibrary.com.ORM.Annotations.FieldName;

public class FieldProperties {

	public static final String getMessage(Field field,String tableName,String fieldName) throws FileNotFoundException, IOException {
		
		
		fieldName="No se puede duplicar "+fieldName;
		
		return fieldName;
	}
}
