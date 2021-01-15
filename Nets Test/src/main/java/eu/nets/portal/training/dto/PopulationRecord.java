package eu.nets.portal.training.dto;

public class PopulationRecord {

    private String regionName;
    private String quarter;
    private String changeCausesContent;
    private Integer changeCount;

    public PopulationRecord() {
    }

    public PopulationRecord(String regionName, String quarter, String changeCausesContent, Integer changeCount) {
        this.regionName = regionName;
        this.quarter = quarter;
        this.changeCausesContent = changeCausesContent;
        this.changeCount = changeCount;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getChangeCausesContent() {
        return changeCausesContent;
    }

    public void setChangeCausesContent(String changeCausesContent) {
        this.changeCausesContent = changeCausesContent;
    }
    public Integer getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(Integer changeCount) {
        this.changeCount = changeCount;
    }
}
