import java.io.Writer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Iterator;

public class InvocarUtilities {
    
    public static void generateIdenPushCastCode(Writer fd, int nextReg, Tipo dest, Tipo source, boolean global) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1);  

        if(source instanceof Arreglo){

            Tipo bsource = ((Arreglo) source).getTipoBase();
            Tipo bdest = ((Arreglo) dest).getTipoBase();

            if(bsource instanceof Basico){
                ASTCast cast = AssemblerInfo.checkCast(bdest, bsource);
                for(int i = 0; i < source.getTam(); i += 8){
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("push qword "+nreg+"\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");
                    if(global)
                        fd.write("add "+reg+",8\n");
                    else
                        fd.write("sub "+reg+",8\n");
                }
            }
            else{
                for(int i = 0; i < source.getTam(); i += bsource.getTam())
                    generateIdenPushCastCode(fd, nextReg, bdest, bsource, global);
            }
        }
       else if(source instanceof Registro){

            Iterator its = ((Registro) source).getTipos().iterator();
            Iterator itd = ((Registro) dest).getTipos().iterator();

            Tipo esource;
            Tipo edest;

            while(its.hasNext()){

                esource = (Tipo) its.next();
                edest = (Tipo) itd.next();

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("push qword "+nreg+"\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");
                    if(global)
                        fd.write("add "+reg+",8\n");
                    else
                        fd.write("sub "+reg+",8\n");
                }
                else
                    generateIdenPushCastCode(fd, nextReg, edest, esource, global);
            }
        }
       else if(source instanceof Union){

            String nnreg = AssemblerInfo.getNombresRegAtPos(nextReg+2);  
            String end = AssemblerInfo.newLabel();

            Iterator ittd = ((Union) dest).getTipos().iterator();
            Iterator itts = ((Union) source).getTipos().iterator();

            fd.write("mov "+nreg+", ["+reg+"]\n");
            fd.write("push qword ["+reg+"]\n");
    
            if(global)
                fd.write("add "+reg+",8\n");
            else
                fd.write("sub "+reg+",8\n");

            int i = 0;

            while(itts.hasNext()){

                Tipo edest = (Tipo) ittd.next();
                Tipo esource = (Tipo) ittd.next();

                String nextLabel = AssemblerInfo.newLabel();

                fd.write("cmp "+nreg+", "+i+"\n");
                fd.write("jne "+nextLabel+"\n");

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nnreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+2, "", "");
                        fd.write("push qword "+nnreg+"\n");
                    }
                    else
                        fd.write("push qword ["+reg+"]\n");

                    if(global)
                        fd.write("add "+reg+",8\n");
                    else
                        fd.write("sub "+reg+",8\n");
                }
                else
                    generateIdenPushCastCode(fd, nextReg, edest, esource, global);

                fd.write("jmp "+end+"\n");

                fd.write(nextLabel+":\n");

            }

            fd.write(end+":\n");

        }
    }

    public static void generateIdenPopCastCode(Writer fd, int nextReg, Tipo dest, Tipo source, boolean global) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1); 

        if(source instanceof Arreglo){

            Tipo bsource = ((Arreglo) source).getTipoBase();
            Tipo bdest = ((Arreglo) dest).getTipoBase();

            if(bsource instanceof Basico){
                ASTCast cast = AssemblerInfo.checkCast(bdest, bsource);

                for(int i = 0; i < source.getTam(); i += 8){
                    fd.write("pop "+nreg+"\n");
                    if(cast != null)
                        cast.generateCode(fd, nextReg+1, "", "");
                    fd.write("mov ["+reg+"], "+nreg+"\n");
                    if(global)
                        fd.write("sub "+reg+",8\n");
                    else
                        fd.write("add "+reg+",8\n");
                }

            }
            else{
                for(int i = 0; i < source.getTam(); i += bsource.getTam())
                    generateIdenPopCastCode(fd, nextReg, bdest, bsource, global);
            }
        }
       else if(source instanceof Registro){

            Object[] its = ((Registro) source).getTipos().toArray(); 
            Object[] itd = ((Registro) dest).getTipos().toArray(); 

            Tipo esource;
            Tipo edest;

            for(int i = ((Registro) source).getTipos().size() -1; i>=0; i--){

                esource = (Tipo) its[i];
                edest = (Tipo) itd[i];

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    fd.write("pop "+nreg+"\n");
                    if(cast != null)
                        cast.generateCode(fd, nextReg+1, "", "");
                    fd.write("mov ["+reg+"], "+nreg+"\n");
                    if(global)
                        fd.write("sub "+reg+",8\n");
                    else
                        fd.write("add "+reg+",8\n");
                }
                else
                    generateIdenPopCastCode(fd, nextReg, edest, esource, global);
            }
        }
       else if(source instanceof Union){

            for(int k = 0; k < source.getTam(); k+=8){
                fd.write("pop "+reg+"\n");
                fd.write("mov ["+reg+"], "+nreg+"\n");
                if(global)
                    fd.write("sub "+reg+",8\n");
                else
                    fd.write("add "+reg+",8\n");
            }

            generateUnionCastCode(fd, nextReg, dest, source, global);

       }

    }

    private static void generateUnionCastCode(Writer fd, int nextReg, Tipo dest, Tipo source, boolean global) throws IOException{

        String reg = AssemblerInfo.getNombresRegAtPos(nextReg); 
        String nreg = AssemblerInfo.getNombresRegAtPos(nextReg+1);
        String nnreg = AssemblerInfo.getNombresRegAtPos(nextReg+2);  

        if(source instanceof Arreglo){

            Tipo bsource = ((Arreglo) source).getTipoBase();
            Tipo bdest = ((Arreglo) dest).getTipoBase();

            if(bsource instanceof Basico){
                ASTCast cast = AssemblerInfo.checkCast(bdest, bsource);
                for(int i = 0; i < source.getTam(); i += 8){
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("mov ["+reg+"], "+nreg+"\n");
                    }
                    if(global)
                        fd.write("add "+reg+",8\n");
                    else
                        fd.write("sub "+reg+",8\n");
                }
            }
            else{
                for(int i = 0; i < source.getTam(); i += bsource.getTam())
                    generateUnionCastCode(fd, nextReg, bdest, bsource, global);
            }
        }
       else if(source instanceof Registro){

            Iterator its = ((Registro) source).getTipos().iterator();
            Iterator itd = ((Registro) dest).getTipos().iterator();

            Tipo esource;
            Tipo edest;

            while(its.hasNext()){

                esource = (Tipo) its.next();
                edest = (Tipo) itd.next();

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+1, "", "");
                        fd.write("mov ["+reg+"], "+nreg+"\n");
                    }
                    if(global)
                        fd.write("add "+reg+",8\n");
                    else
                        fd.write("sub "+reg+",8\n");
                }
                else
                    generateUnionCastCode(fd, nextReg, edest, esource, global);
            }
        }
       else if(source instanceof Union){

            String end = AssemblerInfo.newLabel();

            Iterator ittd = ((Union) dest).getTipos().iterator();
            Iterator itts = ((Union) source).getTipos().iterator();

            fd.write("mov "+nreg+", ["+reg+"]\n");

            if(global)
                fd.write("add "+reg+",8\n");
            else
                fd.write("sub "+reg+",8\n");

            int i = 0;

            while(itts.hasNext()){

                Tipo edest = (Tipo) ittd.next();
                Tipo esource = (Tipo) ittd.next();

                String nextLabel = AssemblerInfo.newLabel();

                fd.write("cmp "+nreg+", "+i+"\n");
                fd.write("jne "+nextLabel+"\n");

                if(esource instanceof Basico){
                    ASTCast cast = AssemblerInfo.checkCast(edest, esource);
                    if(cast != null){
                        fd.write("mov "+nnreg+", ["+reg+"]\n");
                        cast.generateCode(fd, nextReg+2, "", "");
                        fd.write("mov ["+reg+"], "+nnreg+"\n");
                    }
                    if(global)
                        fd.write("add "+reg+",8\n");
                    else
                        fd.write("sub "+reg+",8\n");
                }
                else
                    generateUnionCastCode(fd, nextReg, edest, esource, global);

                fd.write("jmp "+end+"\n");

                fd.write(nextLabel+":\n");

            }

            fd.write(end+":\n");

        }

    }

}
