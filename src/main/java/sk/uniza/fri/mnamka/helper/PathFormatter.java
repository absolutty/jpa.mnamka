package sk.uniza.fri.mnamka.helper;

import java.util.Objects;

public final class PathFormatter {

    //path to be used to specific controller pages
    //for example: "error/%s"
    //it says that all error pages will be stored inside /error folder
    private final String path;

    public PathFormatter(String path) {
        this.path = Objects.requireNonNullElse(path, "");
    }

    public String getPageNameWithPath(String pageName) {
        return String.format(path, pageName);
    }

}
