public class MinBinHeap implements HeapInterface {

	public EntryPair[] array;
	public int size;
	
	public MinBinHeap ( ) { 
		array= new EntryPair[99];
		size=0;
	}
  

  @Override
  public void insert(EntryPair entry) {
	  if(size==0){
		  array[1]=entry;
		  size++;
	  }
	  else{
		  for(int x=1; x<=size()+1; x++){
			  if(array[x]==entry){
				  System.out.println("Entry already in heap");
				  return;
			  }
			  if(array[x]==null){
				  array[x]=entry;
				  size++;
				  moveUp(array[x]);
				  return;
			  }
		  }
	  }
  }
  
  private void moveUp(EntryPair e){
	  int marker =0;
	  for(int x=1; x<=size(); x++){
		  if(array[x]==e){
			marker = x;  
		  }
	  }
	  if(e.priority<array[marker/2].priority){
		  array[marker]=array[marker/2];
		  array[marker/2]=e;
		  if(array[1]!=e){
			  moveUp(e);
		  }
		  else{}
	  }
  }
  
  private void moveDown(EntryPair e){
	  int marker =0;
	  int child=0;
	  for(int x=1; x<=size(); x++){
		  if(array[x]==e){
			marker = x;  
		  }
	  }
	  if(array[marker*2]==null){
		  return;
	  }
	  else if(array[marker*2+1]==null){
		  child=1;
	  }
	  else if(array[marker*2].priority<=array[marker*2+1].priority){
		  child=1;
	  }
	  else{
		  child=2;
	  }
	  
	  if(child==1&&e.priority>array[marker*2].priority){
		  array[marker]=array[marker*2];
		  array[marker*2]=e;
		  moveDown(e);
	  }
	  else if(child==2&&e.priority>array[marker*2+1].priority){
		  array[marker]=array[marker*2+1];
		  array[marker*2+1]=e;
		  moveDown(e);
	  }
  }

  @Override
  public void delMin() {
	  if(size()==0){
		  return;
	  }
	  array[1]=null;
	  array[1]=array[size()];
	  array[size()]=null;
	  size--;
	  moveDown(array[1]);
  }
  
  @Override
  public EntryPair getMin() {
	  return array[1];
  }

  @Override
  public int size() {
	  return size;
  }
  
  @Override
  public void build(EntryPair[] entries) {
	  array=new EntryPair[99];
	  size=1;
	  for(EntryPair e: entries){
		  array[size]=e;
		  size++;
	  }
	  size--;
	  for(int x=size()/2; x>=1; x--){
	  moveDown(array[x]);
	  }
  }
  
  public void print(){
	  for(int x=1; x<=size(); x++){
		  System.out.print("["+array[x].priority+", "+ array[x].value+"] ");
	  }
	  System.out.println();
  }
  
  public void sort(){
	  int amount=size();
	  EntryPair[] sort = new EntryPair[99];
	  for(int x=1; x<=amount; x++){
		  sort[x]=getMin();
		  delMin();
	  }
	  
	  for(int y= 1; y<=amount; y++){
		  System.out.print(sort[y].priority+" ");
	  }
  }
}