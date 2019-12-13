package com.tecnicas_pucrs.entidades.geometria;

public class CohenSutherland { //PRONTA

    /* Determina se a Reta(p1,p2) está totalmente dentro da Area(cp1,cp2) */
    public static boolean totalmenteDentro(Ponto p1, Ponto p2, Ponto cp1, Ponto cp2) {
        return ((bitcode(cp1, cp2, p1) | bitcode(cp1, cp2, p2)) == 0);
    }

    /* Determina se a Reta(p1,p2) está totalmente fora da Area(cp1,cp2) */
    public static boolean totalmenteFora(Ponto p1, Ponto p2, Ponto cp1, Ponto cp2) {
        int p1code, p2code, icode;
        p1code = bitcode(cp1, cp2, p1);
        p2code = bitcode(cp1, cp2, p2);
        icode = p1code & p2code;
        return (p1code != 0 && p2code != 0 && icode != 0);
    }

    /* Determina se a Reta(p1,p2) intersecta a Area(cp1,cp2) */
    public static boolean intersect(Ponto p1, Ponto p2, Ponto cp1, Ponto cp2) {
        int p1code, p2code, icode;
        boolean r;
        p1code = bitcode(cp1, cp2, p1);
        p2code = bitcode(cp1, cp2, p2);
        icode = p1code & p2code;
        if (p1code == 0 && p2code == 0)
            r = false;
        else if (p1code != 0 && p2code != 0 && icode != 0)
            r = false;
        else
            r = true;
        return r;
    }

    /* Faz um codigo de 4-bit para o Ponto p em relação a linha(p1,p2) */
    public static int bitcode(Ponto p1, Ponto p2, Ponto p) {
        return (acima(p1, p2, p) | abaixo(p1, p2, p) | esquerda(p1, p2, p) | direita(p1, p2, p));
    }

    /* 2**3 bit (Ponto acima linha(p1,p2)) */
    public static int acima(Ponto p1, Ponto p2, Ponto p) {
        if (p.y < Math.min(p1.y, p2.y))
            return 0x8;
        return 0;
    }

    /* 2**2 bit (Ponto abaixo linha(p1,p2)) */
    public static int abaixo(Ponto p1, Ponto p2, Ponto p) {
        if (p.y > Math.max(p1.y, p2.y)) {
            return 0x4;
        }
        return 0;
    }

    /* 2**1 bit (Ponto direita da linha(p1,p2)) */
    public static int direita(Ponto p1, Ponto p2, Ponto p) {
        if (p.x > Math.max(p1.x, p2.x))
            return 0x2;
        return 0;
    }

    /* 2**0 bit (Ponto esquerda da linha(p1,p2)) */
    public static int esquerda(Ponto p1, Ponto p2, Ponto p) {
        if (p.x < Math.min(p1.x, p2.x))
            return 0x1;
        return 0;
    }
}
