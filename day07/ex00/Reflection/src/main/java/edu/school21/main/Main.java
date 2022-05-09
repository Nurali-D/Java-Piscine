package edu.school21.main;

public class Main {

    public static void main(String[] args) {
        ClassReader cr = new ClassReader();
        if (!cr.showClasses()) {
            System.out.println("There is no classes in package.");
            System.exit(0);
        }
        System.out.println(ClassReader.DELIMITER);
        cr.readClass();
        System.out.println(ClassReader.DELIMITER);
        cr.showClassStructure();
        System.out.println(ClassReader.DELIMITER);
        cr.createdObject();
        System.out.println(ClassReader.DELIMITER);
        cr.modifyFiledObject();
        System.out.println(ClassReader.DELIMITER);
        cr.callMethod();
        System.out.println(ClassReader.DELIMITER);

    }
}