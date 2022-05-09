package edu.school21.models;

import edu.school21.interfaces.PreProcessor;

public class PreProcessorToLower implements PreProcessor {

    @Override
    public String preProcess(String message) {
        return message.toLowerCase();
    }
}
