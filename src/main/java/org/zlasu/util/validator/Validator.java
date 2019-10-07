package org.zlasu.util.validator;

public class Validator {

    private StringBuffer errorMessage;

    public Validator() {
        errorMessage = new StringBuffer();
    }

    public boolean isEmpty(String string) {
        if (string == null || string.isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean isEmpty(String string, String key) {
        if (isEmpty(string)) {
            errorMessage.append(key).append(" - is MISSING | ");
            return true;
        }

        return false;
    }

    public boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public boolean isNotEmpty(String string, String key) {
        return !isEmpty(string, key);
    }

    public boolean isDouble(String string) {
        if (isEmpty(string)) {
            return false;
        }

        try {
            double d = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException | NullPointerException nfe) {
            System.out.println(nfe);
        }

        return false;
    }

    public boolean isDouble(String string, String key) {
        if (isDouble(string)) {
            return true;
        }

        errorMessage.append(key).append(" - is not DOUBLE | ");
        return false;
    }

    public boolean isNotDouble(String string) {
        return !isDouble(string);
    }

    public boolean isNotDouble(String string, String key) {
        if (isEmpty(string, key)) {
            return false;
        }

        return !isDouble(string, key);
    }

    public boolean isInt(String string) {
        if (isEmpty(string)) {
            return false;
        }

        try {
            double d = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException | NullPointerException nfe) {
            System.err.println(nfe);
        }

        return false;
    }

    public boolean isInt(String string, String key) {
        if (isInt(string)) {
            return true;
        }

        errorMessage.append(key).append(" - is not INT | ");
        return false;
    }

    public boolean isNotInt(String string) {
        return !isInt(string);
    }

    public boolean isNotInt(String string, String key) {
        if (isEmpty(string, key)) {
            return false;
        }

        return !isInt(string, key);
    }

    public boolean isNotPositiveId(String string, String key) {
        if (isEmpty(string, key)) {
            return false;
        }

        if (isNotInt(string, key)) {
            return false;
        }

        if (Integer.parseInt(string) < 1) {
            errorMessage.append(key).append(" - must be greater than zero | ");
            return true;
        }

        return false;
    }

    public String getErrorMessage() {
        return errorMessage.toString();
    }

}
