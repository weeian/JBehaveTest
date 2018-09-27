package base;

import org.apache.commons.lang.StringUtils;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.reporters.Format.*;

/**
 * 这是JBehave 的基础架构,运行原理
 */
public abstract class BaseAcceptanceTest extends JUnitStory {

    @Test
    @Override
    public void run() throws Throwable {
        Embedder embedder = configuredEmbedder();
        StoryPathResolver pathResolver = embedder.configuration().storyPathResolver();
        try {
            String rootDir =System.getenv("RUN_DIR");

            if(StringUtils.isEmpty(rootDir))
            {
                rootDir = System.getProperty("user.dir");
            }

            String storiesDir = rootDir + File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"stories";

            System.out.println(storiesDir);
            File[] files = new File(storiesDir).listFiles();
            List<String> stories = new ArrayList<String>();
            for (File file : files){
                String fileName = file.getName();
                if(fileName.endsWith(".story")){
                    stories.add("stories/"+fileName);
                }
            }
            embedder.runStoriesAsPaths(stories);
        } finally {
            embedder.generateCrossReference();
        }
    }

    public BaseAcceptanceTest() {
        Embedder embedder = configuredEmbedder();
        embedder.useMetaFilters(getMetaFilters());
        embedder.embedderControls().doVerboseFailures(true)
                .useStoryTimeoutInSecs(120);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withDefaultFormats()
                        .withFormats(CONSOLE, HTML, TXT)
                        .withCodeLocation(
                                CodeLocations.codeLocationFromPath("build/jbehave")));
    }

    private List<String> getMetaFilters() {
        String metaFiltersProperty = System.getProperty("metaFilters", "");
        List<String> result =Arrays.asList(metaFiltersProperty.split(","));
        System.out.println("result******"+result);
        return result;
    }


}
