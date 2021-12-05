package processors;

import mappers.Mapper;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.support.reflect.code.CtNewClassImpl;
import spoon.support.reflect.declaration.CtFieldImpl;

public class AnonymousProcessor extends AbstractProcessor<CtNewClassImpl> {


    Mapper mapper = new Mapper();

    @Override
    public boolean isToBeProcessed(CtNewClassImpl candidate) {

        CtMethod ctMethod = eMethodContainer(candidate);

        if (ctMethod != null) {
            CtClass ctClass = eClassContainer(ctMethod);

            if (ctClass.getSimpleName().equals("MainActivity")) {
                System.err.println(ctMethod.getSimpleName());
                return true;
            }
        }


        return false;
    }


    /**
     *
     * @param candidate
     * @return
     */
     private CtClass eClassContainer(CtMethod candidate) {
        return (CtClass) candidate.getParent();
     }


    /**
     *
     * @param candidate
     * @return
     */
     private CtMethod eMethodContainer(CtNewClassImpl candidate) {

        CtElement item = candidate;

        while ( (item != null) && (!(item instanceof CtMethod)  ) ) {
            item = item.getParent();
        }
        return (CtMethod)item;

    }




    @Override
    public void process(CtNewClassImpl candidate) {

        CtCodeSnippetStatement snippet1 = getFactory().Core().createCodeSnippetStatement();
        if (candidate.getAnonymousClass().isAnonymous()) {

            candidate.getAnonymousClass()
                    .getMethods()
                    .forEach( m -> {

                        CtBlock<?> ctBlock = ((CtExecutable<?>) m).getBody();
                        String key = eMethodContainer(candidate).getSimpleName();
                        String value = String.format("Operation operation = new Operation(user,product, "
                                + mapper.getBindEventsToOp().get(key) +")");

                        snippet1.setValue(value);
                        ctBlock.insertEnd(snippet1);
                        /**
                         * Add statement !!
                         */
                        ctBlock.insertEnd(createLoggerCodeSnippetStatement());
                    });
        }
    }

    /**
     *
     * @return
     */
    private CtCodeSnippetStatement createLoggerCodeSnippetStatement() {

        String expression = "LoggingHelper.getInstance().log(operation)";

        final CtCodeSnippetStatement snippet2 = getFactory().Code().createCodeSnippetStatement(expression);

        return snippet2;
    }



}
