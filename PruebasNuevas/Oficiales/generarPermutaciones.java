void mostrarArreglo (int ncs, char[9] cs) {

    int i;

    for (i = 0; i < ncs - 1; i++) {
        print cs[i]; print ','; print ' ';
    }

    if (ncs > 0) {
        print cs[i];
        print '\n';
    }
}

void swap (char[9] a, int p1, int p2) {

    char tmp = a[p1];
    a[p1] = a[p2];
    a[p2] = tmp;
}

void generarPermutacionesAux (int ncs, char[9] cs, int m) {

    if (m == 0) {
        mostrarArreglo(ncs, cs);
    } elif (m > 0) {

        int l;

        for (l = m - 1; l >= 0; l--) {
            swap(cs, l, m-1);
            generarPermutacionesAux(ncs,cs,m-1);
            swap(cs, l, m-1);
        }
    }
    else{}
}

void generarPermutaciones (int ncs, char[9] cs) {

    generarPermutacionesAux(ncs, cs, ncs);
}

void main{

    /* inicializar en 9 en caso de s'olo manejar arreglos de tamaño 
       constante con valor conocido a momento de compilaci'on */

    int n = 9;
    char[9] cs;

    for (int i = 0; i < n; i++) {

        print 'C'; print 'a'; print 'r'; print 'a'; print 'c'; print 't'; print 'e'; print 'r'; 
        print ' '; 
        print i;
        print ' '; 
        print 'd'; print 'e'; print 'l'; 
        print ' '; 
        print 'a'; print 'r'; print 'r'; print 'e'; print 'g'; print 'l'; print 'o'; print ':'; 
        print ' '; 
        cs[i] = read char;
    }
  
    generarPermutaciones(n, cs);
}
