package br.com.alura.loja.modelo;

import com.thoughtworks.xstream.XStream;

public class Project {
	private String name;
	private long id;
	private int startYear;

	public Project() {
	}

	public Project(long id, String name, int startYear) {
		super();
		this.name = name;
		this.id = id;
		this.startYear = startYear;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStartYear() {
		return startYear;
	}

	public String toXML() {
		XStream stream = new XStream();
		return stream.toXML(this);
	}

}
