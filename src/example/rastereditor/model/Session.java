package example.rastereditor.model;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private int sessionId;
    private List<ImageFile> images = new ArrayList<>();

    public Session(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public List<ImageFile> getImages() {
        return images;
    }

    public void addImage(ImageFile img) {
        images.add(img);
    }

    public void listImages() {
        if (images.isEmpty()) {
            System.out.println("No images in this session.");
        } else {
            System.out.print("Images: ");
            for (int i=0; i<images.size(); i++) {
                ImageFile img = images.get(i);
                System.out.println(img.getFilename());
            }
            System.out.println();
        }
    }
}
