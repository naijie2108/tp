package semester;

import module.ModuleList;
import module.Module;

public class Semester {
    protected int credits;
    protected double points;
    protected double cap;
    protected String semester;
    public ModuleList moduleList;

    public Semester(String semester) {
        this.semester = semester;
        this.credits = 0;
        this.points = 0;
        this.cap = 0;
        this.moduleList = new ModuleList();
    }

    /**
     * For deserialization from JSON file.
     */
    public Semester() {
    }

    private void updateCredits() {
        int c = 0;
        for (Module module : moduleList.getModuleList()) {
            c += module.getCredits();
        }
        credits = c;
    }

    private void updatePoints() {
        double p = 0;
        for (Module module : moduleList.getModuleList()) {
            p += module.getCredits() * module.getPoints();
        }
        points = p;
    }

    private void updateCap() {
        updateCredits();
        updatePoints();
        cap = points / credits;
    }

    public void updateSemester() {
        updateCap();
    }

    public int getCredits() {
        updateCredits();
        return credits;
    }

    public double getPoints() {
        updatePoints();
        return points;
    }

    public double getCap() {
        updateCap();
        return cap;
    }

    public String getSem() {
        return this.semester;
    }
}
