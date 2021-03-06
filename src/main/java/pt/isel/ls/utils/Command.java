package pt.isel.ls.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Command {
    private Method method;
    private Path path;
    private Header header;
    private Parameters parameters;

    private Command template;
    private Map<String, String> values;

    public Command() {
    }

    public Command(Method method, Path path) {
        this(method, path, new Header(), new Parameters());
    }

    public Command(Method method, Path path, Parameters parameters) {
        this(method, path, new Header(), parameters);
    }

    public Command(Method method, Path path, Header header, Parameters parameters) {
        this.method = method;
        this.path = path;
        this.header = header;
        this.parameters = parameters;
        values = new LinkedHashMap<>();

        /* set internal values from user parameters */
        extractValuesParameters();

        /* set internal values from user headers */
        extractValuesHeaders();
    }

    public Command(Method method, Path path, Header header) {
        this(method, path, header, new Parameters());
    }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters params) {
        parameters = params;

        updateValues();
    }

    public Command getTemplate() {
        return template;
    }

    public void setTemplate(Command template) {
        if (template != null) {
            values = new LinkedHashMap<>();
            extractValuesParameters();
            extractValuesHeaders();
        }

        this.template = template;
        extractValuesPath(template);
    }

    public String getValue(String key) {
        return this.values.get(key);
    }

    /* Add values to internal Map
     *
     * This must be use to store pairs (key, value) that are extracted
     * from both path and parameters, to use later on handlers
     */
    private void addKeyValue(String key, String value) {
        this.values.put(key, value);
    }

    /* Extract values from, preferably, a path used as a template */
    private void extractValuesPath(Command other) {
        Map<String, String> pathValues = this.path.setValues(other.getPath());
        pathValues.entrySet().forEach(entry -> {
            addKeyValue(entry.getKey(), entry.getValue());
        });
    }

    private void extractValuesParameters() {
        Map<String, String> parametersValues = this.parameters.getParameters();
        parametersValues.entrySet().forEach(entry -> {
            addKeyValue(entry.getKey(), entry.getValue());
        });
    }

    private void extractValuesHeaders() {
        Map<String, String> headerValues = this.header.getHeaders();
        headerValues.entrySet().forEach(entry -> {
            addKeyValue(entry.getKey(), entry.getValue());
        });
    }

    public void setHeader(Header header) {
        this.header = header;

        updateValues();
    }

    public Header getHeader() {
        return header;
    }

    private void updateValues() {
        /* because we are setting new parameters or headers, we need to re-create
         * our internal values mapping
         */
        values = new LinkedHashMap<>();
        if (template != null) {
            extractValuesPath(template);
        }
        if (header != null) {
            extractValuesHeaders();
        }

        if (parameters != null) {
            extractValuesParameters();
        }
    }

    public boolean matches(Command command) {
        if (!this.method.equals(command.getMethod())) {
            return false;
        }

        if (!this.path.matches(command.getPath())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(method.toString());
        str.append(" ");

        str.append(path.toString());

        if (!parameters.isEmpty()) {
            str.append(" ").append(parameters.toString());
        }

        if (!header.isEmpty()) {
            str.append(" ").append(header.toString());
        }

        return str.toString();
    }
}
