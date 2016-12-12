package school.lemon.changerequest.java.junit.hw1.task1.model;

/**
 * Take look at <a href="https://en.wikipedia.org/wiki/MSISDN">MSISDN</a>
 */
public class CallNumber {
    private String SN;
    private String NDC;
    private String CC;

    public CallNumber() {
    }

    public CallNumber(String SN, String NDC, String CC) {
        this.SN = SN;
        this.NDC = NDC;
        this.CC = CC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CallNumber)) return false;

        CallNumber that = (CallNumber) o;

        if (SN != null ? !SN.equals(that.SN) : that.SN != null) return false;
        if (NDC != null ? !NDC.equals(that.NDC) : that.NDC != null) return false;
        return CC != null ? CC.equals(that.CC) : that.CC == null;
    }

    @Override
    public int hashCode() {
        int result = SN != null ? SN.hashCode() : 0;
        result = 31 * result + (NDC != null ? NDC.hashCode() : 0);
        result = 31 * result + (CC != null ? CC.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CallNumber{" +
                "SN='" + SN + '\'' +
                ", NDC='" + NDC + '\'' +
                ", CC='" + CC + '\'' +
                '}';
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getNDC() {
        return NDC;
    }

    public void setNDC(String NDC) {
        this.NDC = NDC;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }
}
