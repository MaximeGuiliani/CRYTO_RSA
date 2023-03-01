// -*- coding: utf-8 -*-

import java.math.BigInteger;
import java.util.Random;

public class RSA_raw
{
  private static BigInteger clair, chiffré, déchiffré ;
  private static BigInteger n ;      // Le module de la clef publique
  private static BigInteger e ;      // L'exposant de la clef publique
  private static BigInteger d ;      // L'exposant de la clef privée
    
  static void fabrique() {
    BigInteger p =  EPP.randomPrime(1024);
    BigInteger q =  EPP.randomPrime(1024);
    n =  p.multiply(q);
    System.out.println(n);

    
    BigInteger w =  p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    System.out.println(w);
    

    do{
      d = nextRandomBigInteger(BigInteger.ONE, w);
    }while( d.gcd(w).compareTo(BigInteger.ONE)!=0 );
    System.out.println(d);
    
    e = d.modInverse(w);
    System.out.println(e);

  }


  public static BigInteger nextRandomBigInteger(BigInteger minCompis, BigInteger maxPasCompis) {
    Random rand = new Random();
    BigInteger result = new BigInteger(n.bitLength(), rand);
    while( result.compareTo(maxPasCompis) >= 0 || result.compareTo(minCompis) < 0) {
        result = new BigInteger(n.bitLength(), rand);
    }
    return result;
}

  public static void main(String[] args)
  {  
    clair = new BigInteger("4b594f544f", 16);

    /* Affichage du clair */
    System.out.println("Clair             : " + clair);
    
    fabrique(); 

    /* Affichage des clefs utilisées */
    System.out.println("Clef publique (n) : " + n);
    System.out.println("Clef publique (e) : " + e);
    System.out.println("Clef privée   (d) : " + d);

    /* On effectue d'abord le chiffrement RSA du clair clair avec la clef publique */
    chiffré = clair.modPow(e, n);
    System.out.println("Chiffré           : " + chiffré);

    /* On déchiffre ensuite avec la clef privée */
    déchiffré = chiffré.modPow(d, n);
    System.out.println("Déchiffré         : " + déchiffré);
  }
}

