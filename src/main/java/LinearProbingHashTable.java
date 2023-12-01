//import Tree.Tree;
//
//public class LinearProbingHashTable
//{
//	private Tree<Student>[] hashTree;    // array holds hash table
//	private int arraySize;
//	private Student  defunct;        // for deleted items
//
//	// -------------------------------------------------------------
//	public LinearProbingHashTable(int size)       // constructor
//	{
//		arraySize = size;
//		hashTree = new Student[arraySize];
//		defunct = new Student(-1,"null",0);   // deleted item key is -1
//	}
//
//	// -------------------------------------------------------------
//	public double loadFactor()
//	{
//		double c=0;
//		for (int i=0;i<arraySize;i++)
//			if (hashTree[i]!=null && hashTree[i]!=defunct)
//				c++;
//		return c/arraySize;
//	}
//
//	// -------------------------------------------------------------
//	public void rehash()
//	{
//		Student oldArray[]= hashTree;
//		hashTree =new Student[arraySize*2];//oldArray points to old array
//		arraySize*=2;
//		for (int i=0;i<arraySize/2;i++)
//			insert(oldArray[i]);
//	}
//
//	// -------------------------------------------------------------
//	public void displayTable()
//	{
//		System.out.println("Table: ");
//		for(int j=0; j<arraySize; j++)
//			if(hashTree[j] != null)
//				System.out.println(hashTree[j]);
//			else
//				System.out.println("** ");
//	}
//
//	//--------------------------------------------------
//	public boolean isFull()
//	{
//		for (int i=0;i<arraySize;i++)
//			if(hashTree[i]==null || hashTree[i]==defunct)
//				return false;
//		return true;
//
//	}
//
//	// -------------------------------------------------------------
//	public int hashFunc(int key)
//	{
//		return key % arraySize;       // hash function
//	}
//
//	// -------------------------------------------------------------
//	public void insert(Student s)
//	{
//		if (loadFactor()>=0.5)
//			rehash();
//
//		int key = s.getId();      // extract key
//		int hashVal = hashFunc(key);  // hash the key
//
//		while(hashTree[hashVal] != null && hashTree[hashVal]!= defunct)
//		{
//			hashVal++;                 // go to next cell
//			hashVal %= arraySize;      // wraparound if necessary
//		}
//		hashTree[hashVal] = s;    // insert item
//
//	}// end insert()
//
//	// -------------------------------------------------------------
//	public Student delete(int key)  // delete a DataItem
//	{
//		int hashVal = hashFunc(key);  // hash the key
//		int start=hashVal;
//		boolean checkAll=false;
//		while(hashTree[hashVal] != null && !checkAll)
//		{
//			if(hashTree[hashVal].getId() == key)
//			{
//				Student temp = hashTree[hashVal]; // save item
//				hashTree[hashVal] = defunct;       // delete item
//				return temp;                        // return item
//			}
//			hashVal++;                 // go to next cell
//			hashVal %= arraySize;      // wraparound if necessary
//			if (hashVal==start) // if we compare all and go back to the same start index
//				checkAll=true;
//		}
//		return null;                  // can't find item
//	}  // end delete()
//
//	// -------------------------------------------------------------
//	public Student find(int key)    // find item with key
//	{
//		int hashVal = hashFunc(key);  // hash the key
//		int start=hashVal;
//		boolean checkAll=false;
//		while(hashTree[hashVal] != null && !checkAll)  // until empty cell,
//		{                               // found the key?
//			if(hashTree[hashVal].getId() == key)
//				return hashTree[hashVal];   // yes, return item
//			hashVal++;                 // go to next cell
//			hashVal %= arraySize;      // wraparound if necessary
//			if (hashVal==start) // if we compare all and go back to the same start index
//				checkAll=true;
//		}
//		return null;                  // can't find item
//	}
//}