package edu.school21.app;

import edu.school21.interfaces.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/main/resources/context.xml");
        Printer printer1 = context.getBean("printerWithPrefixErrUp", Printer.class);
        printer1.print("Hello!");
        Printer printer2 = context.getBean("printerWithPrefixErrLow", Printer.class);
        printer2.print("Hello!");
        Printer printer3 = context.getBean("printerWithPrefixStandUp", Printer.class);
        printer3.print("Hello!");
        Printer printer4 = context.getBean("printerWithPrefixStandLow", Printer.class);
        printer4.print("Hello!");

        Printer printer5 = context.getBean("printerWithDateErrUp", Printer.class);
        printer5.print("Hello!");
        Printer printer6 = context.getBean("printerWithDateErrLow", Printer.class);
        printer6.print("Hello!");
        Printer printer7 = context.getBean("printerWithDateStandUp", Printer.class);
        printer7.print("Hello!");
        Printer printer8 = context.getBean("printerWithDateStandLow", Printer.class);
        printer8.print("Hello!");
    }
}
