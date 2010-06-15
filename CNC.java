import java.io.*;
import java.util.Random;

public class CNC {

    public static Writer openFile(String file_name) throws IOException {
	Writer fd = null;

	try {
	    fd = new BufferedWriter(new FileWriter(file_name));
	}
	catch (Exception e) {
	    System.out.println("Error abriendo el archivo : \"" + file_name + "\"");
	    System.exit(1);
	}

	return fd;
    }

    public static void main(String argv[]) throws java.io.IOException, java.lang.Exception {
	Lexer scanner = null;
	String file_name = "";

	try {
	    scanner = new Lexer( new java.io.FileReader(argv[0]) );
	    file_name = argv[1];
	}
	catch (java.io.FileNotFoundException e) {
	    System.out.println("Archvio no encontrado : \""+argv[0]+"\"");
	    System.exit(1);
	}
	catch (java.io.IOException e) {
	    System.out.println("Error abriendo el archivo : \""+argv[0]+"\"");
	    System.exit(1);
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    System.out.println("Uso: java CNC <Archivo> <Archivo>");
	    System.exit(1);
	}

	Object result = null;
        Parser p = null;

	try {	
	    p = new Parser(scanner);
	    result = p.parse().value;
	    if(p.error || p.syntaxErrors)
            {
		System.out.println("Revise y vuelva a compilar.");
                System.exit(1);
            }
	    else
		System.out.println("Programa sin errores estáticos.");
	}
	catch (Exception e) {
	    System.out.println("Revise el error y vuelva a compilar.");
	    System.exit(1);
	}

        Writer fd = openFile(file_name);
  	AssemblerInfo ai = new AssemblerInfo();

        ai.writeStart(fd, p.desplazamientoglobal, p.globales);
	SymProc procedimiento = (SymProc)((SymTable)result).getSym("main");
        ai.writeMain(fd, procedimiento);

        fd.close();
    }
}
