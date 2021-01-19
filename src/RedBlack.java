import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RedBlack {
    public RedBlack() {
        TNULL = new Node();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    Node root=null;
    Node TNULL;
    String s="ab";

    public Node search(Node node,String key)
    {
        if(node==TNULL||node.data.equalsIgnoreCase(key)){
            return node;

        }
        else if(key.compareToIgnoreCase(node.data)>0)
            return search(node.right,key);
        else
            return search(node.left,key);
    }


    public void insert(String key)
    {
        Node newNode=new Node();
        newNode.data=key;
        newNode.left=TNULL;
        newNode.right=TNULL;
        newNode.parent=null;
        newNode.color=1;


        Node trackParent=null;
        Node trackRoot=this.root;

        while (trackRoot!=TNULL)
        {

            trackParent=trackRoot;
            if(key.compareToIgnoreCase(trackRoot.data)>0)
                trackRoot=trackRoot.right;

            else
                trackRoot=trackRoot.left;
        }

        newNode.parent=trackParent;

        if(trackParent==null)
            root=newNode;
        else if(newNode.data.compareToIgnoreCase(trackParent.data)>0)
            trackParent.right=newNode;
        else
            trackParent.left=newNode;


        if(newNode.parent==null)
        {
            newNode.color=0;
            return;
        }

        if(newNode.parent.parent==null)
            return;

        fixInsert(newNode);
    }


    public void fixInsert(Node c)
    {


        Node uncle;

        while(c.parent.color==1)
        {

            if(c.parent==c.parent.parent.right)
            {
                uncle=c.parent.parent.left;

                if(uncle.color==1)//uncle is left and red
                {
                    uncle.color=0;
                    c.parent.color=0;
                    c.parent.parent.color=1;
                    c=c.parent.parent;
                }

                else//uncle is left and black
                {

                    if(c==c.parent.left)
                    {
                        c=c.parent;
                        rightRotate(c);
                    }

                    c.parent.color=0;
                    c.parent.parent.color=1;
                    leftRotate(c.parent.parent);

                }
            }

            else//uncle is right
            {
                uncle=c.parent.parent.right;

                if(uncle.color==1)//uncle is right and red
                {
                    uncle.color=0;
                    c.parent.color=0;
                    c.parent.parent.color=1;
                    c=c.parent.parent;
                }

                else//uncle is right and black
                {
                    if(c==c.parent.right)
                    {
                        c=c.parent;
                        leftRotate(c);
                    }

                    c.parent.color=0;
                    c.parent.parent.color=1;
                    rightRotate(c.parent.parent);
                }
            }

            if(c==root)
                break;

        }


        root.color=0;
    }

    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }



    public int height(Node root)
    {
        if(root==TNULL)
            return -1;

        else
        {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            if (leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return (rightHeight + 1);
        }

    }

    public void heightHelper(){
        System.out.println("Height of the tree is : "+height(root));

    }

    public String userInput(){
        Scanner sc= new Scanner(System.in);
        String input=sc.nextLine();

        return input;
    }

    public boolean lookUp(Node c){
        if(c==null||c==TNULL)
        {
            return false;

        }
        else
        {
            return true;
        }
    }

    public int size(Node node) {
        if (node == null || node == TNULL)
        {
            return 0;

        }
        else
            return(size(node.left) + 1 + size(node.right));
    }

    public static void main(String [] args) {
        RedBlack bst = new RedBlack();


        ReadFile readFile = new ReadFile();
        ArrayList<String> dictionary = new ArrayList<>();
        try {
            dictionary = readFile.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < dictionary.size(); i++)
        {
            //System.out.println(dictionary.get(i));
            bst.insert(dictionary.get(i));
        }

        bst.heightHelper();
        System.out.println("Size of the tree is :" + bst.size(bst.root));

        System.out.println("Enter the input you want to look up for : ");
        String inputLookUp=bst.userInput();
        Node m=bst.search(bst.root,inputLookUp);

        if(bst.lookUp(m))
            System.out.println("YES");
        else
            System.out.println("NO");


        System.out.println("Enter the input you want to insert : ");
        String inputInsert=bst.userInput();
        Node n=bst.search(bst.root,inputInsert);
        if(bst.lookUp(n))
            System.out.println("ERROR: Word already in the dictionary!");
        else
            bst.insert(inputInsert);

        bst.heightHelper();

        System.out.println("Size of the tree is :" + bst.size(bst.root));


    }
}
