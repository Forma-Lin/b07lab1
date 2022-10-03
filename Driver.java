import java.io.File;
public class Driver { 
 public static void main(String [] args) { 
  Polynomial p = new Polynomial(); 
  //System.out.println(p.evaluate(3)); 
  double [] c1 = {6,5}; 
  int [] e1 = {0,1};
  Polynomial p1 = new Polynomial(c1, e1); 
  double [] c2 = {-2,-9}; 
  int [] e2 = {1,2};
  Polynomial p2 = new Polynomial(c2,e2); 
  /*Polynomial s = p1.add(p2); 
  for(int i=0;i<s.p.length;i++){
    System.out.println(s.p[i]);
  }
  for(int i=0;i<s.p.length;i++){
    System.out.println(s.exp[i]);
  }
  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 
 } */
  Polynomial m = p1.multiply(p2);
  for(int i=0;i<m.p.length;i++){
    System.out.println(m.p[i]);
  }
  for(int i=0;i<m.p.length;i++){
    System.out.println(m.exp[i]);
  }
  m.SavetoFile("savem.txt");
  File f = new File("savem.txt");
  Polynomial cm = new Polynomial(f);
  for(int i=0;i<cm.p.length;i++){
    System.out.println(cm.p[i]);
  }
  for(int i=0;i<cm.p.length;i++){
    System.out.println(cm.exp[i]);
  }
 }
}