/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.hawklibrary.com.ORM.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

  
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataModelAnnotations {
    
     String tableName();
     String Id() default "id";
    
    
}
