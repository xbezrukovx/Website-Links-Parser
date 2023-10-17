import java.util.*;
import java.util.concurrent.RecursiveAction;

public class ListOfLinks extends RecursiveAction {
    private Node node;
    private static Vector<String> linksPool = new Vector<>();
    public ListOfLinks (Node node){
        this.node = node;
    }

    @Override
    protected void compute() {
        Vector<String> links = HtmlParser.getLinks(node.getUrl());
        linksPool.add(node.getUrl());
        for (String link : links) {
            if (linksPool.contains(link)) {
                continue;
            }
            linksPool.add(link);
            node.addChild(new Node(link));
        }


        List<ListOfLinks> taskList = new ArrayList<>();
        for (Node child : node.getChildren()) {
            ListOfLinks task = new ListOfLinks(child);
            task.fork();
            taskList.add(task);
        }
        for (ListOfLinks task : taskList) {
            task.join();
        }
    }
}
