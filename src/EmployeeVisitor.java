import java.util.*;
class EmployeeVisitor {
    public EmployeeVisitor(String n, double s) {
        name = n;
        salary = s;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public final boolean equals(Object other)  {
        if (this == other) return true;
        if (other == null) return false;
        if (!(other instanceof EmployeeVisitor)) return false;
        EmployeeVisitor e = (EmployeeVisitor) other;
        return name.equals(e.name);
    }

    public final int hashCode() {
        return name.hashCode();
    }

    void accept(Visitor v, String indent) {
        v.visit(this, indent);
    }

    private String name;
    private double salary;
}

class ManagerVisitor extends EmployeeVisitor {
    public ManagerVisitor(String n, double s, EmployeeVisitor[] es) {
        super(n, s);
        bonus = 0;
        if (es != null) {
            for (EmployeeVisitor e : es) {
                add(e);
            }
        }
    }
    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }
    public double getBonus() {
        return bonus;
    }

    public void setBonus(double b) {
        bonus = b;
    }

    public void add(EmployeeVisitor sub) {
        if (!subs.contains(sub)) {
            subs.add(sub);
        }
    }

    public void remove(EmployeeVisitor sub) {
        subs.remove(sub);
    }

    void accept(Visitor v, String indent) {
        v.visit(this, indent);
        for (EmployeeVisitor e : subs) {
            e.accept(v, indent + "   ");
        }
    }

    private double bonus;
    private List<EmployeeVisitor> subs = new ArrayList<EmployeeVisitor>();
}

