import java.util.*;

public class FileNavigator {
    private Map<String, List<FileData>> files;

    public FileNavigator() {
        this.files = new HashMap<>();
    }

    public void add(FileData fileData) {
        if (!fileData.getPath().equals(getPathKey(fileData.getPath()))) {
            System.out.println("Error: Path-key and file path do not match");
            return;
        }

        if (!files.containsKey(fileData.getPath())) {
            files.put(fileData.getPath(), new ArrayList<>(Collections.singletonList(fileData)));
        } else {
            List<FileData> fileList = files.get(fileData.getPath());
            fileList.add(fileData);
            files.put(fileData.getPath(), fileList);
        }
    }

    public List<FileData> find(String path) {
        if (files.containsKey(path)) {
            return files.get(path);
        }
        return null;
    }

    public List<FileData> filterBySize(int size) {
        List<FileData> filteredList = new ArrayList<>();
        for (List<FileData> fileList : files.values()) {
            for (FileData fileData : fileList) {
                if (fileData.getSize() <= size) {
                    filteredList.add(fileData);
                }
            }
        }
        return filteredList;
    }

    public void remove(String path) {
        files.remove(path);
    }

    public List<FileData> sortBySize() {
        List<FileData> sortedList = new ArrayList<>();
        for (List<FileData> fileList : files.values()) {
            sortedList.addAll(fileList);
        }
        sortedList.sort(Comparator.comparingInt(FileData::getSize));
        return sortedList;
    }

    private String getPathKey(String path) {
        String[] pathSplit = path.split("/");
        return pathSplit[pathSplit.length - 1];
    }
}
