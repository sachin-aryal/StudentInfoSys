package Service;

import Interface.IDataService;
import Model.Marks;
import Model.Student;
import ObjectFactory.ObjectFactory;
import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by saaryal on 12/3/15.
 */
public class DataService implements IDataService {

	public Map<Student, Marks> getData(Student student, Marks marks, String filePath[], String whichData,String parameter) {
		List<Student> stdList = new ArrayList<Student>();
		List<Marks> markList = new ArrayList<Marks>();

		Map<Student,Marks> allIfo = new HashMap<Student, Marks>();

		ObjectFactory obj = new ObjectFactory();

		DecimalFormat df = new DecimalFormat("#.00");
		CSVReader reader = null;
		CSVReader reader1 = null;
		try {
			reader = new CSVReader(new FileReader(filePath[0]));
			reader1 = new CSVReader(new FileReader(filePath[1]));

			while (true) {
				String[] partOne = reader.readNext();
				String[] partTwo = reader1.readNext();

				if (partOne == null || partTwo == null)
					break;

				student = obj.getStudentObject();
				marks = obj.getMarksObject();

				student.setRollNo(Integer.parseInt(partOne[0]));
				student.setBatch(Integer.parseInt(partOne[1]));
				student.setName(partOne[2] + " " + partOne[3]);
				student.setAddress(partOne[4]);

				marks.setSemester(partTwo[1]);
				marks.setPercentage(Double.parseDouble(df.format(Double.parseDouble(partTwo[2]))));
				marks.setStatus(Boolean.parseBoolean(partTwo[3]));

				if (whichData.equals("all")) {
					allIfo.put(student, marks);
				} else if (whichData.equals("lessPercentage")) {
					double percentage = Double.parseDouble(parameter);
					if (Double.parseDouble(partTwo[2]) < percentage) {
						allIfo.put(student, marks);
					}
				} else if (whichData.equals("greaterPercentage")) {
					double percentage = Double.parseDouble(parameter);
					if (Double.parseDouble(partTwo[2]) > percentage) {
						allIfo.put(student, marks);
					}
				}else if(whichData.equals("rollNo")){
					int roll = Integer.parseInt(parameter);
					if(student.getRollNo()==roll){
						allIfo.put(student, marks);
					}
				}
				else if(whichData.equals("name")){
					if(student.getName().equals(parameter)){
						allIfo.put(student, marks);
					}
				}
				else if(whichData.equals("batch")){
					int batch = Integer.parseInt(parameter);
					if(student.getBatch()==batch){
						allIfo.put(student, marks);
					}
				}
				else if(whichData.equals("semester")){
					if(marks.getSemester().equals(parameter)){
						allIfo.put(student, marks);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allIfo;
	}

	public void display(Student student,Marks marks,Map<Student,Marks> allInfo) {

		List<Student> stdList = new ArrayList<Student>(allInfo.keySet());
		List<Marks> markList = new ArrayList<Marks>(allInfo.values());

		System.out.println("Name\t\t\tRollNo\tBatch\tAddress\tSemester\tPercentage\tStatus");

		for(int i=0;i<markList.size();i++){
			student = stdList.get(i);
			marks = markList.get(i);

			String status ="Pass";
			if(!marks.isStatus()){
				status="Fail";
			}

			System.out.println(student.getName()+"\t"+student.getRollNo()+"\t\t"+student.getBatch()+"\t"+student.getAddress()+"\t"
					+marks.getSemester()+"\t"+marks.getPercentage()+"\t"+status);
		}

	}

	public void filter(Student student, Marks marks, String[] filePath, String whichFilter, String percentage) {

		Map<Student,Marks> studentMarksMap = getData(student,marks,filePath,whichFilter,percentage);
		display(student,marks,studentMarksMap);

	}

	public void search(Student student, Marks marks, String[] filePath, String searchBy, String searchByVal) {
		Map<Student,Marks> studentMarksMap = getData(student,marks,filePath,searchBy,searchByVal);
		display(student,marks,studentMarksMap);
	}

	public void sort(Student student, Marks marks, String[] filePath, String sortBy) {
		Map<Student,Marks> studentMarksMap = getData(student,marks,filePath,"all",null);

		List<Student> stdList = new ArrayList<Student>(studentMarksMap.keySet());
		List<Marks> markList = new ArrayList<Marks>(studentMarksMap.values());
		System.out.println("Name\t\t\tRollNo\tBatch\tAddress\tSemester\tPercentage\tStatus");

		if(sortBy.equals("name")){
			Map<Integer,String> previousIndex = new HashMap<Integer, String>();
			String addressList[]=new String[stdList.size()];
			int i=0;
			for(Student std:stdList){
				addressList[i] = std.getName();
				previousIndex.put(i,addressList[i]);
				i++;
			}
			String sortedAddress [] = sortMeth(addressList);

				for (String eName : sortedAddress) {
					for(int k=0;k<stdList.size();k++) {
						if (eName.equals(previousIndex.get(k))) {
							student = stdList.get(k);
							marks = markList.get(k);
							break;
						}
					}
				String status ="Pass";
				if(!marks.isStatus()){
					status="Fail";
				}

				System.out.println(student.getName()+"\t"+student.getRollNo()+"\t\t"+student.getBatch()+"\t"+student.getAddress()+"\t"
						+marks.getSemester()+"\t"+marks.getPercentage()+"\t"+status);
			}

		}else if(sortBy.equals("rollNo")){
			Map<Integer,Integer> previousIndex = new HashMap<Integer, Integer>();
			Integer[] rollList=new Integer[stdList.size()];
			int i=0;
			for(Student std:stdList){
				rollList[i] = std.getRollNo();
				previousIndex.put(i,rollList[i]);
				i++;
			}
			Integer sortedRoll [] = sortMeth(rollList);
			for (int eRoll : sortedRoll) {
				for(int k=0;k<stdList.size();k++) {
					if (eRoll == previousIndex.get(k)) {
						student = stdList.get(k);
						marks = markList.get(k);
						//previousIndex.remove(k);
						break;
					}
				}
				String status ="Pass";
				if(!marks.isStatus()){
					status="Fail";
				}

				System.out.println(student.getName()+"\t"+student.getRollNo()+"\t\t"+student.getBatch()+"\t"+student.getAddress()+"\t"
						+marks.getSemester()+"\t"+marks.getPercentage()+"\t"+status);
			}

		}else if(sortBy.equals("address")){
			Map<Integer,String> previousIndex = new HashMap<Integer, String>();
			String addressList[]=new String[stdList.size()];
			int i=0;
			for(Student std:stdList){
				addressList[i] = std.getAddress();
				previousIndex.put(i,addressList[i]);
				i++;
			}
			String sortedAddress [] = sortMeth(addressList);

			for (String eAddress : sortedAddress) {
//				System.out.println("EaAddress "+eAddress);
				for(int k=0;k<stdList.size();k++) {
					if (eAddress.equals(previousIndex.get(k))) {
//						System.out.println("Previous "+previousIndex.get(k));
						student = stdList.get(k);
						marks = markList.get(k);
						previousIndex.remove(k);
						stdList.remove(k);
						markList.remove(k);
						break;
					}
				}
				String status ="Pass";
				if(!marks.isStatus()){
					status="Fail";
				}

				System.out.println(student.getName()+"\t"+student.getRollNo()+"\t\t"+student.getBatch()+"\t"+student.getAddress()+"\t"
						+marks.getSemester()+"\t"+marks.getPercentage()+"\t"+status);
			}

		}/*else if(sortBy.equals("batch")){

		}else if(sortBy.equals("percentage")){

		}*/

	}



	public static <E extends Comparable<E>> E[] sortMeth(E x[]){
		for(int i=0;i<x.length-1;i++){
			for(int j=i+1;j<x.length;j++){
				if(x[i].compareTo(x[j])>0){
					E temp=x[i];
					x[i]=x[j];
					x[j]=temp;
				}
			}
		}
		return x;
	}
}
