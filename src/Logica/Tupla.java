package Logica;

	public class Tupla <K,V> implements Comparable<Tupla<K,V>>
	{

	    private K elem1;
	    private V elem2;

	    public Tupla(K e1, V e2) 
	    {
	        elem1=e1;
	        elem2=e2;
	    }


	    public K getElem1() {
	        return elem1; 
	    }

	    public void setElem1(K elem1) 
	    {
	        this.elem1 = elem1;
	    }

	    public V getElem2() 
	    {
	        return elem2;
	    }

	    public void setElem2(V elem2) 
	    {
	        this.elem2 = elem2;
	    }

	    public boolean equals(Tupla<Integer, Integer> t) 
	    {
	        if(this.elem1.equals(t.getElem1())  &&   this.elem2.equals(t.getElem2())) {
	            return true;
	        }else {
	            return false;
	        }
	    }

	    @Override
	    public int compareTo(Tupla<K, V> o) 
	    {
	        // TODO Auto-generated method stub
	        return 0;
	    }

	    public String toString() 
	    {
	        return this.elem1.toString()+","+this.elem2.toString(); 
	    }

	}
