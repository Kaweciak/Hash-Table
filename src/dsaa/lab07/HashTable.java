package dsaa.lab07;

import java.util.LinkedList;

public class HashTable{
	LinkedList[] arr; // use pure array
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;
	private int elements;
	private final double maxLoadFactor;
	public HashTable() {
		this(defaultInitSize);
	}
	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}


	public HashTable(int initCapacity, double maxLF) {
		size = initCapacity;
		arr = new LinkedList[size];
		for(int i = 0; i < size; i++)
		{
			arr[i] = new LinkedList();
		}
		this.maxLoadFactor=maxLF;
	}



	public boolean add(Object elem) {
		if(get(elem) != null) return false;
		if((double)++elements/size >= maxLoadFactor) doubleArray();
		arr[elem.hashCode()%size].add(elem);
		return true;
	}

	
	private void doubleArray() {
		size *= 2;
		LinkedList[] oldArr = arr;
		elements = 0;
		arr = new LinkedList[size];
		for(int i = 0; i < size; i++)
		{
			arr[i] = new LinkedList();
		}
		for(int i = 0; i < oldArr.length; i++)
		{
			for(Object element : oldArr[i])
			{
				add(element);
			}
		}
	}


	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("");
		for(int i = 0; i < arr.length; i++)
		{
			res.append(i).append(":");
			for(int j = 0; j < arr[i].size(); j++)
			{
				IWithName x=(IWithName)arr[i].get(j);
				if(j != 0) res.append(",");
				res.append(" ").append(x.getName());
			}
			res.append("\n");
		}
		return res.toString();
	}

	public Object get(Object toFind) {
		LinkedList listContaining = arr[toFind.hashCode()%size];
		for (Object o : listContaining) {
			if (o.equals(toFind)) return o;
		}
		return null;
	}
}

