package commands;

import com.beust.jcommander.Parameter;

public class ProcessCommands {
    @Parameter(names = "--limit", required = true)
    int limit;

    public ProcessCommands() {
    }

    public ProcessCommands(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
