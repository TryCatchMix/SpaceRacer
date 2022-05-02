package es.iespolitecnicomalaga.spaceracer;

public class Mando {

    public boolean chocaBorde(float posx){
        if (posx >= 971 || posx <= 31){
            return true;
        }
        return false;
    }
}
