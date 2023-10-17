import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        Node root = new Node("https://skillbox.ru/");
        new ForkJoinPool().invoke(new ListOfLinks(root));

        try {
            FileOutputStream stream = new FileOutputStream("SiteMap.txt");
            String siteMapFile = createSiteMap(root, 0);
            stream.write(siteMapFile.getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createSiteMap(Node node, int level) {
        String tab = String.join("", Collections.nCopies(level, "\t"));
        StringBuilder result = new StringBuilder(tab + node.getUrl());
        node.getChildren().forEach(child -> result.append("\n").append(createSiteMap(child, level + 1)));
        return result.toString();
    }
}