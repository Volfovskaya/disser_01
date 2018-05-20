public class Employee {
    int employee_id;

    private String employee_name;
    String employee_birth;
    String employee_position;

    String employee_pc5;
    String employee_pc6;
    String employee_pc15;

    public int getEmployee_id() {
        return employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public String getEmployee_birth() {
        return employee_birth;
    }

    public String getEmployee_position() {
        return employee_position;
    }

    public String getEmployee_pc5() {
        return employee_pc5;
    }

    public String getEmployee_pc6() {
        return employee_pc6;
    }

    public String getEmployee_pc15() {
        return employee_pc15;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public void setEmployee_birth(String employee_birth) {
        this.employee_birth = employee_birth;
    }

    public void setEmployee_position(String employee_position) {
        this.employee_position = employee_position;
    }

    public void setEmployee_pc5(String employee_pc5) {
        this.employee_pc5 = employee_pc5;
    }

    public void setEmployee_pc6(String employee_pc6) {
        this.employee_pc6 = employee_pc6;
    }

    public void setEmployee_pc15(String employee_pc15) {
        this.employee_pc15 = employee_pc15;
    }

    public Employee(int employee_id, String employee_name, String employee_birth, String employee_position, String employee_pc5, String employee_pc6, String employee_pc15) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_birth = employee_birth;
        this.employee_position = employee_position;
        this.employee_pc5 = employee_pc5;
        this.employee_pc6 = employee_pc6;
        this.employee_pc15 = employee_pc15;
    }



}
