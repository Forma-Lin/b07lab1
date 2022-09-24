import java.lang.Math;
public class Polynomial{
	double [] p = new double[1];
	
	public Polynomial(){
		double[] ze = {0};
		p = ze;
	}
	public Polynomial(double[] in){
		p = in;
	}
	public Polynomial add(Polynomial a){
		int l=0;
		int lon;
		int sho;
		double[] lo;
		if(p.length>=a.p.length){
			lon = p.length;
			sho = a.p.length;
			lo = p;
		}
		else{
			lon = a.p.length;
			sho = p.length;
			lo = a.p;
		}
		double[] out = new double[lon];
		while(l<sho){
			out[l] = p[l] + a.p[l];
			l++;
		}
		while(l<lon){
			out[l] = lo[l];
			l++;
		}
		Polynomial res = new Polynomial(out);
		return res;
	}
	public double evaluate(double x){
		double res =0;
		for(int i = 0; i<p.length; i++){
			res = res + Math.pow(x,i)*p[i]; 
		}
		return res;
	}
	public boolean hasRoot(double x){
		double eva = this.evaluate(x);
		if(eva==0)
			return true;
		return false;
	}
}