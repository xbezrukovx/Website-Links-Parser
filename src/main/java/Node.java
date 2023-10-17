import java.util.*;

public class Node{
    private String url;
    private HashSet<Node> children;

    public Node(String url) {
        this.url = url;
        children = new HashSet<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public HashSet<Node> getChildren(){
        return children;
    }
    public String getUrl() {
        return url;
    }
}
