import java.lang.Math;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Polynomial{
	double [] p = new double[1];
	int [] exp = new int[1];
	
	public Polynomial(){
		double[] ze = {0};
		p = ze;
		int[] no = {0};
		exp = no;
	}
	public Polynomial(double[] in, int[] exin){
		p = in;
		exp = exin;
	}
	public Polynomial add(Polynomial a){
		HashMap<Integer, Double> poly = new HashMap<Integer, Double>();
		for(int i=0;i<exp.length;i++){
			poly.put(exp[i],p[i]);
		}
		for(int j=0;j<a.exp.length;j++){
			if(poly.containsKey(a.exp[j])){
				poly.put(a.exp[j],poly.get(a.exp[j])+a.p[j]);
			}
			else
				poly.put(a.exp[j],a.p[j]);
		}
		Map<Integer, Double> sorted = new TreeMap<Integer, Double>();
		sorted.putAll(poly);
		int[] resi = sorted.keySet().stream().mapToInt(i->i).toArray();
		double[] resd = sorted.values().stream().mapToDouble( (Double i) -> i.doubleValue() ).toArray();
		Polynomial out = new Polynomial(resd, resi);
		return out;
	}
	public double evaluate(double x){
		double res =0;
		for(int i = 0; i<p.length; i++){
			res = res + Math.pow(x,exp[i])*p[i]; 
		}
		return res;
	}
	public boolean hasRoot(double x){
		double eva = this.evaluate(x);
		if(eva==0)
			return true;
		return false;
	}
    public Polynomial multiply(Polynomial a){
		HashMap<Integer, Double> poly = new HashMap<Integer, Double>();
		for(int i=0;i<exp.length;i++){
			for(int j=0;j<a.exp.length;j++){
				int ex = exp[i]+a.exp[j];
				double co = p[i]*a.p[j];
				if(poly.containsKey(ex)){
					poly.put(ex, poly.get(ex)+co);
				}
				else
					poly.put(ex,co);
			}
		}
		Map<Integer, Double> sorted = new TreeMap<Integer, Double>();
		sorted.putAll(poly);
		int[] resi = sorted.keySet().stream().mapToInt(i->i).toArray();
		double[] resd = sorted.values().stream().mapToDouble( (Double i) -> i.doubleValue() ).toArray();
		Polynomial out = new Polynomial(resd, resi);
		return out;
    }
	public Polynomial(File f){
		try{
			BufferedReader bf = new BufferedReader(new FileReader(f));
			String inp = bf.readLine();
			int bl = 0;
			String pa = "";
			bf.close();
			ArrayList<String> sav = new ArrayList<String>();
			for(int i=0;i<inp.length();i++){
				char c = inp.charAt(i);
				if((i!=0)&&((c=='+')||(c=='-'))){
					pa = inp.substring(bl,i);
					bl = i;
					sav.add(pa);
				}
			}
			pa = inp.substring(bl,inp.length());
			sav.add(pa);
			HashMap<Integer, Double> poly = new HashMap<Integer, Double>();
			for(String x : sav){
				String[] nums = x.split("x");
				if(nums.length==1){
					poly.put(0,Double.parseDouble(nums[0]));
				}
				else{
					poly.put(Integer.parseInt(nums[1]),Double.parseDouble(nums[0]));
				}
			}
			exp = poly.keySet().stream().mapToInt(i->i).toArray();
			p = poly.values().stream().mapToDouble( (Double i) -> i.doubleValue() ).toArray();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void SavetoFile(String name){
		String outp = "";
		for(int i=0;i<exp.length;i++){
			if(exp[i]==0){
				outp = outp + String.valueOf(p[i]);
			}
			else{
				String coe = String.valueOf(p[i]);
				String ex = String.valueOf(exp[i]);
				if(p[i]>0){
					outp = outp + "+" + coe + "x" + ex;
				}
				else
					outp = outp + coe + "x" + ex;
			}
		}
		File fn = new File(name);
		try{
			if(fn.createNewFile()){
				System.out.println("File created");
			}
			else{
				System.out.println("File existed");
			}
		}
		catch(IOException e){
			System.out.println("Error");
			e.printStackTrace();
		}
		try{
			BufferedWriter wr = new BufferedWriter(new FileWriter(name));
			wr.write(outp);
			wr.close();
		}
		catch(Exception e){
			e.getStackTrace();
		}
	}
}
 