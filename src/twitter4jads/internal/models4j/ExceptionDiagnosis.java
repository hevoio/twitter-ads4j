package twitter4jads.internal.models4j;

/**
 *
 * @since Twitter4J 2.1.3
 */
final class ExceptionDiagnosis implements java.io.Serializable {
    int stackLineHash;
    int lineNumberHash;
    String hexString = "";
    Throwable th;
    private static final long serialVersionUID = 453958937114285988L;

    ExceptionDiagnosis(Throwable th, String[] inclusionFilter) {
        this.th = th;

        StackTraceElement[] stackTrace = th.getStackTrace();
        stackLineHash = 0;
        lineNumberHash = 0;
        for (int i = stackTrace.length - 1; i >= 0; i--) {
            StackTraceElement line = stackTrace[i];
            for (String filter : inclusionFilter) {
                if (line.getClassName().startsWith(filter)) {
                    int hash = line.getClassName().hashCode() + line.getMethodName().hashCode();
                    stackLineHash = 31 * stackLineHash + hash;
                    lineNumberHash = 31 * lineNumberHash + line.getLineNumber();
                    break;
                }
            }
        }
        hexString += toHexString(stackLineHash) + "-" + toHexString(lineNumberHash);
        if (th.getCause() != null) {
            this.hexString += " " + new ExceptionDiagnosis(th.getCause(), inclusionFilter).asHexString();
        }

    }

    String getStackLineHashAsHex() {
        return toHexString(stackLineHash);
    }

    String getLineNumberHashAsHex() {
        return toHexString(lineNumberHash);
    }

    String asHexString() {
        return hexString;
    }

    private String toHexString(int value) {
        String str = "0000000" + Integer.toHexString(value);
        return str.substring(str.length() - 8, str.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExceptionDiagnosis that = (ExceptionDiagnosis) o;

        if (lineNumberHash != that.lineNumberHash) return false;
        if (stackLineHash != that.stackLineHash) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stackLineHash;
        result = 31 * result + lineNumberHash;
        return result;
    }

    @Override
    public String toString() {
        return "ExceptionDiagnosis{" +
                "stackLineHash=" + stackLineHash +
                ", lineNumberHash=" + lineNumberHash +
                '}';
    }
}
