package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import java.io.IOException;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Program {

    @Parameter(names="--white")
	private String color1;
	@Parameter(names="--black")
	private String color2;

    private static final String FILE = "./target/resources/image.bmp";

    public static void main(String[] args) {
        Program program = new Program();

		try {
			JCommander.newBuilder().addObject(program).build().parse(args);
			program.run();
		} catch (ParameterException ex) {
			System.err.println("Error arguments!");
			System.err.println("Valid arguments are:");
			System.err.println("--white=\"replace_color\"");
			System.err.println("--black=\"replace_color\"");
			System.exit(-1);
		}
        
    }

    private void run() {
		try {
            Logic.printImage(FILE, color1, color2);
        } catch(IOException e) {
            e.printStackTrace();
        }
		
	}
}