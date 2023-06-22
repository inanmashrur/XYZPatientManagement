package org.xyz.patientmanagement.editor;

import org.springframework.stereotype.Component;
import org.xyz.patientmanagement.model.Test;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author inanmashrur
 * @since 5/28/2023
 */
@Component
public class TestEditor extends PropertyEditorSupport  {

    @Override
    public String getAsText() {
        return super.getAsText();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("Test in String: " + text);

        List<String> testProps = Arrays.stream(text.split("-%-"))
                .map(String::trim)
                .collect(Collectors.toList());

        Test test = new Test(testProps.get(0), testProps.get(1));

        System.out.println(test);

        super.setValue(test);
    }
}
