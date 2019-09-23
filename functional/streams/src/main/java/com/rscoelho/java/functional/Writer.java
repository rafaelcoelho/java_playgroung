package com.rscoelho.java.functional;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	private final FileWriter writer;

	private Writer(final String fileName) throws IOException {
		this.writer = new FileWriter(fileName);
	}

	private void close() throws IOException {
		System.out.println("Writer.close()");

		writer.close();
	}

	public void writeStuff(final String text) throws IOException {
		writer.write(text);
	}

	public static void use(final String fileName, IMethodPattern<Writer, IOException> block) throws IOException {
		final Writer write = new Writer(fileName);

		try {
			block.accept(write);
		} finally {
			write.close();
		}
	}

}
