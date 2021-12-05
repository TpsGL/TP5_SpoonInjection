package processors;

import parsers.SpoonParser;

public class SpoonProcessor extends Processor<SpoonParser> {

    public SpoonProcessor(String projectPath) {
        super(projectPath);
    }

    @Override
    public void setParser(String projectPath) {
        parser = new SpoonParser(projectPath);
    }

    public void setParser(SpoonParser parser) {
        this.parser = parser;
    }
}
