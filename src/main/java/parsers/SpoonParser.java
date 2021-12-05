package parsers;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.processing.Processor;

public class SpoonParser extends Parser<Launcher> {

    private boolean launcherOption;

    public void setLauncherOption(boolean launcherOption) { this.launcherOption = launcherOption; }

    public SpoonParser(String projectPath) { super(projectPath); }

    public void createSampleLauncher() { parser = new Launcher(); }

    public void createMavenLauncher() { parser = new MavenLauncher(getProjectSrcPath(), MavenLauncher.SOURCE_TYPE.APP_SOURCE); }


    public void initLauncher(boolean stateLauncher) {
        setLauncherOption(stateLauncher);
        if (launcherOption) {
            createSampleLauncher();
        } else {
            createMavenLauncher();
        }
    }

    public void setLauncher(String sourceOutputPath, String binaryOutputPath,
                            boolean autoImports, boolean commentsEnabled) {
        //1. create launcher
        initLauncher(true);
        //2. set project source path
        parser.addInputResource(getProjectSrcPath());
        //3. set project classpath
        parser.getEnvironment().setSourceClasspath(new String[] {getProjectBinPath()});
        //4. set generated source code directory path
        parser.setSourceOutputDirectory(sourceOutputPath);
        //5. set generated binary code directory path
        parser.setBinaryOutputDirectory(binaryOutputPath);
        //6. set auto imports
        parser.getEnvironment().setAutoImports(autoImports);
        //7. set comments enabled
        parser.getEnvironment().setCommentEnabled(commentsEnabled);
    }

    @Override
    public void configure() {
        setLauncher(projectPath+"\\spooned\\src",
                projectPath+"\\spooned\\bin", true, true);
    }

    public void addProcessor(Processor processor) {
        parser.addProcessor(processor);
    }

    public void run() {
        parser.run();
    }
}
