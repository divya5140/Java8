package com.alvas.assignment.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.alvas.assignment.Employee;
import com.alvas.assignment.TechnicalLead;





public class EmployeeManagement {
	public static List<Employee> readEmployee(String filename) throws IOException
	{
		List<Employee> emp=Files.lines(Paths.get(filename))
				.skip(1)

				.map(line->
				{
					String[] fields=line.split(",");
					return new Employee(Integer.parseInt(fields[0]),fields[1],Integer.parseInt(fields[2]),LocalDateTime.parse(fields[3]),Boolean.parseBoolean(fields[4]));
				}
						)

				.collect(Collectors.toList());


		return emp;
	}
	public static void fresher(List<Employee> emp)
	{
		emp.stream().sorted(Comparator.comparing(Employee::getJoindate).reversed()).limit(5).forEach(System.out::println);
	}
	public static void getEmployee(List<Employee> emp,LocalDateTime date)
	{
		emp.stream().filter(i->i.getJoindate().equals(date)).forEach(System.out::println);
	}
	private static void nextWorkingdays() {
		// TODO Auto-generated method stub
		LocalDateTime localDateTime=LocalDateTime.now();
		LocalDateTime nextMonday = localDateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		IntStream.range(0, 5).mapToObj(nextMonday::plusDays).collect(Collectors.toList()).forEach(System.out::println);
	}

	public static void isManager(List<Employee> emp)
	{
		System.out.println("Employee as manager"+
				emp.stream().filter(e->e.getIsmanager()==true).collect(Collectors.groupingBy(Employee::getJoindate)));

		System.out.println("Employee as Regular"+
				emp.stream().filter(e->e.getIsmanager()==false).collect(Collectors.groupingBy(Employee::getJoindate)));
	}
	public static void TechnicalLead(List<Employee> emp)
			throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

		List<Employee> leads = emp.stream()
				.filter(i -> Period.between(i.getJoindate().toLocalDate(), LocalDate.now()).getYears() >= 7)
				.collect(Collectors.toList());

		List<TechnicalLead> technicalLeads = leads.stream().map(employee ->

		{
			TechnicalLead technicalLead = new TechnicalLead(employee.getId(), employee.getName(), employee.getAge(),
					employee.getJoindate(), employee.getIsmanager());
			return technicalLead;
		}

				).collect(Collectors.toList());

		technicalLeads.forEach(System.out::println);

	}

	public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException  {
		String fileName = "C:\\Users\\divya\\Documents\\employee.txt";
		List<Employee> emp=readEmployee(fileName);
		System.out.println("\nTop 5 employes:-\n");
		fresher(emp);
		System.out.println("\nEmployee based on joining date:-\n");
		getEmployee(emp,LocalDateTime.parse("2017-09-30T12:28:29.455"));
		System.out.println("\nNext working days:-\n");
		nextWorkingdays();
		System.out.println("\nEmployee as Manager or Regular:-\n");
		isManager(emp);
		System.out.println("\nEmployee as Technical lead with experience more than 7 yrs:-\n");
		TechnicalLead(emp);
	}
}