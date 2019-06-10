package com.poc.docker;

import com.poc.docker.entity.Car;

public class DockerProject {

	public static void main(String[] args) {
		Car myCar = new Car();

		myCar.setName("vw up");
		myCar.setBrand("vw");
		myCar.setValue(40000.00);

		while (true) {
			try {
				Thread.currentThread().sleep(2000);
				System.out.println(myCar);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
