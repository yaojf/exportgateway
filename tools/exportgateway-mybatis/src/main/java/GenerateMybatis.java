import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * Created by yaojiafeng on 16/2/19.
 */
public class GenerateMybatis {

    private static final String XML_NAME = "generatorConfig.xml";

    public static void main(String[] args) {
        String configDir = GenerateMybatis.class.getClassLoader().getResource("config").getFile();
        List<String> warnings = new ArrayList<String>();
        Set<String> fullyqualifiedTables = new HashSet<String>();

        Set<String> contexts = new HashSet<String>();
        try {
            for (File configurationFile : new File(configDir).listFiles()) {
                if (configurationFile.getName().equals(XML_NAME)) {
                    ConfigurationParser cp = new ConfigurationParser(warnings);
                    Configuration config = cp.parseConfiguration(configurationFile);

                    DefaultShellCallback shellCallback = new DefaultShellCallback(Boolean.TRUE);

                    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);

                    ProgressCallback progressCallback = new VerboseProgressCallback();

                    myBatisGenerator.generate(progressCallback, contexts, fullyqualifiedTables);
                }
            }
        } catch (XMLParserException e) {
            writeLine(getString("Progress.3"));
            writeLine();
            for (String error : e.getErrors()) {
                writeLine(error);
            }

            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (InvalidConfigurationException e) {
            writeLine(getString("Progress.16"));
            for (String error : e.getErrors()) {
                writeLine(error);
            }
            return;
        } catch (InterruptedException e) {
            return;
        }

        for (String warning : warnings) {
            writeLine(warning);
        }

        if (warnings.size() == 0) {
            writeLine(getString("Progress.4"));
        } else {
            writeLine();
            writeLine(getString("Progress.5"));
        }
    }

    private static void writeLine(String message) {
        System.out.println(message);
    }

    private static void writeLine() {
        System.out.println();
    }

}
