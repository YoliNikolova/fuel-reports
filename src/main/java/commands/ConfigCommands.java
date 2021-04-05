package commands;

import com.beust.jcommander.Parameter;

public class ConfigCommands {
    @Parameter(names = "--data-dir")
    String localDir;

    public ConfigCommands(String localDir) {
        this.localDir = localDir;
    }

    public ConfigCommands() {

    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }
}
