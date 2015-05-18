package analyzer.fileidentifier;

import analyzer.datastore.Data;
import java.io.File;

public interface ReaderLoader {
    Data loadData(File file);
}
