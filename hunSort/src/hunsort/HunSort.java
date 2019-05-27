/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunsort;

import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Thomas
 */
public class HunSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         List<Eredm�ny> eredm�nyek = new ArrayList<>();
        eredm�nyek.add(new Eredm�ny("Edit",100));
        eredm�nyek.add(new Eredm�ny("Barna",103));
        eredm�nyek.add(new Eredm�ny("�va",100));
        eredm�nyek.add(new Eredm�ny("Edit",102));
        eredm�nyek.add(new Eredm�ny("Endre",101));
        eredm�nyek.add(new Eredm�ny("�d�m",102));
        Collections.sort(eredm�nyek);
        System.out.println(eredm�nyek);
        System.out.println("N�vsor:");
        
        RuleBasedCollator myCollator = (RuleBasedCollator) Collator.getInstance(new Locale("hu", "HU"));
        Collections.sort(eredm�nyek, (Eredm�ny egyik, Eredm�ny m�sik)->myCollator.compare(egyik.n�v, m�sik.n�v));
        System.out.println(eredm�nyek);

        System.out.println("Id�sor:");
        Collections.sort(eredm�nyek, new Eredm�nyId�());
        System.out.println(eredm�nyek);

    }
    
}
class Eredm�nyN�v implements Comparator<Eredm�ny>{
    @Override
    public int compare(Eredm�ny egyik, Eredm�ny m�sik){
        
        return egyik.n�v.compareTo(m�sik.n�v);
    }
}

class Eredm�nyId� implements Comparator<Eredm�ny>{
    @Override
    public int compare(Eredm�ny egyik, Eredm�ny m�sik){
        
        return egyik.id�-m�sik.id�;
    }
}

class Eredm�ny implements Comparable<Eredm�ny> {
    String n�v;
    int id�; //sec

    public Eredm�ny(String n�v, int id�) {
        this.n�v = n�v;
        this.id� = id�;
    }
    
    @Override
    public int compareTo(Eredm�ny m�sik){
       int result = n�v.compareTo(m�sik.n�v);
       if(result == 0)
           result = id�-m�sik.id�;
       return result;
    }

    @Override
    public String toString() {
        return "Eredm�ny: " + id� + " - "+n�v+"\n";
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof Eredm�ny) {
            Eredm�ny temp = (Eredm�ny) obj;
            result = temp.n�v.equals(this.n�v) && temp.id� == this.id�;
        } 
        
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.n�v);
        hash = 67 * hash + this.id�;
        return hash;
    }
}