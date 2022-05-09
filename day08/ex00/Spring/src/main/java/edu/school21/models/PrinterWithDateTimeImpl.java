package edu.school21.models;

import edu.school21.interfaces.Printer;
import edu.school21.interfaces.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        renderer.print(LocalDateTime.now() + " " + message);
    }

}
