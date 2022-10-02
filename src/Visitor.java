interface Visitor {
  public void visit(EmployeeVisitor e, String indent);
  public void visit(ManagerVisitor m, String indent);
}
class PrettyPrintVisitor implements Visitor {
  public void visit(EmployeeVisitor e, String indent) {
    String jobTitle = getClass().getName();
    System.out.printf("%s" + "%s" + " (%s) " + "%s" + "\n", indent, e.getName(), e.getSalary(), jobTitle);
  }
  public void visit(ManagerVisitor m, String indent) {
    String jobTitle = getClass().getName();
    System.out.printf("%s" + "%s" + " (%s) " + "%s" + "\n", indent, m.getName(), m.getSalary(), jobTitle);
  }
}

class VisitorLaunch {
  public static void main(String[] args) {
    final String initialIndent = "";
    ManagerVisitor CEO = new ManagerVisitor("Alice", 1, new EmployeeVisitor[]
    { new ManagerVisitor("Erin", 200000, new EmployeeVisitor[]
      { new EmployeeVisitor("Frank", 30000) }),
      new EmployeeVisitor("Bob", 40000),
      new ManagerVisitor("Dan", 100000, null),
      new EmployeeVisitor("Carlos", 50000)
    });
    CEO.setBonus(1000000);
    // Should print the following (note indentation is three spaces!):
    // Alice (1000001.0) Manager
    //    Erin (200000.0) Manager
    //       Frank (30000.0) Employee
    //    Bob (40000.0) Employee
    //    Dan (100000.0) Manager
    //    Carlos (50000.0) Employee
      CEO.accept(new PrettyPrintVisitor(), initialIndent);
  }
}