package analyzer.fileidentifier;

import analyzer.datastore.Data;

public interface ReaderLoader {
    Data loadData(String fileName);
}
