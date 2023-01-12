import java.awt.*;

public class BinarySearchTree<E extends Comparable<E>>{
    private Node<E> root;
    private int passes = 0;
    private int level;

    public BinarySearchTree(){
        root = null;
    }

    public void add(E data){
        passes = 0;
        if(root == null){
            root = new Node<E>(data);
        }
        else{
            add(data, root);
        }
    }

    private void add(E data, Node<E> current){
        passes++;

        if (data.compareTo(current.get()) < 0) {
            if (current.getLeft() == null) {
                Node<E> toAdd = new Node<E>(data);
                toAdd.setParent(current);
                current.setLeft(toAdd);

                // System.out.println("Added");

            } else {
                add(data, current.getLeft());
            }
        }

        if (data.compareTo(current.get()) > 0) {
            if (current.getRight() == null) {
                Node<E> toAdd = new Node<E>(data);
                toAdd.setParent(current);
                current.setRight(toAdd);

                // System.out.println("Added");
            } else {
                add(data, current.getRight());
            }
        }
    }

    public E get(E data){
        passes = 0;
        return get(data, root);
    }

    private E get(E data, Node<E> current){
        passes++;
        if(current.get().equals(data)){
            return current.get();
        }
        else if(current.get().compareTo(data) < 0){
            if(current.getRight() != null)
                return get(data, current.getRight());
            else
                return null;
        }
        else if(current.get().compareTo(data) > 0){
            if(current.getLeft() != null)
                return get(data, current.getLeft());

            else
                return null;
        }
        return null;
    }

    public int getPasses(){
        return passes;
    }

    public String toString(){
        return inOrderString(root);
    }

    public String inOrderString(Node<E> current){
        String returnString = "";
        if(current != null){
            returnString += inOrderString(current.getLeft());
            returnString += current.get() + ",";
            returnString += inOrderString(current.getRight());
        }
        return returnString;
    }

    public boolean contains(E data){
        return contains(data, root);
    }

    private boolean contains(E data, Node<E> current){
        if(data.equals(current.get())){
            return true;
        }
        else{
            if(data.compareTo(current.get()) > 0){
                if(current.getRight() == null){
                    return false;
                }
                current = current.getRight();
            }
            else if(data.compareTo(current.get()) < 0){
                if(current.getLeft() == null){
                    return false;
                }
                current = current.getLeft();
            }
            return contains(data, current);
        }
    }

    public void remove(E data){
        if(contains(data)){
            remove(data, root);
        }
    }

    private void remove(E data, Node<E> current){
        if(current.get().equals(data)){
             if(current.getLeft() == null && current.getRight() == null){//if has no children
                if(data.compareTo(current.getParent().get()) > 0){
                    current.getParent().setRight(null);
                }
                else if(data.compareTo(current.getParent().get()) < 0){
                    current.getParent().setLeft(null);
                }
            }
            else if(current.getRight() != null){//if has children
                E temp = getLowest(current.getRight());
                remove(temp);
                current.set(temp);
            }
        }
        else{
            //call remove passing in new current and parent
            if(data.compareTo(current.get()) > 0){
                current = current.getRight();
            }
            else if(data.compareTo(current.get()) < 0){
                current = current.getLeft();
            }
            if(current != null)
            remove(data, current);
   
            else
            return;
        }
    }

    private E getLowest(Node<E> current){
        Node<E> temp = current;
        if(current.getLeft() != null){
            current = current.getLeft();
            return getLowest(current);
        }else{
            return temp.get();
        }
    }

    public String toStringPreOrder(){
        return toStringPreOrder(root);
    }

    private String toStringPreOrder(Node<E> current){
        String returnString = "";
        returnString += current.get() + ",";
        if(current.getLeft() != null){
            returnString += toStringPreOrder(current.getLeft());
        }
        if(current.getRight() != null){
            returnString += toStringPreOrder(current.getRight());
        }
        return returnString;
    }

    public int getHeight(){
        return getHeight(root);
    }


    private int getHeight(Node<E> current){
        if(current == null){
            return -1;
        }
        else{
            int left = getHeight(current.getLeft());
            int right = getHeight(current.getRight());

            if(left > right){
                return left + 1;
            }
            else{
                return right + 1;
            }
        }
    }

    public int getHeight(E data){
        return getHeight(getNode(data, root));
    }

    public int getLevel(){
        return getHeight(root) + 1;
    }

    private Node<E> getNode(E data, Node<E> current){
        if(current.get().equals(data)){
            return current;
        }
        else{
            if(data.compareTo(current.get()) < 0){
                return getNode(data, current.getLeft());
            }
            else{
                return getNode(data,current.getRight());
            }
        }
    }

    public void drawMe(Graphics g, int x, int y){
        level = getLevel();
        drawMe(g, x, y, root, 0);
    }

    private void drawMe(Graphics g, int x, int y, Node<E> current, int depth){
        if(current != null){
            Font font = new Font("Cochin", Font.PLAIN, 16);
            g.setFont(font);
            g.drawString(current.get().toString(), x, y);
            if(current.getLeft() != null){
                g.drawLine(x + 2, y + 5, x - (20 * (level - depth)) + 2, y + 37);
                drawMe(g, x - (20 * (level - depth)), y + 50, current.getLeft(), depth + 1);
            }
            if(current.getRight() != null){
                g.drawLine(x + 2, y + 5, x + (20 * (level - depth)) + 2, y + 37);
                drawMe(g, x + (20 * (level - depth)), y + 50, current.getRight(), depth + 1);
            }
        }
        return;
    }

    public DLList<E> listInOrder(){
        return listInOrder(root, new DLList<E>());
    }
    private DLList<E> listInOrder(Node<E> current, DLList<E> list){
        if(current != null){
            listInOrder(current.getLeft(), list);
            list.add(current.get());
            listInOrder(current.getRight(), list);
        }
        return list;
    }

    public void balance(){
        DLList<E> storeList = listInOrder();
        root = null;
        balance(storeList, 0, storeList.size());
        
    }

    private void balance(DLList<E> temp, int start, int end){
        int mid = (start + end)/2;
        a
        dd(temp.get(mid));
        if(start!=mid){
            balance(temp, mid + 1, end);
        }
        if(mid!=end){
            balance(temp, start, mid);
        }
    }

}
   
  