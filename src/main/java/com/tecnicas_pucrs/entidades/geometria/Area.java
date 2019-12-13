package com.tecnicas_pucrs.entidades.geometria;

public class Area { //PRONTA

    private Ponto pSupEsq, pInfDir;
    private CohenSutherland cs = new CohenSutherland();

    public Area(Ponto pSupEsq, Ponto pInfDir) {
        if ((pSupEsq.getX() >= pInfDir.getX()) || (pSupEsq.getY() <= pInfDir.getY())) {
            throw new IllegalArgumentException("O retangulo deve ser definido pela diagonal principal");
        } else {
            this.pSupEsq = pSupEsq;
            this.pInfDir = pInfDir;
        }
    }

    public Ponto getPSupEsq() {
        return pSupEsq;
    }

    public Ponto getPInfDir() {
        return pInfDir;
    }


    public SituacaoDaReta classifica(Reta reta) {
        if(cs.totalmenteDentro(reta.getP1(), reta.getP2(), pInfDir, pSupEsq)){
            return SituacaoDaReta.TODA_DENTRO;
        }else if(cs.totalmenteFora(reta.getP1(), reta.getP2(), pInfDir, pSupEsq)){
            return SituacaoDaReta.TODA_FORA;
        }else if(cs.intersect(reta.getP1(), reta.getP2(), pInfDir, pSupEsq)){
            return SituacaoDaReta.INTERSECT;
        }else{
            throw new IllegalArgumentException("Invalid value");
        }
    }

    @Override
    public String toString() {
        return "Area [pInfDir=" + pInfDir + ", pSupEsq=" + pSupEsq + "]";
    }

    public Ponto pontoCentral() {
        int x_central = pSupEsq.x + (pInfDir.x - pSupEsq.x)/2;
        int y_central = pInfDir.y + (pSupEsq.y - pInfDir.y)/2;
        return  new Ponto(x_central, y_central);
    }
}