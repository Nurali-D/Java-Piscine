package edu.school21.models;

import edu.school21.interfaces.PreProcessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String preProcess(String message) {
        return message.toUpperCase();
    }
}
