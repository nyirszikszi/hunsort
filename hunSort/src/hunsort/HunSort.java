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
         List<Eredmény> eredmények = new ArrayList<>();
        eredmények.add(new Eredmény("Edit",100));
        eredmények.add(new Eredmény("Barna",103));
        eredmények.add(new Eredmény("Éva",100));
        eredmények.add(new Eredmény("Edit",102));
        eredmények.add(new Eredmény("Endre",101));
        eredmények.add(new Eredmény("Ádám",102));
        Collections.sort(eredmények);
        System.out.println(eredmények);
        System.out.println("Névsor:");
        
        RuleBasedCollator myCollator = (RuleBasedCollator) Collator.getInstance(new Locale("hu", "HU"));
        Collections.sort(eredmények, (Eredmény egyik, Eredmény másik)->myCollator.compare(egyik.név, másik.név));
        System.out.println(eredmények);

        System.out.println("Idõsor:");
        Collections.sort(eredmények, new EredményIdõ());
        System.out.println(eredmények);

    }
    
}
class EredményNév implements Comparator<Eredmény>{
    @Override
    public int compare(Eredmény egyik, Eredmény másik){
        
        return egyik.név.compareTo(másik.név);
    }
}

class EredményIdõ implements Comparator<Eredmény>{
    @Override
    public int compare(Eredmény egyik, Eredmény másik){
        
        return egyik.idõ-másik.idõ;
    }
}

class Eredmény implements Comparable<Eredmény> {
    String név;
    int idõ; //sec

    public Eredmény(String név, int idõ) {
        this.név = név;
        this.idõ = idõ;
    }
    
    @Override
    public int compareTo(Eredmény másik){
       int result = név.compareTo(másik.név);
       if(result == 0)
           result = idõ-másik.idõ;
       return result;
    }

    @Override
    public String toString() {
        return "Eredmény: " + idõ + " - "+név+"\n";
    }
    
    @Override
    public boolean equals(Object obj){
        boolean result = false;
        if(obj instanceof Eredmény) {
            Eredmény temp = (Eredmény) obj;
            result = temp.név.equals(this.név) && temp.idõ == this.idõ;
        } 
        
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.név);
        hash = 67 * hash + this.idõ;
        return hash;
    }
}