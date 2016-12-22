package plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by yaojiafeng on 16/2/19.
 */
public class SqlMapperOverridePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return Boolean.TRUE;
    }

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.set(sqlMap, Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

}
