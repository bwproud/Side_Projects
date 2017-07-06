public interface HeapInterface {
  void insert(EntryPair entry);
  void delMin();
  EntryPair getMin();
  int size();
  void build(EntryPair [] entries);
}