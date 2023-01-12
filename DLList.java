public class DLList<E> {
    private DLListNode<E> head;
    private DLListNode<E> tail;
    private int size;

    public DLList(){
        head = new DLListNode<E>(null);
        tail = new DLListNode<E>(null);
  
        head.setNext(tail);
        tail.setPrevious(head);
        size = 0;
   
    }
  
    private DLListNode<E> getNode(int index){
        DLListNode<E> current;
        int i;
        current = head.next();
        for(i = 0; i < index; i++){
            current = current.next();
        }
       
        return current;
    }
   
  
    public void add(E data){
        DLListNode<E> toAdd = new DLListNode<E>(data);
        toAdd.setNext(tail);
        toAdd.setPrevious(tail.previous());
        tail.previous().setNext(toAdd);
        tail.setPrevious(toAdd);
        size++;
    }
    public void add(E data, int index){
        DLListNode<E> current;
        DLListNode<E> toAdd = new DLListNode<E>(data);
        if(index >= size){
            add(data);
            return;
        }
        if(size/2>index){
            // add from beginning
            current = head.next();
            int i = 0;
            while(current != null){
                if(index == i){
                    DLListNode<E> previousNode = current.previous();

  
                   
                    toAdd.setPrevious(previousNode);
                    toAdd.setNext(current);
                   
                    current.setPrevious(toAdd);
                    previousNode.setNext(toAdd);
                    size++;
                    break;
                }
                i++;
                current = current.next();
            }
  
        }else{
            // add from end
            current = tail.previous();
            int i = size-1;
            while(current != null){
                if(index == i){
                    DLListNode<E> previousNode = current.previous();
                   
                    toAdd.setPrevious(previousNode);
                    toAdd.setNext(current);
                   
                    current.setPrevious(toAdd);
                    previousNode.setNext(toAdd);
                    size++;
                    break;
                }
                i--;
                current = current.previous();
            }
  
            current = tail.previous();
           
        }
    }
  
    public E get(int index){
        return getNode(index).get();
    }
  
    public int size(){
        return size;
    }
  
    public String toString(){
        DLListNode<E> current = head.next();
        String returnString = "";
        while(current.next() != null){
            returnString = returnString + current.get() + " ";
            current = current.next();
        }
        return returnString;
    }
    public void remove(int index){
        DLListNode<E> current;
        int i;
        if(size/2>index){
            i = 0;
            current = head.next();
            while(current!=null){
                if(i==index){
                    DLListNode<E> previousNode = current.previous();
                    DLListNode<E> nextNode = current.next();
                    previousNode.setNext(nextNode);
                    nextNode.setPrevious(previousNode);
                    size--;
                    break;
                }
                i++;
                current = current.next();
            }
        }else{
            i = size-1;
            current = tail.previous();
            while(current!=null){
                if(i==index){
                    DLListNode<E> previousNode = current.previous();
                    DLListNode<E> nextNode = current.next();
                    previousNode.setNext(nextNode);
                    nextNode.setPrevious(previousNode);
                    size--;
                    break;
                }
                i--;
                current = current.previous();
            }
  
  
        }
    }
    public void remove(E data){
        DLListNode<E> current = new DLListNode<E>(data);
        current = head.next();
        while(current != null){
            if(current.get().equals(data)){
                DLListNode<E> previousNode = current.previous();
                DLListNode<E> nextNode = current.next();
                previousNode.setNext(nextNode);
                nextNode.setPrevious(previousNode);
                size--;
                break;
            }
            current = current.next();
        }
    }
  
    public void set(int index, E data){
        this.remove(index);
        this.add(data,index);
    }

    public E pop(){
        E temp = get(0);
        this.remove(0);
        return temp;
    }
   
 }
 