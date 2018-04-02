package SpringWebFluxExample.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student
{

    private int studentId;
    private String firstName;
    private String lastName;
    private String department;

    public Student(@JsonProperty("id") int studentId, @JsonProperty("firstName") String firstName,@JsonProperty("lastName") String lastName, @JsonProperty("department")String department)
    {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    @Override
    public String toString(){
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                '}';
    }
}
