// -*- coding: utf-8 -*-

import java.math.BigInteger;
import java.util.Random;
    
public class EPP
{	
    static long totalTimeFor10Tests = 0;
    static int totalTestsFor10Tests = 0;
    static int nbTests = 10;
    public static void main(String[] args)
    {
        BigInteger n = new BigInteger("170141183460469231731687303715884105727", 10);

        System.out.print("Le nombre " + n);
        if (est_probablement_premier(n))
            System.out.println(" est très probablement premier!");
        else
            System.out.println(" n'est absolument pas premier!");
        for (int i = 0; i < nbTests; i++) {
            randomPrime();
        }
       System.out.println( "moyenne : " + (totalTimeFor10Tests/nbTests) + " ms");
        System.out.println( "moyenne : " + (totalTestsFor10Tests/nbTests) + " tests");
    }

    static boolean est_probablement_premier(BigInteger n)
    {   
        // 2^-c <= 10^-15
        // c >= log(10^-15)/log(2) = 50
        int certainty = 50;
        return  n.isProbablePrime(certainty);		     
    }

    static BigInteger randomPrime(){
      Random alea = new Random ();
      int count = 1;
      long startTime =  System.currentTimeMillis();
      BigInteger n = new BigInteger(512,alea);
        while (!n.isProbablePrime(50)){
            n = new BigInteger(512,alea);
            count++;
        }
      totalTimeFor10Tests += System.currentTimeMillis() - startTime;
      totalTestsFor10Tests += count;
      long endTime =  System.currentTimeMillis();
      System.out.println ("Valeur de n : " + n);
      System.out.println ("Nombre de tests : " + count);
      System.out.println ("Temps d'execution : " + (endTime - startTime) + " ms");
      return n; 
    }
    /*
      moyenne : 22 ms
      moyenne : 433 tests
     * 
     * 
     */
}

/*



  $ make
  javac *.java 
  $ java EPP
  Le nombre 170141183460469231731687303715884105727 ...
*/

