package DB;

public class Emp {

	String ename, desig, dept;
	int sal,eno=0;
	public Emp(String ename, String desig, String dept, int sal, int eno) {
		this.ename = ename;
		this.desig = desig;
		this.dept = dept;
		this.sal = sal;
		this.eno = eno;
		
	}
	@Override
	public String toString() {
		return "Employee [Employee name= " + ename + ", Designation= " + desig + ", Department= " + dept + ", salery= " + sal + ", employee no= " + eno + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eno;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emp other = (Emp) obj;
		if (eno != other.eno)
			return false;
		return true;
	}
	
	
}
