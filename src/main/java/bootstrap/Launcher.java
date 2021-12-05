package bootstrap;

import parsers.SpoonParser;
import processors.AnonymousProcessor;

public class Launcher {


    public static void main(String[] args) {

        // Psensez à changer changer "projectPath" qui dépend !!
        SpoonParser parser = new SpoonParser("C:\\Users\\gm_be\\IdeaProjects\\tp5");

        parser.configure();

        //parser.addProcessor(new LoggingProcessor());
        parser.addProcessor(new AnonymousProcessor());

        parser.run();

    }
}
