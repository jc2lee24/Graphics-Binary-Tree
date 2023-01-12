public class DLListNode<E> {
    private DLListNode<E> previous, next;
    private E data;
   
    public DLListNode(E data){
        this.data = data;
    }
  
    public void setNext(DLListNode<E> next){
        this.next = next;
    }
    public void setPrevious(DLListNode<E> previous){
        this.previous = previous;
    }
    public DLListNode<E> next(){
        return this.next;
    }
    public DLListNode<E> previous(){
        return this.previous;
    }
    public E get(){
        return data;
    }
    public String toString(){
        return this.data+"";
    }
  
   
 } 